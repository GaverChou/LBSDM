package com.gaver.dao.impl;

import java.net.UnknownHostException;

import com.gaver.dao.MongoUtil;
import com.gaver.dao.UserPointsDAO;
import com.gaver.domain.UserPoint;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class UserPointImp implements UserPointsDAO {

	public static String DBNAME = "UserPoints";
	@Override
	public boolean addUserPoint(UserPoint up) {
		Mongo mongo = null;
		DB db = null;
		DBCollection collection = null;
		try {
			mongo = MongoUtil.getMongo();
			db = mongo.getDB(MongoUtil.dbName);
			collection = db.getCollection(DBNAME);
			BasicDBObject document = new BasicDBObject();
			document.put("uid", 1003);
			collection.insert(document);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (MongoException e) {
			e.printStackTrace();
			return false;
		}finally{
			MongoUtil.free(mongo);
		}
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
