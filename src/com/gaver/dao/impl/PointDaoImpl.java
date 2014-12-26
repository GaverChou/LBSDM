package com.gaver.dao.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;

import com.gaver.dao.MyMongo;
import com.gaver.dao.PointDAO;
import com.gaver.domain.Point;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

public class PointDaoImpl implements PointDAO {

	@Override
	public List<? extends BasicDBObject> getList() {
		List points = new ArrayList();
		try {
			MyMongo mg = new MyMongo("localhost", "Test");
			// 使用collection的find方法查找document
			DBCursor cursor = mg.getCollection("Points").find();
			while (cursor.hasNext()) {
				points.add(cursor.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return points;
	}

	@Override
	public void insert(Point point) {
		// TODO Auto-generated method stub

	}

	@Override
	public DBCursor getCursor() {
		DBCursor cursor = null;
		try {
			MyMongo mg = new MyMongo("localhost", "Test");
			// 使用collection的find方法查找document
			cursor = mg.getCollection("Points").find();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cursor;
	}

	@Override
	public int getClusterSize(BasicDBObject point,double radius) {
		int count = 0;
		try {
			MyMongo mg = new MyMongo("localhost", "Test");
			DBCollection collection = mg.getCollection("Points");
			collection.ensureIndex(new BasicDBObject("coordinate", "2dsphere"),
					"loc_cluster");
			BasicDBObject filter = new BasicDBObject("$near",point);
			filter.put("$maxDistance", 0.05/111);
			BasicDBObject query = new BasicDBObject("loc", filter);
			// 使用collection的find方法查找document
			DBCursor cursor = collection.find(query);
			count = cursor.count();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public void printOutputs(DBCollection collection,BasicDBObject query) {
		DBCursor cursor = collection.find(query);
		int size = cursor.size();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(size);
//		List<BasicDBList> outputs = new ArrayList<BasicDBList>();
		while (cursor.hasNext()) {
			DBObject result = cursor.next();
			System.out.println(result.get("loc"));
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//		for (int y = 9; y >= 0; y--) {
//			String s = "";
//			for (int x = 0; x < 10; x++) {
//				boolean found = false;
//				for (BasicDBList obj : outputs) {
//					double xVal = (Double) obj.get(0);
//					double yVal = (Double) obj.get(1);
//					if (yVal == y && xVal == x) {
//						found = true;
//					}
//				}
//				if (found) {
//					s = s + " @";
//				} else {
//					s = s + " +";
//				}
//			}
//			System.out.println(s);
//		}
	}

	@Override
	public void mapReduce() {
		
	}

	@Override
	public void delete(DBObject item) {
		MyMongo mg=null;
		try {
			mg = new MyMongo("localhost", "Test");
			DBCollection collection = mg.getCollection("Points");
			collection.remove(item);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
