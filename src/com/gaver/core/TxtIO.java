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
			// ʵ����Mongo��������27017�˿�
			Mongo mongo = new Mongo("localhost", 27017);
			// ������Ϊyourdb�����ݿ⣬�������ݿⲻ���ڵĻ���mongodb���Զ�����
			DB db = mongo.getDB("Test");
			// Get collection from MongoDB, database named "yourDB"
			// ��Mongodb�л����ΪyourColleection�����ݼ��ϣ���������ݼ��ϲ����ڣ�Mongodb��Ϊ���½���
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
