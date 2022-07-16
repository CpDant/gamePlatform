<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="style/style.css" rel="stylesheet">
</head>
<body>
<div class="col d-flex justify-content-center mb-2">
<form action="LoginServlet" method="post"> 
	<br/><br/><br/><br/><br/><br/><br/>
	<div class="form-outline mb-4">
		 <label class="form-label" for="email">Email</label>
	     <input id="email" type="email" name="email" class="form-control" placeholder="email">
	     
	</div> 
	<div class="form-outline mb-4">
		 <label class="form-label" for="password">Password</label> 
	     <input id="password" type="password" name="password" class="form-control" placeholder="password">
	    
	</div>
	<input type="submit" class="btn btn-primary btn-block mb-4" value="Login"/>
    <input type="reset" class="btn btn-danger btn-block mb-4" value="Reset"/>
	
	
	
	<div class="text-center">
   		 <p>Non sei registrato? <a href="/gamePlatformSite/signup-form.jsp">Registrati</a></p>
    </div>
     
</form>
</div>

</body>
