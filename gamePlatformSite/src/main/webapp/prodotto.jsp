<%@ page language="java" 
	import="javax.sql.DataSource, it.unisa.gp.model.bean.VideogiocoBean, it.unisa.gp.model.DAO.VideogiocoDS,
	 it.unisa.gp.model.interfaceDS.Videogioco, it.unisa.gp.model.interfaceDS.Recensione, it.unisa.gp.model.DAO.RecensioneDS,
	 it.unisa.gp.model.bean.RecensioneBean, it.unisa.gp.model.interfaceDS.Clienti, it.unisa.gp.model.DAO.ClientiDS, it.unisa.gp.model.bean.ClientiBean,
	java.time.format.DateTimeFormatter,	java.time.LocalDateTime,   
	java.util.*" pageEncoding="ISO-8859-1" contentType="text/html; charset=ISO-8859-1"
	%>
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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="style/style.css" rel="stylesheet">
<link href="style/styleProdotto.css" rel="stylesheet">
<link href="style/styleRecensione.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="32x32" href="img/icon/favicon.png">

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700|Open+Sans:400,700">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="script/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>Videogioco</title>
</head>

<script>
function changeImage(element) {
    var main_prodcut_image = document.getElementById('main_product_image');
    main_prodcut_image.src = element.src;
}
</script>

	<body>
	<%@ include file="../fragments/header.jsp" %>
		
	<div class="container mt-5 mb-5">
	    <div class="card">
	        <div class="row g-0">
	            <div class="col-md-6 border-end">
	                <div class="d-flex flex-column justify-content-center">
	                    <div class="main_image"> <img src="ImageServlet?immagine=<%=vidBean.getCodice()%>_1.jpg" id="main_product_image" width="350">
	                    </div>

	                </div>
	            </div>
	            <div class="col-md-6 d-flex justify-content-center">
	                <div class="p-3 right-side">
	                    <div class="d-flex justify-content-between align-items-center">
	                        <h3><%= vidBean.getNomeVideogioco() %></h3>
	                    </div>
	                    <h5>Prezzo: &euro;<%= vidBean.getCosto() %></h5>
	                    <div class="ratings d-flex flex-row align-items-center">
	                        <div class="d-flex flex-row"> <i class='bx bxs-star' ></i> <i class='bx bxs-star' ></i>
	                            <i class='bx bxs-star' ></i> <i class='bx bxs-star' ></i> <i class='bx bx-star' ></i> </div>
	                    </div>
	                    <div class="mt-5"> <span class="fw-bold">Specifiche</span>
	                        <div>
	                            <ul >
	                                <li>Prodotto da: <%= vidBean.getNomeSoftwareHouse() %></li>
	                                <li>Dimensione: <%= vidBean.getDimensione() %> (GB)</li>
	                                <li>Sviluppato nel: <%= vidBean.getAnnoDiProduzione() %></li>
	                                <li>Pegi: <%= vidBean.getPegi() %></li>
	                                
	                            </ul>
	                        </div>
	                    </div>
						<a href="AddToCartServlet?id=<%=vidBean.getCodice()%>" class="btn border-dark"> <img
							src="img\icon\shopping-cart.svg" alt="add-to-cart" class="icona"> Aggiungi al carrello
						</a>

					</div>
	        	</div>
	    	</div>
		</div>

		<h2>Recensioni</h2>
		<div id="myCarousel" class="carousel slide" data-ride="carousel">

			<!-- Wrapper for carousel items -->
			<div class="carousel-inner">
				<div class="carousel-item active">
					<%
					if(!colRec.isEmpty()){
						for(RecensioneBean rec: colRec){
							ClientiBean clBean = clDS.doRetrieveByKey(rec.getCodiceFiscaleCliente());
					%>
					<p class="overview">
						<b><%=clBean.getNome()%> <%=clBean.getCognome() %> </b>
					</p>
					<p class="testimonial">
						<%= rec.getDescrizione()%>
						<br/>Data e ora: <%= DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(rec.getDataOraIns()) %>
					
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
					
					<div class="star-rating">
						<ul class="list-inline">
						
							<% 
								for(int i=1; i<=grado; i++){
							%>
								<li class="list-inline-item"><i class="fa fa-star"></i></li>
							<% } %>
							
							<% 
								for(int i=grado; i<5; i++){
							%>
								<li class="list-inline-item"><i class="fa fa-star-o"></i></li>
							<% } %>
						</ul>
					</div>
				<%
					}
				} else {
				
				%>
				
				<h5>Non ci sono recensioni.</h5>				
				
				<%
					}
				%>
			
				</div>
				
			</div>
			<div class ="row d-flex justify-content-center align-content-center">
				<button class="btn btn-warning">Aggiungi recensione</button>
			</div>
			
			<br/><br/>
			<form method="post">
				
				<div class="row">
					<div class="form-group">
						<label for="exampleFormControlTextarea3">Rounded corners</label>
						<textarea class="form-control" id="exampleFormControlTextarea3"
							rows="7"></textarea>
					</div>
				</div>
				<div class="row">	
					<div class="col-md-6 mb-3">
						<label for="ruolo">Ruolo*</label> <br /> <select name="ruolo"
							id="ruolo" class="form-control">
							<option value="supVid">Supervisore videogioco</option>
							<option value="assCl">Assistente cliente</option>
						</select>
					</div>
				</div>
			</form>
		</div>
		


	</div>
	
		
	<%@ include file="../fragments/footerReg.jsp" %>	
</body>
</html>