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
	        		login = cookie[i].getValue();
	        		sesja++;
	        	}
	        	else if(cookie[i].getName().equals("type") )
	       		{
	        		type = cookie[i].getValue();
	        		sesja++;
	        	}
	    	}
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
				
				<div class="user_profil_left_side">
					<div class="up">
						Tu bedzie zdjecie
					</div>
					<div class="down">
							<form action="userProfile" method="post">
							<input type="hidden" value="2" name="mode" id="mode">
							<input type="hidden" value=<%= request.getParameter("login") %> name="login">
							<input type="hidden" value=<%= request.getParameter("typeOfUser") %> name="typeOfUser">
							<input type="submit" class="ver_menu" value="Profil"/>
							</form>
							<form action="userProfile" method="post">
							<input type="hidden" value="1" name="mode" id="mode">
							<input type="hidden" value=<%= request.getParameter("login") %> name="login">
							<input type="hidden" value=<%= request.getParameter("typeOfUser") %> name="typeOfUser">
							<input type="submit" class="ver_menu" value="Wizyty"/>
							</form>
							<form action="userProfile" method="post">
							<input type="hidden" value="0" name="mode" id="mode">
							<input type="hidden" value=<%= request.getParameter("login") %> name="login">
							<input type="hidden" value=<%= request.getParameter("typeOfUser") %> name="typeOfUser">
							<input type="submit" class="ver_menu" value="Ustawienia"/>
							</form>
					</div>
				</div>
				<div class="user_profil_right_side">
					<div class="profile_content">
					<%
						
						if(Integer.valueOf(request.getParameter("mode")) == 2)
						{
							ArrayList<String> rekordy = new ArrayList<String>();
						
							if(request.getAttribute("label").equals("Pacjent") && request.getAttribute("label")!=null)
							{	
								if(request.getAttribute("rekordy") != null)
								{
									rekordy = (ArrayList<String>) request.getAttribute("rekordy");	
									for(int i=0;i<rekordy.size();i++)
									{
										if(i==0)		
									 		out.println("<p>Imie:"+rekordy.get(i)+"</p>");
										if(i==1)
											out.println("<p>Nazwisko:"+rekordy.get(i)+"</p>");
										if(i==2)
											out.println("<p>Email:"+rekordy.get(i)+"</p>");
										if(i==3)
											out.println("<p>Telefon:"+rekordy.get(i)+"</p>");
										if(i==4)
											out.println("<p>Login:"+rekordy.get(i)+"</p>");
										if(i==5)
											out.println("<p>Haslo:"+rekordy.get(i)+"</p>");
										if(i==6)
											out.println("<p>Specjalizacja:"+rekordy.get(i)+"</p>");
										if(i==7)
											out.println("<p>Miasto:"+rekordy.get(i)+"</p>");
									}
								}
							}
							else if(request.getAttribute("label").equals("Specjalista") && request.getAttribute("label")!=null)
							{	
								if(request.getAttribute("rekordy") != null)
								{
									rekordy = (ArrayList<String>) request.getAttribute("rekordy");	
									for(int i=0;i<rekordy.size();i++)
									{
										if(i==0)
										{
											out.println("<p>Imie: "+rekordy.get(i)+"</p>");
										} 	
										else if(i==1)
											out.println("<p>Nazwisko: "+rekordy.get(i)+"</p>");
										if(i==2)
											out.println("<p>Email: "+rekordy.get(i)+"</p>");
										if(i==3)
											out.println("<p>Telefon: "+rekordy.get(i)+"</p>");
										if(i==4)
											out.println("<p>Login: "+rekordy.get(i)+"</p>");
										if(i==5)
											out.println("<p>Haslo: "+rekordy.get(i)+"</p>");
										if(i==6)
											out.println("<p>Specjalizacja: "+rekordy.get(i)+"</p>");
										if(i==7)
											out.println("<p>Miasto: "+rekordy.get(i)+"</p>");
									}
								}
							}%></div><%
						}
						else if(Integer.valueOf(request.getParameter("mode")) == 1)
						{
							ArrayList<String> rekordy = new ArrayList<String>();
							System.out.println("witamy na salonach");
							if(request.getAttribute("setWizyty") != null)
							{
								System.out.println("witamy na salonach");
							ArrayList<String> listaWizyt = new ArrayList<String>(40);
								listaWizyt = (ArrayList<String>) request.getAttribute("setWizyty");
								int licznik = 0;
					
								for(int i=0;i<listaWizyt.size();i++)
								{
									if(licznik == 6)
									{
										%><br><%
										licznik = 0;
										
									}
									else if(licznik == 0)
									{
										licznik++;
										continue;
									}
									else if(licznik == 1)
									{
										%><div class="viewWizyta"><%out.println(listaWizyt.get((i-1))+" "+listaWizyt.get(i)); %></div><%
									}
									else
									{
										%><div class="viewWizyta"><%out.println(listaWizyt.get(i)); %></div><%
									}
									licznik++;
								}
							}
						}
						else if(Integer.valueOf(request.getParameter("mode")) == 0)
						{
							%><div class="profile_content"><%
							%>
							<div class="error">${infoMess}</div>
							<form action="userProfile" method="post">
							<input type="hidden" value="0" name="mode">
							<input type="hidden" value="zmienLogin" name="changeSet">
            				<div class="textplace">Podaj nowy login:</div>
            				<input type="password" name="newLogin"/>
							<input type="submit" class="ver_menu" value="Zmien login"/>
							</form>
							<form action="userProfile" method="post">
							<input type="hidden" value="0" name="mode">
							<input type="hidden" value="zmienHaslo" name="changeSet">
							<div class="textplace">Podaj stare haslo:</div>
							<input type="password" name="oldPass"/>
            				<div class="textplace">Podaj nowe haslo:</div>
            				<input type="password" name="newPass0"/>
            				<div class="textplace">Powtorz nowe haslo:</div>
            				<input type="password" name="newPass1"/>
							<input type="submit" class="ver_menu" value="Zmien haslo"/>
							</form>
							<form action="userProfile" method="post">
							<input type="hidden" value="0" name="mode">
							<input type="hidden" value="zmienEmail" name="changeSet">
							<div class="textplace">Podaj nowy adres email:</div>
            				<input type="text" name="newEmail"/>
							<input type="submit" class="ver_menu" value="Zmien email"/>
							</form>
							<% 
						}
					%>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>