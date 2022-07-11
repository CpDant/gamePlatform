<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>

<form action="LoginServlet" method="post"> 
<fieldset>
     <legend>Login </legend>
     <label for="email">Email</label>
     <input id="email" type="email" name="email" placeholder="email"> 
     <br>   
     <label for="password">Password</label>
     <input id="password" type="password" name="password" placeholder="password"> 
     <br>
     <input type="submit" value="Login"/>
     <input type="reset" value="Reset"/>
</fieldset>
</form>

</body>
</html>