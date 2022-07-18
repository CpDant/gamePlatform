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
<link rel="icon" type="image/png" sizes="32x32" href="img/icon/favicon.png">
<title>Protected Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="style/style.css" rel="stylesheet">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
	<%@ include file="../fragments/header.jsp"%>
	<div class="container">
		<h1>Welcome to the Protected Page</h1>
		Congratulations. You have accessed a protected document.
		<br>
		<br>
	
		<%
		if (roles.equals("cliente")) {
		%>
		<%=clBean.toString()%>
		<%
		} else if (roles.equals("supVid")) {
		%>
		<%=supBean.toString()%>
		<%
		} else if (roles.equals("assCl")) {
		%>
		<%=assBean.toString()%>
		<%
		} else if (roles.equals("admin")) {
		%>
		<%=admBean.toString()%>
		<%
		}
		%>
	
		<form action="LogoutServlet" method="get">
			<input type="submit" value="Logout" />
		</form>
	</div>
	<%@ include file="../fragments/footerReg.jsp" %>
</body>
</html>