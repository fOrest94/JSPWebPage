<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
			<div class="content" name="specjalnosc">
				<div class="contactSideLeft">
					<h2>Dane firmy</h2>
					<h3>SAISP Sp. z o. o.</h3>
					ul. Kowala 5</br>
					31-866 Krakow</br>
					Polska</br></br>
					
					NIP: 4576454654</br>
					KRS: 000045654</br>
				</div>
				<div class="contactSideRight">
					<h2>Kontakt</h2>
					<form action="whatIsYourQuestion" method="post">
						<div class="error">${odpowiedz}</div>
    					<select name="specjalizacja" style="width:350px; margin-top: 10px; border-radius: 3px; border-style: solid; height:2.0em; font size=13 ">
        					<option disabled selected value="wybierz">wybierz</option>
  							<option value="specjalista">Specjalista</option>
 							<option value="pacjent">Pacjent</option>
						</select>
    					<input type="text" name="email" style="width:350px; margin-top: 10px; border-radius: 3px; border-style: solid; height:2.0em;"  placeholder="email"/>
						<textarea name="textArea" style="width:350px; height:200px; margin-top: 10px; border-style: solid; border-radius: 3px;" name="tresc" font size="13" placeholder="tresc"></textarea></p>
    					<input type="submit" class="wizyta" value="Wyslij"/>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>