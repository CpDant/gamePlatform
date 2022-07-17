<%@ page language="java" import="javax.sql.DataSource, it.unisa.gp.model.bean.AbbonamentoBean, it.unisa.gp.model.bean.VideogiocoBean,
 	it.unisa.gp.model.bean.SoftwareHouseBean, it.unisa.gp.model.DAO.SoftwareHouseDS, it.unisa.gp.model.interfaceDS.SoftwareHouse,
	it.unisa.gp.model.DAO.AbbonamentoDS, it.unisa.gp.model.interfaceDS.Abbonamento,
	it.unisa.gp.model.DAO.VideogiocoDS, it.unisa.gp.model.interfaceDS.Videogioco, java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
	String roles = (String) session.getAttribute("roles");
	
	if(roles == null){
		response.sendRedirect(request.getContextPath() + "/login-form.jsp");
	} else if (roles.equals("admin") || roles.equals("assCl") || roles.equals("cliente")) {
		response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
	}

	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	Videogioco vidDS = new VideogiocoDS(ds);
	Abbonamento abbDS = new AbbonamentoDS(ds);
	SoftwareHouse softDS = new SoftwareHouseDS(ds);
	Collection<SoftwareHouseBean> collSoft = softDS.doRetrieveAll(null);
	Collection<VideogiocoBean> collVid = vidDS.doRetrieveAllExists(null);
	Collection<AbbonamentoBean> collAbb = abbDS.doRetrieveAllExists("costo");
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifica elemento in Catalogo</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="style/style.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="32x32" href="img/icon/favicon.png">
<script src="script/jquery-3.6.0.min.js"></script>
<script>
$(document).ready(function () {
	$("#tipo").change(function () {
		$.ajax({url:"", success: function(){
			var selectedSubject = $("#tipo option:selected").val()
			if(selectedSubject == "videogioco"){
				$("#videogSel").css("display", "inline");
				$("#abbSel").css("display", "none");
				$("#submit").css("display", "inline");
				$("#dati").html("");
			}else if(selectedSubject == "abbonamento"){
				
				$("#abbSel").css("display", "inline");
				$("#videogSel").css("display", "none");
				$("#submit").css("display", "inline");
				$("#dati").html("");
			}else{

				$("#pagina").html("<div id = 'pagina'>"
								+ "</div>");
				$("#abbSel").css("display", "none");
				$("#videogSel").css("display", "none");
				$("#submit").css("display", "none");
				$("#dati").html("");
			}
		}});
		
	});
	
	$("#videogSelect").change(function () {
		$.ajax({url:"", success: function(){
			var selectedSubject = $("#videogSelect option:selected").val();
			
			$("#dati").html("<div class='row'>"
						+"<div class='col-md-6 mb-3'>"
							+ "<label for='nome-vid'>Nome: </label>"
							+ "<input type='text' class='form-control'  id='nome-vid' name='nome-vid' required>"
						+ "</div>"
						
					+ "</div>"
					+ "<div class='row'>"
						+ "<div class='col-md-6 mb-3'>"
							+ "<label for='dim-vid'>Dimensione (GB): </label>"
							+ "<input type='text' class='form-control' id='dim-vid' name='dim-vid' required>"
						+ "</div>"
						+ "<div class='col-md-6 mb-3'>"
							+ "<label for='annoProd'>Anno produzione: </label>"
							+ "<input type='text' class='form-control' id='annoProd' name='annoProd' required>"
						+ "</div>"	
					+ "</div>"	
					+ "<div class='row'>"	
						+ "<div class='col-md-6 mb-3'>"
							+ "<label for='costo'>Costo: </label>"
							+ "<input type='text' class='form-control' id='costo' name='costo' required>"
						+ "</div>"
						+ "<div class='col-md-6 mb-3'>"
							+ "<label for='pegi'>Pegi: </label>"
							+ "<select name='pegi' id='pegi' class='form-control'>"
								+ "<option value='tre'>tre</option>"
								+ "<option value='sette'>sette</option>"
								+ "<option value='dodici'>dodici</option>"
								+ "<option value='sedici'>sedici</option>"
								+ "<option value='diciotto'>diciotto</option>"
							+ "</select>"
						+ "</div>"
					+ "</div>"
					
				+ "</div>");
			
			//$('#nome-vid').val('');
			//$('#cod-vid').val('1000');
			//$('#dim-vid').val('1000');
			//$('#annoProd').val('1000');
			//$('#costo').val('1000');
			//$('#pegi').val('1000');
		}});
		
	});
	
	$("#abbSelect").change(function () {
		$.ajax({url:"", success: function(){
			var selectedSubject = $("#abbSelect option:selected").val();
			$("#dati").html(""
				+ "<div class='row'>"	
					+ "<div class='col-md-6 mb-3'>"
						+ "<label for='costo'>Costo: </label>"
						+ "<input type='text' class='form-control' id='costo' name='costo' required>"
					+ "</div>"
					+ "<div class='col-md-6 mb-3'>"
						+ "<label for='durata'>Durata: </label>"
						+ "<input type='text' class='form-control' id='durata' name='durata' required>"
					+ "</div>"
				+ "</div>"
				+ "<br/>");
			//$('#nome-abb').val('');
			//$('#costo').val('1000');
			//$('#durata').val('1000');
		}});
		
	});
});

function validateNomeAbb(){
	
	let text = document.getElementById("nome-abb").value;

	let pattern = / pass/i;

	let result = pattern.test(text);
	
	return result;

}

function validate(){
	var tipo = document.getElementById("tipo").value;
	if(tipo == "videogioco"){
		
	}else if(tipo == "abbonamento"){
		if(validateNomeAbb() == true){
			
		}else{
			
			alert("errore nome abbonamento");
			return false;
		}
	}
	return true;
}
</script>
</head>
<body>
	<div id ="cont" class="container">
		<h2>Modifica in Catalogo</h2>
		<br/><br/>
		
			<form action="ModInCatServlet" method="post" onsubmit="return validate()">
				<div>
					<label for="tipo">Tipologia:</label>
					<select name="tipo" id="tipo" class=' col-md-6 mb-3 form-control '>
						<option value="null" selected>-</option>
						<option value="videogioco">videogioco</option>
						<option value="abbonamento">abbonamento</option>
					</select>
				</div>
				<div id="videogSel" style="display:none">
					<label for="videogSelect">Scegli videogioco:</label>
					<select name="videogSelect" id="videogSelect" class=' col-md-6 mb-3 form-control '>
						<%
							for(VideogiocoBean vidBean: collVid){
						%>
						<option value="<%= vidBean.getCodice() %>"><%= vidBean.getNomeVideogioco() %></option>
						<%
							}
						%>
					</select>
				</div>
				<div id="abbSel" style="display:none">
					<label for="abbSelect">Scegli abbonamento:</label>
					<select name="abbSelect" id="abbSelect" class=' col-md-6 mb-3 form-control '>
						<%
							for(AbbonamentoBean abbBean: collAbb){
						%>
						<option value="<%= abbBean.getNomeUnivoco() %>"><%= abbBean.getNomeUnivoco() %></option>
						<%
							}
						%>
					</select>
				</div>
				<br/>
				<div id = "pagina">	
				
				
				</div>
				<div id = "dati">
				
				
				</div>
				
				<input id = "submit" type = "submit" value = "Modifica" class="btn btn-primary btn-block" style="display:none">
			</form>
		</div>
		
</body>
</html>