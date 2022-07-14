<%@ page language="java" import="javax.sql.DataSource,it.unisa.gp.model.bean.AbbonamentoBean, it.unisa.gp.model.interfaceDS.Abbonamento, 
it.unisa.gp.model.DAO.AbbonamentoDS, java.util.*" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>

<%
	String roles = (String) session.getAttribute("roles");
	
	if(roles == null){
		
	} else if(roles.equals("supVid")){
		response.sendRedirect(request.getContextPath() + "/catalogoAbbSup.jsp");
	} else if (roles.equals("admin") || roles.equals("assCl")) {
		response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
	}
	
	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	Abbonamento abbDS = new AbbonamentoDS(ds);
	Collection<AbbonamentoBean> colAbb = abbDS.doRetrieveAll("costo");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="style/style.css" rel="stylesheet">
<title>Catalogo Abbonamenti</title>
</head>
<body>
	<div class="container">
		<div class="card-header my-3">
			<h2>Catalogo Abbonamenti</h2>
		</div>
		<div class="row">
		<%
			if(!colAbb.isEmpty()){
				for(AbbonamentoBean abb: colAbb){
		%>
		
			<div class="col-md-4 my-3">
				<div class="card text-center w-100" style="width: 18rem;">
					<img class="card-img-top" src="img\abb\<%=abb.getNomeUnivoco()%>.jpg" alt="Card image">
					<div class="card-body">
						<h5 class="card-title"><%= abb.getNomeUnivoco() %></h5>
						<h6 class="price"> &euro; <%= abb.getCosto() %></h6>
						<a href="AddToCartServlet?id=<%= abb.getNomeUnivoco() %>" class="btn">
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