<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Registrazione</title>
</head>

<script>

	function checkCodFis(inputtxt) {
	    var codice = /^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$/;
	    if (inputtxt.value.match(codice))
	        return true;
	
	    return false;
	}

	function checkNamesurname(inputtxt) {
		var name = /^[A-Za-z]+$/;
		if (inputtxt.value.match(name))
			return true;

		return false;
	}
	
	function checkEmail(inputtxt) {
		var email = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		if(inputtxt.value.match(email)) 
			return true;
		
		return false;	
	}
	
    function checkPassword(inputtxt) {
        var password = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}/;
        if (inputtxt.value.match(password))
            return true;

        return false;
    }
	
	function checkPhonenumber(inputtxt) {
		var phoneno = /^([0-9]{10})$/;
		if(inputtxt.value.match(phoneno)) 
			return true;
		
		return false;
	}

	function checkAddress(inputtxt) {
		var letters = /(Via|Piazza|Corso) [a-zA-Z']+(| [a-zA-Z']+) [0-9]{1,3}/g;
		if (inputtxt.value.match(letters))
			return true;
		
		return false;

	}	
	
	function validate(obj) {
		var valid = true;
		
		var codice = document.getElementsByName("codFis")[0];
			if (!checkCodFis(codice)) {
				valid = false;
				alert("Il codice fiscale è errato");
				codice.focus();
			}
		
		var name = document.getElementsByName("nome")[0];
			if (!checkNamesurname(name)) {
				valid = false;
				alert("Il nome può contenere solo lettere");
				name.focus();
			}

		var surname = document.getElementsByName("cognome")[0];
			if(!checkNamesurname(surname)) {
				valid = false;
				alert("Il cognome può contenere solo lettere");
				surname.focus();
			}
		
		var email = document.getElementsByName("email")[0];
			if(!checkEmail(email)) {
				valid = false;
				alert("Indirizzo e-mail errato o non valido.");
				email.focus();
			}
		
		var pwd = document.getElementsByName("password")[0];
	        if(!checkPassword(pwd)) {
	            valid = false;
	            alert("La password deve contenere almeno un numero, una lettera maiuscola, minuscola e almeno 8 o più caratteri");
	            pwd.focus();
       		 }
		
		var address = document.getElementsByName("indFatt")[0];
			if (!checkAddress(address)) {
				valid = false;
				alert("L'indirizzo deve avere questo formato: <via/piazza/etc.> <nome> <numero civico/SNC>");
				address.focus();
			}

		
		
		var numbers1 = document.getElementsByName("tel1")[0];
			if(!checkPhonenumber(numbers1)) {
				valid = false;
				alert("Numero di telefono errato o non valido.");
				numbers1.focus();
			}
		
				

		
				
		return valid;
		
		
	}
</script>

<body>
<form action="SignUpClienteServlet" method="post" onsubmit="return validate(this)"> 
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
	     <label for="tel1">Numero telefono(formato: senza spazi)</label>
	     <input id="tel1" type="tel" name="tel1" placeholder="numero di telefono" required> 
	     <br>
	     
	     
	     <input type="submit" value="Registrati"/>
	     <input type="reset" value="Reset"/>
	</fieldset>
</form>

</body>
</html>