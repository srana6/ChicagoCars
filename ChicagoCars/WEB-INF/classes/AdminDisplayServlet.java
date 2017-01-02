/*
ADMIN MAIN PAGE ALL CARS DISPLAY SERVELT
*/
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import java.util.*;


public class AdminDisplayServlet extends HttpServlet{
	MongoClient mongoClient;
	public ServletContext sc;
	public String username="admin";
	
	public void init() throws ServletException{
		mongoClient=new MongoClient("localhost",27017);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException 
	{
		sc=request.getSession().getServletContext();
		response.setContentType("text/html");
		PrintWriter pw= response.getWriter();
		
		DB db = mongoClient.getDB("CarRentalDatabase");
		
		HttpSession session=request.getSession();
		if(session!=null){
			//username= ((String)session.getAttribute("username"));
			
			pw.println("<head><title>Car Rentals</title><script type='text/javascript' src='SignIn.js'></script><link rel='stylesheet' href='Admin_styles.css' type='text/css' /></head><body><div id='container'><header>"+
				
				"<h1><a href='index.jsp'></a><h1>"+ 
				"<h1><a href='Home'>CHICAGO<span>CARS</span></a></h1>"+
				
				"</header><nav class=\"navigationbar\"><ul><li class='start'><a href='AdminDisplayServlet'>Home</a></li><li>"+
			
				"<a onclick=\"return signOut();\">Logout</a>"+
				"</li>"+
							"<li><a href='AddCarByAdminDesignServlet'>Add Car</a></li>"+
			"<li><a href='ManageReservationByAdminServlet'>Add/Manage Reservations</a></li>"+
				"</nav><hr>");
			
		pw.println("<div id=\"body\"><section id=\"content\"><article>");
		if(username.equals("admin")){
			
			DBCollection collection = db.getCollection("Cars");
			try{
				DBCursor cursor = collection.find();
				if(cursor.count()==0){
					pw.println("Sorry there are no cars to display");
				}
				pw.println("<form method='POST' action='UpdateCarInformationAdminServlet'> <table>"+
									"<tr>"+
									"<th></th>"+
									"<th>Car Id</th>"+
									"<th>Car Name</th>"+
									"<th>Number of Seats</th>"+
									"<th>Price</th>"+
									"<th>Transmission</th>"+
									"<th>Air Conditioning</th>"+
									//"<th>Rating</th>"+
									"<th>Manufacturer</th>"+
									"<th>Car Type</th>"+
									//"<th>Car Image</th>"+
									"<th>Location</th>"+
									"</tr>");
				while(cursor.hasNext()){
					BasicDBObject obj=(BasicDBObject)cursor.next();
					
					pw.println("<tr>");
					pw.println("<td><input type='radio' name='carid' value='"+obj.getString("carID")+"'></td>");
					pw.println("<td>"+obj.getString("carID")+"</td>");
					pw.println("<td>"+obj.getString("carName")+"</td>");
					pw.println("<td>"+obj.getString("number_of_seats")+"</td>");
					pw.println("<td>"+obj.getString("price")+"</td>");
					pw.println("<td>"+obj.getString("transmission")+"</td>");
					pw.println("<td>"+obj.getString("airConditioning")+"</td>");
					//pw.println("<td>"+obj.getString("rating")+"</td>");
					pw.println("<td>"+obj.getString("manufacturer")+"</td>");
					pw.println("<td>"+obj.getString("carType")+"</td>");
					//pw.println("<td>"+obj.getString("filename")+"</td>");
					pw.println("<td>"+obj.getString("location_id")+"</td>");
					pw.println("</tr>");
				}
				pw.println("</table>");
				pw.println("<input type='submit' class='blue-button' name='update_car' value='Update'>&nbsp;&nbsp;"+
							"<input class='delete-button' name='delete_car' type='submit' value='Delete'></form>");
									
			}catch(MongoException e){
					e.printStackTrace();
				}
			
		}
		else{
			
			pw.println("<P> You dont not have permission for this page </p>");
		}
		
			pw.println("</article></section>");
			
			pw.println("<div class='clear'></div></div>"+
			"<footer><div class='footer-bottom'>"+
			"<p>&copy; ChicagoCars 2016.</p> "+
			"</div></body></html>");
		}
		else{
			pw.println(	"<html>"+
					"<head>"+
					"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
					"<title>Product Added : Best Deal</title>" + "<link rel='stylesheet' href='styles.css' type='text/css' />"+
					"</head>"+
					"<body>"+
					"<div id='container'>"+
					"<header>" + "<div class='header_logo'>" + "<img src='images/best_deals.png'/>" + 
					"<h1><a href='#'>BEST <span>DEAL</span></a></h1>"+
					"<h2>new deals everyday</h2>" + "</div>" + "</header>"+
					"<h3>" + "<span style='width:310px;display:inline-block'></span>" + "Your Product Has Been Added" + "</h3>"+
					//ADD REDIRECTION TO LOGIN SCREEN IF FAILS
					"<span style='width:310px;display:inline-block'></span>"+"<a href='AdminDisplayServlet'>Due to Extra Security Reasons click here to see the changes</a>");  
					
					pw.close();
		}
		
		
		
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