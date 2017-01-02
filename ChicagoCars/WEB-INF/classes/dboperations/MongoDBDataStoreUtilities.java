package dboperations;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.AggregationOutput;


public class MongoDBDataStoreUtilities {
	
	public static MongoClient mongo;
	public static DB db;
	public static DBCollection myCars ;
	public static DBCursor cursor;
	public static AggregationOutput aggregate;
	public static DBObject match = null;
	public static DBObject groupFields = null;
	public static DBObject group = null;
	public static DBObject projectFields = null;
	public static DBObject project = null;
	public static DBObject sort=null;


	public static DBCollection createMongoDB(){

		mongo = new MongoClient("localhost", 27017);
		db = mongo.getDB("CarRentalDatabase"); // use it in actual implementation
		myCars = db.getCollection("Cars");
		//db = mongo.getDB("carrental");
		//myCars = db.getCollection("myRentals");
		return myCars;
	}
	

	public static DBCursor fetchReviewMongoDB(String carId){

		DBObject searchQuery = new BasicDBObject();
		System.out.println("ID:::"+carId);
		searchQuery.put("carID", carId);
		DBCursor cursor = myCars.find(searchQuery);
		//curs = coll.find(query, fields);
		

		System.out.println("IN fetchReviewMongoDB::");
		System.out.println("IN fetchReviewMongoDB cursor::"+cursor);
		return cursor;
	}


}

