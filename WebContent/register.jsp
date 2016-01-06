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
		<div class="select-place">
    		<form action="register.jsp" method="post">
    		<div class="t">Rejestrujesz sie jako:</div>
	    		<select name="typeOfUser" id="typeOfUser" onchange="submit();">
			        <option selected value="Pacjent">Wybierz:</option>
			        <option value="Pacjent">Pacjent</option>
			        <option value="Specjalista">Specjalista</option>
	    		</select>
	    	</form>
		</div>
    	<%
            if(request.getParameter("typeOfUser") != null) 
            {
                if(request.getParameter("typeOfUser").equals("Specjalista")) 
                {
                	System.out.print("lekarz");
                	%>
                		<div class="form-register">
          				<div class="t">Wypelnij formularz:</div>
				  			<form action="register" method="post">
				  				<div class="textplace">Imie:</div>
				  				<input type="text" name="imie"/><br>
				  				<div class="textplace">Nazwisko:</div>
				            	<input type="text" name="nazwisko"/><br>
				            	<div class="textplace">Email:</div>
				            	<input type="text" name="email"/><br>
				            	<div class="textplace">Telefon:</div>
				            	<input type="text" name="telefon"/><br>
				            	<div class="textplace">Login:</div>
				            	<input type="text" name="login"/><br>
				            	<div class="textplace">Haslo:</div>
				            	<input type="password" name="password"/><br>
				            	<div class="textplace">Specjalizacja:</div>
				            	<input type="text" name="specjalizacja"/><br>
				            	<div class="textplace">Miasto:</div>
				            	<input type="text" name="miasto"/><br>
				            	<div class="wrapper">
				            	    <input type="submit" value="Zarejestruj"/>
				            	</div>
				        	</form>
				    	</div>
    				<%
                }
                else if(request.getParameter("typeOfUser").equals("Pacjent")) 
                {
                	System.out.print("pacjent");
                	%>
            			<div class="form-register">
        				<div class="t">Wypelnij formularz:</div>
							<form action="register" method="post">
								<div class="textplace">Imie:</div>
								<input type="text" name="imie"/>
								<div class="textplace">Nazwisko:</div>
			        			<input type="text" name="nazwisko"/>
			        			<div class="textplace">Nazwisko:</div>
			        			<input type="text" name="PESEL"/>
			        			<div class="textplace">Email:</div>
			        			<input type="text" name="email"/>
			        			<div class="textplace">Login:</div>
			        			<input type="text" name="userId"/>
			        			<div class="textplace">Haslo:</div>
			        			<input type="password" name="password"/>
        						<div class="wrapper">
        		    				<input type="submit" value="Zarejestruj"/>
        						</div>
    						</form>
						</div>
					<%
                } 
            }
        %>
	</div>
</body>
</html>