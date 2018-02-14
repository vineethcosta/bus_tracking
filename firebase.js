window.alert("Yo");




function myMap() {
	window.alert("Yola");

	var firebaseRef = firebase.database().ref();
var firebaseLocRef = firebase.database().ref().child("Location");
  var mapCanvas = document.getElementById("map");

  firebaseLocRef.on("value", function(snapshot){
  	var location = snapshot.val();
  	location = location.split(",");
  	var latitude = parseFloat(location[0]);
  	var longitude = parseFloat(location[1]);
  	var myCenter = new google.maps.LatLng(latitude,longitude); 
    var mapOptions = {center: myCenter, zoom: 15};
    var map = new google.maps.Map(mapCanvas,mapOptions);
    var marker = new google.maps.Marker({
    position: myCenter,
    animation: google.maps.Animation.BOUNCE
  });
  marker.setMap(map);

  })


  
}