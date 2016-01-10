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
	
	String login, haslo;
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
	        	else if(cookie[i].getName().equals("passWord") )
	       		{
	        		System.out.println("xd"+cookie[i].getValue()+"xd");
	        		haslo = cookie[i].getValue();
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
				<form action="userPanel" method="post">
				<input type="hidden" value="2" name="mode" id="mode">
				<input type="hidden" value="${login}" name="userId">
				<input type="hidden" value="${haslo}" name="password">
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