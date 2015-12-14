<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8d859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Style/loginStyle.css" type="text/css"/>
<title>Login Page</title>
</head>
<body>
	
	<div class="container">
		<div class="form-login">
            <h4>Wpisz dane logowania</h4>
  			<form action="login" method="post">
            	<input type="text" name="userId" placeholder="login" />
            	<input type="password" name="password" placeholder="haslo" />
            	<div class="wrapper">
            	    <input type="submit" value="Zaloguj"/>
            	</div>
        	</form>
    	</div>
	</div>
</body>
</html>
