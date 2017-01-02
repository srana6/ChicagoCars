package dboperations;

import java.sql.*;

public class CreateDBEntities {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost:3306/chicagocars?useSSL=false";
   static final String USER = "root";
   static final String PASS = "root";
   Connection conn;
   
   public CreateDBEntities(){
       try{
           Class.forName("com.mysql.jdbc.Driver");
           conn = DriverManager.getConnection(DB_URL, USER, PASS);
       }catch(Exception e){
           e.printStackTrace();
       }
   }

    public ResultSet getAllBookedCars(String pick, String drop) {
       ResultSet rs = null;
       try{
          String sql = "Select * from bookedcars where book_car_pick_date='"+pick+"' and book_car_drop_date='"+drop+"'";
          PreparedStatement ps = conn.prepareStatement(sql);
          rs = ps.executeQuery();      
       
       }catch(Exception e){
          e.printStackTrace();
       }
       return rs;
    }
}