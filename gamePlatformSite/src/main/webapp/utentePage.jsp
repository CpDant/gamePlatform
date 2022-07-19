<%@ page import="javax.sql.DataSource,it.unisa.gp.model.bean.ClientiBean, 
	it.unisa.gp.model.bean.AdministratorsBean, it.unisa.gp.model.bean.SupervisoreVideogiochiBean,
	it.unisa.gp.model.bean.SupervisoreVideogiochiBean, it.unisa.gp.model.bean.AssistenteClientiBean,
	it.unisa.gp.model.DAO.AziendaDS, it.unisa.gp.model.interfaceDS.Azienda, it.unisa.gp.model.bean.AziendaBean, 
	java.util.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
	String roles = (String) session.getAttribute("roles");
	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	 
	ClientiBean clBean = null;
	AdministratorsBean admBean = null;
	SupervisoreVideogiochiBean supBean = null;
	AssistenteClientiBean assBean = null;
	AziendaBean azBean = null;
	boolean existAz = false;
	if(roles == null){
		response.sendRedirect(request.getContextPath() + "/login-form.jsp");
	} else if (roles.equals("admin")) {
		admBean = (AdministratorsBean) session.getAttribute("utente");
	} else if (roles.equals("supVid")) {
		supBean = (SupervisoreVideogiochiBean) session.getAttribute("utente");
	} else if (roles.equals("assCl")) {
		assBean = (AssistenteClientiBean) session.getAttribute("utente");
	} else if (roles.equals("cliente")) {
		clBean = (ClientiBean) session.getAttribute("utente");
		Azienda aziendaDS = new AziendaDS(ds);
		azBean = aziendaDS.doRetrieveByKeyCodFis(clBean.getCodiceFiscale());
		
		existAz = false;
		if(azBean.getCodiceFiscaleCliente() == null)
			existAz = false;
		else 
			existAz = true;
	}
	
	
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Il mio profilo</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="32x32" href="img/icon/favicon.png">
<link href="style/style.css" rel="stylesheet">
<script src="script/jquery-3.6.0.min.js"></script>
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
								+"<h4>Dati aziendali</h4>"
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
								+"<h4>Dati aziendali</h4>"
								+"<div class='col-md-6 mb-3'>"
								+"<label for='sdi'> Sdi </label>" 
								+"<input type='text' class='form-control' id='sdi' name='sdi' placeholder=''>"
								+"</div>"
								
								+"<div class='col-md-6 mb-3'>"
								+"<label for='pec'> Pec </label>" 
								+"<input type='email' class='form-control' id='pec' name='pec' placeholder=''>"
								+"</div>"
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
	
	
		function modificaDati(roles) {
			if(roles == "cliente"){
				document.getElementById("datiModifica").innerHTML=""
					+"<form action='ModificaDatiServlet' method='post'>"
						+"<div class='row'>"
							+"<h4>Dati personali</h4>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='nome'> Nome </label>" 
								+"<input type='text' class='form-control' id='nome' name='nome' placeholder=''>"
							+"</div>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='cognome'> Cognome </label>" 
								+"<input type='text' class='form-control' id='cognome' name='cognome' placeholder=''>"
							+"</div>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='data'> Data di nascita </label>" 
								+"<input type='date' class='form-control' id='data' name='data' placeholder=''>"
							+"</div>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='email'> Email </label>" 
								+"<input type='email' class='form-control' id='email' name='email' placeholder=''>"
							+"</div>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='passw'> Password </label>" 
								+"<input type='password' class='form-control' id='passw' name='passw' placeholder=''>"
							+"</div>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='username'> Username </label>" 
								+"<input type='text' class='form-control' id='username' name='username' placeholder=''>"
							+"</div>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='indFatt'> Indirizzo di fatturazione </label>" 
								+"<input type='text' class='form-control' id='indFatt' name='indFatt' placeholder=''>"
							+"</div>"
							
						+"</div><br/>"
						+"<div class='col-md-6 mb-3'><input type='checkbox' name='fattura' id='fattura' onclick='datiFattura()'> Dati aziendali </div>"
						+"<br/>"
						+"<div id='datiFattura' class='row'>"
							
							
						+"</div>"
						+"<input type='submit' value='Modifica' class='btn btn-primary'>"
						+""
					+"</form>";
			} else if(roles == "supVid") {
				document.getElementById("datiModifica").innerHTML=""
					+"<form action='ModificaDatiServlet' method='post'>"
						+"<div class='row'>"
							+"<h4>Dati personali</h4>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='nome'> Nome </label>" 
								+"<input type='text' class='form-control' id='nome' name='nome' placeholder=''>"
							+"</div>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='cognome'> Cognome </label>" 
								+"<input type='text' class='form-control' id='cognome' name='cognome' placeholder=''>"
							+"</div>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='data'> Data di nascita </label>" 
								+"<input type='date' class='form-control' id='data' name='data' placeholder=''>"
							+"</div>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='email'> Email </label>" 
								+"<input type='email' class='form-control' id='email' name='email' placeholder=''>"
							+"</div>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='passw'> Password </label>" 
								+"<input type='password' class='form-control' id='passw' name='passw' placeholder=''>"
							+"</div>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='retrAnn'> Retribuzione annuale </label>" 
								+"<input type='text' class='form-control' id='retrAnn' name='retrAnn' placeholder=''>"
							+"</div>"
							
						+"</div>"
					
						+"<input type='submit' value='Modifica' class='btn btn-primary'>"
					+"</form>";
			} else if(roles == "assCl") {
				document.getElementById("datiModifica").innerHTML=""
					+"<form action='ModificaDatiServlet' method='post'>"
						+"<div class='row'>"
							+"<h4>Dati personali</h4>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='nome'> Nome </label>" 
								+"<input type='text' class='form-control' id='nome' name='nome' placeholder=''>"
							+"</div>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='cognome'> Cognome </label>" 
								+"<input type='text' class='form-control' id='cognome' name='cognome' placeholder=''>"
							+"</div>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='data'> Data di nascita </label>" 
								+"<input type='date' class='form-control' id='data' name='data' placeholder=''>"
							+"</div>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='email'> Email </label>" 
								+"<input type='email' class='form-control' id='email' name='email' placeholder=''>"
							+"</div>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='passw'> Password </label>" 
								+"<input type='password' class='form-control' id='passw' name='passw' placeholder=''>"
							+"</div>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='retrAnn'> Retribuzione annuale </label>" 
								+"<input type='text' class='form-control' id='retrAnn' name='retrAnn' placeholder=''>"
							+"</div>"
							
						+"</div>"
					
						+"<input type='submit' value='Modifica' class='btn btn-primary'>"
					+"</form>";
			} else if(roles == "admin") {
				document.getElementById("datiModifica").innerHTML=""
					+"<form action='ModificaDatiServlet' method='post'>"
						+"<div class='row'>"
						+"<h4>Dati personali</h4>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='nome'> Nome </label>" 
								+"<input type='text' class='form-control' id='nome' name='nome' placeholder=''>"
							+"</div>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='cognome'> Cognome </label>" 
								+"<input type='text' class='form-control' id='cognome' name='cognome' placeholder=''>"
							+"</div>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='data'> Data di nascita </label>" 
								+"<input type='date' class='form-control' id='data' name='data' placeholder=''>"
							+"</div>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='email'> Email </label>" 
								+"<input type='email' class='form-control' id='email' name='email' placeholder=''>"
							+"</div>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='passw'> Password </label>" 
								+"<input type='password' class='form-control' id='passw' name='passw' placeholder=''>"
							+"</div>"
							+"<div class='col-md-6 mb-3'>"
								+"<label for='retrAnn'> Retribuzione annuale </label>" 
								+"<input type='text' class='form-control' id='retrAnn' name='retrAnn' placeholder=''>"
							+"</div>"
							
						+"</div>"
					
						+"<input type='submit' value='Modifica' class='btn btn-primary'>"
						
					+"</form>";
			}
			
		}
		
		
	</script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
	<%@ include file="../fragments/header.jsp" %>
	<div class="container">
		<h2>Il mio profilo</h2>
		<br/>
		<%
			if(roles.equals("cliente")){
		%>
			<div class="row">
				<h4>Riepilogo dati personali</h4>
				<div class="col-md-6 mb-3"> Nome: <%=clBean.getNome()%></div>
				<div class="col-md-6 mb-3"> Cognome: <%=clBean.getCognome()%></div>
				<div class="col-md-6 mb-3"> Codice Fiscale:	<%=clBean.getCodiceFiscale() %> </div>
				<div class="col-md-6 mb-3"> Data Nascita: <%=clBean.getDataNascita() %> </div>
				<div class="col-md-6 mb-3"> Username: <%=clBean.getUsername() %> </div>
				<div class="col-md-6 mb-3"> Email: <%=clBean.getEmail() %> </div>
				<div class="col-md-6 mb-3"> Indirizzo di fatturazione <%=clBean.getIndFatt() %></div>
				
				<h4 class="mt-3">Riepilogo dati aziendali</h4>
				<%
					if(existAz){
				%>
				<div class="col-md-6 mb-3"> Partita Iva: <%=azBean.getpIva()%></div>
				<div class="col-md-6 mb-3"> Sdi: <%=azBean.getSdi()%></div>
				<div class="col-md-6 mb-3"> Pec:	<%=azBean.getPec() %> </div>
				<% 		
					} else {
				%>
				<p>Non è associata nessuna azienda al tuo account, per farlo e successivamente poter richiedere la fattura di un ordine, inserisci i dati relativi alla tua azienda in "Dati aziendali" cliccando "Modifica dati personali".</p>
				<%
					}
				%>
				<div class="row">
					<div>
						<a href='ordini.jsp' class='btn btn-primary'>Visualizza tutti gli ordini</a> <br/><br/>
						<button onclick="modificaDati('<%=roles%>')" class="btn btn-primary">Modifica dati personali</button>
					</div>
				</div>
				
			</div>
		<%
			} else if(roles.equals("supVid")) {
		%>
			<div class="row">
				<h4>Riepilogo dati personali</h4>
				<div class="col-md-6 mb-3"> Nome: <%=supBean.getNome()%></div>
				<div class="col-md-6 mb-3"> Cognome: <%=supBean.getCognome()%></div>
				<div class="col-md-6 mb-3"> Codice Fiscale:	<%=supBean.getCodiceFiscale() %> </div>
				<div class="col-md-6 mb-3"> Data Nascita: <%=supBean.getDataNascita() %> </div>
				<div class="col-md-6 mb-3"> Email: <%=supBean.getEmail() %> </div>
				<div class="col-md-6 mb-3"> Retribuzione annuale: <%=supBean.getRetribuzioneAnnuale() %> </div>
				<div class="row">
					
					<div class="col-md-6 mb-3" >
						<a href='addInCat.jsp' class='btn btn-primary'>Gestisci i cataloghi</a> <br/><br/>
						<button onclick="modificaDati('<%=roles%>')" class="btn btn-primary">Modifica dati personali</button>
						
					</div>
					
				</div>
			</div>
		<%
			} else if(roles.equals("assCl")) {
		%>
			<div class="row">
				<h4>Riepilogo dati personali</h4>
				<div class="col-md-6 mb-3"> Nome: <%=assBean.getNome()%></div>
				<div class="col-md-6 mb-3"> Cognome: <%=assBean.getCognome()%></div>
				<div class="col-md-6 mb-3"> Codice Fiscale:	<%=assBean.getCodiceFiscale() %> </div>
				<div class="col-md-6 mb-3"> Data Nascita: <%=assBean.getDataNascita() %> </div>
				<div class="col-md-6 mb-3"> Email: <%=assBean.getEmail() %> </div>
				<div class="col-md-6 mb-3"> Retribuzione annuale: <%=assBean.getRetribuzioneAnnuale() %> </div>
				<div class="row">
					<div class="col-md-6 mb-3" >
						<a href='gestTick.jsp' class='btn btn-primary'>Gestisci tickets</a> <br/><br/>
						<button onclick="modificaDati('<%=roles%>')" class="btn btn-primary">Modifica dati personali</button>
					</div>
				</div>
			</div>
		<%
			} else if(roles.equals("admin")) {
		%>
			<div class="row">
				<h4>Riepilogo dati personali</h4>
				<div class="col-md-6 mb-3"> Nome: <%=admBean.getNome()%></div>
				<div class="col-md-6 mb-3"> Cognome: <%=admBean.getCognome()%></div>
				<div class="col-md-6 mb-3"> Codice Fiscale:	<%=admBean.getCodiceFiscale() %> </div>
				<div class="col-md-6 mb-3"> Data Nascita: <%=admBean.getDataNascita() %> </div>
				<div class="col-md-6 mb-3"> Email: <%=admBean.getEmail() %> </div>
				<div class="col-md-6 mb-3"> Retribuzione annuale: <%=admBean.getRetribuzioneAnnuale() %> </div>
			</div>
			<div class="row">
				<div class="col-md-6 mb-3" >
					<a href='addOperatore.jsp' class='btn btn-primary'>Aggiungi operatori</a> <br/><br/>
					<button onclick="modificaDati('<%=roles%>')" class="btn btn-primary">Modifica dati personali</button>				
				</div>
			</div>
		<%
			} 
		%>
		
		<hr class="mb-4">
		
		<div id="datiModifica">
			
		</div>
	
	</div>
	
	
	<%@ include file="../fragments/footerReg.jsp" %>
</body>
</html>