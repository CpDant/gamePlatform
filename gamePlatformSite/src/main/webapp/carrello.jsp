<%@ page language="java" import="it.unisa.gp.model.bean.Carrello, java.util.*, it.unisa.gp.model.bean.VideogiocoBean, it.unisa.gp.model.bean.AbbonamentoBean" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>

<%
	boolean flag = false;
	Carrello carrello = (Carrello) session.getAttribute("carrello");
	String roles = (String) session.getAttribute("roles");
	
	if(roles == null){
		flag = false;
	}else if(roles.equals("admin") || roles.equals("assCl") || roles.equals("supVid")){
		response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
	}else if(roles.equals("cliente")){
		flag = true;
	}
	
%>

<html>
<head>
<meta charset="utf-8">
<title>Carrello</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="style/style.css" rel="stylesheet">
</head>
<body>
<script>
	function remOggetto(id){
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function(){
			if (this.readyState == 4 && this.status == 200){
				document.getElementById("cont").innerHTML = this.responseText;
			}
		};
		xhttp.open("GET","RemFromCartServlet?id=" + id,true);
		xhttp.send();
	}
	
	function checkTot(tot){
		if(tot == "0"){
			alert("Per procedere all'acquisto devi contenere almeno un oggetto nel carrello.");
		} else {
			window.location.href="/gamePlatformSite/checkOut.jsp";
		}
	}
	
	
</script>
<%@ include file="../fragments/header.jsp"%>
<%
	if(flag == true){	
%>
	
	<div id = "cont" class="container">
	<h2>Carrello</h2>
		<div class="d-flex py-3"><h3>Prezzo totale: &euro; <%= carrello.getTotale() %></h3><button class="mx-3 btn btn-primary" onclick='checkTot("<%= carrello.getTotale() %>")'>Compra ora</button></div>

		<%
			Collection<VideogiocoBean> arrVid = carrello.getArrVidBean();
			Collection<AbbonamentoBean> arrAbb = carrello.getArrAbbBean();
			if(arrVid.isEmpty() && arrAbb.isEmpty()){
		%>		
			<p>Il carrello è vuoto.</p>	
		<% 
			}else{
				
			
		%>
		<table class="table table-light align-middle">
			<thead>
				<tr>
					<th scope="col">Nome</th>
					<th scope="col">Tipo</th>
					<th scope="col">Prezzo</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<% 
					for(AbbonamentoBean abbBean : arrAbb){
				%>
				
				<tr id = "<%= abbBean.getNomeUnivoco() %>">
					
					<td><%= abbBean.getNomeUnivoco() %></td>
					<td>Abbonamento</td>
					<td>&euro; <%= abbBean.getCosto() %></td>
					<td>
						<button type="button" class="btn btn-sm btn-danger" onclick='remOggetto("<%= abbBean.getNomeUnivoco() %>")'>Rimuovi</button>
					</td>
				</tr>
				<%
					}
				%>
			
			
				<% 
					for(VideogiocoBean vidBean : arrVid){
				%>
				
				<tr id = "<%=vidBean.getCodice()%>">
					
					<td><%= vidBean.getNomeVideogioco() %></td>
					<td>Videogioco</td>
					<td>&euro; <%= vidBean.getCosto() %></td>
					<td><button type="button" class="btn btn-sm btn-danger" onclick='remOggetto("<%= vidBean.getCodice() %>")'>Rimuovi</button></td>
				</tr>
				<%
					}
				%>
				
			</tbody>
		
		</table>
		<%
			}
		%>

	</div>			
<% 
	}else{
%>		
		<div id = "cont" class="container">
			<h2>Carrello</h2>
			<p>La funzionalità carrello non è disponibile se non sei autenticato, effettua il <a href = "/gamePlatformSite/login-form.jsp">login</a></p>
		</div>
<%	
	}

%>

<%@ include file="../fragments/footerReg.jsp" %>
</body>
</html>