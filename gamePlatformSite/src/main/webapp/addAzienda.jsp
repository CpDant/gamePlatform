<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Attenzione</title>
<link rel="icon" type="image/png" sizes="32x32" href="img/icon/favicon.png">
<link href="style/style.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<script src="script/jquery-3.6.0.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<div>
	<%@ include file="../fragments/header.jsp"%>
	
	<div class="container page">
		<h2>Attenzione</h2>
		<br/>
		<p>Per generare la fattura bisogna aggiungere i dati aziendali, aggiungili <a href="utentePage.jsp">cliccando qui</a></p>
	
	
	</div>
	
	<%@ include file="../fragments/footerReg.jsp"%>
</div>
</body>
</html>