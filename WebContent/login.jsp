<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8d859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Style/loginStyle.css" type="text/css"/>
<title>Log in</title>
</head>
<body>
	
	<div class="container">
		<div class="form-login">
            <h4>Wpisz dane logowania</h4>
  			<form action="login" method="post">
  				<select name="typeOfUser" id="typeOfUser"">
			        <option selected value="admin">Wybierz:</option>
			        <option value="Pacjent">Pacjent</option>
			        <option value="Specjalista">Specjalista</option>
	    		</select>
	    		<input type="hidden" value="0" name="mode" id="mode">
	    		<div class="error">${rekordy}</div>
  				<div class="textplace">Login:</div>
            	<input type="text" name="userId"/>
            	<div class="textplace">Haslo:</div>
            	<input type="password" name="password"/>
            	<div class="wrapper">
            	    <input type="submit" value="Zaloguj"/>
            	</div>
        	</form>
    	</div>
	</div>
</body>
</html>
