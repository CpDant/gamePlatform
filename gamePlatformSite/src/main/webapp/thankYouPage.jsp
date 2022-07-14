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
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="style/style.css" rel="stylesheet">
</head>
<body>
 	<div class="container">
 		<h2>Grazie per aver effettuato l'acquisto</h2>
 		<p>id acquisto: <%= id %> </p>
 		<p>codice riscatto: <%=acqBean.getCodiceRiscatto() %> </p>
 	</div>
</body>
</html>