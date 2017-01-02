<%@page import="utilities.*,java.util.*" %>

<!doctype html> 
<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<title>ChicagoCars Home</title> 
<link rel="stylesheet" href="HomePage_styles.css" type="text/css" />
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script type="text/javascript" src="SignIn.js"></script>
<script src="autocompleter.js"></script>
</head> 
<body>

<div id="container"> 
    <header> 
    	<h1><a href="Home">Chicago<span>Cars</span></a></h1> 
        <h2></h2> 
    </header> 
	
    <nav> 
    	<ul> 
        	<li class="start selected"><a href="Home">Home</a></li> 
			<li class="start selected"><a href="ReadReview.jsp">What Others Say About Our Service</a></li> 
			<li class="start selected"><a href="WriteReview.jsp">Give Us Reviews</a></li>
			<li class="start selected"><a href="TwitterServlet">Twitter Trends</a></li>
			<%if(request.getSession().getAttribute("email")==null || request.getSession().getAttribute("email").equals("Guest")){ %>
			<li class="start selected"><a href="LoginPage.jsp">Login/ Signup</a></li>
			<span class="showLoginDetails">Welcome Guest</span>
			<%}

			else{ %>
			<li class="start selected"><a onclick="return signOut();">Logout</a></li>
			<span class="showLoginDetails">Welcome <%=request.getSession().getAttribute("email")%></span>
			<%}%>
						
            
<!---->

	<li></li>
        </ul> 
    </nav> 

    <div id="body"> 

		

		<section id="content"> 

	    <article> 
			
			<h2>Search, Compare & Save!</h2><br/> 
			
			<jsp:include page="AutoCompletePage.jsp"/>
		
		</article> 
	
		</section> 
		
        <aside class="sidebar"> 
	
            <ul>	 
               <li> 
				
                <li> 
                    <!-- h4>About us</h4> 
                    <ul> 
                        <li class="text"> 
                        	<p style="margin: 0;">CarRentals is a web based application that supports customers to place orders for renting cars</p> 
                        </li> 
                    </ul--> 
                </li> 
				
            </ul> 
		
        </aside> 
    	<div class="clear"></div> 
    </div> 
    <footer> 
        <div class="footer-bottom"> 
            <p>&copy; ChicagoCars 2016.</p> 
         </div> 
    </footer> 
</div> 
</body> 
</html>