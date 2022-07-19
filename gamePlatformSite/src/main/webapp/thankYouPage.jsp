<%@ page language="java" import="javax.sql.DataSource, it.unisa.gp.model.DAO.AcquistiDS, it.unisa.gp.model.interfaceDS.Acquisti,
	java.util.*, it.unisa.gp.model.bean.AcquistiBean" contentType="text/html; charset=utf-8" 
    pageEncoding="utf-8"%>
  
<%
	int id = (int) session.getAttribute("idAcquisto");
	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	Acquisti acqDS = new AcquistiDS(ds);
	AcquistiBean acqBean = acqDS.doRetrieveByKey(id);
%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Acquisto Effettuato</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="style/style.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="32x32" href="img/icon/favicon.png">
</head>
<body>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>


	<%@ include file="../fragments/header.jsp" %>
 	<div class="container page">
 		<h2>Grazie per aver effettuato l'acquisto.</h2>
 		<br/>
 		<p>Id acquisto: <%= id %> </p>
 		<p>Codice Riscatto: <%=acqBean.getCodiceRiscatto() %> </p>
 		<p><a href="ordini.jsp">Visualizza tutti gli ordini</a> oppure <a href="index.jsp">torna alla home page</a></p>
 	</div>
 	<%@ include file="../fragments/footerReg.jsp" %>
</body>
</html>