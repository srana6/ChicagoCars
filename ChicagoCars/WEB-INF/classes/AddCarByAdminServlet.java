
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import pojos.Car;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import javax.servlet.http.*;
import java.util.*;


public class AddCarByAdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	MongoClient mongoClient;
	public String username="admin";
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongoClient = new MongoClient("localhost", 27017);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException 
	{
		
		MongoDatabase db = mongoClient.getDatabase("CarRentalDatabase");
		
		PrintWriter pw= response.getWriter();
	
		HttpSession session=request.getSession(); 
		//String username=((String)session.getAttribute("username")); 
		
		if(username.equals("admin")){
		
		//String carid = request.getParameter("carId");
        String carname = request.getParameter("carname");
		int numberofseats= Integer.valueOf(request.getParameter("numberofseats"));
		Float price= Float.parseFloat(request.getParameter("price"));
		String transmission= request.getParameter("transmission");
		String airconditioning= request.getParameter("airConditioning");
		String manufacturer= request.getParameter("manufacturer");
		String cartype= request.getParameter("carType");
		String carimage= request.getParameter("carimage");
		String location= request.getParameter("location");
		String message = "";
        //System.out.println(Float.valueOf(String.format("%.2f",price)));
		
		MongoCollection<BasicDBObject> collection = db.getCollection("Cars", BasicDBObject.class);
		
		
		//BasicDBObject queryObject = new BasicDBObject("carID",carid);
		//DBCursor cursor = collection.find(queryObject);

		//if(cursor.count() == 0){
			BasicDBObject newCarInfo = new BasicDBObject("carID",returnNewID())
							.append("carName",carname)
							.append("number_of_seats",numberofseats)
							.append("price",price)
                            .append("rating",5)
							.append("transmission",transmission)
							.append("airConditioning",airconditioning)
							.append("manufacturer",manufacturer)
							.append("carType",cartype)
							.append("filename",carimage)
							.append("location_id",location);
			
			collection.insertOne(newCarInfo);
			
			message = "Car successfully added";
		//}else{
			//message = "Car id already exist";
		//}
		session.setAttribute("message",message);
		response.sendRedirect("AdminDisplayServlet");
	  
	 }else 
	 
	 {
	 
	 
			String docType ="<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
				"Transitional//EN\">\n";
				pw.println(docType+"<html>"+
				"<head>"+
				"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
				"<h1><a href='#'><img src='12.png'></a><h1>"+ 	
				"<link rel='stylesheet' href='Admin_styles.css' type='text/css' />"+
				"</head>"+
				"<body>"+
				"<div id='container'>"+
				"<header>"+
				"<p>Welcome: "+username+"</p>"+
				"<a href='#'>Logout</a>"+
				"</header>"+
				"<P> You don not have permission for this page </p>");
					
				
				pw.println("<footer>"+		
				"<div class='footer-bottom'>"+
				"<p>FALL 2016 - CSP595 Project by TEAM - 17</p>"+
				"</div>"+
				"</footer>"+
				"</div>"+
				"</body>"+
				"</html>");
			
		
		
	 
	 
	 }
		
	}
    
    String returnNewID(){
        MongoDatabase db = mongoClient.getDatabase("CarRentalDatabase");
        MongoCollection<BasicDBObject> collection = db.getCollection("Cars", BasicDBObject.class);
        List<Car> clist = new ArrayList<Car>();
        BasicDBObject query = new BasicDBObject();
        List<BasicDBObject> foundDocument = collection.find(query).into(new ArrayList<BasicDBObject>());
        for(BasicDBObject obj: foundDocument){
            float rating=0;
            if(obj.getString("rating")==null)
                rating=5;
            else
                rating = Float.valueOf(obj.getString("rating"));
            String val = new String(String.valueOf(obj.getString("number_of_seats").charAt(0)));
            Car c = new Car(obj.getString("carID"),obj.getString("carName"),obj.getString("carType"),obj.getString("manufacturer"),obj.getString("transmission"), Integer.valueOf(val), Float.valueOf(obj.getString("rating")), obj.getString("airConditioning"), Float.valueOf(obj.getString("price")), obj.getString("filename"),obj.getString("location_id"));
            clist.add(c);
        }
		List<Integer> li = new ArrayList<Integer>();
		for(Car p:clist){
			li.add(Integer.parseInt(p.getCarID().substring(1)));
		}
        Collections.sort(li);
        if(li.size()==0)
            return "c1";
		return "c"+(li.get(li.size()-1)+1);
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