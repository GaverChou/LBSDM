package com.gaver.dao.test;

import com.gaver.dao.impl.PointDaoImpl;
import com.gaver.domain.Point;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

public class Main {

	public static void main(String[] args){
		BasicDBObject point = new Point(117.323495, 31.88885).retDBObject();
		PointDaoImpl imp = new PointDaoImpl();
		imp.getClusterSize(point,10);
		DBCursor cursor = imp.getCursor();
		System.out.println(cursor.count());
		while (cursor.hasNext()) {
			System.out.println(imp.getClusterSize((BasicDBObject)cursor.next().get("loc"),10d));
		}
	}
}
