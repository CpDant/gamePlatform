<%@ page language="java" import="it.unisa.gp.model.bean.AdministratorsBean, 
it.unisa.gp.model.bean.AssistenteClientiBean, it.unisa.gp.model.bean.ClientiBean, 
it.unisa.gp.model.bean.SupervisoreVideogiochiBean, java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
// Check user credentials
String roles = (String) session.getAttribute("roles");

ClientiBean clBean = null;
SupervisoreVideogiochiBean supBean = null;
AssistenteClientiBean assBean = null;
AdministratorsBean admBean = null;

if (roles == null) {	
    response.sendRedirect("./login-form.jsp");
    return;
} else if(roles.equals("cliente")) {
	clBean = (ClientiBean) session.getAttribute("utente");
} else if(roles.equals("supVid")) {
	supBean = (SupervisoreVideogiochiBean) session.getAttribute("utente");
} else if(roles.equals("assCl")) {
	assBean = (AssistenteClientiBean) session.getAttribute("utente");
} else if(roles.equals("admin")) {
	admBean = (AdministratorsBean) session.getAttribute("utente");
}
%>



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

<%
if(roles.equals("cliente")) {
%>
	<%= clBean.toString() %>
<%
} else if(roles.equals("supVid")) {
%>
	<%= supBean.toString() %>
<%
} else if(roles.equals("assCl")) {
%>
	<%= assBean.toString() %>
<%
} else if(roles.equals("admin")) {
%>
	<%= admBean.toString() %>
<%	
}
%>

<form action="LogoutServlet" method="get" > 
     <input type="submit" value="Logout"/>
</form> 
</body>
</html>