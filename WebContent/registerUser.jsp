<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
  			<form action="registerUser" method="post">
  				<input type="text" name="imie" placeholder="imie" />
            	<input type="text" name="nazwisko" placeholder="nazwisko" />
            	<input type="text" name="email" placeholder="email" />
            	<input type="text" name="userId" placeholder="login" />
            	<input type="password" name="password" placeholder="haslo" />
            	<div class="wrapper">
            	    <input type="submit" value="Zarejestruj"/>
            	</div>
        	</form>
    	</div>
	</div>
</body>
</html>
