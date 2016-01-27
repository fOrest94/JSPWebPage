<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javax.servlet.http.Cookie"%>
<%@page import="java.io.IOException"%>
<!DOCTYPE html>
<html>
<head lang="pl">

<meta charset="UTF-8">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="${pageContext.request.contextPath}/Style/style.css" type="text/css"/>
<link rel="stylesheet" href="{pageContext.request.contextPath}/Style/bootstrap.min.css"/>
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
	<body ng-app="mainModule" ng-controller="dupaCtrl">
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
										<input type="hidden" value="<%=request.getParameter("miejsce")%>"  name = "miejsce"/>			
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
					{%>
						<div class = "show_spec_left_side">
							<div class = "up">ZDJECIE</div>
							<div class = "down">
								<form action="setWizyta" method = "post">
								<input type="hidden" value="3" name="mode"/>
								<input type="hidden" value="<%= login %>" name="login"/>
								<input type="hidden" value="<%= type %>" name="type"/>
								<input type="hidden" value=<%= request.getParameter("id_specjalisty")%> name="id_specjalisty" />
								<input type="submit" class="wizyta" value="Umow Wizyte"/>	
										
							</div>
						</div><%
						%><div class = "show_spec_right_side"><%
						ArrayList<String> rekordy = new ArrayList<String>(8);
						rekordy = (ArrayList<String>) request.getAttribute("spec");
						for(int i=0;i<rekordy.size();i++)
						{
							if(i==0)
							{
								out.println("<p>Imie: "+rekordy.get(i)+"</p>");
								%><input type="hidden" value="<%= rekordy.get(i)%>" name="imie" /><%
							}
							if(i==1)
							{
								out.println("<p>Nazwisko: "+rekordy.get(i)+"</p>");
								%><input type="hidden" value="<%= rekordy.get(i)%>" name="nazwisko" /><%
							}
							if(i==2)
								out.println("<p>Email: "+rekordy.get(i)+"</p>");
							if(i==3)
								out.println("<p>Telefon: "+rekordy.get(i)+"</p>");
							if(i==4)
							{
								out.println("<p>Specjalizacja: "+rekordy.get(i)+"</p>");
							%><input type="hidden" value="<%= rekordy.get(i)%>" name="specjalizacja" />
							<%}
							if(i==5)
							{
								out.println("<p>Miasto: "+rekordy.get(i)+"</p>");
								%><input type="hidden" value="<%= rekordy.get(i)%>" name="miejsce" />
								</form>
							<%}
							if(i==6)
							{
								%><p>Opis:</p><div class="spec_describe"><%out.println(rekordy.get(i)); %></div><%
							}
						}
						%></div><%
					}
					else if(Integer.valueOf(request.getParameter("mode")) == 3)
					{
						%>
						<div class="wizytaForm">
						<form action="sendWizyta" method="post">
							<input type="hidden" value="4" name="mode"/>
							<input type="hidden" value="<%= (String)request.getAttribute("PESEL")%>" name="PESEL" />
							<input type="hidden" value="<%= (String)request.getAttribute("id_specjalisty")%>" name="id_specjalisty" />
							<p>Specjalista : <% out.println(request.getParameter("imie")+" "+request.getParameter("nazwisko")); %></p>
							<p>PESEL : <% out.println((String)request.getAttribute("PESEL"));%></p>
								
								<p>Typ wizyty : <select name="typWizyty" >
									<option disabled selected value="wybierz">wybierz</option>
		  							<option value="prywatna">Prywatna</option>
		 							<option value="publiczna">Narodowy Fundusz Zdrowia</option>
		  							</select></br>
		  						</p>
							<p>Miejsce wizyty: <% 
							
							ArrayList<String> rekordy = new ArrayList<String>(40);
							rekordy = (ArrayList<String>) request.getAttribute("placeInfo");
							for(int i = 0 ;i < rekordy.size();i++)
							{
								out.println(rekordy.get(i));
								%><input type="hidden" value="<%= rekordy.get(i) %>" name="miejsceWizyty" /><%
							}%>
							</p> 
							<p>Termin wizyty:
						      		<input ng-model="departureDate" type="text" name = "dataWizyty"
						             datepicker-popup="yyyy-MM-dd" datepickerOptions="dateOptions"
						             is-open="false" ng-click="data.isOpen = true"
						             class="validate form-control" READONLY required placeholder="wybierz">
							</p>
							<p>Dolegliwosci:<br> 
							<textarea name="textarea" style="width:350px; height:100px; margin-top: 10px;"></textarea></p>
							<input type="submit" class="wizyta" value="Umow Wizyte"/>
								
					  		</form>
							<script src="js/shared/angular.min.js"></script>
						  	<script src="js/shared/jquery-1.11.3.min.js"></script>
						 	<script src="js/shared/bootstrap.min.js"></script>
						  	<script src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.10.0.js"></script>
						  	<script src="js/mainModule.js"></script>
						  	<script src="js/controllers/dupaCtrl.js"></script>
						</div>
						<%}	
						}%>
			</div>
		</div>
	</body>
</html>