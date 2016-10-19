<%-- 
    Document   : userProfile
    Created on : 18-Oct-2016, 21:56:40
    Author     : allan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
    </head>
    <body>
        <h1>Instagrim</h1>
        <h2>Your world in Black and White</h2>#
        <h3>Welcome back</h3>
        <nav>
            <ul>
                <li class="nav"><a href="/Instagrim/Upload">Upload</a></li>
                <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                <li><form method="POST"  action="Logout"><input type="submit" value="Logout"></form></li>
            </ul>
        </nav>
                
        <article>
            <h1>Your Profile</h1>
            
        </article>
    </body>
</html>
