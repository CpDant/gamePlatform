<%@ page language="java" import="javax.sql.DataSource,it.unisa.gp.model.bean.ClientiBean,java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
	String roles = (String) session.getAttribute("roles");
	
	if(roles == null){
		response.sendRedirect(request.getContextPath() + "/login-form.jsp");
	}else if (roles.equals("cliente")) {
		
	}else{
		response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
	}
	
	
%>    
    
    

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Contattaci</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="32x32" href="img/icon/favicon.png">
<link href="style/style.css" rel="stylesheet">


</head>
<body>

<script src="script/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="../fragments/header.jsp" %>
	<div class="container page">
		<h2>Contatta l'assistenza</h2>
		<br/>
		<form action="CreazioneTicket" method="post">
			<div class="row">
				<div class="col-md-6 mb-3">
					<label for="textArea">Descrizione problema</label>
					<textarea class="form-control" id="textArea" name="textArea" rows="7"></textarea>
				</div>
				<div class="col-md-6 mb-3">
						<label for="problema">Problema riscontrato</label> <br/> 
						<select name="problema" id="problema" class="form-control">
							<option value="pagamenti" id="pagamenti">pagamenti</option>
							<option value="account" id="account">account</option>
							<option value="rimborso" id="rimborso">rimborso</option>
							<option value="abbonamenti" id="abbonamenti">abbonamenti</option>
						</select>
				</div>
			</div>
			<input type="submit" value="Apri ticket" class="btn btn-block btn-primary"/>
	
	
	
	
	
		</form>
	</div>
	<%@ include file="../fragments/footerReg.jsp" %>
</body>

</html>