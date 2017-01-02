package utilities;

import java.util.HashMap;
import java.util.Map;
import java.sql.*; 
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class AjaxUtility
{
	public static HashMap <String,Zipcode> getData() throws Exception
	{
		HashMap <String,Zipcode> hm = new HashMap <String,Zipcode>();
		Connection conn = null;
		Statement stmt= null;
		ResultSet rs= null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chicagocars","root","root");
			stmt=conn.createStatement();
			String selectCustomerQuery ="select * from zipcodes"; 
			rs = stmt.executeQuery(selectCustomerQuery);
			while(rs.next())
			{
				Zipcode p = new Zipcode(rs.getString("ZipCode"),rs.getString("City"),rs.getString("State"));
				hm.put(rs.getString("ZipCode"),p);
			}
		}
		catch(Exception e){
			throw e;
		}
		finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
			try { stmt.close(); } catch (Exception e) { /* ignored */ }
			try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
		return hm;
	}
	
	public static List<String> getZipcodes(String city) throws Exception
	{
		List<String> zipcodes = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pst= null;
		ResultSet rs= null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chicagocars","root","root");
			String selectCustomerQuery ="select * from zipcodes where city = ?"; 
			pst = conn.prepareStatement(selectCustomerQuery);
			pst.setString(1,city.toUpperCase());
			rs = pst.executeQuery();
			while(rs.next())
			{
				zipcodes.add(rs.getString("ZipCode"));
				
			}
		}
		catch(Exception e){
			throw e;
		}
		finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
			try { pst.close(); } catch (Exception e) { /* ignored */ }
			try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
		return zipcodes;
	}
	
	public static HashMap <String,Zipcode> getCityData(String searchId) throws Exception
	{
		HashMap <String,Zipcode> hm = new HashMap <String,Zipcode>();
		Connection conn= null;
		PreparedStatement pst= null;
		ResultSet rs= null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chicagocars","root","root");
			
			String selectCustomerQuery ="select * from zipcodes where city like ? group by city;"; 
			pst =	conn.prepareStatement(selectCustomerQuery);
			pst.setString(1,searchId.toUpperCase()+"%");
			rs = pst.executeQuery();
			
			while(rs.next())
			{
				Zipcode p = new Zipcode(rs.getString("ZipCode"),rs.getString("City"),rs.getString("State"));
				hm.put(rs.getString("ZipCode"),p);
			}
			
		}
		catch(Exception e){
			throw e;
		}
		finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
			try { pst.close(); } catch (Exception e) { /* ignored */ }
			try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
		return hm;
	}

	public List<String> readData(String searchId) throws Exception
	{
		try
		{
			HashMap <String,Zipcode> data = getCityData(searchId);
			Iterator it = data.entrySet().iterator();
			List<String> matched = new ArrayList<String>();
			while(it.hasNext())
			{
				Map.Entry pi = (Map.Entry)it.next();
				Zipcode p=(Zipcode) pi.getValue();
				if (p.getCity().toLowerCase().startsWith(searchId))
				{
					matched.add(p.getCity() );
				}
			}
			return matched;
		}
		catch(Exception e){
			throw e;
		}
	}
}