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
  
            			<div class="form-register">
        				<div class="t">Wypelnij formularz:</div>
        				<div class="error">${rekordy}</div>
							<form action="register" method="post">
								<input type="hidden" name="typeOfUser" value="pacjent" />
								<div class="textplace">Imie:</div>
								<input type="text" name="imie"/>
								<div class="textplace">Nazwisko:</div>
			        			<input type="text" name="nazwisko"/>
			        			<div class="textplace">PESEL:</div>
			        			<input type="text" name="PESEL"/>
			        			<div class="textplace">Email:</div>
			        			<input type="text" name="email"/>
			        			<div class="textplace">Login:</div>
			        			<input type="text" name="login"/>
			        			<div class="textplace">Haslo:</div>
			        			<input type="password" name="password"/>
        						<div class="wrapper">
        		    				<input type="submit" value="Zarejestruj"/>
        						</div>
    						</form>
						</div>
	</div>
</body>
</html>