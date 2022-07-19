<%@ page language="java" import="javax.sql.DataSource, it.unisa.gp.model.bean.VideogiocoBean, it.unisa.gp.model.DAO.VideogiocoDS,
	 it.unisa.gp.model.interfaceDS.Videogioco, it.unisa.gp.model.interfaceDS.Recensione, it.unisa.gp.model.DAO.RecensioneDS,
	 it.unisa.gp.model.bean.RecensioneBean, it.unisa.gp.model.interfaceDS.Clienti, it.unisa.gp.model.DAO.ClientiDS, it.unisa.gp.model.bean.ClientiBean,
	java.time.format.DateTimeFormatter,	java.time.LocalDateTime, java.util.*"
	contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>

<%	
	String roles = (String) session.getAttribute("roles");
	
	if(roles == null){
		
	} else if (roles.equals("admin") || roles.equals("assCl")) {
		response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
	}
	
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
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><%=vidBean.getNomeVideogioco()%></title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="32x32" href="img/icon/favicon.png">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700|Open+Sans:400,700">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link href="style/style.css" rel="stylesheet">


<script src="script/jquery-3.6.0.min.js"></script>
</head>
<body>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
	<%@ include file="../fragments/header.jsp" %>
	<div class="container">
		<h2 class="text-center"><%=vidBean.getNomeVideogioco()%></h2>
	</div>
	<div class="container mt-5 mb-5" id="product-section">
		<div class="row text-center">
			<div class="col-md-6 border-end">
				<img class="image-responsive" width="70%" src="ImageServlet?immagine=<%=vidBean.getCodice()%>_1.jpg" alt="image">
			</div>
			<div class="col-md-6">
				<h4>Videogioco</h4>
				<br/>
				<h5>Prezzo: &euro;<%= vidBean.getCosto() %></h5>
				<div class="mt-5"> <span class="fw-bold">Specifiche:</span>
                   <div>
                       <ul style="list-style-type:none">
                           <li class="text-center">Prodotto da: <%= vidBean.getNomeSoftwareHouse() %></li>
                           <li>Dimensione: <%= vidBean.getDimensione() %> (GB)</li>
                           <li>Sviluppato nel: <%= vidBean.getAnnoDiProduzione() %></li>
                           <li>Pegi: <%= vidBean.getPegi() %></li>
                           
                       </ul>
                   </div>
               </div>
               <% if(roles==null || roles.equals("cliente")){
            	   
               
               %>
               <br/>
               <a href="AddToCartServlet?id=<%=vidBean.getCodice()%>" class="btn border-dark"> 
               		<img src="img\icon\shopping-cart.svg" alt="add-to-cart" class="icona"> Aggiungi al carrello
			   </a>
			   <%
			   
              	 }
               
			   %>
			   
			</div>
		</div><!-- end row -->
		<br/>
		<div class="row">
			<div class="col-md-12 d-flex justify-content-center">
			
				<section>
					<div class="row d-flex justify-content-center py-3">
						<div class="col-md-10 col-xl-8 text-center">
							<h3 class="mb-4">Recensioni</h3>
						</div>
					</div>
					
					<div class="row text-center">
						<%
							if(!colRec.isEmpty()){
								for(RecensioneBean rec: colRec){
								ClientiBean clBean = clDS.doRetrieveByKey(rec.getCodiceFiscaleCliente());
						%>
						<div class="col-md-12  mb-md-5">
							<h5 class="mb-3"><%=clBean.getNome() %> <%=clBean.getCognome() %></h5>
							<p class="px-xl-3">
								<%=rec.getDescrizione() %><br/>
								Data e ora: <%=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(rec.getDataOraIns()) %>
							</p>
							<%
								String strGrado = rec.getGradoDiApprezzamento().toString();
								int grado = 0;
							
								if(strGrado.equals("cinque")){
									grado = 5; 
								} else if (strGrado.equals("quattro")){
									grado=4;
								} else if (strGrado.equals("tre")){
									grado=3;
								} else if (strGrado.equals("due")){
									grado=2;
								} else if (strGrado.equals("uno")){
									grado=1;
								}
									
							%>
							<div>
							<ul class="list-inline">
							
								<% 
									for(int i=1; i<=grado; i++){
								%>
									<img src="img\icon\star.svg" alt="star-piena" class="icona">
									
								<% } %>
								
								<% 
									for(int i=grado; i<5; i++){
								%>
									<img src="img\icon\star-vuota.svg" alt="star-vuota" class="icona")>
									
								<% } %>
							</ul>
						</div>
							
						</div>
						
						<% 
						}
						}else{
						
						%>
						<h5>Non ci sono recensioni.</h5>				
						
						<% 
					}
						
				%>
					</div><!-- chiusura div per il for -->
				
				</section>
				
			</div>
		
		</div><!-- end row -->
		
		
		
		<br/><br/>
			 <% 
			 	if(roles==null){
			 		
			 	}else if(roles.equals("cliente")){
			 		
             %>
			<form action="RecensioneServlet?id=<%=vidBean.getCodice() %>" method="post">
				<div class="row">
					<div class="form-group">
						<label for="textArea">Descrizione</label>
						<textarea class="form-control" id="textArea" name="textArea" rows="7"></textarea>
					</div>
				</div>
				<br/>
				<div class="row">	
					<div class="col-md-6 mb-3">
						<label for="grado">Grado di Apprezzamento</label> <br /> <select name="grado"
							id="grado" class="form-control">
							<option value="uno" id="uno">uno</option>
							<option value="due" id="due">due</option>
							<option value="tre" id="tre">tre</option>
							<option value="quattro" id="quattro">quattro</option>
							<option value="cinque" id="cinque">cinque</option>
						</select>
					</div>
				</div>
				<input type="submit" value="Aggiungi recensione" class="btn btn-block btn-warning"/>
			</form>
			<%
			 	}
			%>
		
	</div><!-- end container -->
	<%@ include file="../fragments/footerReg.jsp" %>
</body>
</html>