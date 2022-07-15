<%@ page language="java" import="javax.sql.DataSource,it.unisa.gp.model.bean.SoftwareHouseBean,
	it.unisa.gp.model.DAO.SoftwareHouseDS, it.unisa.gp.model.interfaceDS.SoftwareHouse, java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	SoftwareHouse softDS = new SoftwareHouseDS(ds);
	Collection<SoftwareHouseBean> collSoft = softDS.doRetrieveAll(null);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aggiungi in Catalogo</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="style/style.css" rel="stylesheet">
<script src="script/jquery-3.6.0.min.js"></script>
<script>

$(document).ready(function () {
	$("#tipo").change(function () {
		$.ajax({url:"", success: function(){
			var selectedSubject = $("#tipo option:selected").val()
			if(selectedSubject == "videogioco"){
				$("#pagina").html("<div class='row'>"
										+"<div class='col-md-6 mb-3'>"
											+ "<label for='nome-vid'>Nome: </label>"
											+ "<input type='text' class='form-control'  id='nome-vid' name='nome-vid' required>"
										+ "</div>"
										+"<div class='col-md-6 mb-3'>"
											+ "<label for='cod-vid'>Codice: </label>"
											+ "<input type='text' class='form-control' id='cod-vid' name='cod-vid' required>"
										+ "</div>"
									+ "</div>"
									+ "<div class='row'>"
										+ "<div class='col-md-6 mb-3'>"
											+ "<label for='softHouse'>Software House: </label>"
											+ "<select name='softHouse' id='softHouse' class='form-control'>"
											<%
												for(SoftwareHouseBean softBean: collSoft){
											 %>
												+ "<option value='<%=softBean.getNomeUnivoco()%>'><%=softBean.getNomeUnivoco()%></option>"
											<%
												}
											 %>
											+ "</select>"
										+ "</div>"
										+ "<div class='col-md-6 mb-3'>"
											+ "<label for='dim-vid'>Dimensione: </label>"
											+ "<input type='text' class='form-control' id='dim-vid' name='dim-vid' required>"
										+ "</div>"
									+ "</div>"	
									+ "<div class='row'>"	
										+ "<div class='col-md-6 mb-3'>"
											+ "<label for='annoProd'>Anno produzione: </label>"
											+ "<input type='text' class='form-control' id='annoProd' name='annoProd' required>"
										+ "</div>"	
										+ "<div class='col-md-6 mb-3'>"
											+ "<label for='costo'>Costo: </label>"
											+ "<input type='text' class='form-control' id='costo' name='costo' required>"
										+ "</div>"
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
									+ "<div class='row'>"
										+ "<div class='col-md-6 mb-3'>"
											+ "<label for='inputImageVid'>Immagine del videogioco:</label>"
											+ "<input type='file' class='form-control' id='inputImage' name='inputImage'>"
										+ "</div>"
									+ "</div>"
								+ "</div>");
				$("#submit").css("display", "inline");
			}else if(selectedSubject == "abbonamento"){
				$("#pagina").html("<div class='col-md-6 mb-3'>"
									+ "<label for='nome-abb'>Nome (formato: nomeAbbonamento Pass): </label>"
									+ "<input type='text' class='form-control' id='nome-abb' name='nome-abb' required>"
								+ "</div>"
								+ "<div class='col-md-6 mb-3'>"
									+ "<label for='costo'>Costo: </label>"
									+ "<input type='text' class='form-control' id='costo' name='costo' required>"
								+ "</div>"
								+ "<div class='col-md-6 mb-3'>"
									+ "<label for='durata'>Durata: </label>"
									+ "<input type='text' class='form-control' id='durata' name='durata' required>"
								+ "</div>"
								+ "<div class='row'>"
									+ "<div class='col-md-6 mb-3'>"
										+ "<label for='inputImageAbb'>Immagine dell'abbonamento:</label>"
										+ "<input type='file' class='form-control' id='inputImage' name='inputImage'>"
									+ "</div>");
				$("#submit").css("display", "inline");
				
			}else{
				$("#pagina").html("<div id = 'pagina'>"
								+ "</div>");
				$("#submit").css("display", "none");
			}
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
		<h2>Aggiungi in Catalogo</h2>
		<br/><br/>
		
			<form action="AddInCatServlet" method="post" onsubmit="return validate()" enctype="multipart/form-data">
				<div>
					<label for="tipo">Tipologia:</label>
					<select name="tipo" id="tipo" class=' col-md-6 mb-3 form-control '>
						<option value="null" selected>-</option>
						<option value="videogioco">videogioco</option>
						<option value="abbonamento">abbonamento</option>
					</select>
				</div>
				<br/>
				<div id = "pagina">	
				
				
				</div>
				<input id = "submit" type = "submit" value = "Aggiungi" class="btn btn-primary btn-block" style="display:none">
			</form>
		</div>
		
</body>
</html>