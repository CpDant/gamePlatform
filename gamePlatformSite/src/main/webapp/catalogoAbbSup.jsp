<%@ page language="java" import="javax.sql.DataSource,it.unisa.gp.model.bean.AbbonamentoBean, it.unisa.gp.model.interfaceDS.Abbonamento, 
it.unisa.gp.model.DAO.AbbonamentoDS, java.util.*" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>

<%
	String roles = (String) session.getAttribute("roles");
	
	if(roles == null){
		response.sendRedirect(request.getContextPath() + "/login-form.jsp");
	} else if(roles.equals("cliente")){
		response.sendRedirect(request.getContextPath() + "/catalogoAbb.jsp");
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
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="style/style.css" rel="stylesheet">
<title>Gestione Abbonamenti</title>
</head>
<body>
	<div class="container">
		<div class="card-header my-3">
			<h2>Gestione Abbonamenti</h2>
		</div>
		<div style="text-align:center">
			<a href="#" class="btn border-dark">
				<img src="img\icon\add.svg" alt="add-to-cart" class="icona"> Aggiungi Abbonamento	
			</a>
		</div>
		<div class="row">
		<%
			if(!colAbb.isEmpty()){
				for(AbbonamentoBean abb: colAbb){
		%>
		
			<div class="col-md-4 my-3">
				<div class="card text-center w-100" style="width: 18rem;">
					<img class="card-img-top" src="img\abb\<%=abb.getNomeUnivoco()%>.jpg" alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%= abb.getNomeUnivoco() %></h5>
						<h6 class="price"> &euro; <%= abb.getCosto() %></h6>
						<a href="#" class="btn border-dark">
							<img src="img\icon\pencil.svg" alt="add-to-cart" class="icona">	
						</a>
						<a href="#" class="btn border-dark">
							<img src="img\icon\trash.svg" alt="add-to-cart" class="icona">	
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