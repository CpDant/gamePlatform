<%@ page language="java"
	import="javax.sql.DataSource,it.unisa.gp.model.bean.ClientiBean, it.unisa.gp.model.bean.Carrello, 
	it.unisa.gp.model.bean.AbbonamentoBean, it.unisa.gp.model.bean.VideogiocoBean,
	it.unisa.gp.model.DAO.AziendaDS, it.unisa.gp.model.interfaceDS.Azienda, 
	it.unisa.gp.model.bean.AziendaBean, java.util.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%
	ClientiBean cliente = (ClientiBean) session.getAttribute("utente");
	Carrello carrello = (Carrello) session.getAttribute("carrello");
	Collection<VideogiocoBean> arrVid = carrello.getArrVidBean();
	Collection<AbbonamentoBean> arrAbb = carrello.getArrAbbBean();
	
	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	Azienda aziendaDS = new AziendaDS(ds);
	AziendaBean azBean = aziendaDS.doRetrieveByKeyCodFis(cliente.getCodiceFiscale());
	boolean existAz = false;
	if(azBean.getCodiceFiscaleCliente() == null)
		existAz = false;
	else 
		existAz = true;
%>


<html>
<head>
<meta charset="ISO-8859-1">
<title>CheckOut</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="style/style.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="32x32" href="img/icon/favicon.png">
</head>
<body>
<script>
	function datiFattura(){
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function(){
			if (this.readyState == 4 && this.status == 200){
				if(<%=existAz%> == false){
					if (document.querySelector('#fattura:checked') != null){
						document.getElementById("datiFattura").innerHTML = ""
							+"<div class='row'>"
								+"<div class='col-md-6 mb-3'>"
									+"<label for='pIva'> Partita Iva&#42; </label>" 
									+"<input type='text' class='form-control' id='pIva' name='pIva' placeholder='' required>"
								+"</div>"
								
								+"<div class='col-md-6 mb-3'>"
								+"<label for='sdi'> Sdi&#42; </label>" 
								+"<input type='text' class='form-control' id='sdi' name='sdi' placeholder='' required>"
								+"</div>"
								
								+"<div class='col-md-6 mb-3'>"
								+"<label for='pec'> Pec&#42; </label>" 
								+"<input type='email' class='form-control' id='pec' name='pec' placeholder='' required>"
								+"</div>"
							+"</div>";					
					} else {
						document.getElementById("datiFattura").innerHTML = "<p></p>";	
					}					
				} else {
					if (document.querySelector('#fattura:checked') != null){
						document.getElementById("datiFattura").innerHTML = ""
						+"<div class='row'>"
							+"<div class='col-md-6 mb-3'> Partita Iva: <%=azBean.getpIva()%> </div>"
							+"<div class='col-md-6 mb-3'> SDI: <%=azBean.getSdi()%> </div>"
							+"<div class='col-md-6 mb-3'> PEC: <%=azBean.getPec()%> </div>"
						+"</div>";					
					} else {
						document.getElementById("datiFattura").innerHTML = "<p></p>";	
					}	
				}
			}
		};
		xhttp.open("GET","",true);
		xhttp.send();
	}
</script>


	<div id="cont" class="container">
		<h2>CheckOut</h2>

		<br/><br/>
		
		<form action="CheckOutServlet" method="post">
		<div class="row">
			<div class="col-md-4 order-md-2 mb-4">
				<h4 class="d-flex justify-content-between align-items-center mb-3">
					<span class="text-muted">Il tuo carrello</span> <span
						class="badge badge-secondary badge-pill">3</span>
				</h4>
				<ul class="list-group mb-3">
				
				<% 
					for(AbbonamentoBean abbBean : arrAbb){
				%>

					<li	class="list-group-item d-flex justify-content-between lh-condensed">
						<div>
							<h6 class="my-0"><%=abbBean.getNomeUnivoco() %></h6>
							<small class="text-muted"></small>
						</div> <span class="text-muted">&euro; <%=abbBean.getCosto()%></span>
					</li>
				<%
					}
				%>
				
							
				<% 
					for(VideogiocoBean vidBean : arrVid){
				%>

					<li	class="list-group-item d-flex justify-content-between lh-condensed">
						<div>
							<h6 class="my-0"><%=vidBean.getNomeVideogioco() %></h6>
							<small class="text-muted"></small>
						</div> <span class="text-muted">&euro; <%=vidBean.getCosto()%></span>
					</li>
				<%
					}
				%>
				
					<li class="list-group-item d-flex justify-content-between"><span>Totale
					</span> <strong>&euro; <%= carrello.getTotale() %>
					</strong></li>
				</ul>

			</div>
			
			<div class="col-md-8 order-md-1">
				<h4 class="mb-3">Riepilogo Dati</h4>
				
				<div class="row">
					<div class="col-md-6 mb-3"> Nome: <%=cliente.getNome()%></div>
					<div class="col-md-6 mb-3"> Cognome: <%=cliente.getCognome()%></div>
				</div>
				

				<div class="row">
					<div class="col-md-6 mb-3"> Codice Fiscale:	<%=cliente.getCodiceFiscale() %> </div>
					<div class="col-md-6 mb-3"> Data Nascita: <%=cliente.getDataNascita() %> </div>
				</div>

				<div class="row">
					<div class="col-md-6 mb-3"> Username: <%=cliente.getUsername() %> </div>
					<div class="col-md-6 mb-3"> Email: <%=cliente.getEmail() %> </div>
				</div>
				
				<div class="row">
					<div class="col-md-6 mb-3"> Indirizzo di fatturazione <%=cliente.getIndFatt() %></div>
				</div>
				
				<div class="row">
					<div class="col-md-6 mb-3"> Richiedi fattura <input type="checkbox" name="fattura" id="fattura" onclick="datiFattura()"> </div>
				</div>
				<div id="datiFattura">
				
				</div>
				
				<div class="row">
					<div class="col-md-6 mb-3" > Se vuoi modificare i dati personali, <a href="#">clicca qui</a>  </div>
				</div>
				<hr class="mb-4">

				<h4 class="mb-3">Pagamento</h4>

				<div class="d-block my-3">
					<div class="custom-control custom-radio">
						<input id="credit" name="paymentMethod" type="radio"
							class="custom-control-input" checked required> <label
							class="custom-control-label" for="credit">Carta di
							credito</label>
					</div>
					<div class="custom-control custom-radio">
						<input id="debit" name="paymentMethod" type="radio"
							class="custom-control-input" required> <label
							class="custom-control-label" for="debit">Carta di debito</label>
					</div>
				</div>
					
				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="cc-name">Nome titolare della carta&#42;</label> <input id="cc-name" type="text"
							name="cc-name" class="form-control" placeholder="" required>
					</div>
					<div class="col-md-6 mb-3">
						<label for="cc-number">Numero della carta di credito&#42;</label> <input
							type="text" class="form-control" id="cc-number" name="cc-number" placeholder="" maxlength="16" required>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-3 mb-3">
						<label for="cc-expiration">Data di scadenza&#42;</label> <input
							type="date" class="form-control" id="cc-expiration" name="cc-expiration" placeholder="" required>
					</div>
					<div class="col-md-3 mb-3">
						<label for="cc-cvv">CVV&#42;</label> <input type="text"
							class="form-control" id="cc-cvv" name="cc-cvv" placeholder="" maxlength="3" required>
					</div>
				</div>
				<hr class="mb-4">
			</div>	
		</div>
		<button class="btn btn-primary btn-block" type="submit">Procedi al pagamento</button>
	</form>				
	</div>


</body>
</html>