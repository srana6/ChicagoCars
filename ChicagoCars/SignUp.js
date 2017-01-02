function onSignUp(){

	var email = document.getElementById("email").value;

	var xhr = new XMLHttpRequest();
	validatePassword().then(function(reason){	
		//document.getElementById("ipreenterpassword").removeChild(document.getElementById("ipreenterpassword").querySelector("span"));
		xhr.open("POST", "SignUp?action=1", true);
		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");	
		xhr.responseType = "json";
		
		xhr.onload = function(){
			validateEmail(xhr.response);		
		}
		console.log("Verifying email");
		var s=JSON.stringify({"email":email});
		xhr.send(s);
		//alert(document.referrer);	
	}, function(reason){
		if(document.getElementById("ipreenterpassword").querySelector("span")==null){
			var errormsg = document.createElement("span");
			errormsg.appendChild(document.createTextNode("Passwords do not match"));
			errormsg.style.color = "red";
			document.getElementById("ipreenterpassword").appendChild(errormsg);
			document.getElementById("password").focus();
		}


	});	

	return false;

}

function validatePassword() {
	if(document.getElementById("password").value !== document.getElementById("reenterpassword").value){				
		return Promise.reject("error");
	}
	else
		return Promise.resolve("success");
}

function validateEmail(data){
	if(data.validity=="OK"){
		//Redirect to home page
		var xhr = new XMLHttpRequest();
		var retVal = {"firstname":document.getElementById("firstname").value,
				"lastname":document.getElementById("lastname").value,	
				"email": document.getElementById("email").value,
				"phone": document.getElementById("phone").value,
				"address": document.getElementById("address").value,
				"password": document.getElementById("password").value
		};
				
		xhr.open("POST", "SignUp?action=2", true);
		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhr.responseType="json";
		xhr.onload = function(){
			window.location.replace("Home");	
		}
		xhr.send(JSON.stringify(retVal));	
		
	}
	else{
		
		if(document.getElementById("ipemail").querySelector("span") === null){
			var errormsg = document.createElement("span");
			errormsg.appendChild(document.createTextNode("This email is already in use"));
			errormsg.style.color = "red";		
			document.getElementById("ipemail").appendChild(errormsg);
			document.getElementById("email").focus();
		}
	}	

}


function passwordRetrieve(){
	var email = document.getElementById("forgottenEmail");
	var phone = document.getElementById("phone");
	var retVal={"forgottenEmail":email, "phone":phone};
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "SignUp?action=3", true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.responseType="json";
	xhr.onload = function(){
		var elementToDelete = document.getElementById("resetbutton");
		var parent = elementToDelete.parentNode;
		
		switch(xhr.response.retval){
		case "OK":			
			var msg = document.createElement("p");
			msg.style.color = "red";
			msg.appendChild(document.createTextNode("Your password is now reset to email-id"));
			parent.innerHTML = msg.toString();
			break;
			
		case "NOT OK":			
			var msg = document.createElement("p");
			msg.style.color = "red";
			msg.appendChild(document.createTextNode("No matching entry found for above details"));
			parent.appendChild(msg);
			setTimeout(function(){msg.parentNode.removeChild(msg);}, 1000);
			break;
		}
		
	}
	xhr.send(JSON.stringify(retVal));
	
	return false;
}