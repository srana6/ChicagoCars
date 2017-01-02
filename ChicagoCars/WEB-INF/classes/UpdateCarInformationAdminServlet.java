
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import com.mongodb.*;


public class UpdateCarInformationAdminServlet extends HttpServlet{
	
	public String username="admin";
	public String message="";
	public PrintWriter pw;
	MongoClient mongoClient;
	
	public void init() throws ServletException{
		mongoClient=new MongoClient("localhost",27017);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException 
	{
		HttpSession session = request.getSession();
		//String username=((String)session.getAttribute("username")); 
		pw = response.getWriter();
		
		if(username.equals("admin")){
			String carid = request.getParameter("carid");
			
			if(request.getParameter("delete_car") != null ){
				deleteCar(carid);
				message = "Car successfully removed";
				session.setAttribute("message",message);
				response.sendRedirect("AdminDisplayServlet");
			}
			
			if(request.getParameter("update_car") != null ){
				updateCarForm(request, response, carid);
			}
			
			if(request.getParameter("execute_update") != null ){
				updateCarInfo(request, response, carid);
				message = "Car details for Car ID: "+carid+" updated successfully";
				//session.setAttribute("message",message);
				response.sendRedirect("AdminDisplayServlet");
			}
			
		}else{
			pw.println("<head><title>Car Rentals</title><link rel='stylesheet' href='Admin_styles.css' type='text/css' /></head><body><div id='container'><header>"+
				"<h1><a href='/'>MID-WEST<span>CAR RENTALS</span></a></h1></header><nav><ul><li class='start'><a href='index.jsp'>Home</a></li><li class='r'>"+
				"<a href='login.jsp'>Login</a></li><li class='r'></li><li class= 'r'></li>"+
				"<li><a href='about-us.jsp'>About US</a></li></ul></nav><hr>");
			
			pw.println("<div id=\"body\"><article>");
			pw.println("<P> You don not have permission for this page </p></article>");
			pw.println("<div class='clear'></div></div>"+
				"<footer><div class='footer-content'> "+
				"<div class='clear'></div></div><div class='footer-bottom'>"+
				"<p>&copy; ChicagoCars 2016.</p></div></footer>"+
				"</div></body></html>");
		}
		
		
	}
	
	private void deleteCar(String carid){
		try{
			DB db = mongoClient.getDB("CarRentalDatabase");
			DBCollection collection = db.getCollection("Cars");
			
			BasicDBObject queryObject= new BasicDBObject("carID",carid);
			
			collection.remove(queryObject);	 
			 
		}
		catch(MongoException e){
			e.printStackTrace();
		}
		
	}
	
	private void updateCarForm(HttpServletRequest request, HttpServletResponse response, String carid) throws ServletException, IOException{
		pw = response.getWriter();
		String contentTitle = "Update Car Details";
		pw.println("<head><title>Car Rentals</title><link rel='stylesheet' href='Admin_styles.css' type='text/css' /></head><body><div id='container'><header>"+
		"<h1><a href='Home'>CHICAGO<span>CARS</span></a></h1>"+
		"</header><nav><ul><li class='start'><a href='#'>Home</a></li><li class='r'>"+
		"<li class='r'></li><li class= 'r'>"+
		"</ul></nav><hr>"+
		"<center><h2>"+contentTitle+"</h2></center>");
		
		try{
			DB db = mongoClient.getDB("CarRentalDatabase");
			DBCollection collection =db.getCollection("Cars");
			
			BasicDBObject queryObject = new BasicDBObject();
			queryObject.put("carID",carid);
			
			DBCursor cursor=collection.find(queryObject);
			
			if(cursor.count()!=0){
				while(cursor.hasNext()){
					BasicDBObject obj = (BasicDBObject) cursor.next();
					pw.println("<form action='' method='POST'><table><tr><td>Car Id</td>"+
							"<td><input type='text' name='carid' value='"+obj.getString("carID") +"' readonly></td></tr><tr><td>Car Name</td>"+
							"<td><input type='text' name='carname' value='"+obj.getString("carName") +"' ></td></tr><tr><td>Number of Seats</td>"+
							"<td><input type='text' name='numberofseats' value='"+obj.getString("number_of_seats") +"' ></td></tr><tr><td>Price</td>"+
							"<td><input type='text' name='price' value='"+obj.getString("price") +"' ></td></tr><tr><td>Transmission</td>"+
							"<td><input type='text' name='transmission' value='"+obj.getString("transmission") +"' ></td></tr><tr><td>Air Conditioning</td>"+
							"<td><input type='text' name='airconditioning' value='"+obj.getString("airConditioning") +"' ></td></tr><tr><td>Manufacturer</td>"+	
							"<td><input type='text' name='manufacturer' value='"+obj.getString("manufacturer") +"' ></td></tr><tr><td>Car Type</td>"+
							"<td><input type='text' name='cartype' value='"+obj.getString("carType") +"' ></td></tr><tr><td>Location</td>"+
							"<td><input type='text' name='location' value='"+obj.getString("location_id") +"' ></td></tr>"+
							"<td><input class='formbutton' type='submit' name='execute_update' value='Update'></td></tr></table></form>");
				}
			}
		}catch(MongoException e){
			e.printStackTrace();
		}
		pw.println("<footer>"+
				"<div class=\"footer-bottom\">"+
				"<p>FALL 2016 - CSP595 Project by TEAM - 17</p>"+
				"</div>"+
				"</footer>"+
				"</div>"+
				"</body>"+
				"</html>");
				
				
		
		//out.println(myPageBottom);
	}
	
	private void updateCarInfo(HttpServletRequest request, HttpServletResponse response, String carid) throws ServletException, IOException{
		
		DB db = mongoClient.getDB("CarRentalDatabase");
		
		pw= response.getWriter();
		ServletContext sc=request.getSession().getServletContext();
		HttpSession session=request.getSession(); 
		
			
		String carname = request.getParameter("carname");
		int numberofseats= Integer.valueOf(request.getParameter("numberofseats"));
		float price= Float.parseFloat(request.getParameter("price"));
		String transmission= request.getParameter("transmission");
		String airconditioning= request.getParameter("airconditioning");
		String manufacturer= request.getParameter("manufacturer");
		String cartype= request.getParameter("cartype");
		String location= request.getParameter("location");
		String message = "";
		
		try{
		DBCollection collection= db.getCollection("Cars");
		
		BasicDBObject queryObject = new BasicDBObject();
		queryObject.put("carID",carid);
		
		BasicDBObject carUpdate =new BasicDBObject("carName",carname)
					.append("number_of_seats",numberofseats)
					.append("price",price)
					.append("transmission",transmission)
					.append("airConditioning",airconditioning)
					.append("manufacturer",manufacturer)
					.append("carType",cartype)
					.append("location_id",location.toLowerCase());
					
		BasicDBObject updateCarInfo = new BasicDBObject("$set",carUpdate);
		
		collection.update(queryObject,updateCarInfo);
		}catch(MongoException e){
			e.printStackTrace();
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