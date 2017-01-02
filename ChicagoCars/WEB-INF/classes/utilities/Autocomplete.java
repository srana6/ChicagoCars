package utilities;


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*; 
import java.util.List;
import com.google.gson.Gson;

import utilities.AjaxUtility;

@WebServlet("/Autocomplete")
public class Autocomplete extends HttpServlet {

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
			String action = (String)request.getParameter("action");
			String searchId = (String)request.getParameter("searchId");
			
			System.out.println("Action : "+action);
			System.out.println("SearchId : "+searchId);
			
			response.setContentType("text/html");
			PrintWriter out= response.getWriter();
			
			boolean namesAdded = false;
			if (action.equals("complete"))
			{
				if (!searchId.equals(""))
				{ 
					AjaxUtility a= new AjaxUtility();
					List<String> locations =a.readData(searchId);
					if(locations !=null)
					{
						namesAdded = true;
					}
					if(namesAdded)
					{
							String searchList = new Gson().toJson(locations);
							out.write(searchList);
					}
				}
			}
			else if (action.equals("lookup"))
			{
				if (!searchId.equals(""))
				{
					//response.sendRedirect("/ewa3/ViewProductPage?searchId="+searchId);
				}
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}