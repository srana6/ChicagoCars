<%@page import="dboperations.MongoDBDataStoreUtilities"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ChicagoCars Manage Reservations</title>
<link rel="stylesheet" href="Booking_styles.css" type="text/css" />
</head>

<body>
	<%
		if (request.getSession().getAttribute("email") == null
				|| request.getSession().getAttribute("email").equals("Guest"))
			response.sendRedirect("LoginPage");
	%>
	<%
		String username = ((String) session.getAttribute("username"));
	%>
	<div id="container">
		<header>
			<!-- %@include file="global/header.jsp"% -->
    	<h1><a href="Home">Chicago<span>Cars</span></a></h1> 
        <h2></h2> 
		</header>

		<nav id="myheader">
			<ul>
				<li class="start selected"><a href="Home">Home</a></li>
				<li class="start selected"><a href="manage_user_reservations.jsp">Manage Reservations</a></li>
			    <li class="start selected"><a><%if(request.getSession().getAttribute("email")==null || request.getSession().getAttribute("email").equals("Guest")){ %>
			
			Welcome Guest
			<%}

			else{ %>
			
			Welcome <%=request.getSession().getAttribute("email")%>
			<%}%></a></li>

			</ul>
		</nav>

		<%@ page import="java.util.*,java.text.*, java.io.*,com.mongodb.*"%>


		<%
			String carID = ""; // Take input from sharath button click
			BasicDBObject obj;

			carID = request.getParameter("id");
			//carID = (String) request.getSession().getAttribute("carID");
			DBCollection myCars = MongoDBDataStoreUtilities.createMongoDB();
			DBCursor cursor1 = MongoDBDataStoreUtilities.fetchReviewMongoDB(carID); // pass the car id to mongodb

			// Actual values to be used 
			String carName = "";
			int number_of_seats = 0;
			float price = 0.0f;
			float total = 0;
			String transmission = "";
			String airConditioning = "";
			float rating = 0.0f;
			String manufacturer = "";
			String carType = "";
			String image_filename = "";
			String location_id = "";

			String pickupdate = "";
			String pickuptime = "";
			String pick = "";
			String dropoffdate = "";
			String dropofftime = "";
			String drop = "";
			String drop_location = "";
			String pickup_location = "";
			String image_filename1 = " ";
			String appendImageExt = ".jpg";

			//System.out.print((String)session.getAttribute("pickLocation"));
			pickup_location = ((String) session.getAttribute("startLocation"));
			pickupdate = ((String) session.getAttribute("pickDate"));
			pickuptime = ((String) session.getAttribute("pickTime"));
			drop_location = ((String) session.getAttribute("endLocation"));
			dropoffdate = ((String) session.getAttribute("dropDate"));
			dropofftime = ((String) session.getAttribute("dropTime"));

			pick = pickupdate + ' ' + pickuptime;
			System.out.println("From Date Session PICK_COMBINE::" + pick);

			drop = dropoffdate + ' ' + dropofftime;
			System.out.println("From Date Session DROP_COMBINE::" + drop);

			if (cursor1.count() == 0) {
				out.println("There are no reviews for this product.");
			} else {

				int count = 0;
				while (cursor1.count() > count) {

					count++;

					obj = (BasicDBObject) cursor1.next();

					carID = obj.getString("carID");
					carName = obj.getString("carName");
					number_of_seats = obj.getInt("number_of_seats");
					price = Float.valueOf(obj.getString("price"));
					//price = (long) price;
					System.out.println("From MongoDB::" + price);
					transmission = obj.getString("transmission");
					airConditioning = obj.getString("airConditioning");
					rating = Float.valueOf(obj.getString("rating"));
					manufacturer = obj.getString("manufacturer");
					carType = obj.getString("carType");
					image_filename = obj.getString("filename");
					image_filename1 = image_filename + appendImageExt;
					System.out.println("The filename is::::" + image_filename1);
					location_id = obj.getString("location_id");
				}
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date s_pickup = sdf.parse(pick);
			System.out.println("From Date Session s_pickup::" + s_pickup.getTime());
			Date s_drop = sdf.parse(drop);
			System.out.println("From Date Session s_drop::" + s_drop.getTime());
			long dateDifference = s_drop.getTime() - s_pickup.getTime();
			System.out.println("From Date Session dateDifference::" + dateDifference);
			long datediff = dateDifference / (24 * 60 * 60 * 1000);

			System.out.println("From Date Session datediff::" + datediff);
			System.out.println("From Date Session for IFF::" + dateDifference % (24 * 60 * 60 * 1000));
			if ((dateDifference % (24 * 60 * 60 * 1000)) == 0) {
				total = price * (datediff);
				System.out.println("IFFFF From Date Session total::" + total);
			} else {
				total = price * (datediff + 1);
				System.out.println("ELSEEEE From Date Session total::" + total);
			}

			HttpSession sess = request.getSession();
			sess.setAttribute("final_rent", total);
			sess.setAttribute("car_id", carID);
		%>

		<h2>REVIEW AND RESERVE</h2>
		<br>
		<div>
			<div style="float: left; width: 469px; margin-right: 75px;">


				<legend>
					<b>Rental information:</b>
				</legend>
				<table>
					<tr>
						<img style="float: left;" src="<%=image_filename1%>" />
					</tr>

					<tr>
						<td><b>Car Name</b></td>
						<td><p>
								<b><%=carName%></b>
							</p></td>
					</tr>

					<tr>
						<td><b>Car Manufacturer</b></td>
						<td><p>
								<b><%=manufacturer%></b>
							</p></td>
					</tr>

					<tr>
						<td><b>Car Type</b></td>
						<td><p>
								<b><%=carType%></b>
							</p></td>
					</tr>

					<tr>
						<td><b>#Seats</b></td>
						<td><p>
								<b><%=number_of_seats%></b>
							</p></td>
					</tr>


					<tr>
						<td><b>Car Transmission</b></td>
						<td><p>
								<b><%=transmission%></b>
							</p></td>
					</tr>

					<tr>
						<td><b>Air Conditioning</b></td>
						<td><p>
								<b><%=airConditioning%></b>
							</p></td>
					</tr>

					<tr>
						<td><b>Car Rating</b></td>
						<td><p>
								<b><%=rating%></b>
							</p></td>
					</tr>

					<tr>
						<td><b>Pick Up Location</b></td>
						<td><p>
								<b><%=pickup_location%></b>
							</p></td>
					</tr>

					<tr>
						<td><b>Pick Up Date and Time </b></td>
						<td><p>
								<b><%=pick%></b>
							</p></td>
					</tr>

					<tr>
						<td><b>Drop off Location</b></td>
						<td><p>
								<b><%=drop_location%></b>
							</p></td>
					</tr>

					<tr>
						<td><b>Drop Off Date and Time </b></td>
						<td><p>
								<b><%=drop%></b>
							</p></td>
					</tr>

					<tr>
						<td><b>Total Rent</b></td>
						<td><p>
								<b>$<%=total%></b>
							</p></td>
					</tr>

				</table>

			</div>
			<script type="text/javascript" src="Booking.js"></script>
			
			<form class="go_right_form" name="personal_information" method="post"
				action="ReviewReservation.jsp" onsubmit="return validate_fields();">

				<legend>
					<b>Renter Details:</b>
				</legend>
				<div>
					<input id="firstName" name="firstName" type="text" required>
					<label for="firstName">First Name</label>
				</div>
				<div>
					<input id="lastName" name="lastName" type="text" required>
					<label for="lastName">Last Name</label>
				</div>
				<div>
					<input id="address" name="address" type="text" required> <label
						for="address">Address</label>
				</div>
				<div>
					<input id="phoneNumber" name="phoneNumber" type="tel" required>
					<label for="phoneNumber">Primary Phone</label>
				</div>
				<div>
					<input id="creditcard" name="creditcard" type="text" maxlength="16"
						required> <label for="creditcard">Credit Card
						Number</label>
				</div>
				<br>

				<legend>
					<b>Save Time At The Counter:</b>
				</legend>

				Provide additional information now and save time when you arrive.
				<div>
					<input id="country" name="country" type="text" required> <label
						for="country">Issuing Country</label>
				</div>
				<div>
					<input id="authority" name="authority" type="text" required>
					<label for="authority">Issuing Authority</label>
				</div>
				<div>
					<input id="license_num" name="license_num" type="text"
						title="Format:<1-3 CAPS letters> - <1-2 CAPS letters> - <1-4 numbers>"
						required> <label for="license_num">Driver's
						License Number</label>
				</div>
				<input type="submit" class="Button" value="Reserve My Car">

			</form>

			<form class="cancel_form" name="cancel_form" method="post"
				action="ReviewReservation.jsp">
				<input type="submit" class="Button" value="Cancel">

			</form>

			<footer style="clear: both;">
				<div class='footer-bottom'>
					<p>&copy; ChicagoCars 2016.</p> 
				</div>
			</footer>
</body>
</html>






















