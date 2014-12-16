package com.gaver.core;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBRef;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

/**
 * Java + MongoDB Hello world Example
 * 
 */
public class App {
	public static void main(String[] args) {
		try {
			// 实例化Mongo对象，连接27017端口
			Mongo mongo = new Mongo("localhost", 27017);
			// 连接名为yourdb的数据库，假如数据库不存在的话，mongodb会自动建立
			DB db = mongo.getDB("Test");
			// Get collection from MongoDB, database named "yourDB"
			// 从Mongodb中获得名为yourColleection的数据集合，如果该数据集合不存在，Mongodb会为其新建立
			DBCollection collection = db.getCollection("Users");
			collection.ensureIndex(new BasicDBObject("uid", 1) , "idindex", true);
//			 使用BasicDBObject对象创建一个mongodb的document,并给予赋值。
			BasicDBObject document = new BasicDBObject();
			document.put("uid", 1003);
			List<DBRef> listRef = new ArrayList<DBRef>();
			DBRef dbRef = new DBRef(db,"Points",new ObjectId("548ee0816ade1937170f8224"));
			DBRef dbRef2 = new DBRef(db,"Points",new ObjectId("548ee74a6ade5c4705a9fe98"));
			listRef.add(dbRef);
			listRef.add(dbRef2);
			document.put("msg", "hello world mongoDB in Java");
			document.put("points", listRef);
//			BasicDBObject info = new BasicDBObject();
//			info.put("uid", "1003");
//			info.put("x", 234);
//		    info.put("y", 112);
//		    document.put("info", info);
			// 将新建立的document保存到collection中去
			collection.insert(document);
			// 创建要查询的document
			BasicDBObject searchQuery = new BasicDBObject();
//			searchQuery.put("id", 1002);
			// 使用collection的find方法查找document
			DBCursor cursor = collection.find(searchQuery);
			// 循环输出结果
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
			System.out.println("Done");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}
}