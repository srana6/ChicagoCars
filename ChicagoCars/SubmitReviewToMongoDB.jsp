<%@ page import=" java.io.*,
 javax.servlet.ServletException,
 javax.servlet.annotation.WebServlet,
 javax.servlet.http.HttpServlet,
 javax.servlet.http.HttpServletRequest,
 javax.servlet.http.HttpServletResponse,
 com.mongodb.MongoClient,
 com.mongodb.MongoException,
 com.mongodb.WriteConcern,
 com.mongodb.DB,
 com.mongodb.DBCollection,
 com.mongodb.BasicDBObject,
 com.mongodb.DBObject,
 com.mongodb.DBCursor,
 com.mongodb.ServerAddress,
 java.util.Arrays,
 java.util.List,
java.util.Set,
java.util.Date"%>

 <%@page import="dboperations.*"%>


	<%DBCollection myReviews = MongoDBDataStoreReviews.createMongoDB();
	
	PrintWriter pw= response.getWriter();		
		
	try{
			
			String username = request.getParameter("username");
			String carbrand = request.getParameter("carbrand");
			int reviewRating = Integer.parseInt(request.getParameter("reviewRating"));	
			String reviewDate = request.getParameter("reviewDate");
			String reviewText = request.getParameter("reviewText");
			
				
			BasicDBObject info = new BasicDBObject("title", "myReviews").
				append("username", username).
				append("carbrand", carbrand).
				append("reviewRating", reviewRating).
				append("reviewDate", reviewDate).
				append("reviewText", reviewText);
				
			Object o = MongoDBDataStoreReviews.insertReviewInMongoDB(info);
									
			
				
			System.out.println("Document inserted successfully");
			
			
			 %>
			
				
					<html>
					<head>
					<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
					<title>Product Added : CAR RENTAL</title><link rel='stylesheet' href='stylesreview.css' type='text/css' />
					</head>
					<body>
					<div id='container'>
					<header><div class='header_logo'>
					<h1><a href='#'>CAR <span></span></a></h1>
					<h2>new deals everyday</h2></div></header>
					<h3><span style='width:310px;display:inline-block'></span>Your Review Has Been Added</h3>
					
			<span style='width:310px;display:inline-block'></span><a href='Home'>CLICK HERE TO GO BACK TO HOME PAGE</a>
					
					
		
		<%} catch (MongoException e) {
			e.printStackTrace();
		}%>
	
	