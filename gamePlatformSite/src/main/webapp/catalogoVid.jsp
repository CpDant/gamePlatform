<%@ page language="java" import="javax.sql.DataSource,it.unisa.gp.model.bean.VideogiocoBean, it.unisa.gp.model.interfaceDS.Videogioco, 
it.unisa.gp.model.DAO.VideogiocoDS, java.util.*" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>

<%
	String roles = (String) session.getAttribute("roles");
	
	if(roles == null){
		
	} else if(roles.equals("supVid")){
		response.sendRedirect(request.getContextPath() + "/catalogoVidSup.jsp");
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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="style/style.css" rel="stylesheet">
<title>Catalogo Videogiochi</title>
</head>
<body>
	<div class="container">
		<div class="card-header my-3">
			<h2>Catalogo Videogiochi</h2>
		</div>
		<div class="row">
		<%
			if(!colVid.isEmpty()){
				for(VideogiocoBean vid: colVid){
		%>
		
			<div class="col-md-3 my-3">
				<div class="card text-center w-100" style="width: 18rem;">
					<img class="card-img-top" src="ImageServlet?immagine=<%=vid.getCodice()%>_1.jpg" alt="Card image">
					<div class="card-body">
						<h5 class="card-title"><%= vid.getNomeVideogioco() %></h5>
						<h6 class="price"> &euro; <%= vid.getCosto() %></h6>
						<a href="AddToCartServlet?id=<%=vid.getCodice()%>" class="btn">
							<img src="img\icon\shopping-cart.svg" alt="add-to-cart" class="icona">	
						</a>
					</div>
				</div>
			</div>
		<%	
				}
			}
		%>
		</div>
	</div>

</body>
</html>