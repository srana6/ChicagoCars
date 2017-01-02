

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dboperations.CreateMongoDBEntities;
import pojos.Car;
import utilities.Request;
import pojos.CarsUnavailable;

import java.util.*;


public class AjaxServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        System.out.println("AJAX REQUESTED");
		response.setContentType("text/xml");
        CreateMongoDBEntities cmd = new CreateMongoDBEntities();
        List<Car> l;
        String firstPair = request.getQueryString();
        if(firstPair==null)
            l = cmd.getCars();
        else{
            String [] keyValues = firstPair.split("&");
            List<String> filterList = new ArrayList<String>();
            for(String s:keyValues){
               filterList.add(s); 
            }
            l = cmd.filterCars(filterList);
        }
        //ResultSet rs = cd.getAllBookedCars(Request.pickdate, Request.dropdate);
        //CarsUnavailable cu = new CarsUnavailable();
        CarsUnavailable.cun.clear();
        CarsUnavailable.m.clear();
        CarsUnavailable.loadHashMap("load");
        CarsUnavailable.loadHashMap("check");
        //System.out.println("Request Variables");
        //for(String str:CarsUnavailable.cun){
          //  System.out.println(str);
        //}
        //System.out.println(Request.pickdate);
        //System.out.println(Request.dropdate);
        //System.out.println(Request.picktime);
        //System.out.println(Request.droptime);
        /*
        try{
            while(rs.next()){
                bl.add(rs.getString(1));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        */
        StringBuilder s = new StringBuilder();
        for(Car c:l){
            s.append("<car>");
            s.append("<id>"+c.getCarID()+"</id>");
            s.append("<name>"+c.getCarName()+"</name>");
            s.append("<nos>"+c.getNumber_of_seats()+"</nos>");
            s.append("<price>"+c.getPrice()+"</price>");
            s.append("<transmission>"+c.getTransmission()+"</transmission>");
            s.append("<ac>"+c.isAirConditioning()+"</ac>");
            s.append("<rating>"+c.getRatingID()+"</rating>");
            s.append("<manufacturer>"+c.getManufacturer()+"</manufacturer>");
            s.append("<cartype>"+c.getCarType()+"</cartype>");
            s.append("<booked>"+carBooked(c.getCarID())+"</booked>");
            s.append("<filename>"+c.getFilename()+"</filename>");
            s.append("<location>"+c.getLocation()+"</location>");
            s.append("</car>");
        }
        
        //System.out.println(l.size());
        response.getWriter().write("<cars>" + s.toString() + "</cars>");         
    }
    
    protected String carBooked(String id){
        if(CarsUnavailable.cun.contains(id))
            return "Yes";
        return "No";
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
            processRequest(request, response);
    } 
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
            processRequest(request, response);
    }
}