<%@ page language="java" import="javax.sql.DataSource,it.unisa.gp.model.bean.VideogiocoBean, it.unisa.gp.model.interfaceDS.Videogioco, 
it.unisa.gp.model.DAO.VideogiocoDS, java.util.*" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>

<%
	String roles = (String) session.getAttribute("roles");
	
	if(roles == null){
		response.sendRedirect(request.getContextPath() + "/login-form.jsp");
	} else if(roles.equals("cliente")){
		response.sendRedirect(request.getContextPath() + "/catalogoVid.jsp");
	} else if (roles.equals("admin") || roles.equals("assCl")) {
		response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
	}
	
	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	Videogioco vidDS = new VideogiocoDS(ds);
	Collection<VideogiocoBean> colVid = vidDS.doRetrieveAllExists(null);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="style/style.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="32x32" href="img/icon/favicon.png">
<title>Gestione Videogiochi</title>
</head>
<body>
<script>
	function remOggetto(id){
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function(){
			if (this.readyState == 4 && this.status == 200){
				document.getElementById("pagina").innerHTML = this.responseText;
			}
		};
		xhttp.open("GET","RemFromCatalogServlet?id=" + id,true);
		xhttp.send();
	}
</script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
	<div id="pagina">
	<%@ include file="../fragments/header.jsp" %>
	<div class="container" id="cont">
		<div class="card-header my-3">
			<h2>Gestione Videogiochi</h2>
			<br/>
		</div>
		<div style="text-align:center">
			<a href="/gamePlatformSite/addInCat.jsp" class="btn border-dark">
				<img src="img\icon\add.svg" alt="add-to-cart" class="icona"> Aggiungi Videogioco	
			</a>
			<a href="/gamePlatformSite/modInCat.jsp" class="btn border-dark"> 	
				<img src="img\icon\pencil.svg" alt="mod-abb" class="icona">	Modifica Videogioco
			</a>
		</div>
		<div class="row">
		<%
			if(!colVid.isEmpty()){
				for(VideogiocoBean vid: colVid){
		%>
		
			<div class="col-md-3 my-3">
				<div class="card text-center w-100" style="width: 18rem;">
					<form>
						 <input type="hidden" name="userId" value="<%=vid.getCodice()%>">
						<a href="prodottoVideog.jsp?id=<%=vid.getCodice()%>"> <img class="card-img-top" src="ImageServlet?immagine=<%=vid.getCodice()%>_1.jpg" alt="Card image"> </a>
					</form>
					<div class="card-body">
						<h5 class="card-title"><%= vid.getNomeVideogioco() %></h5>
						<h6 class="price"> &euro; <%= vid.getCosto() %></h6>
						<button type="button" class="btn border-dark" onclick='remOggetto("<%= vid.getCodice() %>")'><img src="img\icon\trash.svg" alt="rem-videog" class="icona"></button>
					</div>
				</div>
			</div>
		<%	
				}
			}
		%>
		</div>
	</div>
	<%@ include file="../fragments/footerReg.jsp" %>
	</div>
</body>
</html>