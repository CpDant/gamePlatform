<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
	import = "javax.sql.DataSource,it.unisa.gp.model.bean.ClientiBean, it.unisa.gp.model.bean.AcquistiBean, 
	it.unisa.gp.model.interfaceDS.Acquisti, it.unisa.gp.model.DAO.AcquistiDS,
	it.unisa.gp.model.DAO.AziendaDS, it.unisa.gp.model.interfaceDS.Azienda, it.unisa.gp.model.bean.AziendaBean,
	java.time.format.DateTimeFormatter, java.time.LocalDateTime, java.util.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%
	String roles = (String) session.getAttribute("roles");
	ClientiBean cliente = (ClientiBean) session.getAttribute("utente");
	
	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	Acquisti acquistiDS = new AcquistiDS(ds);
	Collection<AcquistiBean> colAcq= acquistiDS.doRetrieveAllCliente(cliente.getCodiceFiscale(),null);
	
	Azienda aziendaDS = new AziendaDS(ds);
	AziendaBean azBean = aziendaDS.doRetrieveByKeyCodFis(cliente.getCodiceFiscale());
	boolean existAz = false;
	if(azBean.getCodiceFiscaleCliente() == null)
		existAz = false;
	else 
		existAz = true;
	
	final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:MM");
%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>I miei ordini</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="32x32" href="img/icon/favicon.png">
<link href="style/style.css" rel="stylesheet">

</head>
<body>
	<script src="script/jquery-3.6.0.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
	<%@ include file="../fragments/header.jsp" %>
	<div class="container">

		<table class="table table-light align-middle">
			<thead>
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Codice Riscatto</th>
					<th scope="col">Data e Ora</th>
					<th scope="col">Prezzo</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<% 
					for(AcquistiBean acq: colAcq){
				%>
				
				<tr id = "<%= acq.getId() %>">
					
					<td><%= acq.getId() %></td>
					<td><%= acq.getCodiceRiscatto() %></td>
					<td><%= acq.getDataOra().format(dtf) %></td>
					<td><%= acq.getCostoIva() + acq.getCostoNetto() %></td>
					
					<td>
					<form>
						 <input type="hidden" name="idAcq" value="<%=acq.getId()%>">
						<a href="viewFattura.jsp?idAcq=<%=acq.getId()%>"> Visualizza fattura </a>
					</form>
					 </td>
				
				</tr>
				
				<%
					}
				%>
			</tbody>
		
		</table>  
		
	</div>

	<%@ include file="../fragments/footerReg.jsp" %>
</body>
</html>