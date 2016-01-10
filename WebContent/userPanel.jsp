<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%
	String login = request.getParameter("login");
	String type = request.getParameter("type");
%>

</head>
<body>
	<form action="userPanel" method="post">
	<input type="hidden" value=<%= login %> name="login" id="mode">
	<input type="hidden" value=<%= type %> name="haslo" id="mode">
	</form>
<h1>Dudek chuju zapierdalaj</h1>
</body>
</html>