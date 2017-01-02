package dboperations;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDBConnector {

	private static MongoDBConnector mongoDBConnector = new MongoDBConnector();	
	private MongoClient mongoClient;
	private DB db;

	private MongoDBConnector(){}

	public static MongoDBConnector getInstance(){
		return mongoDBConnector;
	}

	public void createConnection()
	{	
		if(mongoClient==null){
			mongoClient = new MongoClient("localhost", 27017);
			db = mongoClient.getDB("CarRentalDatabase");
		}
	}

	public DBCollection getCollection(String collection){
		return db.getCollection(collection);
	}
	
	public void closeConnection(){
		mongoClient.close();
		mongoClient = null;
	}


	@Override
	protected void finalize() throws Throwable {
		closeConnection();
		super.finalize();
	}
}
