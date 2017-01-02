package dboperations;
import pojos.BookingStatusModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MySqlDataStore{

	public static BookingStatusModel bsm;
	public static HashMap<String,List<BookingStatusModel>> bookingdatamap;
	public static List<BookingStatusModel> bookinglist;

	public static Connection conn = null;
	public static PreparedStatement pst;
	public static Statement stmt;
	public static ResultSet rs;


	public static HashMap<String, List<BookingStatusModel>> fetchBookingData(){
		try{
			System.out.println("Inside fetchBookingDATA");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chicagocars","root","root");
			String query = "SELECT * FROM bookedcars;";

			System.out.println("QUERY GENERATED");
			stmt= conn.createStatement();
			rs=stmt.executeQuery(query);

			System.out.println("The fetched data is:::"+rs);

			bookingdatamap= new HashMap<String,List<BookingStatusModel>>();
			//bookinglist = new ArrayList<BookingStatusModel>();

			while(rs.next()){
				//System.out.println("Heeere");
				bookinglist = new ArrayList<BookingStatusModel>();
				bsm=new BookingStatusModel();
				bsm.setBookCarId(rs.getString(1));
				bsm.setBookCarPickDate(rs.getString(2));
				bsm.setBookCarPickTime(rs.getString(3));
				bsm.setBookCarDropDate(rs.getString(4));
				bsm.setBookCarDropTime(rs.getString(5));
				bsm.setBookCarUserId(rs.getString(6));
				bsm.setBookCarStatus(rs.getString(7));
				bsm.setBookCarConfirmNum(rs.getString(8));
				bsm.setPickupDateTime(rs.getString(9));
				bsm.setDropDateTime(rs.getString(10));
				bsm.setUsername(rs.getString(11));

				bookinglist.add(bsm);

				bookingdatamap.put(rs.getString(8),bookinglist);

			}
		}catch(Exception e){}
		finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
			try { stmt.close(); } catch (Exception e) { /* ignored */ }
			try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
		return bookingdatamap;

	}

	public static void cancelOrderByAdmin(String confirmNumber){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRentalSqlDatabase","root","root");
			String query= "DELETE FROM bookedcars WHERE book_car_confirm_num='"+confirmNumber+"';";

			stmt = conn.createStatement();
			int rs;
			rs = stmt.executeUpdate(query);
		}
		catch(Exception e){}
		finally {
			try { stmt.close(); } catch (Exception e) { /* ignored */ }
			try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
	}

	public static void updateOrderHistoryByAdmin(String confirmNumber){
		try{
			String status="canceled by admin";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRentalSqlDatabase","root","root");
			String query= "UPDATE order_history_user_details SET car_book_status='"+status+"'WHERE car_confirm_num='"+confirmNumber+"';";

			stmt = conn.createStatement();
			System.out.println("updateSelectedOrderHistory Here3"+stmt);
			int rs;
			rs = stmt.executeUpdate(query);
		}catch(Exception e){}
		finally {
			try { stmt.close(); } catch (Exception e) { /* ignored */ }
			try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
	}

}