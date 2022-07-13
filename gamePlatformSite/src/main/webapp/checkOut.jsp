<%@ page language="java"
	import="javax.sql.DataSource,it.unisa.gp.model.bean.ClientiBean, it.unisa.gp.model.bean.Carrello, 
	it.unisa.gp.model.bean.AbbonamentoBean, it.unisa.gp.model.bean.VideogiocoBean, java.util.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%
	ClientiBean cliente = (ClientiBean) session.getAttribute("utente");
	Carrello carrello = (Carrello) session.getAttribute("carrello");
	Collection<VideogiocoBean> arrVid = carrello.getArrVidBean();
	Collection<AbbonamentoBean> arrAbb = carrello.getArrAbbBean();
%>


<html>
<head>
<meta charset="ISO-8859-1">
<title>CheckOut</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="style/style.css" rel="stylesheet">
</head>
<body>

	<div class="container">
		<h2>CheckOut</h2>

		<br/><br/>
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
					<div class="col-md-6 mb-3"> Data Nascita: <%= cliente.getDataNascita() %> </div>
				</div>

				<div class="row">
					<div class="col-md-6 mb-3"> Username: <%=cliente.getUsername() %> </div>
					<div class="col-md-6 mb-3"> Email: <%=cliente.getEmail() %> </div>
				</div>
				
				<div class="row">
					<div class="col-md-6 mb-3"> Indirizzo di fatturazione <%=cliente.getIndFatt() %></div>
				</div>
				
				<div class="row">
					<div class="col-md-6 mb-3"> Richiedi fattura <input type="checkbox"> </div>
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
						<label for="cc-name">Nome titolare della carta</label> <input type="text"
							class="form-control" id="cc-name" placeholder="" required>
						<div class="invalid-feedback">Il nome della carta è	richiesto</div>
					</div>
					<div class="col-md-6 mb-3">
						<label for="cc-number">Numero della carta di credito</label> <input
							type="text" class="form-control" id="cc-number" placeholder="" maxlength="16"
							required>
						<div class="invalid-feedback">il numero della carta di
							credito è richiesto</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-3 mb-3">
						<label for="cc-expiration">Data di scadenza</label> <input
							type="date" class="form-control" id="cc-expiration"
							placeholder="" required>
						<div class="invalid-feedback">La data di scadenza è
							richiesta</div>
					</div>
					<div class="col-md-3 mb-3">
						<label for="cc-cvv">CVV</label> <input type="text"
							class="form-control" id="cc-cvv" placeholder="" maxlength="3" required>
						<div class="invalid-feedback">Il codice di sicurezza è
							richiesto</div>
					</div>
				</div>
				<hr class="mb-4">
				<button class="btn btn-primary btn-lg btn-block" type="submit">Procedi al pagamento</button>
			</div>	
		</div>
	</div>


</body>
</html>