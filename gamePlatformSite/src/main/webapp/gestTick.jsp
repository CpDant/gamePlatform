<%@ page language="java" import="javax.sql.DataSource, it.unisa.gp.model.bean.TicketsBean, it.unisa.gp.model.interfaceDS.Tickets, 
it.unisa.gp.model.DAO.TicketsDS, java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8" %>
<!DOCTYPE html>

<%
	String roles = (String) session.getAttribute("roles");
	
	if(roles == null){
		response.sendRedirect(request.getContextPath() + "/login-form.jsp");
	} else if (roles.equals("admin") || roles.equals("supVid") || roles.equals("cliente")) {
	response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
	}
	
	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	Tickets ticketsDS = new TicketsDS(ds);
	Collection<TicketsBean> colTic = ticketsDS.doRetrieveAllExists(null,false);

%>



<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="style/style.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="32x32" href="img/icon/favicon.png">
<title>Gestione tickets</title>
</head>
<body>
<script src="script/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

<script>
	function remOggetto(id){
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function(){
			if (this.readyState == 4 && this.status == 200){
				document.getElementById("pagina").innerHTML = this.responseText;
			}
		};
		xhttp.open("GET","RemTicketRes?id=" + id,true);
		xhttp.send();
	}
</script>


<div id="pagina">
<%@ include file="../fragments/header.jsp" %>
<div id = "cont" class="container page">
	<h2>Gestione tickets</h2>
		
		<br/>
		<%
			if(colTic.isEmpty()){
		%>		
			<p>Non ci sono ticket da risolvere.</p>	
		<% 
			}else{
		%>
		<table class="table table-light align-middle">
			<thead>
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Problema</th>
					<th scope="col">Messaggio</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<% 
					for(TicketsBean ticBean : colTic){
				%>
				
					<tr id = "<%= ticBean.getId() %>">
					
						<td><%= ticBean.getId() %></td>
						<td><%= ticBean.getCategoria() %></td>
						<td><%= ticBean.getMessaggio() %></td>
						<td>
							<button type="button" class="btn border-dark" onclick='remOggetto("<%= ticBean.getId() %>")'><img src="img\icon\check.svg" alt="prob-risolto" class="icona"></button>
						</td>
						
					</tr>
				<%
					}
				}
				%>
			
			</tbody>
		</table>
	</div>
	<%@ include file="../fragments/footerReg.jsp" %>
	</div>
</body>
</html>