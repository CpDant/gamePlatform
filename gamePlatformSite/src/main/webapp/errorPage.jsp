<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Errore</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="style/style.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="32x32" href="img/icon/favicon.png">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="../fragments/header.jsp" %>
<div class="container">
<div class="alert alert-danger alert-dismissible fade show">
    <h4 class="alert-heading"><i class="bi-exclamation-octagon-fill"></i> Oops! Qualcosa è andato storto.</h4>
    <p>Non hai i permessi necessari per entrare in questa pagina torna alla pagina di login e entra con le credenziali corrette per poter avere accesso a questa pagina.</p>
</div>
</div>
<%@ include file="../fragments/footerReg.jsp" %>
</body>
</html>