package com.gaver.dao;

import java.net.UnknownHostException;

import com.gaver.util.TextUtil;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class MyMongo {

	public static int port = 27017;
	public static String dbUrl = "localhost";
	public static String dbName = "Test";

	private Mongo _mongo;
	private DB _db;

	public MyMongo() throws UnknownHostException {
		_mongo = new Mongo(dbUrl, port);
	}

	public MyMongo(String connecString, String dbName)
			throws UnknownHostException {
		this(connecString, port, dbName);
	}

	public MyMongo(String connecString, int port, String dbName)
			throws UnknownHostException {
		if (!TextUtil.isValid(connecString))
			throw new NullPointerException("connecString");

		_mongo = new Mongo(connecString, port);

		if (TextUtil.isValid(dbName))
			_db = _mongo.getDB(dbName);
	}

	public DB useDb(String dbName) {
		if (!TextUtil.isValid(dbName))
			throw new NullPointerException("dbName");

		_db = _mongo.getDB(dbName);
		return _db;
	}

	public DB currentDb() {
		if (_db == null)
			throw new NullPointerException(
					"当前连接没有指定任何数据库。请在构造函数中指定数据库名或者调用useDb()方法切换数据库。");

		return _db;
	}

	public DBCollection getCollection(String name) {
		return this._db.getCollection(name);
	}
}
