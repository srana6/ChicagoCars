package dboperations;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import pojos.Car;
import utilities.Request;

import com.mongodb.client.MongoCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

import java.util.*;

public class CreateMongoDBEntities {
    MongoCollection<BasicDBObject> cars;
	MongoClient mongoClient;
    MongoDatabase db;
    //DBObject searchQuery;
    DBCursor cursor;

    public void getConnection(){
        mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDatabase("CarRentalDatabase");
        cars = db.getCollection("Cars", BasicDBObject.class);
    }
    
    public void insertCars(Car c){
        this.getConnection();
        BasicDBObject doc = new BasicDBObject("fields", "values");
        doc.append("carID", c.getCarID());
        doc.append("carName", c.getCarName());
        doc.append("carType", c.getCarType());
        doc.append("manufacturer", c.getManufacturer());
        doc.append("transmission", c.getTransmission());
        doc.append("number_of_seats", c.getNumber_of_seats());
        doc.append("ratingID", c.getRatingID());        
        doc.append("airConditioning", c.isAirConditioning());        
        doc.append("price", c.getPrice());
        this.cars.insertOne(doc);
    }
    
    /*
    public List<Car> getCars(){
        getConnection();
        List<Car> clist = new ArrayList<Car>();
        BasicDBObject query = new BasicDBObject();
        List<BasicDBObject> foundDocument = cars.find(query).into(new ArrayList<BasicDBObject>());
        for(BasicDBObject obj: foundDocument){
            Car c = new Car(obj.getString("carID"),obj.getString("carName"),obj.getString("carType"),obj.getString("manufacturer"),obj.getString("transmission"), Integer.valueOf(obj.getString("number_of_seats")), Integer.valueOf(obj.getString("ratingID")), Boolean.valueOf(obj.getString("airConditioning")), Float.valueOf(obj.getString("price")));
            clist.add(c);
        }
        return clist;
    }
    */
    
    public List<Car> getCars(){
        getConnection();
        List<Car> clist = new ArrayList<Car>();
        BasicDBObject query = new BasicDBObject();
        BasicDBObject gtltquery = new BasicDBObject("$in", union(Request.pZipCodes, Request.dZipCodes));
        query.put("location_id",gtltquery);
        List<BasicDBObject> foundDocument = cars.find(query).into(new ArrayList<BasicDBObject>());
        for(BasicDBObject obj: foundDocument){
            String val = new String(String.valueOf(obj.getString("number_of_seats").charAt(0)));
            Car c = new Car(obj.getString("carID"),obj.getString("carName"),obj.getString("carType"),obj.getString("manufacturer"),obj.getString("transmission"), Integer.valueOf(val), Float.valueOf(obj.getString("rating")), obj.getString("airConditioning"), Float.valueOf(obj.getString("price")), obj.getString("filename"), obj.getString("location_id"));
            clist.add(c);
        }
        return clist;
    }
    
    public List<Car> filterCars(List<String> fl){
        getConnection();
        List<Car> clist = new ArrayList<Car>();
        BasicDBObject query = new BasicDBObject();    
        List<BasicDBObject> ob = new ArrayList<BasicDBObject>();
        for(String s:fl){
            String key = s.split("=")[0];
            String value = s.split("=")[1];
            if(key.equals("first") && !value.equals("NULL")){
                BasicDBObject query1 = new BasicDBObject();                
                BasicDBObject gtltquery1 = new BasicDBObject("$in", getCommaDelimitedList(value));
                query1.put("manufacturer",gtltquery1);
                ob.add(query1);
            }
            if(key.equals("second") && !value.equals("NULL")){                
                BasicDBObject query2 = new BasicDBObject();                
                BasicDBObject gtltquery2 = new BasicDBObject("$in", getCommaDelimitedList(value));
                query2.put("carType",gtltquery2);
                ob.add(query2);
            }
            if(key.equals("third") && !value.equals("NULL")){                
                BasicDBObject query3 = new BasicDBObject();                
                BasicDBObject gtltquery3 = new BasicDBObject("$in", getCommaDelimitedIntegerList(value));
                query3.put("number_of_seats",gtltquery3);
                ob.add(query3);
            }
            if(key.equals("fourth") && !value.equals("NULL")){                
                BasicDBObject query4 = new BasicDBObject();                
                BasicDBObject gtltquery4 = new BasicDBObject("$gt", Integer.valueOf(value.split("-")[0])).append("$lt", Integer.valueOf(value.split("-")[1]));
                query4.put("price",gtltquery4);
                ob.add(query4);
            }
        }
        BasicDBObject query5 = new BasicDBObject();                
        BasicDBObject gtltquery5 = new BasicDBObject("$in", union(Request.pZipCodes, Request.dZipCodes));
        query5.put("location_id",gtltquery5);
        ob.add(query5);
        query.put("$and", ob);
        List<BasicDBObject> foundDocument = cars.find(query).into(new ArrayList<BasicDBObject>());
        for(BasicDBObject obj: foundDocument){
            String val = new String(String.valueOf(obj.getString("number_of_seats").charAt(0)));
            Car c = new Car(obj.getString("carID"),obj.getString("carName"),obj.getString("carType"),obj.getString("manufacturer"),obj.getString("transmission"), Integer.valueOf(val), Float.valueOf(obj.getString("rating")), obj.getString("airConditioning"), Float.valueOf(obj.getString("price")), obj.getString("filename"),obj.getString("location_id"));
            clist.add(c);
        }
        //System.out.println(clist.size());
        return clist;
    }
    
    public List<String> getCommaDelimitedList(String val){
        String [] s = val.split(",");
        List<String> l = new ArrayList<String>();
        for(String each:s){
            l.add(each.replace("%20", " "));
        }
        return l;
    }
    
    public List<String> union(List<String> list1, List<String> list2) {
        Set<String> set = new HashSet<String>();
        set.addAll(list1);
        set.addAll(list2);
        return new ArrayList<String>(set);
    }
    
    public List<Integer> getCommaDelimitedIntegerList(String val){
        String [] s = val.split(",");
        List<Integer> l = new ArrayList<Integer>();
        for(String each:s){
            l.add(Integer.valueOf(each));
        }
        return l;
    }
    
    public List<String> getUniqueItems(String s){
        getConnection();
        List<String> clist = new ArrayList<String>();
        BasicDBObject query = new BasicDBObject();
        List<BasicDBObject> foundDocument = cars.find(query).into(new ArrayList<BasicDBObject>());
        for(BasicDBObject obj: foundDocument){
            String str = obj.getString(s);
            if(!clist.contains(str))
                clist.add(str);
        }
        return clist;
    }
    
    /*
    public MultiMap mostLikedProducts(){
        getConnection();
        BasicDBObject query = new BasicDBObject();
        BasicDBObject sortQuery = new BasicDBObject();
        sortQuery.put("reviewRating", -1);
        List<BasicDBObject> foundDocument = cars.find(query).sort(sortQuery).into(new ArrayList<BasicDBObject>());
        MultiMap m = new MultiMap();
        for(BasicDBObject obj: foundDocument){
            String rating=obj.getString("reviewRating");
            if(rating==null)
                rating="0";
            m.put(obj.getString("prodName"), Integer.valueOf(rating));
        }
        return m;
    }
    */
}