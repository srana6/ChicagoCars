package dboperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnector {
	
	public static String adminEmail = "admin@admin.com";
	private static MySQLConnector mySQLConnector = new MySQLConnector();
	private Connection connection;
	private Statement statement; 
	
	private MySQLConnector(){}
	
	public static MySQLConnector getInstance(){
		return mySQLConnector;
	}
	
		
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public void createConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{		
		if(connection==null || connection.isClosed()){
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chicagocars", "root", "root");
			statement = connection.createStatement();	
			
		}
	}

	public void closeConnection() throws SQLException{		
		statement.close();
		connection.close();	
	}
	
	public ResultSet executeQuery(String command) throws SQLException{		
		return statement.executeQuery(command);				
	}
	
	public void execute(String command) throws SQLException{
		statement.execute(command);
	}
	
	@Override
	protected void finalize() throws Throwable {
		closeConnection();
		super.finalize();
	}
	
	

}
