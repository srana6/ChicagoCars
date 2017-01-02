

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import pojos.BookingStatusModel;
import dboperations.*;
import java.util.*;


public class ManageReservationByAdminServlet extends HttpServlet{
	
	public String username;
	public HashMap<String,List<BookingStatusModel>> map;
	public List<BookingStatusModel> bookingstatuslist;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw= response.getWriter();
		
		HttpSession session=request.getSession();
		
		//username= ((String)session.getAttribute("username"));
		
		map= MySqlDataStore.fetchBookingData();
		System.out.println("The Size of hashmap is:::"+map.size());
		
		
       String docType =
                    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
                    "Transitional//EN\">\n";
                    
                    
                    
                pw.println(docType+"<html>"+
                "<head>"+
                "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
                "<title>Car Rentals</title>"+
                "<link rel='stylesheet' href='Admin_styles.css' type='text/css' />"+
                "</head>"+
                "<body>"+
                "<div id='container'>"+
                "<header>"+
               
               "<h1><a href='/'>CHICAGO<span>CARS</span></a></h1>"+
                
                
                "</header>");
                pw.println("<nav>"
                    + "<ul>"
                    + "<li class='start'><a href='#'>Home</a></li>");
                    
                   /* if(user == null){                   
                    out.println("<li class='r'><a href='login.jsp'>Login</a></li>");
                    }
                    else{
                    out.println("<li class='r'><a href='Logout'>Logout</a></li>");
                    }*/
                    pw.println("<li class= 'r'><a href='#'>Manage Your Reservations</a></li>"
                    + "<li><a href='#'>About US</a></li>"
                    + "</ul>"
                    + "</nav>");
            
            
            
            
            pw.println("<br><br><hr>");
            pw.println("<h1>All Reservations</h1>");
            if(map.size() <= 0){
                pw.println("There are no reservartion yet.");
            }
            else{
            
                pw.println("<table>"+
                "<tr>"+
                "<th>CarID</th>"+
                "<th>Pickup Date</th>"+
                "<th>Pickup Time</th>"+
                "<th>Drop Off Date</th>"+
                "<th>Drop Off Time</th>"+
                "<th>UserID</th>"+
                "<th>Booking Status</th>"+
                "<th>Confirmation Number</th>"+
                "<th>User Name</th>"+
                //"<th>Drop Off Date & Time</th>"+
                "</tr>");
                
                
                String userid="";
                String confirmationnumber = "";
                String pick="";
                String drop="";
                String firstName = "";
                String lastName = "";
                String address="";
                String phoneNumber="";
                String carmake="";
                String cartype="";
                String totalprice="";
                String carmodel="";
                String status="";
                
                for(Map.Entry<String,List<BookingStatusModel>> hm : map.entrySet()){
					String key= hm.getKey();
					bookingstatuslist= hm.getValue();
					
					
					for(BookingStatusModel bsm : bookingstatuslist){
				        
                    
                    pw.println("<tr>");
                    
                    //userid = obj.getString("user");
                    pw.println("<td>" +bsm.getBookCarId()+ "</td>");

                    //confirmationnumber = obj.getString("confirmation_number");
                    pw.println("<td>" +bsm.getBookCarPickDate()+ "</td>");

                   // pick =obj.getString("pick");
                    pw.println("<td>" +bsm.getBookCarPickTime()+ "</td>");
                    
                    //drop = obj.getString("drop");
                    pw.println("<td>" +bsm.getBookCarDropDate()+ "</td>");
                    
                    //carmodel = obj.getString("carmodel");
                    pw.println("<td>" +bsm.getBookCarDropTime()+ "</td>");
                                    
                   // totalprice = obj.getString("total_price");
                    pw.println("<td>" +bsm.getBookCarUserId()+ "</td>");
                    
                    
                   // firstName = obj.getString("first_name");
                    pw.println("<td>" +bsm.getBookCarStatus()+ "</td>");
                    
    
                   // lastName = obj.getString("last_name");
				   confirmationnumber=bsm.getBookCarConfirmNum();
				   System.out.println("The Confirmation number is ::::"+confirmationnumber);
                    pw.println("<td>" +bsm.getBookCarConfirmNum()+ "</td>");
                    
                    
                
                    
                   // address = obj.getString("address");
                    pw.println("<td>" +bsm.getUsername()+ "</td>");
                       
                    
                    
                   // phoneNumber = obj.getString("phone_number");
                   // pw.println("<td>" +bsm.getDropDateTime()+ "</td>");
                    
                   /* status = obj.getString("status");
                    out.println("<td>" +status+ "</td>");*/

                    pw.println("<td>"+
                        "<form class = 'submit-button' method = 'post' action = 'CancelReservationByAdminServlet'>"+
                        "<input type = 'hidden' name = 'confirmation_number' value = '"+confirmationnumber+"'>"+
                        "<input type = 'hidden' name = 'hiddenUser' value = 'admin'>"+
                        "<input class = 'delete-button' type = 'submit' name = 'cancelOrder' value = 'Cancel Order'>"+
                        "</form>"+
                        "</td>");                    
                
                    pw.println("</tr>");
					
					}
					
				}
                    
                }

            String backURL =
          response.encodeURL("AdminDisplayServlet");
       
        // "Proceed to Checkout" button below table
         pw.println
          ("</TABLE>\n" +
           "<FORM ACTION=\"" + backURL + "\" METHOD=\"post\">\n" +
           "<BIG><CENTER>\n" +
          
           "<INPUT class='blue-button' TYPE=\"SUBMIT\"\n" +
           "       VALUE=\"Go to Home Page\">\n" +
           
           "</CENTER></BIG></FORM>");

                String myPageBottom = "<footer>"
                    + "<div class=\"footer-bottom\">"
                    + "<p>FALL 2016 - CSP595 Project by TEAM - 17</p>"
                    + "</div>"
                    + "</footer>"
                    + "</div>"
                    + "</body>"
                    + "</html>";
        
        pw.println(myPageBottom);
		
		
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }
	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

}