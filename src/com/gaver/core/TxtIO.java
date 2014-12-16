package com.gaver.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.gaver.domain.User;
import com.gaver.domain.UserPoint;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class TxtIO {

//	public static ArrayList<User> users = new ArrayList<User>();
	public static String Test(String filepath){
		StringBuffer buffer = new StringBuffer(5*1024*1024);
		BufferedReader bReader = null;
		try {
			// 实例化Mongo对象，连接27017端口
			Mongo mongo = new Mongo("localhost", 27017);
			// 连接名为yourdb的数据库，假如数据库不存在的话，mongodb会自动建立
			DB db = mongo.getDB("Test");
			// Get collection from MongoDB, database named "yourDB"
			// 从Mongodb中获得名为yourColleection的数据集合，如果该数据集合不存在，Mongodb会为其新建立
			DBCollection collection = db.getCollection("Points");
			bReader = new BufferedReader(new FileReader(filepath));
			String temp;
			int count=0;
			User user = null;
			while ((temp=bReader.readLine())!=null) {
				user = User.parseUserByTxt(temp);
//				users.add(user);
				for (BasicDBObject point:user.getUserPoints()) {
					collection.insert(point);
				}
				count+=user.getUserPoints().size();
			}
			System.out.println(count);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (bReader!=null) {
				try {
					bReader.close();
				} catch (IOException e){}
			}
		}
		return buffer.toString();
	}
	
	public static String getTxtContentByPath(String filepath){
		StringBuffer buffer = new StringBuffer(5*1024*1024);
//		FileInputStream fileInputStream = null;  
//        BufferedInputStream bufferedInputStream = null;  
//        InputStreamReader inputStreamReader = null;  
		BufferedReader bReader = null;
		try {
//			fileInputStream = new FileInputStream(filepath);  
//	        bufferedInputStream = new BufferedInputStream(fileInputStream);  
//	        inputStreamReader = new InputStreamReader(bufferedInputStream);  
			bReader = new BufferedReader(new FileReader(filepath));
			String temp;
			while ((temp=bReader.readLine())!=null) {
				buffer.append(temp+"\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (bReader!=null) {
				try {
					bReader.close();
				} catch (IOException e){}
			}
		}
		return buffer.toString();
	}
	
	public static void main(String[] args) {
		Test("C:\\Users\\Administrator\\Desktop\\LBS_DATASET\\LBS_DATASET\\traj_hefei.txt");
//		String splite = getTxtContentByPath("C:\\Users\\Administrator\\Desktop\\LBS_DATASET\\LBS_DATASET\\traj_hefei.txt");
//		System.out.println(splite.split("\n")[0]);
		//		LinkHefeiList list = LinkHefeiList.parseLinkHefeisByTxt(splite);
//		System.out.println(list.getLinkHefeis().toString());
//		RoadRefPointList list = RoadRefPointList.parseRefPointListByTxt(splite);
//		System.out.println(list.getPoints().size());
	}
}
