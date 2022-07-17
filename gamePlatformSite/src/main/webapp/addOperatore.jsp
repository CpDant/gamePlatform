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
%>
<html>
<head>
<meta charset="UTF-8">
<title>Aggiungi operatore</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="style/style.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="32x32" href="img/icon/favicon.png">
</head>
<body>
<script>
function genPassw(){
	var chars = "0123456789abcdefghijklmnopqrstuvwxyz!@#$%^&*()ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	var passwordLength = 12;
	var password = "";
	for (var i = 0; i <= passwordLength; i++) {
		   var randomNumber = Math.floor(Math.random() * chars.length);
		   password += chars.substring(randomNumber, randomNumber +1);
		  }
	document.getElementById("password").value = password;
}
</script>
<%@ include file="../fragments/header.jsp" %> 
<div class="container">
	<h2>Aggiungi operatore</h2>
	<form action="AddOperatorServlet" method="post">
		<div class="row mt-3">
			<div class="col-md-6 mb-3">
				<label for="codFis">Codice Fiscale*</label>
				<input type="text" class="form-control" id="codFis" name="codFis" required>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6 mb-3">
				<label for="nome">Nome*</label>
				<input type="text" class="form-control" id="nome" name="nome" required>
			</div>
			<div class="col-md-6 mb-3">
				<label for="cognome">Cognome*</label>
				<input type="text" class="form-control" id="cognome" name="cognome" required>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6 mb-3">
				<label for="data">Data di nascita*</label>
				<input type="date" class="form-control" id="data" name="data" required>
			</div>
			<div class="col-md-6 mb-3">
				<label for="email">Email*</label>
				<input type="email" class="form-control" id="email" name="email" required>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6 mb-3">
				<label for="password">Password*</label>
				<input type="password" class="form-control" id="password" name="password" required>
			</div>
			<div class="col-md-6 mb-3">
				<br/>
				<button type="button" onclick="genPassw()" class="btn btn-primary btn-block">Genera password</button>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6 mb-3">
				<label for="retrAnn">Retribuzione annuale*</label>
				<input type="text" class="form-control" id="retrAnn" name="retrAnn" required>
			</div>
			<div class="col-md-6 mb-3">
				<label for="ruolo">Ruolo*</label>
				<br/>
				<select name="ruolo" id="ruolo" class="form-control">
					<option value="supVid">Supervisore videogioco</option>
					<option value="assCl">Assistente cliente</option>
				</select>
			</div>
		</div>
		
		
		<input id = "submit" type = "submit" value = "Aggiungi" class="btn btn-primary btn-block mt-3">
	</form>
</div>
<%@ include file="../fragments/footerReg.jsp" %> 
</body>
</html>