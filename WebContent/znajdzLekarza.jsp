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
			<div class="content" name="specjalnosc" >
			
				<div class="searchSpecA">
					<form action="znajdzLekarza" method="get">
					<input type="hidden" name="typeOfSearch" value="noName" />
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
						<label for="Imie">Imie:  </label>
						<input type="text" name="imie" placeholder="wpisz"/></br>
						<label for="Nazwisko">Nazwisko:</label>
						<input type="text" name="nazwisko" placeholder="wpisz"/></br>
            	    <input type="submit" value="Szukaj"/>
					</form>
				</div>	
				<%@page  %>
				<div class="results">
				<%
				if(request.getAttribute("label") != null)	
					out.println(request.getAttribute("label"));
					if(request.getAttribute("rekordy") != null)
					{
						ArrayList<String> rekordy = new ArrayList<String>(40);
						rekordy = (ArrayList<String>) request.getAttribute("rekordy");	
						for(int i=0;i<rekordy.size();i++)
						{
							if(i%4 == 0)
							%><br>
							<% 
							out.println(rekordy.get(i));
						}
					}
				%>
				</div>
			</div>
			
		</div>
	</body>
</html>