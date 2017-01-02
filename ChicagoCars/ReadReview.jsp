<%@ page import=" java.io.*,
 javax.servlet.ServletException,
 javax.servlet.annotation.WebServlet,
 javax.servlet.http.HttpServlet,
 javax.servlet.http.HttpServletRequest,
 javax.servlet.http.HttpServletResponse,
 javax.servlet.*,
 javax.servlet.http.*,
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


	
	<% 
	 String username=" ";
	 String carbrand=" ";
	 String reviewRating=" ";
	 String reviewDate=" ";
	 String reviewText=" ";
	 String userid=" ";
	
	MongoClient mongo;
	
	DBCollection myReviews = MongoDBDataStoreReviews.createMongoDB();
	
	
	try{
			
	HttpSession s=request.getSession();
	userid=(String)s.getAttribute("userid");
		
			
	DBCursor cursor1 = MongoDBDataStoreReviews.fetchReviewMongoDB();

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
		<!-- %@include file="global/nav.jsp"%-->
    </nav>
			
			<%if(cursor1.count() == 0){
			pw.print("There are no reviews yet.");
		}else{
				int counter=0;
				
				while (cursor1.count()>counter) {
					counter++;
					
					BasicDBObject obj = (BasicDBObject) cursor1.next();	%>
					
				<TABLE BORDER=1 ALIGN=CENTER>
                <TR BGCOLOR='#FFAD00'>
                <TH>Review<TH>Data
				
				<TR><TD> User Name </TD>
				<TD><%=obj.getString("username")%></TD></TR>

				<TR><TD> Car Brand </TD>
				<TD><%=obj.getString("carbrand")%></TD></TR>

				<TR><TD> Review Rating </TD>
				<TD><%=obj.getString("reviewRating").toString()%></TD></TR>

				<TR><TD> Review Date </TD>
				<TD><%=obj.getString("reviewDate")%></TD></TR>

				<TR><TD> Review Text </TD>
				<TD><%=obj.getString("reviewText")%></TD></TR>

				</TABLE>

			</TABLE>
           <FORM ACTION='Home' METHOD='get'>
           <BIG><CENTER>
           <INPUT TYPE='SUBMIT' VALUE='Back'>
		   
           </CENTER></BIG></FORM>		
					
					
					
				<%}
			}%>
		
					</div>
					</div>
					</body>
					</html>
			
		<%}	
		catch (MongoException e) {
				e.printStackTrace();
		}  %>

	