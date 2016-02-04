


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

				
				<div class="map"><div id="map" style="height:600px; width:1000px"></div>
				
			 
			</div>
		</div>
	
<script>

function initMap() {

	var cracowLoc = {lat: 50.0647, lng: 19.945};
  	var map = new google.maps.Map(document.getElementById('map'), {
    	center: cracowLoc,
    	zoom: 10
  });
  	var infoWindow = new google.maps.InfoWindow({map: map});

  //lokalizuj
  	if (navigator.geolocation) {
    	navigator.geolocation.getCurrentPosition(function(position) {
      	var pos = {
        	lat: position.coords.latitude,
        	lng: position.coords.longitude
      	};

      	infoWindow.setPosition(pos);
      	infoWindow.setContent('Location found.');
      	map.setCenter(pos);
    	}, function() {
      	handleLocationError(true, infoWindow, map.getCenter());
    	});
  	} 
  	else {
    	// brak wsparcia dla geolokalizacji
    	handleLocationError(false, infoWindow, map.getCenter());
  	}
  	

  
	function createMarkers() {



		/*var markers = [];
		for (var i = 0; i < lenght; i ++)
			{
				markers[i] = [];
			}
      	for (var i = 0; i < markers[i].length; i++)
      	{
          var name = markers[i][1];
          var address = markers[i][2];
          var point = new google.maps.LatLng(
              parseFloat(markers[i][3]),
              parseFloat(markers[i][4]));
          var html = "<b>" + name + "</b> <br/>" + address;
          var marker = new google.maps.Marker({
            map: map,
            position: point,
            icon: icon.icon
          });
          bindInfoWindow(marker, map, infoWindow, html);
        }*/
	}
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
  	infoWindow.setPosition(pos);
  	infoWindow.setContent(browserHasGeolocation ?
                        'Error: The Geolocation service failed.' :
                        'Error: Your browser doesn\'t support geolocation.');
}



    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDqpCUtiwkYDvy7y6ldMzzvJFwZn_S0Sys&signed_in=true&language=pl&callback=initMap" async defer>
    </script>	
</body>
</html>