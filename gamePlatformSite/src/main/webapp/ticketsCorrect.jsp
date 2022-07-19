<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Ticket aperto</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="32x32" href="img/icon/favicon.png">
<link href="style/style.css" rel="stylesheet">
</head>
<body>
	<script src="script/jquery-3.6.0.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
	<%@ include file="../fragments/header.jsp" %>
		<div class="container page">
			<h2>Ticket aperto con successo!</h2>
			<br/>
			<p>Il ticket è stato aperto con successo, un assistente clienti ti contatter&agrave; via mail.</p>
			<p>Torna alla <a href="index.jsp">Home Page</a></p>
		</div>
	
	
	<%@ include file="../fragments/footerReg.jsp" %>
</body>
</html>