package com.gaver.dao.impl;

import com.gaver.dao.UserPointsDAO;
import com.gaver.domain.UserPoint;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class UserPointImp implements UserPointsDAO {

	public static String DBNAME = "UserPoints";
	@Override
	public boolean addUserPoint(UserPoint up) {
		Mongo mongo = null;
		DB db = null;
		DBCollection collection = null;
//		try {
////			mongo = MyMongo.getMongo();
////			db = mongo.getDB(MyMongo.dbName);
////			collection = db.getCollection(DBNAME);
////			BasicDBObject document = new BasicDBObject();
////			document.put("uid", 1003);
////			collection.insert(document);
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}catch (MongoException e) {
//			e.printStackTrace();
//			return false;
//		}finally{
////			MyMongo.free(mongo);
//		}
		return true;
	}

	@Override
	public UserPoint getUserPoint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUserPoint(String uid) {
		// TODO Auto-generated method stub

	}

}
