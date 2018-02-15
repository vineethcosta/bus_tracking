//window.alert("Yo");

function myMap() {
	window.alert("Jai Balayya");
	//var flag = true;

	var firebaseRef = firebase.database().ref();
var firebaseLocRef = firebase.database().ref().child("Location");
  var mapCanvas = document.getElementById("map");
  var map;
  var marker;

  firebaseLocRef.once("value", function(snapshot){
  	var location = snapshot.val();
  	location = location.split(",");
  	var latitude = parseFloat(location[0]);
  	//window.alert(latitude);
  	var longitude = parseFloat(location[1]);
  	var myCenter = new google.maps.LatLng(latitude,longitude); 
    var mapOptions = {center: myCenter, zoom: 15};
    
     map = new google.maps.Map(mapCanvas,mapOptions);
    		
     marker = new google.maps.Marker({
    position: myCenter,
    animation: google.maps.Animation.BOUNCE
  });
  marker.setMap(map);

  })


  firebaseLocRef.on("value", function(snapshot){
  	var location = snapshot.val();
  	location = location.split(",");
  	var latitude = parseFloat(location[0]);
  	//window.alert(latitude);
  	var longitude = parseFloat(location[1]);
  	var myCenter = new google.maps.LatLng(latitude,longitude); 
    var mapOptions = {center: myCenter, zoom: 15};
    
    //var map = new google.maps.Map(mapCanvas,mapOptions);
   	marker.setMap(null);
    marker = new google.maps.Marker({
    position: myCenter,
    animation: google.maps.Animation.BOUNCE
  });
  map.panTo(myCenter);
  marker.setMap(map);

  })


  
}
