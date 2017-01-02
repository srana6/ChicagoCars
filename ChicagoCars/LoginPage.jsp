<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="google-signin-client_id"
	content="10737562217-2jruahg9ieod40retuiegdclathecknn.apps.googleusercontent.com">
	<link rel="stylesheet" type="text/css" href="SignIn_styles.css" />
<title>ChicagoCars Sign In</title>
</head>
<body onload>
	<script src="SignIn.js"></script>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<div id="container">
		<header>
		<h1>
			<a href="Home.jsp">Chicago<span>Cars</span></a>
		</h1>
		<h2>Rent-a-car!</h2>
		</header>

		<div class="modal-content">
			<h3>Login</h3>
			<div id="errorMsg">				
			</div>
			<form onsubmit="return signInFormOnSubmit();" accept-charset="UTF-8" method="post">
				<p>
					<input placeholder="Email" name="signInEmail" required="true"
						type="email">
				</p>
				<p>
					<input placeholder="Password" name="signInPassword" required="true"
						type="password"> <a href="ForgotPasswordPage.jsp">Forgot
						password?</a>
				</p>
				<p>
					<button name="send" class="formbutton" value="Sign in"
						type="submit">Sign-in</button>
				</p>
			</form>
		<!-- <span>
				<p>Or login with</p>
				<div class="g-signin2" data-onsuccess="onGoogleSignIn"></div>
			</span>
			<p></p>
			-->
			<hr>
			<h5>New user?</h5>
			<a href="SignUpPage.jsp"><button type="button" class="formbutton">Create account</button></a>

		</div>
	</div>
</body>
</html>