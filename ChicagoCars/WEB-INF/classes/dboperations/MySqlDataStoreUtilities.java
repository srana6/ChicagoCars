package dboperations;
import pojos.Manage_reservations_model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MySqlDataStoreUtilities
{
		public static Connection conn = null;
		public static PreparedStatement pst;
		public static ResultSet rs;
		public static ResultSet rs_one;
		public static Statement stmt;
		public static Statement stmt_one;
		public static Manage_reservations_model manage;
		public static Manage_reservations_model manage_cancel;
		public static ArrayList<Manage_reservations_model> selectUserRentalDetails_list;
		public static HashMap<String, List<Manage_reservations_model>> selectUserRentalDetails_hash;
		public static HashMap<String, List<Manage_reservations_model>> selectRentalDetailsCancel_hash;			
		public static ArrayList<Manage_reservations_model> selectRentalDetailsCancel_list;

		public void getConnection()
		{
			try
			{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/chicagocars","root","root");
			}
			catch(Exception e)
			{}
		}


		public static void insert_order_history_user(String firstName){
			try{
				System.out.println("IN insertUserRentalHistory1::"+firstName);
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chicagocars","root","root");
				//String insertIntoCustomerRegisterQuery = "INSERT INTO userrentalhistory(user_name_pk) "+ "VALUES (?);";
				String query = " insert into order_history_user"+ " values (?);";
				System.out.println("IN order insertUserRentalHistory2::"+query);
				pst = conn.prepareStatement(query);
				System.out.println("IN order insertUserRentalHistory3::"+pst);
				pst.setString(1,firstName);
				pst.execute();
				System.out.println("IN order insertUserRentalHistory4::");
				//pst.execute();
				}
				catch(Exception e){}

				finally {
		    try { pst.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}

		}


		public static void insert_order_history_user_details(String user_car_id,String car_pick_date,String car_pick_time,String car_drop_date,String car_drop_time,String car_rent,String car_pick_location,String user_address,String user_phone , String user_credit_card,String user_license, String user_name_pk, String booking_status, String confirm_num){

				try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chicagocars","root","root");
				System.out.println("IN insertUserRentalDetails1::");
				String insertIntoCustomerRegisterQuery = "insert into order_history_user_details"+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
				System.out.println("IN insertUserRentalDetails2::"+insertIntoCustomerRegisterQuery);
				pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
				System.out.println("IN insertUserRentalDetails3::"+pst);
				pst.setString(1,user_car_id);
				pst.setString(2,car_pick_date);
				pst.setString(3,car_pick_time);
				pst.setString(4,car_drop_date);
				pst.setString(5,car_drop_time);
				pst.setString(6,car_rent);
				pst.setString(7,car_pick_location);
				pst.setString(8,user_address);
				pst.setString(9,user_phone);
				pst.setString(10,user_credit_card);
				pst.setString(11,user_license);
				pst.setString(12,user_name_pk);
				pst.setString(13,booking_status);
				pst.setString(14,confirm_num);
				pst.execute();
				System.out.println("IN insertUserRentalDetails4::");
				}
				catch(Exception e){}
				finally {
		    try { pst.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
	}


	public static HashMap<String, List<Manage_reservations_model>> selectUserRentalDetails(String key){

			try{
			System.out.println("IN Reservations SQL key:::"+key);
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chicagocars","root","root");
			String selectIntoCustomerRegisterQuery = "SELECT car_pick_date,car_pick_time,car_drop_date,car_drop_time,car_rent,car_pick_location,car_confirm_num,car_book_status FROM order_history_user_details WHERE user_name_pk ='"+key+"';";
			System.out.println(" selectUpdatedOrderHistory incoming data:"+selectIntoCustomerRegisterQuery);
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(selectIntoCustomerRegisterQuery);

			System.out.println("IN Reservations SQL rs:::"+rs);

			selectUserRentalDetails_hash = new HashMap<String, List<Manage_reservations_model>>();			
			
			while (rs.next()) {
			    	selectUserRentalDetails_list = new ArrayList<Manage_reservations_model>();
					manage = new Manage_reservations_model();
					System.out.println("IN Reservations SQL rs.getString(1):::"+rs.getString(1));
					manage.setPick_up_date(rs.getString(1));
					System.out.println("IN Reservations SQL rs.getString(2):::"+rs.getString(2));
					manage.setPick_up_time(rs.getString(2));
					System.out.println("IN Reservations SQL rs.getString(3):::"+rs.getString(3));
					manage.setDrop_off_date(rs.getString(3));
					System.out.println("IN Reservations SQL rs.getString(4):::"+rs.getString(4));
					manage.setDrop_off_time(rs.getString(4));
					System.out.println("IN Reservations SQL rs.getString(5):::"+rs.getString(5));
					manage.setTotal_car_rent(rs.getString(5));
					System.out.println("IN Reservations SQL rs.getString(6):::"+rs.getString(6));
					manage.setPick_up_location(rs.getString(6));
					System.out.println("IN Reservations SQL rs.getString(7):::"+rs.getString(7));
					manage.setConfirm_num(rs.getString(7));
					System.out.println("IN Reservations SQL rs.getString(8):::"+rs.getString(8));
					manage.setBook_status(rs.getString(8));
					
					selectUserRentalDetails_list.add(manage);
					System.out.println("IN Reservations SQL selectUserRentalDetails_list:::"+selectUserRentalDetails_list);
					
					selectUserRentalDetails_hash.put(rs.getString(7),selectUserRentalDetails_list);
				
			}
		}catch(Exception e){}

		finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}


			System.out.println("IN Reservations SQL selectUserRentalDetails_hash:::"+selectUserRentalDetails_hash);
			return selectUserRentalDetails_hash;
		

	}


	public static void insert_bookedcars(String book_car_id,String book_car_pick_date,String book_car_pick_time,String book_car_drop_date,String book_car_drop_time,String book_car_user_id, String book_car_status,String book_car_confirm_num,String pickup_date_time,String drop_date_time){

				try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chicagocars","root","root");
				System.out.println("IN insert_bookedcars1::");
				String insertIntoCustomerRegisterQuery = "insert into bookedcars"+ " values (?,?,?,?,?,?,?,?,?,?);";
				System.out.println("IN insert_bookedcars2::"+insertIntoCustomerRegisterQuery);
				pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
				System.out.println("IN insert_bookedcars3::"+pst);
				pst.setString(1,book_car_id);
				pst.setString(2,book_car_pick_date);
				pst.setString(3,book_car_pick_time);
				pst.setString(4,book_car_drop_date);
				pst.setString(5,book_car_drop_time);
				pst.setString(6,book_car_user_id);
				pst.setString(7,book_car_status);
				pst.setString(8,book_car_confirm_num);				
				pst.setString(9,pickup_date_time);
				pst.setString(10,drop_date_time);		
				pst.execute();
				System.out.println("IN insert_bookedcars4::");
				}
				catch(Exception e){}
				finally {
		    try { pst.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
	}


public static HashMap<String, List<Manage_reservations_model>> selectRentalDetailsCancel(String con_num){

			try{
			System.out.println("IN Cancel SQL con_num:::"+con_num);
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chicagocars","root","root");
			String selectIntoCustomerRegisterQuery = "SELECT user_car_id,car_pick_date,car_pick_time,car_drop_date,car_drop_time,car_confirm_num FROM order_history_user_details WHERE car_confirm_num ='"+con_num+"';";
			System.out.println(" selectUpdatedOrderHistory incoming data:"+selectIntoCustomerRegisterQuery);
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(selectIntoCustomerRegisterQuery);

			System.out.println("IN Cancel SQL rs:::"+rs);

			selectRentalDetailsCancel_hash = new HashMap<String, List<Manage_reservations_model>>();
			
			selectRentalDetailsCancel_list = new ArrayList<Manage_reservations_model>();
			while (rs.next()) {
			    // Get data at cursor
				//if(rs.getString(1).equals("Laptop")){
				
					manage_cancel = new Manage_reservations_model();
					System.out.println("IN Cancel SQL rs.getString(1):::"+rs.getString(1));
					manage_cancel.setCar_id(rs.getString(1));					
					System.out.println("IN Cancel SQL rs.getString(2):::"+rs.getString(2));
					manage_cancel.setPick_up_date(rs.getString(2));
					System.out.println("IN Cancel SQL rs.getString(3):::"+rs.getString(3));
					manage_cancel.setPick_up_time(rs.getString(3));
					System.out.println("IN Cancel SQL rs.getString(4):::"+rs.getString(4));
					manage_cancel.setDrop_off_date(rs.getString(4));
					System.out.println("IN Cancel SQL rs.getString(5):::"+rs.getString(5));
					manage_cancel.setDrop_off_time(rs.getString(5));
					System.out.println("IN Cancel SQL rs.getString(6):::"+rs.getString(6));
					manage_cancel.setConfirm_num(rs.getString(6));

					
					selectRentalDetailsCancel_list.add(manage_cancel);
					System.out.println("IN Cancel SQL selectRentalDetailsCancel_list:::"+selectRentalDetailsCancel_list);
					
					selectRentalDetailsCancel_hash.put(rs.getString(6),selectRentalDetailsCancel_list);
				
			}
		}catch(Exception e){}

		finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}


			System.out.println("IN Cancel SQL selectRentalDetailsCancel_hash:::"+selectRentalDetailsCancel_hash);
			return selectRentalDetailsCancel_hash;	

	}

	public static void cancelReservation(String car_confirm_num){

			try{
				System.out.println("The car_confirm_num to be deleted is: "+car_confirm_num);
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chicagocars","root","root");
			System.out.println("Here1");
			String selectIntoCustomerRegisterQuery = "DELETE FROM order_history_user_details WHERE car_confirm_num='"+car_confirm_num+"';";
			String selectIntoCustomerRegisterQuery_one = "DELETE FROM bookedcars WHERE book_car_confirm_num='"+car_confirm_num+"';";
			System.out.println("Here2");
			stmt = conn.createStatement();
			stmt_one = conn.createStatement();
			System.out.println("Here3"+stmt);
			int rs;
			int rs1;
			rs = stmt.executeUpdate(selectIntoCustomerRegisterQuery);
			rs1 = stmt_one.executeUpdate(selectIntoCustomerRegisterQuery_one);
			System.out.println("Here4"+rs);

		}
		catch(Exception e){}

		finally {			
		    try { stmt.close(); 
		    	  stmt_one.close();} catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}

		}


		
}

