<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Registrazione</title>
</head>
<body>

<form action="SignUpClienteServlet" method="post"> 
<fieldset>
     <legend>Registrazione</legend>
     <label for="codFis">Codice Fiscale</label>
     <input id="codFis" type="text" name="codFis" placeholder="codice fiscale" required>  
     <br>   
     <label for="nome">Nome</label>
     <input id="nome" type="text" name="nome" placeholder="nome" required> 
     <br>
     <label for="cognome">Cognome</label>
     <input id="cognome" type="text" name="cognome" placeholder="cognome" required> 
     <br>   
     <label for="data">Data</label>
     <input id="data" type="date" name="data" placeholder="data" required> 
     <br>     
     <label for="email">Email</label>
     <input id="email" type="email" name="email" placeholder="email" required> 
     <br>
     <label for="password">Password</label>
     <input id="password" type="password" name="password" placeholder="password" required> 
     <br>
     <label for="username">Username</label>
     <input id="username" type="text" name="username" placeholder="username" required> 
     <br>     
     <label for="indFatt">Indirizzo di fatturazione</label>
     <input id="indFatt" type="text" name="indFatt" placeholder="indirizzo di fatturazione" required> 
     <br>
     
     <label for="tel1">Numero telefono 1</label>
     <input id="tel1" type="tel" name="tel1" placeholder="telefono 1" required> 
     <br>     
     <label for="tel2">Numero telefono 2</label>
     <input id="tel2" type="tel" name="tel2" placeholder="telefono 2"> 
     <br>
     
     
     <input type="submit" value="Registrati"/>
     <input type="reset" value="Reset"/>
</fieldset>
</form>

</body>
</html>