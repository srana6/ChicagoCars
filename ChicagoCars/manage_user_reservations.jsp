<%@page import="dboperations.MySqlDataStoreUtilities"%>
<%@page import="pojos.Manage_reservations_model"%>
<html>

<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>ChicagoCars Manange reservations</title>
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
		System.out.println("IN Reservations 1:::");
		String car_confirm_num = "";
		String firstName = "";

		HashMap<String, List<Manage_reservations_model>> mapInFile = new HashMap<String, List<Manage_reservations_model>>();

		List<Manage_reservations_model> manage_list;

		firstName=(String)session.getAttribute("firstName");
		System.out.println("IN Reservations 2:::"+firstName);

		mapInFile = MySqlDataStoreUtilities.selectUserRentalDetails(firstName);

		//System.out.println("IN Reservations 2 mapInFile:::"+len(mapInFile));

		System.out.println("IN Reservations 2 mapInFile:::"+mapInFile.size());
	
%>



<table class="rwd-table">
  <tr>
    <th>Confirmation Number</th>
    <th>Pick Up Date</th>
    <th>Pick Up Time</th>
    <th>Drop Off Date</th>
    <th>Drop Off Time</th>
    <th>Pick Up Location</th>
    <th>Total Rent</th>
    <th>Status</th>
    <th>Cancel Booking</th>
  </tr>
  
<%
  for (Map.Entry<String,List<Manage_reservations_model>> ee : mapInFile.entrySet()) 
  {
            car_confirm_num = ee.getKey();
            System.out.println("IN Reservations confirmation_num::"+car_confirm_num);
            manage_list = new ArrayList<Manage_reservations_model>();
            manage_list = ee.getValue();
            System.out.println("IN Reservations manage_list::"+manage_list.size());
%>

<%

    for (Manage_reservations_model manage : manage_list) {
%>

  <tr>
    <td data-th="Confirmation Number"><%=manage.getConfirm_num()%></td>
    <td data-th="Pick Up Date"><%=manage.getPick_up_date()%></td>
    <td data-th="Pick Up Time"><%=manage.getPick_up_time()%></td>
    <td data-th="Drop Off Date"><%=manage.getDrop_off_date()%></td>
    <td data-th="Drop Off Time"><%=manage.getDrop_off_time()%></td>
    <td data-th="Pick Up Location"><%=manage.getPick_up_location()%></td>
    <td data-th="Total Rent"><%=manage.getTotal_car_rent()%></td>
    <td data-th="Status"><%=manage.getBook_status()%></td> 
    <td data-th="Cancel Booking"><a href="CancelBooking.jsp?con_num=<%=manage.getConfirm_num()%>">Cancel Booking</a></td>    
  </tr>

<%  } %>

<%
}
%>
 </table>


			<footer style="clear:both;"> 
			<div class='footer-bottom'>
            <p>&copy; ChicagoCars 2016.</p> 
			</div>
			</footer>
</h5>
</div>
</body>
</html>		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
