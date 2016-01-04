<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Style/style.css" type="text/css"/>
<title>System asygnacji i segregacji pacjentow</title>
<%   
   Cookie firstName = new Cookie("login", request.getParameter("login"));
   Cookie lastName = new Cookie("type", request.getParameter("type"));

   firstName.setMaxAge(60*60*24); 
   lastName.setMaxAge(60*60*24); 
   
   response.addCookie( firstName );
   response.addCookie( lastName );
%>
</head>
	<body>
		<div class="container">
			<div class="header">
				<div class="logo">SAISP.pl</div>
				<form action="register.jsp">
				<%
				//	if(firstName.)
				%>
				<input type="submit" class="signin" value="Sign in"/>
				</form>
				<form action="login.jsp">
				<input type="submit" class="login" value="Log in"/>
				</form>
			</div>
			<div class="menu">
				<ol>
				<li><a href="index.jsp">Strona glowna</a></li>
				<li><a href="znajdzLekarza.jsp">Znajdz lekarza</a></li>
				<li><a href="googleMaps.jsp">Znajdz placowke</a></li>
				<li><a href="oAutorach.jsp">O Autorach</a></li>
			
				</ol>
			</div>
			<div class="content">
			<p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium,
			 totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae 
			 dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit,
			  sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est,
			   qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi 
			   tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, 
			   quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi 
			   consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae
			    consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?</p>
			   
			</div>
		</div>
	</body>
</html>