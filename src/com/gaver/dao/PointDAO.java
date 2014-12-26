package com.gaver.dao;

import java.util.List;

import com.gaver.domain.Point;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public interface PointDAO {

	public void insert(Point point);

	public void delete(DBObject item);
	
	public List<? extends BasicDBObject> getList();
	
	public DBCursor getCursor();
	
	public int getClusterSize(BasicDBObject point,double radius);
	
	public void mapReduce();
}
