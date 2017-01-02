package dboperations;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import com.mongodb.AggregationOutput;
import com.mongodb.client.AggregateIterable;
import java.util.List;
import java.util.Set;
import java.util.Date;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;



public class MongoDBDataStoreReviews {
	
	private static final long serialVersionUID = 1L;
	
	public static MongoClient mongo;
	public static DB db;
	public static DBCollection myReviews ;
	public static DBCursor cursor;
	public static BasicDBObject object;
	public static AggregationOutput aggregate;
	public static DBObject match = null;
	public static DBObject groupFields = null;
	public static DBObject group = null;
	public static DBObject projectFields = null;
	public static DBObject project = null;
	public static DBObject sort=null;
	public static DBObject limit=null;


	public static DBCollection createMongoDB(){

		mongo = new MongoClient("localhost", 27017);
		db = mongo.getDB("CarRentalDatabase"); // use it in actual implementation
		myReviews = db.getCollection("CarsReviews");
		//db = mongo.getDB("carrental");
		//myCars = db.getCollection("myRentals");
		return myReviews;
	}
	
	public static BasicDBObject insertReviewInMongoDB(BasicDBObject info){

		myReviews.insert(info);
		return object;
	}

	public static DBCursor fetchReviewMongoDB(){
		DBCursor cursor = myReviews.find();
		return cursor;
	}
	


}

