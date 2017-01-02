/*function drawSignInDialogBox(){

	var s = document.getElementById("loginLogoffButton");
	s.addEventListener("click", showSignInDialog, false);

	myModal = document.createElement("div");
	myModal.setAttribute("id", "myModal");
	myModal.setAttribute("class", "modal");

	var modalContent = document.createElement("div");
	modalContent.setAttribute("class", "modal-content");

	var closeButton = document.createElement("span");
	closeButton.setAttribute("class", "close");
	closeButton.addEventListener("click", function(){myModal.style.visibility = "hidden";}, false);
	closeButton.appendChild(document.createTextNode("x"));

	myModal.appendChild(modalContent);
	modalContent.appendChild(closeButton);		

	var h3 = document.createElement("h3");
	h3.appendChild(document.createTextNode("Login"));
	modalContent.appendChild(h3);

	var fieldset = document.createElement("fieldset");
	var form = document.createElement("form");
	form.setAttribute("accept-charset", "UTF-8");
	form.onsubmit = signInFormOnSubmit;
	form.setAttribute("method", "post");	
	modalContent.appendChild(form);

	var p1 = document.createElement("p");	
	var email = document.createElement("input");
	email.setAttribute("placeholder", "Email");
	email.setAttribute("name", "signInEmail");
	email.setAttribute("type", "email");
	email.setAttribute("required", true);
	p1.appendChild(email);
	form.appendChild(p1);	

	var p2 = document.createElement("p");	
	var password = document.createElement("input");
	password.setAttribute("placeholder", "Password");
	password.setAttribute("name", "signInPassword");
	password.setAttribute("type", "password");
	password.setAttribute("required", true);
	p2.appendChild(password);
	form.appendChild(p2);

	var p3 = document.createElement("p");	
	var submitButton = document.createElement("button");
	submitButton.setAttribute("name", "send");
	submitButton.setAttribute("class", "formbutton");	
	submitButton.setAttribute("value", "Sign-in");
	submitButton.setAttribute("type", "submit");
	submitButton.appendChild(document.createTextNode("Sign-in"));
	p3.appendChild(submitButton);
	form.appendChild(p3);

	var p4 = document.createElement("p");
	p4.appendChild(document.createElement("hr"));
	form.appendChild(p4);

	var googleScript = document.createElement("script");
	googleScript.setAttribute("src", "https://apis.google.com/js/platform.js");
	googleScript.setAttribute("async","");
	googleScript.setAttribute("defer","");
	document.body.appendChild(googleScript);

	var googleSignInButton = document.createElement("div");
	var p4 = document.createElement("p");
	googleSignInButton.setAttribute("class", "g-signin2");
	googleSignInButton.setAttribute("data-onsuccess", "onGoogleSignIn");
	p4.appendChild(googleSignInButton);
	modalContent.appendChild(googleSignInButton);

	document.getElementsByClassName("ourSignIn")[0].appendChild(myModal);
}*/

function onGoogleSignIn(googleUser){
	
	profile = googleUser.getBasicProfile();	
	window.location.replace("Home");	
	var xhr = new XMLHttpRequest();
	sessionStorage.setItem("username", profile.getName());
	xhr.open("GET", "Login?"+JSON.stringify({"action":"googlesignin","email":profile.getEmail(), "firstname":profile.getName()}), true);	
	xhr.send();

	sessionStorage.setItem("loginType", "google");

	var myModal = document.getElementById("myModal");
	myModal.style.visibility = "hidden";

}

function signInFormOnSubmit(){
	var referer = document.referrer;
	
	var errMsg = document.getElementById("errorMsg");
	var details = {"email":document.getElementsByName("signInEmail")[0].value, "pwd":document.getElementsByName("signInPassword")[0].value};


	var xhr = new XMLHttpRequest();
	xhr.open("POST", "Login?"+JSON.stringify({"action":"defaultsignin"}), true);	
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");		
	xhr.responseType = "json";


	xhr.onload = function(){
		//alert("Got");
		sessionStorage.setItem("loginType", "default");
		
		var resp = xhr.response;
		
		if(xhr.response.credentialstatus=="OK"){
			if(referer.includes("BookCar"))	
				location.replace("RetrieveCarInfoServlet");
			else
				location.replace(referer);
		}
		else if(xhr.response.credentialstatus=="ADMIN"){
			location.replace("AdminDisplayServlet");
		}
		else{	

			if(errMsg.querySelector("p")==null) {
				var msg = document.createElement("p");
				msg.style.color = "red";
				msg.appendChild(document.createTextNode("Invalid credentials"));
				errMsg.appendChild(msg);
			}
			//setTimeout(function(){
			//	var item = document.getElementById("errorMsg").lastChild;
			//	document.getElementById("errorMsg").removeChild(item);
			//}, 1000);

		}
	}

	var s = JSON.stringify(details);
	xhr.send(s);

	return false;
}

function sampleFunc(data){

	if(data.credentialstatus=="OK"){

	}		
	else{			
		alert("Please enter valid credentials");
	}
}

function signOut() {
	if(sessionStorage.getItem("loginType") === "google"){
		gapi.auth2.init({client_id:'10737562217-2jruahg9ieod40retuiegdclathecknn.apps.googleusercontent.com'});
		var auth2var = gapi.auth2.getAuthInstance();
		autauth2var.signOut().then(function () {
			var xhr = new XMLHttpRequest();
			xhr.open("GET", "Login?"+JSON.stringify({"action":"logout"}), true);
			xhr.send();
			window.location.replace("Home");		
		});		
	}
	else{
		var xhr = new XMLHttpRequest();
		xhr.open("GET", "Login?"+JSON.stringify({"action":"logout"}), true);
		xhr.send();
		window.location.replace("Home");
	}
	
	sessionStorage.setItem("logintype", "");

	//Call Login Java to indicate logout

	return false;
}

/*function showSignInDialog(){

	document.getElementById("myModal").style.visibility = "visible";

	window.onclick = function(event){
		if(event.target == myModal){
			myModal.style.visibility = "hidden";
		}		
	}
}*/