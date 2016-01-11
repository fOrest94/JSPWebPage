<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="javax.servlet.http.Cookie"%>
<%@page import="java.io.IOException"%>
<%@page import="java.util.ArrayList"%>
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
				<li><a href="oAutorach.jsp">O Autorach</a></li>
			
				</ol>
			</div>
			<div class="content">
				<form action="ziomek" method="post">
					<input type="hidden" value="3" name="mode" >
					<input type="hidden" value=<%= request.getParameter("login") %> name="login" >
					<input type="hidden" value=<%= request.getParameter("typeOfUser") %> name="type" >
					<input type="hidden"  onchange="submit();">
				</form>
			<div class="user_profil_left_side">
			fdgdfgdf
			</div>
			<div class="user_profil_right_side">
				<%
					
					out.println("OMG wypisz to : "+request.getAttribute("label"));
					if(request.getAttribute("rekordy") != null)
					{
						ArrayList<String> rekordy = new ArrayList<String>(40);
						rekordy = (ArrayList<String>) request.getAttribute("rekordy");	
						for(int i=0;i<rekordy.size();i++)
						{
							out.println(rekordy.get(i));
						}
					}
				%>
				</div>
			</div>
		</div>
	</body>
</html>