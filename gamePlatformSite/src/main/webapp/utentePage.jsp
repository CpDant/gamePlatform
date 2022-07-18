<%@ page import="javax.sql.DataSource,it.unisa.gp.model.bean.ClientiBean, 
	it.unisa.gp.model.bean.AdministratorsBean, it.unisa.gp.model.bean.SupervisoreVideogiochiBean,
	it.unisa.gp.model.bean.SupervisoreVideogiochiBean, it.unisa.gp.model.bean.AssistenteClientiBean,
	java.util.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
	String roles = (String) session.getAttribute("roles");
	
	 
	ClientiBean clBean = null;
	AdministratorsBean admBean = null;
	SupervisoreVideogiochiBean supBean = null;
	AssistenteClientiBean assBean = null;
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
		function modificaDati(roles) {
			if(roles == "cliente"){
				document.getElementById("datiModifica").innerHTML=""
					+"<form action='ModificaDatiServlet' method='post'>"
						+"<div class='row'>"
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
							
						+"</div>"
						
						+"<input type='submit' value='Modifica' class='btn btn-primary'>"
						+""
					+"</form>";
			} else if(roles == "supVid") {
				document.getElementById("datiModifica").innerHTML=""
					+"<form action='ModificaDatiServlet' method='post'>"
						+"<div class='row'>"
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
	<%@ include file="../fragments/header.jsp" %>
	<div class="container">
		<h2>Il mio profilo</h2>
		<%
			if(roles.equals("cliente")){
		%>
			<div class="row">
				<div class="col-md-6 mb-3"> Nome: <%=clBean.getNome()%></div>
				<div class="col-md-6 mb-3"> Cognome: <%=clBean.getCognome()%></div>
				<div class="col-md-6 mb-3"> Codice Fiscale:	<%=clBean.getCodiceFiscale() %> </div>
				<div class="col-md-6 mb-3"> Data Nascita: <%=clBean.getDataNascita() %> </div>
				<div class="col-md-6 mb-3"> Username: <%=clBean.getUsername() %> </div>
				<div class="col-md-6 mb-3"> Email: <%=clBean.getEmail() %> </div>
				<div class="col-md-6 mb-3"> Indirizzo di fatturazione <%=clBean.getIndFatt() %></div>
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