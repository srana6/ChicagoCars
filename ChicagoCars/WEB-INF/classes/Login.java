import java.io.IOException;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import dboperations.MySQLConnector;



public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String details = request.getQueryString();
		String decodedstring = URLDecoder.decode(details, "UTF-8");
		DBObject dbObject = (DBObject) JSON.parse(decodedstring);
		String fullname = (String) dbObject.get("fullname");
		String email = (String) dbObject.get("email");
		
		switch((String)dbObject.get("action")){
		
		case "googlesignin":
			request.getSession().setAttribute("email", request.getParameter("email"));
			break;
		
		case "logout":
			request.getSession().setAttribute("email", "Guest");			
			break;
			
		}
		

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		response.setContentType(request.getContentType());
		Scanner scanner = new Scanner(request.getInputStream());
		String details = scanner.nextLine();
		DBObject dbObject = (DBObject) JSON.parse(details);
		String email = (String) dbObject.get("email");
		String password = (String) dbObject.get("pwd");		

		response.setContentType("application/json");
		DBObject retVal = new BasicDBObject();
		retVal.put("credentialstatus", validateCredentials(email, password, request));
		
		response.getWriter().write(retVal.toString());
		scanner.close();


	}

	private String validateCredentials(String email, String password, HttpServletRequest request){
		try {
			MySQLConnector connector = MySQLConnector.getInstance();	
			connector.createConnection();
			ResultSet userEntry = connector.executeQuery("SELECT * FROM login WHERE email='"+email+"'");
			
			//If the email id does not exist
			if(!userEntry.next()) return "NOT OK";
						
			if(password.equals((String)userEntry.getString("pwd"))){
				request.getSession().setAttribute("email", email);
				//If admin redirect to Admins page
				if(email.equals(MySQLConnector.adminEmail)){
					return "ADMIN";
				}
				return "OK";
			}

			return "NOT OK";


		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {			
			e.printStackTrace();
			return "ERROR";
		}

	}

}
