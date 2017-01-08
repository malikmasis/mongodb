package controller;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Set;

public class MongoDB {
	public static void main(String args[]) {
		/*
		try {
			// Mongodb'yi bağlanmak için
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			// veritabanÄ±mÄ±nÄ±z ismi
			DB db = mongoClient.getDB("test");
			System.out.println("Connect to database successfully");
			DBCollection coll = db.getCollection("user");// tablo ismi
			//nosql'de collection=tablo
			System.out.println("Collection user selected successfully");
			//indexler ise sutÃ¼nlara denk gelmekteditr.
			BasicDBObject doc = new BasicDBObject("title", "MongoDB")
					.append("false/true", false)//boolean deÄŸer
					.append("like", 100)//int deÄŸer
					.append("url", "http://www.malik.masis.com/mongodb/")//string
					.append("ondalik", 10.1);//ondalÄ±k sayÄ±
			coll.insert(doc);//en son kaydedilir.
			System.out.println("Document inserted successfully");
		} catch (Exception e) {
			System.err.println("--------");
		}
	
 */
		 try{   
			 // To connect to mongodb server
		         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		         // Now connect to your databases
		         DB db = mongoClient.getDB( "test" );
		         System.out.println("Connect to database successfully");       
		         DBCollection coll = db.getCollection("user");
		         System.out.println("Collection mycol selected successfully");
		         DBCursor cursor = coll.find();
		         while (cursor.hasNext()) { 
		            DBObject updateDocument =  cursor.next();
		            updateDocument.put("likesss",200);
		            coll.update(updateDocument,updateDocument); 
		         }
		         System.out.println("Document updated successfully");
		         cursor = coll.find();
		         int i=1;
		         while (cursor.hasNext()) { 
		            System.out.println("Updated Document: "+i); 
		            System.out.println(cursor.next()); 
		            i++;
		         }
		      }catch(Exception e){
			     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			  }
		   
		
	/*	
    try{   
	 // To connect to mongodb server
         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
         // Now connect to your databases
         DB db = mongoClient.getDB( "test" ); 
	 System.out.println("Connect to database successfully");
         DBCollection coll = db.getCollection("user");
         System.out.println("Collection mycol selected successfully");
         DBCursor cursor = coll.find();
         int i=1;
         while (cursor.hasNext()) { 
            System.out.println("Inserted Document: "+i); 
            System.out.println(cursor.next()); 
            i++;
         }
      }catch(Exception e){
	     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  }
    
   */
		 
		
	
	}
}