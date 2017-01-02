import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class TwitterServlet extends HttpServlet {
	public ServletContext sc;
	
	
	
	
	public void runThisMethod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		sc=request.getSession().getServletContext();
		PrintWriter pw= response.getWriter(); 
		response.setContentType("text/html");
		
		String line=null;
		
		HttpSession s=request.getSession();
    	String userid=(String)s.getAttribute("userid");
		
		pw.println("<html>"+
					"<head>"+
					"<meta http-equiv='Content-Type' content='text/html' charset='utf-8' />"+
					"<title>ChicagoCars Rentals</title>"+
					"<link rel='stylesheet' href='HomePage_styles.css' type='text/css' /> "+
					"</head>");
					
		
		pw.println("<body>"+
					"<div id='container'>"+
					"<header>"+
					"<div id=imageLogo>"+
					"<h1><a href='/'>CHICAGO<span>CARS</span></a></h1>"+
					"<h5 style='color:blue;float:right' ></h5></br>"+					
					"</div>"+
					"</header>"+
					"<nav id='myheader'>"+
					"<ul><li class='start selected'><a href='Home'>Home</a></li>");	
					
					if(request.getSession().getAttribute("email")==null || request.getSession().getAttribute("email").equals("Guest")){
						pw.println(
								"<span class=\"showLoginDetails\">Welcome Guest</span>");
					}
					else{
						pw.println("<li class=\"start selected\"><a onclick=\"return signOut();\">Logout</a></li>+"
								+ "<span class=\"showLoginDetails\">Welcome "+request.getSession().getAttribute("email")+"</span>");
					}
		
					
					pw.println("</ul></nav>"+
					
					"<h2>TWITTER TRENDS</h2>");
					BufferedReader reader= new BufferedReader(new FileReader(new File("C:\\H_Drive\\Install_Files\\apache-tomcat-7.0.34\\webapps\\ChicagoCars\\DealMatches.txt")));
					line=reader.readLine();
							if(line==null){
								 pw.println("<h2 align='center'>No Offers Found</h2>");
							}
							else{
								do{
										pw.println("<h2>"+line+"</h2>");
										pw.println("<br>");
										
								}while((line = reader.readLine()) != null);
							}
						
					pw.println(
							"<footer><div class='footer-bottom'><p>&copy; ChicagoCars 2016.</p></div></footer>"+
							"</div>"+
					"</div>"+
					
					"</body>"+
					"</html>");	
					
			pw.close();
 
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException{
	runThisMethod(request,response);
}
/*
	CALL COMES HERE AND RUN THIS METHOD WILL BE CALLED
	*/
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException{
	runThisMethod(request,response);
}
	
		
	}