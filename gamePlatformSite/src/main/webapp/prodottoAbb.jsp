<%@ page language="java" import="javax.sql.DataSource, it.unisa.gp.model.bean.VideogiocoBean, it.unisa.gp.model.DAO.VideogiocoDS,
	 it.unisa.gp.model.interfaceDS.Videogioco, it.unisa.gp.model.interfaceDS.Recensione, it.unisa.gp.model.DAO.RecensioneDS,
	 it.unisa.gp.model.bean.RecensioneBean, it.unisa.gp.model.interfaceDS.Clienti, it.unisa.gp.model.DAO.ClientiDS, it.unisa.gp.model.bean.ClientiBean,
	java.time.format.DateTimeFormatter,	java.time.LocalDateTime, java.util.*, it.unisa.gp.model.bean.AbbonamentoBean, it.unisa.gp.model.DAO.AbbonamentoDS,
	 it.unisa.gp.model.interfaceDS.Abbonamento, it.unisa.gp.model.bean.AddInAbbBean, it.unisa.gp.model.DAO.AddInAbbDS,
	 it.unisa.gp.model.interfaceDS.AddInAbb"
	contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html>

<%	
	String roles = (String) session.getAttribute("roles");
	if(roles == null){
		response.sendRedirect(request.getContextPath() + "/login-form.jsp");
	} else if (roles.equals("admin") || roles.equals("assCl")) {
		response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
	}
	
	String id = (String) request.getParameter("id");
	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	AbbonamentoDS abbDS = new AbbonamentoDS(ds);
	Videogioco vidDS = new VideogiocoDS(ds);
	AddInAbb addInAbbDS = new AddInAbbDS(ds);
	
	AbbonamentoBean abbBean = abbDS.doRetrieveByKey(id);
	Collection<AddInAbbBean> colInAbb = addInAbbDS.doRetrieveAllAbbonamento(id);
	
	
	
	Clienti clDS = new ClientiDS(ds);
%>


<html>
<head>
<meta charset="utf-8">
<title>Abbonamento</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="32x32" href="img/icon/favicon.png">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700|Open+Sans:400,700">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link href="style/style.css" rel="stylesheet">


<script src="script/jquery-3.6.0.min.js"></script>
</head>
<body>
	<%@ include file="../fragments/header.jsp" %>
	<div class="container mt-5 mb-5" id="product-section">
		<div class="row">
			<div class="col-md-6 border-end">
				<img class="image-responsive" width="80%" src="ImageServlet?immagine=<%=abbBean.getNomeUnivoco()%>.jpg" alt="image">
			</div>
			<div class="col-md-6">
				<h3><%=abbBean.getNomeUnivoco()%></h3>
				<h5>Prezzo: &euro;<%= abbBean.getCosto() %></h5>
				<h5>Durata: <%= abbBean.getDurata() %> mesi</h5>
               <a href="AddToCartServlet?id=<%=abbBean.getNomeUnivoco()%>" class="btn border-dark"> 
               		<img src="img\icon\shopping-cart.svg" alt="add-to-cart" class="icona"> Aggiungi al carrello
			   </a>
			</div>
		</div><!-- end row -->
		<br/>
		<div class="row">
			<div class="col-md-12 d-flex justify-content-center">
			
				<section>
					<div class="row d-flex justify-content-center py-3">
						<div class="col-md-10 col-xl-8 text-center">
							<h3 class="mb-4">Videogiochi contenuti nell'abbonamento</h3>
						</div>
					</div>
					<%
                   		for(AddInAbbBean addInAbbBean : colInAbb){
                   			VideogiocoBean vidBean = vidDS.doRetrieveByKey(addInAbbBean.getCodiceVideogioco());
                   		
                   
                   	 %>
					<div class="row text-center">
					 
						<div class="col-md-12 mb-md-5">
							<img class ="image-responsive" width ="30%" src="ImageServlet?immagine=<%=vidBean.getCodice()%>_1.jpg">
							<h5 class="mb-3"><%=vidBean.getNomeVideogioco() %></h5>
							
						</div>
							
					</div>
						
						<% 
						}
						 %>
						
				</section>
			</div>
		</div><!-- end row -->
		
		
	</div><!-- end container -->
	
	<%@ include file="../fragments/footerReg.jsp" %>
</body>
</html>