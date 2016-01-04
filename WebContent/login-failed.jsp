<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Blad logowania</title>
</head>
<body>
<%
	out.println("<h3>Blad logowania</h3>");
System.out.println("Bylem w faioledzie");
RequestDispatcher rd = request.getRequestDispatcher("login.jsp");  
rd.forward(request, response);  
if(true)
Thread.sleep(5000);
%>
</body>
</html>