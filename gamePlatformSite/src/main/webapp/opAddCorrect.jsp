<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String roles = (String) session.getAttribute("roles");
	
	if(roles == null){
		response.sendRedirect(request.getContextPath() + "/login-form.jsp");
	} else if (roles.equals("supVid") || roles.equals("assCl") || roles.equals("cliente")) {
		response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
	}
	
	String codFisOpAgg = (String) session.getAttribute("codFisOpAgg");
	String ruoloOpAgg = (String) session.getAttribute("ruoloOpAgg");
	
%>
<html>
<head>
<meta charset="UTF-8">
<title>Operatore Aggiunto</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="style/style.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="32x32" href="img/icon/favicon.png">
</head>
<body>
	<%@ include file="../fragments/header.jsp" %> 
	<div class="container">
		<h2>Operatore aggiunto correttamente!</h2>
		<div class="row mt-3">
			<div>
				<p>L'operatore:</p>
				<p>Codice Fiscale: <%=codFisOpAgg%></p>
				<p>ruolo: <%=ruoloOpAgg%></p>
				<p>&egrave; stato aggiunto correttamente, <a href="addOperatore.jsp">clicca qui</a> per aggiungere altri operatori. </p>
			</div>
		</div>
	</div>
	<%@ include file="../fragments/footerReg.jsp" %> 
</body>
</html>