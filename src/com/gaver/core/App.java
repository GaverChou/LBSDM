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
			// ʵ����Mongo��������27017�˿�
			Mongo mongo = new Mongo("localhost", 27017);
			// ������Ϊyourdb�����ݿ⣬�������ݿⲻ���ڵĻ���mongodb���Զ�����
			DB db = mongo.getDB("Test");
			// Get collection from MongoDB, database named "yourDB"
			// ��Mongodb�л����ΪyourColleection�����ݼ��ϣ���������ݼ��ϲ����ڣ�Mongodb��Ϊ���½���
			DBCollection collection = db.getCollection("Users");
			collection.ensureIndex(new BasicDBObject("uid", 1) , "idindex", true);
//			 ʹ��BasicDBObject���󴴽�һ��mongodb��document,�����踳ֵ��
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
			// ���½�����document���浽collection��ȥ
			collection.insert(document);
			// ����Ҫ��ѯ��document
			BasicDBObject searchQuery = new BasicDBObject();
//			searchQuery.put("id", 1002);
			// ʹ��collection��find��������document
			DBCursor cursor = collection.find(searchQuery);
			// ѭ��������
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