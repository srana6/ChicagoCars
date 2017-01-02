<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="SignIn_styles.css" />
<title>ChicagoCars Forgot password</title>
</head>
<body>
	<script src="SignUp.js"></script>	
	<div id="container">
		<header>
		<h1>
			<a href="Home.jsp">Chicago<span>Cars</span></a>
		</h1>
		<h2>Rent-a-car!</h2>
		</header>

		<div class="modal-content">
			<div id="errorMsg">
				<h3>Retrieve password</h3>
			</div>
			<form onsubmit="return passwordRetrieve();" accept-charset="UTF-8"
				method="post">
				<p>
					<input placeholder="Enter your email" id="forgottenEmail" name="forgottenEmail" required="true"
						type="email">
				</p>
				<p>
					<input placeholder="Enter your phone#" id="phone" name="phone" required="true"
						type="text">
				</p>
				<p id="message">
					<button name="send" id="resetbutton" class="formbutton" value="Reset password"
						type="submit">Reset password</button>
				</p>
			</form>		

		</div>
	</div>
</body>
</html>