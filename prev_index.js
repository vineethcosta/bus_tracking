window.alert("Heyya");
function submitBtn()
{

	var firebaseRef = firebase.database().ref();
	//var content = document.getElementById("content");
	var content = document.getElementById("content").value;
	firebaseRef.child("Location2").set(content);
	var location = "hello";
	var firebaseLocRef = firebase.database().ref().child("Location");
	firebaseLocRef.on("value", function(snapshot){
		//window.alert(snapshot.val());
		location = snapshot.val();

		document.getElementById("something").style.color = 'red';
		document.getElementById("something").innerHTML = snapshot.val();
		window.alert(location);

	})
	window.alert(location+"sssss");
	
}
