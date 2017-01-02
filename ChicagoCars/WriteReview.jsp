<%@ page import="java.io.*,
java.util.HashMap,
java.util.Map,
javax.servlet.*,
javax.servlet.http.*,
java.util.*"%>

<%@page import="dboperations.*"%>


	
	<% String username="username comes here";
	 String firstLetter=" ";
	 String productCategory=" ";
	

		
	HttpSession s=request.getSession();
	//username=(String)s.getAttribute("userid");
		
	response.setContentType("text/html");
		
	PrintWriter pw = response.getWriter();
			
	 %>
			
			<html>
					<head>
					<meta http-equiv='Content-Type' content='text/html' charset='utf-8' />
					<title>Chicago Car Rentals</title>
					<link rel='stylesheet' href='stylesreview.css' type='text/css' /> 
					</head>
					
		
		<body>
					<div id='container'>
					<header>
					<div id=imageLogo>
					<h1><a href='/'>CAR<span>RENTAL</span></a></h1>
					<h5 style='color:blue;float:right' ></h5></br>
					<h3 style='color:blue;float:right;font-style:italic' >Welcome:<%=username%></h3>
					</div>
					</header>
					<nav id="myheader">
		
    </nav>
					
					<h2>Please Write Review</h2>
			
									
			 
			<form method='get' action='SubmitReviewToMongoDB.jsp'>
		
			<table>
	
			<tr>
			<td> User Name: </td>
			<td> <input type='text' name='username'> </td>
			</tr>
			
			<tr>
			<td> Car Brand Used: </td>
			<td> <select name='carbrand'> 
			<option value='jaguar' selected>Jaguar</option>
			<option value='skoda'>Skoda</option>
			<option value='Mercedes'>Mercedes</option>
			<option value='Toyota'>Toyota</option>
			</td>
			</tr>
			
			<tr>
			<td> Review Rating: </td>
			<td>
			<select name='reviewRating'>
			<option value='1' selected>1</option>
			<option value='2'>2</option>
			<option value='3'>3</option>
			<option value='4'>4</option>
			<option value='5'>5</option>
			</td>
			</tr>
			
			
			
			
			<tr>
			<td> Review Date: </td>
			<td> <input type='text' name='reviewDate'> </td>
			</tr>
			<tr>
			
			<td> Review Text: </td>
			<td><textarea name='reviewText' rows='4' cols='50'> </textarea></td>
			</tr>
			</table>
			<br><br>
			<input type='submit' value='Submit Review'></input>
			</fieldset>
			</form>
			</article>
					</section>
					<!--<aside class='sidebar'>
					<ul>
					<li>
                    <h4>Products</h4>
                    <ul>
                    <li><a href='#'>Home Page</a></li>
                    <li><a href='PhoneDataStoreJsp.jsp'>Smart Phones</a></li>
                    <li><a href='TabletDataStoreJsp.jsp'>Tablets</a></li>
                    <li><a href='LaptopDataStoreJsp.jsp'>Laptops</a></li>
                    <li><a href='TvDataStoreJsp.jsp'>TV</a></li>
                    <li><a href='TrendingProductJsp.jsp'>Trending</a></li>
                    </ul>
					</li>
					<li>
                    <h4>About us</h4>
                    <ul>
                    <li class='text'>
                    <p style='margin: 0;'>Welcome to Best Deals, Where you will get new deals everyday</p>
                    </li>
                    </ul>
					</li>
					</ul>
					</aside>-->
					<div class='clear'></div>
					</div>
					</div>
					</body>
					</html>	
	