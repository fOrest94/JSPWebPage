<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8d859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Style/loginStyle.css" type="text/css"/>
<title>Register</title>
</head>
<body>
	
	<div class="container">
		<div class="form-login">
            <h4>Wypelnij formularz:</h4>
  			<form action="registerMedic" method="post">
  				<input type="text" name="imie" placeholder="imie" />
            	<input type="text" name="nazwisko" placeholder="nazwisko" />
            	<input type="text" name="email" placeholder="email" />
            	<input type="text" name="userId" placeholder="login" />
            	<input type="password" name="password" placeholder="haslo" />
            	<input type="text" name="specjalizacja" placeholder="specjalizacja" />
            	<input type="text" name="miasto" placeholder="miasto" />
            	<div class="wrapper">
            	    <input type="submit" value="Zarejestruj"/>
            	</div>
        	</form>
    	</div>
	</div>
</body>
</html>