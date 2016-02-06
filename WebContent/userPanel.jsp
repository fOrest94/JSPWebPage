<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="javax.servlet.http.Cookie"%>
<%@page import="java.io.IOException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.*"%>
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
	        		System.out.println("LOGIN TO: "+cookie[i].getValue());
	        		login = cookie[i].getValue();
	        		sesja++;
	        	}
	        	else if(cookie[i].getName().equals("type") )
	       		{
	        		System.out.println("TYP UZYTKOWNIKA "+cookie[i].getValue());
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
					<li><a href="oAutorach.jsp">O Nas</a></li>
				</ol>
			</div>
			<div class="content">
				<div class="user_profil_left_side">
					<div class="up">
								<img src="images/${fileName}"/>
							<form action="formUpload" method="post" enctype="multipart/form-data">
								<input type="hidden" value="2" name="mode" id="mode">
								<input type="hidden" value="${login}" name="login">
								<input type="hidden" value="${typeOfUser}" name="typeOfUser">
								<input type="file" name="photo" >
								<input type="submit" class="ver_menu" value="pobierz zdjecie"/>
							</form>
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
								<input type="submit" class="ver_menu" value="Lista wizyt"/>
							</form>
							<%
							if(request.getParameter("typeOfUser").equals("admin"))
							{%>
								<form action="userProfile" method="post">
								<input type="hidden" value="3" name="mode">
								<input type="hidden" value=<%= request.getParameter("login") %> name="login">
								<input type="hidden" value=<%= request.getParameter("typeOfUser") %> name="typeOfUser">
								<input type="submit" class="ver_menu" value="Lista uzytkownikow"/>
								</form>
							<%}
							%>
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
							System.out.println("tu powin byc");
							if((request.getAttribute("label").equals("Pacjent") || request.getAttribute("label").equals("admin")) && request.getAttribute("label")!=null)
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
							if(!request.getParameter("typeOfUser").equals("admin"))
							{
								ArrayList<String> rekordy = new ArrayList<String>();
								%><h2>Lista wizyt</h2><%
								if(request.getAttribute("setWizyty") != null)
								{
									ArrayList<String> listaWizyt = new ArrayList<String>();
									ArrayList<String> nowa = new ArrayList<String>();
									listaWizyt = (ArrayList<String>) request.getAttribute("setWizyty");
									int licznik = 0;
						
									for(int i=0;i<listaWizyt.size();i++)
									{
										if(licznik == 7)
										{
											%><br><%
											licznik = 0;
											
										}
										if(licznik == 0)
										{
											%>
											<form action="edytujWizyte" method="post">
											<input type="hidden" value="1" name="mode">
											<input type="hidden" value=<%= request.getParameter("login") %> name="login">
											<input type="hidden" value=<%= request.getParameter("typeOfUser") %> name="typeOfUser">
											<div class="viewWizyta_ID"><input type="checkbox" name="id_wizyty" value="<%=listaWizyt.get(i)%>"></div>
											<%
											licznik++;
											continue;
										}
										else if(licznik == 1)
										{
											licznik++;
											continue;
										}
										else if(licznik == 2)
										{
											%><div class="viewWizyta"><%out.println(listaWizyt.get((i-1))+" "+listaWizyt.get(i)); %></div><%
										}
										else
										{
											%><div class="viewWizyta"><%out.println(listaWizyt.get(i)); %></div><%
										}
										licznik++;
									}
									%><input type="submit" class="viewWizyta_button" value="Usun"/>
									</form><%
								}
							}
							else if(request.getParameter("typeOfUser").equals("admin"))
							{
								ArrayList<String> rekordy = new ArrayList<String>();
								%><h2>Lista wizyt</h2><%
								if(request.getAttribute("setWizyty") != null)
								{
									ArrayList<String> listaWizyt = new ArrayList<String>();
									listaWizyt = (ArrayList<String>) request.getAttribute("setWizyty");
									int licznik = 0;
						
									for(int i=0;i<listaWizyt.size();i++)
									{
										if(licznik == 7)
										{
											%><br><%
											licznik = 0;
											
										}
										if(licznik == 0 && i<7)
										{
											%>
											<div class="viewWizyta_ID_empty"></div>
											<%
											licznik++;
											continue;
										}
										else if(licznik == 0 && i>6)
										{
											%>
											<form action="edytujWizyte" method="post">
											<input type="hidden" value="1" name="mode">
											<input type="hidden" value="wizyta" name="whatToDelete">
											<input type="hidden" value=<%= request.getParameter("login") %> name="login">
											<input type="hidden" value=<%= request.getParameter("typeOfUser") %> name="typeOfUser">
											<div class="viewWizyta_ID"><input type="checkbox" name="id_wizyty" value="<%=listaWizyt.get(i)%>"></div>
											<%
											licznik++;
											continue;
										}
										else
										{
											%><div class="viewWizytaA"><%out.println(listaWizyt.get(i)); %></div><%
										}
										licznik++;
									}
									%><input type="submit" class="viewWizyta_button" value="Usun"/>
									</form><%
								}
							}
						}
						else if(Integer.valueOf(request.getParameter("mode")) == 0)
						{
							%><div class="profile_content">
							<div class="error">${infoMess}</div>
							<div class="part_left">
								<form action="userProfile" method="post">
								<input type="hidden" value="0" name="mode">
								<input type="hidden" value="zmienHaslo" name="changeSet">
								<input type="hidden" value=<%= request.getParameter("login") %> name="login">
								<input type="hidden" value=<%= request.getParameter("typeOfUser") %> name="typeOfUser">
								<div class="textSett">Podaj stare haslo:</div>
								<input type="password" name="oldPass"/>
	            				<div class="textSett">Podaj nowe haslo:</div>
	            				<input type="password" name="newPass0"/>
	            				<div class="textSett">Powtorz nowe haslo:</div>
	            				<input type="password" name="newPass1"/><br>
								<input type="submit" class="bottomSett" value="Zmien haslo"/>
								</form>
							</div>
							<div class="part_right">
								<form action="userProfile" method="post">
								<input type="hidden" value="0" name="mode">
								<input type="hidden" value="zmienLogin" name="changeSet">
								<input type="hidden" value=<%= request.getParameter("login") %> name="login">
								<input type="hidden" value=<%= request.getParameter("typeOfUser") %> name="typeOfUser">
	            				<div class="textSett">Podaj nowy login:</div>
	            				<input type="password" name="newLogin"/><br>
								<input type="submit" class="bottomSett" value="Zmien login"/>
								</form>
								<form action="userProfile" method="post">
								<input type="hidden" value="0" name="mode">
								<input type="hidden" value="zmienEmail" name="changeSet">
								<input type="hidden" value=<%= request.getParameter("login") %> name="login">
								<input type="hidden" value=<%= request.getParameter("typeOfUser") %> name="typeOfUser">
								<div class="textSett">Podaj nowy adres email:</div>
	            				<input type="text" name="newEmail"/><br>
								<input type="submit" class="bottomSett" value="Zmien email"/>
								</form>
							</div>
							<% 
						}
						else if(Integer.valueOf(request.getParameter("mode")) == 3)
						{%>
							<form action="typUsera" method="post">
							<input type="hidden" value="3" name="mode">
							<input type="hidden" value=<%= request.getParameter("login") %> name="login">
							<input type="hidden" value=<%= request.getParameter("typeOfUser") %> name="typeOfUser">
								Pokaz liste: 
								<select name="typSearch" onchange="submit();">
										<option selected value="Pacjent">Wybierz:</option>
										<option value="Pacjent">Pacjent</option>
								 		<option value="Specjalista">Specjalista</option>
								</select>
							</form>
							<div class="error">${infoMess}</div>
							<br>
							<%
							if(request.getAttribute("listOfUsers") != null && request.getAttribute("typ") != null)
							{
									ArrayList<String> rekordy = new ArrayList<String>();
									rekordy = (ArrayList<String>) request.getAttribute("listOfUsers");
									int licznik = 0;
						
									for(int i=0;i<rekordy.size();i++)
									{
										if(licznik == 6)
										{
											%><br><%
											licznik = 0;
											
										}
										if(licznik == 0 && i<5)
										{
											%>
											<div class="viewWizyta_ID_empty"></div>
											<%
											licznik++;
											continue;
										}
										if(licznik == 0 && i>5)
										{
											%>
											<form action="edytujWizyte" method="post">
											<input type="hidden" value="3" name="mode">
											<input type="hidden" value=<%= request.getParameter("login") %> name="login">
											<input type="hidden" value=<%= request.getParameter("typeOfUser") %> name="typeOfUser">
											<input type="hidden" value=<%= request.getAttribute("typ") %> name="typSearch">
											<div class="viewWizyta_ID"><input type="checkbox" name="id_usera" value="<%=rekordy.get(i)%>"></div>
											<%
											licznik++;
											continue;
										}
										else
										{
											%><div class="viewWizyta"><%out.println(rekordy.get(i)); %></div><%
										}
										licznik++;
									}
									%><input type="submit" class="viewWizyta_button" value="Usun"/>
									</form><%
							}
						}%>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>