<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Style/style.css" type="text/css"/>
<title>System asygnacji i segregacji pacjentow</title>

<%! 
	boolean sprawdzCiasteczko(HttpServletRequest request)
    {
		Cookie[] cookie;
		cookie = request.getCookies();
      
		if(cookie != null) 
     	{
        	for(int i =0 ; i<cookie.length ; i++)
        	{
            	Cookie c = cookie[i];   
            	if(c.getName().equals("userBean") && c.getValue().length() > 4)
           		{
            		System.out.println(c.getValue());
                	return true;
            	}
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
				<form action="userPanel.jsp">
				<input type="submit" class="login" value="Log out"/>
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
			<p>Stronka zrobiona przez super ziomka Dudsona Rapera i jeszcze bardziej oldschoolowego G4CU!!</p>
			   
			</div>
		</div>
	</body>
</html>