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
<script src="jquery-3.6.0.min.js"></script>
<script>

	$.ajax({
		$('select').click(function() {
			if($(this).val() == 'videogioco'){
				$("div.container").html("ciao");
			}
		}) 
	})

</script>
</head>
<body>
	<div class="container">
		<h2>Aggiungi in Catalogo</h2>
		<br/><br/>
		<form action="#" method="post">
			<div>
				<label for="tipo">Tipologia:</label>
				<select name="tipo" onchange="sceltaTipologia()">
					<option value="null" selected>-</option>
					<option value="videogioco">videogioco</option>
					<option value="abbonamento">abbonamento</option>
				</select>
			</div>
			<br/>	
			<div>
				<label for="nome-vid">Nome: </label>
				<input type="text" id="nome-vid" name="nome-vid" required>
			</div>
			<br/>
			<div>
				<label for="cod-vid">Codice: </label>
				<input type="text" id="cod-vid" name="cod-vid" required>
			</div>	
			<br/>
			<div>
				<label for="softHouse">Software House: </label>
				<select name=softHouse>
					<%
						for(SoftwareHouseBean softBean: collSoft){
					 %>
						<option value="<%=softBean.getNomeUnivoco()%>"><%=softBean.getNomeUnivoco()%></option>
					<%
						}
					 %>
				</select>
			</div>	
			<br/>	
			<div>
				<label for="dim-vid">Dimensione: </label>
				<input type="text" id="dim-vid" name="dim-vid" required>
			</div>
			<br/>	
			<div>
				<label for="annoProd">Anno produzione: </label>
				<input type="text" id="annoProd" name="annoProd" required>
			</div>
			<br/>	
			<div>
				<label for="costo">Costo: </label>
				<input type="text" id="costo" name="costo" required>
			</div>
			<br/>
			<div>
				<label for="pegi">Pegi: </label>
				<select name="pegi">
					<option value="tre">tre</option>
					<option value="sette">sette</option>
					<option value="dodici">dodici</option>
					<option value="sedici">sedici</option>
					<option value="diciotto">diciotto</option>
				</select>
			</div>
			
			<br/>
			<br/>
			<br/>
			<br/>
			
			<div>
				<label for="nome-abb">Nome: </label>
				<input type="text" id="nome-abb" name="nome-abb" required>
			</div>
			<br/>
			<div>
				<label for="costo">Costo: </label>
				<input type="text" id="costo" name="costo" required>
			</div>
			<br/>
			<div>
				<label for="durata">Durata: </label>
				<input type="text" id="durata" name="durata" required>
			</div>
				
			
		</form>
	</div>
</body>
</html>