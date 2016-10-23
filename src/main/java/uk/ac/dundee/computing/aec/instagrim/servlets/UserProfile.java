/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.PicModel;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;

/**
 *
 * @author allan
 */
@WebServlet(name = "userProfile", urlPatterns = {"/UserProfile"})
@MultipartConfig
public class UserProfile extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
    */
    
    private Cluster cluster;
    
    public void init(ServletConfig config) throws ServletException{
        cluster = CassandraHosts.getCluster();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      //  LoggedIn lg = (LoggedIn) request.getSession().getAttribute("LoggedIn");
      //  User us = (User) request.getSession().getAttribute(name);
       // if(lg.getProfPicID() != )
        
        /*    
        java.util.UUID profilePicID = us.getProfilePicID(username);
        if(profilePicID != null)
        {   
            lg.setProfPicID(profilePicID);
        }

        session.setAttribute("LoggedIn", lg);
        System.out.println("Session in servlet "+session);

        PicModel tm = new PicModel();
        tm.setCluster(cluster);

        if(profilePicID != null)
        {
            Pic profilePic = tm.getPic(0,profilePicID);
            profilePic.setUUID(profilePicID);
            System.out.println("profilePic UUID at login = " + profilePic.getUUID());
            session.setAttribute("ProfilePic", profilePic);
        }          
            response.sendRedirect("/Instagrim/UserProfile");*/
        processRequest(request, response);
        }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd=request.getRequestDispatcher("/userProfile.jsp");
	rd.forward(request,response);  
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
