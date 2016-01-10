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
      
		if(cookie != null) //There are cookies
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
    	return false; //this means no cookies were found
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
				<div class="map"><div id="map" style="height:350px; width:550px"></div>
				
			 
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
  	} else {
    	// brak wsparcia dla geolokalizacji
    	handleLocationError(false, infoWindow, map.getCenter());
  	}
//function createMarker(place) {
//	  var placeLoc = place.geometry.location;
	  var marker = new google.maps.Marker({
	    map: map,
	    position: cracowLoc
	  });  
  
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