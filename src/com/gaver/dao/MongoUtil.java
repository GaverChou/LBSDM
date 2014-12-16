package com.gaver.dao;

import java.net.UnknownHostException;

import com.mongodb.Mongo;


public class MongoUtil {

	public static int port = 27017;
	public static String dbUrl = "localhost";
	public static String dbName = "Test";
//	public static DBCollection getDBCollection(){
//		Mongo mongo = null;
//		DB db = mongo.getDB("Test");
//	}
	
	public static Mongo getMongo() throws UnknownHostException{
		Mongo mongo = new Mongo(dbUrl,port);
		return mongo;
	}

	public static void free(Mongo mongo) {
		if (mongo!=null) {
			mongo.close();
		}
	}
}
