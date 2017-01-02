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

public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignUp() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DBObject retVal = new BasicDBObject();
		//String qs = request.getQueryString();
		//String queryString = URLDecoder.decode(qs, "UTF-8");
		//DBObject action = (DBObject) JSON.parse(queryString);

		response.setContentType(request.getContentType());
		Scanner scanner = new Scanner(request.getInputStream());
		String details = scanner.nextLine();
		DBObject dbObject = (DBObject) JSON.parse(details);
		response.setContentType("application/json");

		switch(request.getParameter("action")){

		//Validating the email for duplicate entry
		case "1":
			String email = (String) dbObject.get("email");
			retVal.put("validity", validateEmail(email));
			break;

			//Store new user in DB
		case "2":
			try {
				MySQLConnector connector = MySQLConnector.getInstance();
				connector.createConnection();
				String values = "'"+dbObject.get("email")+"','"+dbObject.get("firstname")+"','"+dbObject.get("lastname")+"','"+dbObject.get("phone")+"','"+dbObject.get("address")+"','"+dbObject.get("password")+"','"+"USER'";
				String command = "INSERT INTO login VALUES("+values+");";
				connector.execute(command);
				retVal.put("retval", "OK");
				request.getSession().setAttribute("email", dbObject.get("email"));
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			break;

			//Reset password
		case "3":
			try {
				MySQLConnector connector = MySQLConnector.getInstance();
				connector.createConnection();
				String command1 = "select *  from chicagocars.login where email='"+dbObject.get("forgottenemail")+"' and phone='"+dbObject.get("phone")+"';";
				String command2 = "update chicagocars.login set pwd='"+dbObject.get("forgottenemail")+"' where email='"+dbObject.get("forgottenemail")+"';";
				if(!connector.executeQuery(command1).next()) 
					retVal.put("retval", "NOT OK");
				else {
					connector.execute(command2);
					retVal.put("retval", "OK");
				}
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			break;
			
		}
		
		response.getWriter().write(retVal.toString());	

	}

	private String validateEmail(String email){
		try {
			MySQLConnector connector = MySQLConnector.getInstance();	
			connector.createConnection();
			ResultSet userEntry = connector.executeQuery("SELECT * FROM login WHERE email='"+email+"'");

			//If the email id does not exist then good to go
			if(!userEntry.next()) return "OK";

			return "NOT OK";


		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {			
			e.printStackTrace();
			return "ERROR";
		}

	}

}
