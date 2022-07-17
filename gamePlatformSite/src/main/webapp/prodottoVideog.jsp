<%@ page language="java" import="javax.sql.DataSource, it.unisa.gp.model.bean.VideogiocoBean, it.unisa.gp.model.DAO.VideogiocoDS,
	 it.unisa.gp.model.interfaceDS.Videogioco, it.unisa.gp.model.interfaceDS.Recensione, it.unisa.gp.model.DAO.RecensioneDS,
	 it.unisa.gp.model.bean.RecensioneBean, it.unisa.gp.model.interfaceDS.Clienti, it.unisa.gp.model.DAO.ClientiDS, it.unisa.gp.model.bean.ClientiBean,
	java.time.format.DateTimeFormatter,	java.time.LocalDateTime, java.util.*"
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
<!DOCTYPE html>

<%	
	String id = (String) request.getParameter("id");
	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	Videogioco vidDS = new VideogiocoDS(ds);
	VideogiocoBean vidBean = vidDS.doRetrieveByKey(id);
	
	Recensione recensioneDS = new RecensioneDS(ds);
	Collection<RecensioneBean> colRec = recensioneDS.doRetrieveAllVideog(id,null);
	
	Clienti clDS = new ClientiDS(ds);
%>


<html>
<head>
<meta charset="ISO-8859-1">
<title>Videogioco</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="32x32" href="img/icon/favicon.png">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700|Open+Sans:400,700">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link href="style/style.css" rel="stylesheet">

<script src="script/jquery-3.6.0.min.js"></script>
</head>
<body>
	<%@ include file="../fragments/header.jsp" %>
	<div class="container">
		 <div class=”row”>
   			<div class=”col-md-6”>
			<img src="ImageServlet?immagine=<%=vidBean.getCodice()%>_1.jpg"
				id="main_product_image" class="image-responsive">
				
			</div>
		</div> <!-- end row -->
			<div class="col-md-6">
				<div class=”row”>
					<div class="col-md-12">
   						<h1> …[this is the product information]</h1>
   					</div>
   				</div>
   			</div>
	
	
	
	
	
	
	
	
	
	
	
	</div> <!-- end container -->

</body>
</html>