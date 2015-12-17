<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/Style/loginStyle.css" type="text/css"/>
		<title>RegisterType</title>
	</head>
<body>
	<%
            if(request.getParameter("typeOfUser") != null) 
            {
                if(request.getParameter("typeOfUser").equals("Specjalista")) 
                {
                	response.sendRedirect("registerMedic.jsp");
                }
                else if(request.getParameter("typeOfUser").equals("Pacjent")) 
                {
                	response.sendRedirect("registerUser.jsp");
                } 
            }
        %>
	<div class="container">
		<div class="form-login">
            <h4>Rejestrujesz sie jako:</h4>
            	<form action="register.jsp" METHOD="post">
  					<input type="radio" value="Specjalista" name="typeOfUser" />Specjalista<br/>
            		<input type="radio" value="Pacjent" name="typeOfUser"/>Pacjent<br/>
            		<div class="wrapper">
            	  	  <input type="submit" value="Zarejestruj"/>
            		</div>
				</form>
		</div>
	</div>
</body>
</html>