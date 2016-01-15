<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
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
	        		login = cookie[i].getValue();
	        		sesja++;
	        	}
	        	else if(cookie[i].getName().equals("type") )
	       		{
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
			<div class="content" name="specjalnosc" >
			
				<div class="searchSpecA">
					<form action="znajdzLekarza" method="get">
					<input type="hidden" name="typeOfSearch" value="noName" />
					<input type="hidden" value="1" name="mode">
						Specjalizacja: <select name="specjalizacja">
							<option disabled selected value="wybierz">wybierz</option>
  							<option value="chirurg">Chirurg</option>
 							<option value="dermatolog">Dermatolog</option>
  							<option value="internista">Internista</option>
  							<option value="Logopeda">Logopeda</option>
						</select></br>
						Miasto: <input type="text" name="miasto" placeholder="wpisz"/></br>
            	    <input type="submit" value="Szukaj"/>
					</form>
				</div>
				
				<div class="searchSpecB">
					<form action="znajdzLekarza" method="get">
					<input type="hidden" name="typeOfSearch" value="byName" />
					<input type="hidden" value="1" name="mode">
						Imie:
						<input type="text" name="imie" placeholder="wpisz"/><br>
						Nazwisko:
						<input type="text" name="nazwisko" placeholder="wpisz"/><br>
            	    <input type="submit" value="Szukaj"/>
					</form>
				</div>	
				<div class="results">
				<%
				if(request.getParameter("mode") != null)
				{
					System.out.print("bumszakalaka:"+request.getParameter("mode"));
					if(Integer.valueOf(request.getParameter("mode")) == 1)
					{
						if(request.getAttribute("label") != null)
						{%>
							<div class = "label"><%out.println(request.getAttribute("label"));%></div>
						<%}
							if(request.getAttribute("rekordy") != null)
							{
								ArrayList<String> rekordy = new ArrayList<String>(40);
								rekordy = (ArrayList<String>) request.getAttribute("rekordy");
								int licznik = 0;
		
								for(int i=0;i<rekordy.size();i++)
								{
									if(i < 5 && licznik == 4)
									{
										%><br><% 
										licznik = 0;	
									}
									if(i > 4 && licznik == 5)
									{
										%><br><% 
										licznik = 0;	
									}
									if(i > 3)
									{
										if(licznik == 0)
										{%>
											<form action="showSpecialist" method="post">
											<input type="hidden" value="2" name="mode">
											<input type="hidden" value="<%=rekordy.get(i)%>"  name = "id_specjalisty"/>
										<%
										licznik++;
										continue;
										}
										if(licznik == 1)
										{%>
											<div class = "record"><input type="submit" value="<%=rekordy.get(i)%>"  name = "name"/></div>
											</form>
										<%
										licznik++;
										continue;
										}
												
									}%>
									<div class = "record"><%out.println(rekordy.get(i));%></div><%
									licznik++;
								}
							}
						}
					else if(Integer.valueOf(request.getParameter("mode")) == 2)
					{
						ArrayList<String> rekordy = new ArrayList<String>(8);
						rekordy = (ArrayList<String>) request.getAttribute("spec");
						for(int i=0;i<rekordy.size();i++)
						{
							out.print(rekordy.get(i));
						}
					}
				}
				%>
				</div>
			</div>
			
		</div>
	</body>
</html>