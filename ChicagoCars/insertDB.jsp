<%@page import="pojos.CarsUnavailable"%>
<%@page import="dboperations.MySqlDataStoreUtilities"%>
<html>

<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>ChicagoCars confirmation</title>
	<link rel="stylesheet" href="Booking_styles.css" type="text/css" />
	<script type="text/javascript" src="SignIn.js"></script>
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
			<%if(request.getSession().getAttribute("email")==null || request.getSession().getAttribute("email").equals("Guest")){ %>
			<li class="start selected"><a href="LoginPage.jsp">Login/ Signup</a></li>
			<li class="start selected"><a>Welcome Guest</a></li>
			<%}

			else{ %>
			<li class="start selected"><a onclick="return signOut();">Logout</a></li>
			<li class="start selected"><a>Welcome <%=request.getSession().getAttribute("email")%></a></li>
			<%}%>

			</ul>
    </nav>
	<%@ page import="java.util.*,java.text.*, java.io.*,com.mongodb.*" %>
	
	<% 
		System.out.println("IN order INSERT DB1::");
		String carid = "";		
		String pickupdate = "";
		String pickuptime = "";
		String dropoffdate = "";
		String dropofftime = "";
		String final_rent = "";
		String pickup_location = "";
		String address= "";
		String phoneNumber= "";
		String creditcard= "";
		String license_num = "";		
		String user_name_pk= "";
		String booking_status = "booked";		

		String firstName = "";
		String lastName = "";
		String carName = "";
		String pick = "";
		String drop = "";
		
		user_name_pk= request.getParameter("firstName");
		System.out.println("IN INSERT DB user_name_pk::"+user_name_pk);
		carid = request.getParameter("carID");
		//System.out.println("IN order carid::"+carid);		
		pickupdate = request.getParameter("pickupdate");
		//System.out.println("IN order pickupdate::"+pickupdate);
		pickuptime = request.getParameter("pickuptime");
		//System.out.println("IN order pickuptime::"+pickuptime);
		dropoffdate = request.getParameter("dropoffdate");
		//System.out.println("IN order dropoffdate::"+dropoffdate);
		dropofftime = request.getParameter("dropofftime");
		//System.out.println("IN order dropofftime::"+dropofftime);
		final_rent = request.getParameter("final_rent");
		//System.out.println("IN order total::"+final_rent);
		pickup_location = request.getParameter("pickup_location");
		//System.out.println("IN order pickup_location::"+pickup_location);
		address= request.getParameter("address");
		//System.out.println("IN order address::"+address);
		phoneNumber= request.getParameter("phoneNumber");
		//System.out.println("IN order phoneNumber::"+phoneNumber);
		creditcard=request.getParameter("creditcard");
		//System.out.println("IN order creditcard::"+creditcard);
		license_num = request.getParameter("license_num");
		//System.out.println("IN order license_num::"+license_num);

		firstName = request.getParameter("firstName");
		lastName =  request.getParameter("lastName");
		address =   request.getParameter("address");
		phoneNumber = request.getParameter("phoneNumber");
		creditcard =  request.getParameter("creditcard");

		carName =  request.getParameter("carName");
		pick = request.getParameter("pick");
		drop = request.getParameter("drop");

		Random r = new Random( System.currentTimeMillis() );
		int rand= 10000000 + r.nextInt(20000000);
		String confirmation_num=Integer.toString(rand);	



		System.out.println("INSERT DB2::");	

		MySqlDataStoreUtilities.insert_order_history_user(firstName);
		System.out.println("INSERT DB3::");

		MySqlDataStoreUtilities.insert_order_history_user_details(carid,pickupdate,pickuptime,dropoffdate,dropofftime,final_rent,pickup_location,address,phoneNumber,creditcard,license_num,user_name_pk,booking_status,confirmation_num);

		System.out.println("INSERT DB4::");	

MySqlDataStoreUtilities.insert_bookedcars(carid,pickupdate,pickuptime,dropoffdate,dropofftime,user_name_pk,booking_status,confirmation_num,pick,drop);

		System.out.println("INSERT DB5::");			
	%>

	<H2 ALIGN=CENTER>Your Car has been Reserved Successfully</H2>
	<H3 ALIGN=CENTER>Your Confirmation Number is :<%=confirmation_num%></H3>

	<table class="rwd-table">
  <tr>
    <th>Confirmation</th>
    <th>Details</th>
  </tr>
  <tr>
    <td data-th="Confirmation">First Name</td>
    <td data-th="Details"><%=firstName%></td>
  </tr>
  <tr>
    <td data-th="Confirmation">Last Name</td>
    <td data-th="Details"><%=lastName%></td>
  </tr>
  <tr>
    <td data-th="Confirmation">Address</td>
    <td data-th="Details"><%=address%></td>
  </tr>
  <tr>
    <td data-th="Confirmation">Phone Number</td>
    <td data-th="Details"><%=phoneNumber%></td>
  </tr>
  <tr>
    <td data-th="Confirmation">Car Booked</td>
    <td data-th="Details"><%=carName%></td>
  </tr>
  <tr>
    <td data-th="Confirmation">Total Rental Amount</td>
    <td data-th="Details"><%=final_rent%></td>
  </tr>
  <tr>
    <td data-th="Confirmation">Pick Up Details</td>
    <td data-th="Details"><%=pick%></td>
  </tr>
  <tr>
    <td data-th="Confirmation">Drop Off Details</td>
    <td data-th="Details"><%=drop%></td>
  </tr>

</table>

			<footer style="clear: both;">
				<div class='footer-bottom'>
					<p>&copy; ChicagoCars 2016.</p> 
				</div>
			</footer>
</h5>
</div>
</body>
</html>
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
