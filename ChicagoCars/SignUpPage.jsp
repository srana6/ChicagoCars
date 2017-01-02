<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="SignIn_styles.css" />
<title>ChicagoCars Sign up</title>
<link rel="shortcut icon" href="favicon.ico" />
</head>
<body>
	<script type="text/javascript" src="SignUp.js"></script>
	<div id="container">
		<header>
		<h1>
			<a href="Home.jsp">Chicago<span>Cars</span></a>
		</h1>
		<h2>Rent-a-car!</h2>
		</header>
		<span>
			<div class="modal-content">
				<h3>Create new account</h3>
				<fieldset>
					<legend></legend>
					<form onsubmit="return onSignUp();" method="post"
						accept-charset="UTF-8">

						<div>
							<label for="firstname">First name*</label>
						</div>
						<input required name="firstname" id="firstname" value=""
							type="text" />
						<p></p>
						<div>
							<label for="lastname">Last name*</label>
						</div>
						<input required name="lastname" id="lastname" value="" type="text" />
						<p></p>
						<div>
							<label for="email">Email*</label>
						</div>
						<div id="ipemail">
							<input required name="email" id="email" value="" type="email" />
						</div>
						<p></p>
						<div>
							<label for="password">Password*</label>
						</div>
						<div id="ippassword">
							<input required name="password" id="password" value=""
								type="password" />
						</div>
						<p></p>
						<div>
							<label for="reenterpassword">Re-enter password*</label>
						</div>
						<div id="ipreenterpassword">
							<input required name="reenterpassword" id="reenterpassword"
								value="" type="password" />
						</div>
						<p></p>
						<div>
							<label for="phone">Phone*</label>
						</div>
						<input required name="phone" id="phone" value="" type="tel" />
						<p></p>
						<div>
							<label for="address">Address*</label>
						</div>
						<textarea required cols="37" rows="11" name="address" id="address"></textarea>

						<p></p>
						<button name="send" class="formbutton" value="Create account"
							type="submit">Create account</button>


					</form>
				</fieldset>
			</div>
		</span>
	</div>
</body>
</html>