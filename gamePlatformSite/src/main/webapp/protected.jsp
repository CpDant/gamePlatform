<%
// Check user credentials
String roles = (String) session.getAttribute("roles");
 
if (roles == null)
{	
    response.sendRedirect("./login-form.jsp");
    return;
}
%>


<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Protected Page</title>
</head>
<body>
<h1>Welcome to the Protected Page</h1>
Congratulations. You have accessed a protected document.
<br><br>

<%= roles %>

<form action="Logout" method="get" > 
     <input type="submit" value="Logout"/>
</form> 
</body>
</html>

