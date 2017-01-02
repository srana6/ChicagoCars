package utilities;


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import utilities.AjaxUtility;

import java.util.List;

@WebServlet("/ProcessLocation")
public class ProcessLocation extends HttpServlet {

	private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    } 

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
	
		try
		{
			String pickLocation = (String)request.getParameter("pickLocation");
			String dropLocation = (String)request.getParameter("dropLocation");
			

			
			String pickDate = (String)request.getParameter("pickDate");
			String pickTime = (String)request.getParameter("pickTime");
			
			String dropDate = (String)request.getParameter("dropDate");
			String dropTime = (String)request.getParameter("dropTime");
			
			System.out.println("pickLocation :"+pickLocation);
			System.out.println("dropLocation :"+dropLocation);
			
			System.out.println("pickDate :"+pickDate);
			System.out.println("pickTime :"+pickTime);
			
			System.out.println("dropDate :"+dropDate);
			System.out.println("dropTime :"+dropTime);
			
			List<String> pickLocations = AjaxUtility.getZipcodes(pickLocation);
			List<String> dropLocations = AjaxUtility.getZipcodes(dropLocation);
			
			HttpSession session = request.getSession(true);
			
			session.setAttribute("startLocation", pickLocation);
			session.setAttribute("endLocation", dropLocation);
			
			System.out.println("pickLocations :"+pickLocations);
			System.out.println("dropLocations :"+dropLocations);
			
			session.setAttribute("pickLocation", pickLocations);
			session.setAttribute("dropLocation", dropLocations);
			session.setAttribute("pickDate", pickDate);
			session.setAttribute("pickTime", pickTime);
			session.setAttribute("dropDate", dropDate);
			session.setAttribute("dropTime", dropTime);
			
			//Forward to Sharath's page
			//response.sendRedirect("RetrieveCarInfoServlet");
			//RequestDispatcher rd=request.getRequestDispatcher("RetrieveCarInfoServlet");  
			//rd.forward(request, response);
			response.sendRedirect("RetrieveCarInfoServlet");

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}