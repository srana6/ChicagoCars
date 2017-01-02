<%@page import="dboperations.MySqlDataStoreUtilities"%>
<%@page import="pojos.Manage_reservations_model"%>
<html>

<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Mid-West Car Rentals</title>
	<link rel="stylesheet" href="Booking_styles.css" type="text/css" />
</head>
<body>
<% String username=((String)session.getAttribute("username")); %>
<div id="container">
    <header>
    	<!-- %@include file="global/header.jsp"% -->
		<p>Welcome <%= username %>
    </header>
	
	<nav id="myheader">
		<!-- %@include file="global/nav.jsp"% -->
    </nav>
	<%@ page import="java.util.*,java.text.*, java.io.*,com.mongodb.*" %>
	
	<% 
		System.out.println("IN Reservations 1:::");
		String car_confirm_num = "";

        String pickupdate = "";
        String pickuptime = "";
        String pick = "";
        String dropoffdate = "";
        String dropofftime = "";
        String drop=  "";
        String drop_location = "";
        String pickup_location = "";
        boolean cancel_true = false;

        car_confirm_num = request.getParameter("con_num");

        System.out.println("IN Reservations 1:::");

		HashMap<String, List<Manage_reservations_model>> mapInFile_cancel = new HashMap<String, List<Manage_reservations_model>>();

		List<Manage_reservations_model> manage_cancel_list = new ArrayList<Manage_reservations_model>();

		mapInFile_cancel = MySqlDataStoreUtilities.selectRentalDetailsCancel(car_confirm_num);

		//System.out.println("IN Reservations 2 mapInFile:::"+len(mapInFile));

		System.out.println("IN Reservations 2 mapInFile:::"+mapInFile_cancel.size());
	
%>
  
<%
  for (Map.Entry<String,List<Manage_reservations_model>> ee : mapInFile_cancel.entrySet()) 
  {
            car_confirm_num = ee.getKey();
            System.out.println("IN Reservations confirmation_num::"+car_confirm_num);
            manage_cancel_list = ee.getValue();
            System.out.println("IN Reservations manage_cancel_list::"+manage_cancel_list.size());

    for (Manage_reservations_model manage : manage_cancel_list) {

    pick= manage.getPick_up_date()+' '+manage.getPick_up_time();

    System.out.println("IN CancelBooking pick:::"+pick);

    /*SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-DD hh:mm");
    Date now = new Date();
    String strDate = sdfDate.format(now);
    System.out.println("IN CancelBooking strDate:::"+strDate); 

    Date current_date_time = sdfDate.parse(strDate);
    System.out.println("IN CancelBooking current_date_time:::"+current_date_time);*/

    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    Date s_pickup = sdf.parse(pick);
    System.out.println("IN CancelBooking s_pickup::"+s_pickup);

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,1);
        Date todate1 = cal.getTime();    
        String fromdate = dateFormat.format(todate1);
        Date current_date_time = dateFormat.parse(fromdate);
        System.out.println("IN CancelBooking current_date_time:::"+current_date_time);

    cancel_true = current_date_time.after(s_pickup);
    System.out.println("IN CancelBooking cancel_true:::"+cancel_true);
    if(cancel_true){
%>
    <h2> You can not cancel the booking withing last 24 hours </h2>    
<%
    }
    else{

    MySqlDataStoreUtilities.cancelReservation(car_confirm_num);
%>
    <h2> Your Booking Has Been Canceled </h2>
<%
}
    //after = date.after(date2);

    /*System.out.println("From Date Session dateDifference::"+dateDifference);
    long datediff= dateDifference/(24*60*60 * 1000);

    System.out.println("From Date Session datediff::"+datediff);
    System.out.println("From Date Session for IFF::"+dateDifference % (24*60*60 * 1000));
    if((dateDifference % (24*60*60 * 1000)) == 0){
        total = price*(datediff);
        System.out.println("IFFFF From Date Session total::"+total);
    }else{
        total = price*(datediff+1);
        System.out.println("ELSEEEE From Date Session total::"+total);
    }*/
%>
   
<%  } %>

<%
}
%>


<h5>Want To Manage Your Reservations ?- <a href="manage_user_reservations.jsp">Manage My Reservations</a>
<h5>Want To Book Another Car ?- <a href="BookCar.jsp">Back To Car Reservation</a>