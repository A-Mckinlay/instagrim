/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.PicModel;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    Cluster cluster=null;


    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        
        User us=new User();
        us.setCluster(cluster);
        boolean isValid=us.IsValidUser(username, password);
        HttpSession session=request.getSession();
        System.out.println("Session in servlet "+session);
        if (isValid){
            LoggedIn lg= new LoggedIn();
            lg.setLoggedin();
            lg.setUsername(username);
            lg.setFirstName(us.getFirstName(username));
            lg.setLastName(us.getLastName(username));
            lg.setEmail(us.getEmail(username));
            lg.setProfPicID(us.getProfilePicID(username));
            
            session.setAttribute("LoggedIn", lg);
            System.out.println("Session in servlet "+session);
            
            PicModel tm = new PicModel();
            tm.setCluster(cluster);
            System.out.println("Profile Pic ID IS:" + us.getProfilePicID(username));
            Pic profilePic = tm.getPic(0,us.getProfilePicID(username));
            if(profilePic == null)
            {
                System.out.println("PROFILE PIC IS NULL");
            }

            session.setAttribute("ProfilePic", profilePic);
            
            
            response.sendRedirect("/Instagrim/UserProfile");
//            RequestDispatcher rd=request.getRequestDispatcher("/UserProfile");
//            rd.forward(request,response);

            
        }else{
            response.sendRedirect("/Instagrim/Login");
        }
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
         RequestDispatcher rd=request.getRequestDispatcher("/login.jsp");
	 rd.forward(request,response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
