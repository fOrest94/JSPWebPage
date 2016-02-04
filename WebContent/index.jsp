<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="javax.servlet.http.Cookie"%>
<%@page import="java.io.IOException"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Style/style.css" type="text/css"/>
<title>System asygnacji i segregacji pacjentow</title>
<%! 
	String login;
	String type;
	
	boolean sprawdzCiasteczko(HttpServletRequest request)
	{
		Cookie[] cookie;
		cookie = request.getCookies();
		int sesja=0;
	  
		if(cookie != null) 
	 	{
	    	for(int i =0 ; i<cookie.length ; i++)
	    	{
	        	if(cookie[i].getName().equals("userBean"))
	       		{
	        		System.out.println("pl"+cookie[i].getValue()+"pl");
	        		login = cookie[i].getValue();
	        		sesja++;
	        	}
	        	else if(cookie[i].getName().equals("type") )
	       		{
	        		System.out.println("xd"+cookie[i].getValue()+"xd");
	        		type = cookie[i].getValue();
	        		sesja++;
	        	}
	        	System.out.println(cookie[i].getValue()+"*"+cookie[i].getName()+"**");
	    	}
	    	System.out.println(sesja+" *** ewr3rewrwe");
	    	if(sesja == 2)
	    	{
	    		return true;
	    	}
	 	}
		return false; 
	}
%>
	
</head>
	<body>
		<div class="container">
			<div class="header">
				<div class="logo">SAISP.pl</div>
				<form action="register.jsp">
				<input type="submit" class="signin" value="Sign in"/>
				</form>
		<% 		if(sprawdzCiasteczko(request))
				{%>
				<form action="logout" method="post">
				<input type="hidden" value="1" name="mode" id="mode">
				<input type="submit" class="login" value="Log out"/>
				</form>
					<form action="showProfile" method="post">
					<input type="hidden" value="2" name="mode" id="mode">
					<input type="hidden" value=<%= login %> name="login">
					<input type="hidden" value=<%= type %> name="typeOfUser">
					<input type="submit" class="user-panel" value="Moj profil"/>
					</form>
			<%	}
			 	else
				{%>
				<form action="login.jsp">
				<input type="submit" class="login" value="Log in"/>
				</form>
				<% 
				}
				%>
			</div>
			<div class="menu">
				<ol>

				<li><a href="index.jsp">Strona glowna</a></li>
				<li><a href="znajdzLekarza.jsp">Znajdz lekarza</a></li>
				<li><a href="googleMaps.jsp">Znajdz placowke</a></li>
				<li><a href="oAutorach.jsp">O Nas</a></li>
			
				</ol>
			</div>
			<div class="content">
			<h3>Witamy na stronie Systemu Asygnacji i segregacji pacjentow</h3>
			<div class="mainLeft"><img src="D:/Apki Java 5 semestr/WebApplication/WebContent/Style/L.jpg" />
			<div class="p">                                      <br>Szukasz najlepszej opieki medycznej? <br>
			Tutaj ja znajdziesz! Nasz serwis <br>zajmuje sie zrzeszaniem specjalistow <br>
			i placowek medycznych z calej Polski. <br>Bez kolejek, Bezpiecznie,Bezplatnie</div>
			</div>
			<div class="mainRight"><img src="D:/Apki Java 5 semestr/WebApplication/WebContent/Style/R.jpg" >
			<div class="p">                                      <br>Grupa LUX MED to najwieksza w Polsce siec prywatnych centrow medycznych.
			 Pod markami LUX MED i Medycyna Rodzinna prowadzimy 87 placowek. Wspolpracujemy rowniez z placowkami partnerskimi na terenie calego kraju.</div>
			</div>
			   
			</div>
		</div>
	</body>
</html>