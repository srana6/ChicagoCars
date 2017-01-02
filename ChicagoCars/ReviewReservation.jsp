<%@page import="dboperations.MongoDBDataStoreUtilities"%>
<html>

<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>ChicagoCars Review reservations</title>
	<link rel="stylesheet" href="Booking_styles.css" type="text/css" />
</head>
<body>
<% String username=((String)session.getAttribute("username")); %>
<div id="container">
    <header>
    	<!-- %@include file="global/header.jsp"% -->
		    	<h1><a href="Home">Chicago<span>Cars</span></a></h1> 
        <h2></h2> 
    </header>
	
	<nav id="myheader">
		<!-- %@include file="global/nav.jsp"% -->
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
	<%@ page import="java.util.*,java.text.*, java.io.*,com.mongodb.*" %>
	
	<% 
		String carID = ""; // Take input from sharath button click
		float final_rent = 0;

		String carName = "";
		int number_of_seats = 0;		
		double total = 0;
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
		String drop=  "";
		String drop_location = "";
		String pickup_location = "";

		String firstName = "";
		String lastName = "";
		String address=  "";
		String phoneNumber = "";
		String creditcard = "";

		String country=  "";
		String authority = "";
		String license_num = "";
		String appendImageExt=".jpg";
		String image_filename1=" ";



		carID=((String)session.getAttribute("car_id"));
		final_rent=(Float)session.getAttribute("final_rent");
		//final_rent =Float.parseFloat(new DecimalFormat("##.##").format(final_rent));
		BasicDBObject obj;
		DBCollection myCars= MongoDBDataStoreUtilities.createMongoDB();
		DBCursor cursor1 = MongoDBDataStoreUtilities.fetchReviewMongoDB(carID); // pass the car id to mongodb		

		pickup_location=((String)session.getAttribute("startLocation"));
		pickupdate=((String)session.getAttribute("pickDate"));
		pickuptime=((String)session.getAttribute("pickTime"));
		drop_location=((String)session.getAttribute("endLocation"));
		dropoffdate=((String)session.getAttribute("dropDate"));
		dropofftime=((String)session.getAttribute("dropTime"));

		pick= pickupdate+' '+pickuptime;
		drop= dropoffdate+' '+dropofftime;

	
		firstName=request.getParameter("firstName");
		lastName=request.getParameter("lastName");
		address=request.getParameter("address");
		phoneNumber=request.getParameter("phoneNumber");
		creditcard=request.getParameter("creditcard");

		country=request.getParameter("country");
		authority=request.getParameter("authority");
		license_num=request.getParameter("license_num");

		HttpSession sess = request.getSession(); 
		sess.setAttribute("firstName",firstName); 

		if(cursor1.count() == 0){
			out.println("There are no reviews for this product.");
		}else{				
								
				int count=0;
				while (cursor1.count()>count) {
				
				count++;
					
				obj = (BasicDBObject) cursor1.next();

		carID=obj.getString("carID");
		carName = obj.getString("carName");
		number_of_seats = obj.getInt("number_of_seats");
		transmission = obj.getString("transmission");
		airConditioning = obj.getString("airConditioning");
		rating = Float.valueOf(obj.getString("rating"));
		manufacturer = obj.getString("manufacturer");
		carType = obj.getString("carType");
		image_filename = obj.getString("filename");
		image_filename1=image_filename+appendImageExt;
		location_id = obj.getString("location_id");
}
}
	
	%>
	
	
	<h1>Review Reservation</h1>


			<form class="confirm_form" method="post" action="insertDB.jsp">
			<div>
				<div style="float:left;width: 469px; margin-right: 75px;">					
					<legend>Rental information:</legend>					
					<table>					
					<tr>
					<img style="float:left;" src="<%= image_filename1 %>"/>
					<input type="hidden" name="image_filename" value="<%= image_filename %>" ></input>
					</tr>
			
					<tr>
					<td><b>Car Name</b></td>
					<td><p><b><%= carName %></b></p> </td>
					<input type="hidden" name="carName" value="<%= carName%>" ></input>
					<input type="hidden" name="carID" value="<%= carID%>" >
					</tr>
					
					<tr>
					<td><b>Car Manufacturer</b></td>
					<td><p><b><%=manufacturer %></b></p></td>
					<input type="hidden" name="manufacturer" value="<%= manufacturer%>" ></input>
					</tr>
					
					<tr>
					<td><b>Car Type</b></td>
					<td><p><b><%=carType %></b></p></td>
					<input type="hidden" name="carType" value="<%= carType%>" ></input>
					</tr>

					<tr>
					<td><b>#Seats</b></td>
					<td><p><b><%= number_of_seats%></b></p></td>	
					<input type="hidden" name="number_of_seats" value="<%= number_of_seats%>" ></input>
					</tr>
					
					<tr>
					<td><b>Car Transmission</b></td>
					<td><p><b><%=transmission %></b></p></td>
					<input type="hidden" name="transmission" value="<%= transmission%>" ></input>
					</tr>					

					<tr>
					<td><b>Air Conditioning</b></td>
					<td><p><b><%=airConditioning %></b></p></td>
					<input type="hidden" name="airConditioning" value="<%= airConditioning%>" ></input>
					</tr>

					<tr>
					<td><b>Car Rating</b></td>
					<td><p><b><%=rating %></b></p></td>
					<input type="hidden" name="rating" value="<%= rating%>" ></input>
					</tr>
					
					<tr>
					<td><b>Pick Up Location</b></td>
					<td><p><b><%= pickup_location%></b></p></td>
					<input type="hidden" name="pickup_location" value="<%= pickup_location%>" ></input>
					</tr>					

					<tr>
					<td><b>Pick Up Date and Time  </b></td>
					<td><p><b><%= pick%></b></p></td>
					<input type="hidden" name="pick" value="<%= pick%>" ></input>
					<input type="hidden" name="pickupdate" value="<%= pickupdate%>" ></input>
					<input type="hidden" name="pickuptime" value="<%= pickuptime%>" ></input>					
					</tr>

					<tr>
					<td><b>Drop off Location</b></td>
					<td><p><b><%= drop_location%></b></p></td>
					<input type="hidden" name="drop_location" value="<%= drop_location%>" ></input>
					</tr>
					
					<tr>
					<td><b>Drop Off Date and Time  </b></td>
					<td><p><b><%= drop%></b></p></td>
					<input type="hidden" name="drop" value="<%= drop%>" ></input>
					<input type="hidden" name="dropoffdate" value="<%= dropoffdate%>" ></input>
					<input type="hidden" name="dropofftime" value="<%= dropofftime%>" ></input>					
					</tr>	

					<tr>
					<td><b>Total Rent</b></td>
					<td><p><b>$<%= final_rent%></b></p></td>
					<input type="hidden" name="final_rent" value="<%= final_rent%>" ></input>
					</tr>					

					</table>
				</div>
				</div>
				
				<div style="float:left;width:300px;">				
					<div>				
						<legend>Personal information:</legend>
						<table>
						<tr>
						<td> First name: </td>
						<td><p><%= firstName%></p></td>
						<input type="hidden" name="firstName" value="<%= firstName%>" ></input>
						</tr>				
						<tr>
						<td> Last name: </td>
						<td><p><%= lastName%></p></td>
						<input type="hidden" name="lastName" value="<%= lastName%>" ></input>
						</tr>				
						<tr>
						<td> Address: </td>
						<td><p><%= address%></p></td>
						<input type="hidden" name="address" value="<%= address%>" ></input>
						</tr>
						<tr>
						<tr>
						<td> Phone: </td>
						<td><p><%= phoneNumber%></p></td>
						<input type="hidden" name="phoneNumber" value="<%= phoneNumber%>" ></input>
						</tr>
						<td> Credit Card Information: </td>
						<td><p><%= creditcard%></p></td>
						<input type="hidden" name="creditcard" value="<%= creditcard%>" ></input>
						</tr>
						<tr>
						<td> Issuing Country: </td>
						<td><p><%= country%></p></td>
						<input type="hidden" name="country" value="<%= country%>" ></input>
						</tr>				
						<tr>
						<td> Issuing Authority: </td>
						<td><p><%= authority%></p></td>
						<input type="hidden" name="authority" value="<%= authority%>" ></input>
						</tr>				
						<tr>
						<td> Driver's License Number: </td>
						<td><p><%= license_num%></p></td>
						<input type="hidden" name="license_num" value="<%= license_num%>" ></input>
						</tr>
						</table>
						<input type="submit" class="Button" value="Reserve">	
					</div>
					</div>
			</form>

			<form class="confirm_form_cancel" name="confirm_form_cancel" method="post" action="BookCar.jsp">
			<input type="submit" class="Button" value="Edit">

			<footer style="clear:both;"> 
			<div class='footer-bottom'>
            <p>&copy; ChicagoCars 2016.</p> 
			</div>
			</footer>			
			</body>
			</html>
			
			