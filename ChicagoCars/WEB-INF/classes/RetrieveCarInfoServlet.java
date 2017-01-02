

import java.io.PrintWriter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dboperations.CreateMongoDBEntities;
import utilities.Request;


public class RetrieveCarInfoServlet extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		//String[] nameValue = firstPair.split("=");        
		response.setContentType("text/html");
	    PrintWriter pw = response.getWriter();
		String s1="<div name='autofillform'><input type='text' name='searchId' value='' id='searchId' placeholder='Search here...' style='width:246px;padding: 5px; font-size: 16px;' />";
        String s2="<div id='auto-row'><table bgcolor='#668CB7' style='width:246px;position: absolute;' id='complete-table'></table></div></div>";
        pw.println("<!doctype html><html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>ChicagoCars Search</title>");
		pw.println("<link rel='stylesheet' href='SearchResultPage_styles.css' type='text/css' /><script type='text/javascript' src='SignIn.js'></script><script type='text/javascript' src='easySearch.js'></script></head><body onload='doCompletion()'><div id='container'><header><h1><a href='Home'>CHICAGO<div>CARS</div></a></h1>");
		pw.println("</header><nav><ul><li class=\"start selected\"><a href=\"Home\">Home</a></li><li class=\"start selected\"><a href=\"ReadReview.jsp\">What Others Say About Our Service</a></li><li class=\"start selected\"><a href=\"WriteReview.jsp\">Give Us Reviews</a></li>");
		if(request.getSession().getAttribute("email")==null || request.getSession().getAttribute("email").equals("Guest")){
			pw.println("<li class=\"start selected\"><a href=\"LoginPage.jsp\">Login/ Signup</a></li>"+
					"<span class=\"showLoginDetails\">Welcome Guest</span>");
		}
		else{
			pw.println("<li class=\"start selected\"><a onclick=\"return signOut();\">Logout</a></li>+"
					+ "<span class=\"showLoginDetails\">Welcome "+request.getSession().getAttribute("email")+"</span>");
		}
		
        pw.println("</ul></nav>");
		pw.println("<div id='body'><section id='content'><div id='noresult' style='display: none;'><h4>NO CARS FOUND</h4></div>");
        HttpSession session = request.getSession(true);
        CreateMongoDBEntities cmd = new CreateMongoDBEntities();
        
/*        Request.pZipCodes = new ArrayList<String>() {{
                add("60616");
                add("60666");
                add("90210");
        }};
        Request.dZipCodes = new ArrayList<String>() {{
                add("90210");
                add("60616");
        }};
        
        Request.picktime="15:00";
        Request.droptime="23:00";
        Request.pickdate="2016-11-21";
        Request.dropdate="2016-11-21";*/
       
        Request.pZipCodes = (List<String>)session.getAttribute("pickLocation");
        Request.dZipCodes = (List<String>)session.getAttribute("dropLocation");
        Request.picktime = (String)session.getAttribute("pickTime");
        Request.droptime = (String)session.getAttribute("dropTime");
        Request.pickdate = (String)session.getAttribute("pickDate");
        Request.dropdate = (String)session.getAttribute("dropDate");
   
        /*List<Car> l = cmd.getAvailableCars();
        if(nameValue[0].equals("category")){
            for(Car c : l){
                pw.println("<div id = 'product'><div id = 'image'><img class='header-image' align = 'right' src = '' /></div><div id = 'details'>");
                pw.println("<div> ID: "+c.getCarID()+"</div><div> Name: "+c.getCarName()+"</div><div> Price: $"+ c.getPrice() +" </div>");
                pw.println("<div> Price: "+ c.getTransmission() +"</div>");
                pw.println("<div> Num of Seats: "+ c.getNumber_of_seats() +"</div>");
                pw.println("<div id = 'addtocartbutton'><a href='/A4/CartInfoServlet?o="+c.getCarID()+"' class='button' name='addtocart'>Add to Cart</a></div></div></div><br>");
            }
        }else{
            for(Car c : l){
                pw.println("<div id = 'product'><div id = 'image'><img class='header-image' align = 'right' src = '' /></div><div id = 'details'>");
                pw.println("<div> ID: "+c.getCarID()+"</div><div> Name: "+c.getCarName()+"</div><div> Price: $"+ c.getPrice() +" </div>");
                pw.println("<div> Price: "+ c.getTransmission() +"</div>");
                pw.println("<div> Num of Seats: "+ c.getNumber_of_seats() +"</div>");
                pw.println("<div id = 'addtocartbutton'><a href='/A4/CartInfoServlet?o="+c.getCarID()+"' class='button' name='addtocart'>Add to Cart</a></div></div></div><br>");
            }
        }
        */
		pw.println("</section><aside class='sidebar'>");
        displayManufacturer(pw, cmd);
        displayCarTypes(pw, cmd);
        displayNumOfSeats(pw, cmd);
        priceRange(pw);
        pw.println("</aside><div class='clear'></div></div><footer><div class='footer-bottom'><p>&copy; ChicagoCars 2016.</p> </div></footer> ");
        pw.println("</div></footer></div></body></html>");		
    } 
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    } 
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }
    
    protected void displayManufacturer(PrintWriter pw, CreateMongoDBEntities cmd){
        List<String> l = cmd.getUniqueItems("manufacturer");
        pw.println("<ul><li><h4>MANUFACTURER</h4><ul><div id='manufacturer_f'>");
        for(String s:l){  
            pw.println("<input type='checkbox' name='brand' value='"+s+"' onchange='filter()'/>    "+s+"<br>");
        }
        pw.println("</div></li></ul></li></ul>");
    }
    
    protected void displayCarTypes(PrintWriter pw, CreateMongoDBEntities cmd){
        List<String> l = cmd.getUniqueItems("carType");
        pw.println("<ul><li><h4>CAR TYPE</h4><ul><div id='cartype_f'>");
        for(String s:l){  
            pw.println("<input type='checkbox' name='ctype' value='"+s+"' onchange='filter()'/>    "+s+"<br>");
        }
        pw.println("</div></li></ul></li></ul>");
    }
    
    protected void displayNumOfSeats(PrintWriter pw, CreateMongoDBEntities cmd){
        List<String> l = cmd.getUniqueItems("number_of_seats");
        pw.println("<ul><li><h4>SEATS</h4><ul><div id='numseats_f'>");
        for(String s:l){  
            pw.println("<input type='checkbox' name='seat' value='"+s.charAt(0)+"' onchange='filter()'/>    "+s.charAt(0)+"<br>");
        }
        pw.println("</div></li></ul></li></ul>");
    }
    
    protected void priceRange(PrintWriter pw){
        pw.println("<ul><li><h4>PRICE RANGE</h4><ul><div id='price_f'>"); 
        pw.println("<input type='radio' name='prc' value='5-10' onchange='filter()'/>  5-10<br>");
        pw.println("<input type='radio' name='prc' value='10-20' onchange='filter()'/>  10-20<br>");
        pw.println("<input type='radio' name='prc' value='20-30' onchange='filter()'/>  20-30<br>");
        pw.println("<input type='radio' name='prc' value='30-40' onchange='filter()'/>  30-40<br>");
        pw.println("<input type='radio' name='prc' value='40-50' onchange='filter()'/>  40-50<br>");
        pw.println("</div></li></ul></li></ul>");
    }
}