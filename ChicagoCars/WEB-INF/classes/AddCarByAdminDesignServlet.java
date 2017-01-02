

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;


public class AddCarByAdminDesignServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	//MongoClient mongoClient;
	public ServletContext sc;
	public String username="admin";
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw= response.getWriter();
		
		String contentTitle = "ADD NEW CAR";
		pw.println("<head><title>Car Rentals</title><link rel='stylesheet' href='Admin_styles.css' type='text/css' /></head><body><div id='container'><h1><a href='/'>CHICAGO<span>CARS</span></a></h1><header>"+ 
		"</header><nav><ul><li class='start'>"+
		"<li class='r'></li><li class= 'r'>"+
		 
		"<li><a href='#'>About US</a></li></ul></nav><hr>");
		//"<center><h2>"+contentTitle+"</h2></center>");
		pw.println("<section id='content'>"+
					"<article>"+	
					"<h2>Add Cars</h2>"+
				"<form method='post' action='AddCarByAdminServlet'>"+
				"<table cellpadding='2' cellspacing='1'>"+
				//"<tr><td>Car Id</td><td><input type='TEXT' size='15' name='carId'></input></td>"+
				"</tr><tr><td>Car Name</td><td><input type='text' size='15' name='carname'/></td>"+
				"</tr><tr><td>Number of Seats</td><td><input type='text' size='15' name='numberofseats'/></td>"+
				"</tr><tr><td>Rent</td><td><input type='TEXT' size='15' name='price'></input></td></tr>"+
				"<tr><td>Car Transmission</td><td><input type='TEXT' size='15' name='transmission'></input></td>"+
				"</tr><tr><td>Air Condition</td><td><input type='TEXT' size='15' name='airConditioning'></input></td></tr>"+
				"<tr><td>Manufacturer</td><td><input type = 'text' name = 'manufacturer'></td></tr>"+
				"<tr><td>Car Type</td>"+
				"<td><select name='carType'>"+
				"<option value='SUV'>SUV</option>"+
				"<option value='Standard'>Standard</option>"+
				"<option value='Economy'>Economy</option>"+
				"<option value='Luxury'>Luxury</option>"+
				"</select></td>"+
				"</tr>"+
				"<tr><td>Car Image</td><td><input type = 'text' name = 'carimage'></td></tr>"+
				"<tr><td>Location</td>"+
				"<td><input type = 'text' name = 'location'></td>"+
				"</tr>"+
				"<tr><td colspan='2'>"+
				"<center><input type='submit' class ='formbutton' value='Add Car' /></center>"+
				"</td>"+
				"</tr>"+
				"</table>"+
				"</form >"+
				"</article>"+
				"</section>");

			
			pw.println("<div class='clear'></div></div>"+
			"<footer><div class='footer-bottom'>"+
			"<p>	FALL 2016 - CSP595 Project by TEAM - 17</p></div></footer>"+
			"</div></body></html>");
	
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