

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dboperations.*;


public class CancelReservationByAdminServlet extends HttpServlet{

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException 
	{
		String confirmationnumber=request.getParameter("confirmation_number");
		
		MySqlDataStore.cancelOrderByAdmin(confirmationnumber);
		MySqlDataStore.updateOrderHistoryByAdmin(confirmationnumber);
		
		response.sendRedirect("ManageReservationByAdminServlet");
		
		
	}


protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }
	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }
}