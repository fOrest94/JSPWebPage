<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Style/style.css" type="text/css"/>
<title>System asygnacji i segregacji pacjentow</title>
<script
    src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false">
</script>
<script>
var map;
function initialize() {
  var mapOptions = {
    zoom: 8,
    center: new google.maps.LatLng(-34.397, 150.644)
  };
  map = new google.maps.Map(document.getElementById('map-canvas'),
      mapOptions);
}
google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>
<body>
	<div class="container">
			<div class="header">
				<div class="logo">SAISP.pl</div>
				<form action="register.jsp">
				<input type="submit" class="signin" value="Sign in"/>
				</form>
				<form action="login.jsp">
				<input type="submit" class="login" value="Log in"/>
				</form>
			</div>
			<div class="menu">
				<ol>
				<li><a href="#">Strona glowna</a></li>
				<li><a href="znajdzLekarza.jsp">Znajdz lekarza</a></li>
				<li><a href="googleMaps.jsp">Znajdz placowke</a></li>
				<li><a href="oAutorach.jsp#">O Autorach</a></li>
			
				</ol>
			</div>
			<div class="content">
			<p><div class="map">
			<div id="map-canvas" style="height:350px; width:550px"></div>
			</<p>
			</div>
			
			</div>
		</div>
</body>
</html>