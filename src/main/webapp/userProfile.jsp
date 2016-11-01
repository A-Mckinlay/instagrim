<%-- 
    Document   : userProfile
    Created on : 18-Oct-2016, 21:56:40
    Author     : allan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
    </head>
    <body>
        <%LoggedIn lg = (LoggedIn) request.getSession().getAttribute("LoggedIn");%>
        <h1>Instagrim</h1>
        <h2>Your world in Black and White</h2>
        <h3>Welcome back <%=lg.getUsername()%></h3>
        <nav>
            <ul>
                <li class="nav"><a href="/Instagrim/Upload">Upload</a></li>
                <li class="nav"><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                <li class="nav"><form method="POST"  action="Logout"><input type="submit" s value="Logout"></form></li>
            </ul>
        </nav>
                
        <article>
            <h1>Your Profile</h1>
            <table>
                <tr>
                    <td>First Name</td>
                    <td><%=lg.getFirstName()%></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><%=lg.getLastName()%></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><%=lg.getEmail()%></td>
                </tr>
            </table>
                
            <h2>Profile Picture</h2>
            <%
            Pic profPic = (Pic) request.getSession().getAttribute("ProfilePic");
            if(profPic == null){
            %>
                <p>No Profile Picture Found</p>
            <%
            }
            else
            {
            %>
                <a href="/Instagrim/Image/<%=profPic.getSUUID()%>" ><img src="/Instagrim/Thumb/<%=profPic.getSUUID()%>"></a><br/>
            <%
            }
            %>

            <table>
                <tr>
                    <td></td>
                </tr>
            </table>
            <h3>Upload New Profile Picture</h3>
            <form method="POST" enctype="multipart/form-data" action="ProfilePic">
                File to upload: <input type="file" name="upfile"><br/>

                <br/>
                <input type="submit" value="Press"> to upload the file!
            </form>
        </article>
        <footer>
            <ul>
                <li class="footer"><a href="/Instagrim">Home</a></li>
            </ul>
        </footer>
    </body>
</html>
