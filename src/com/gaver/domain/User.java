package com.gaver.domain;

import java.io.Serializable;
import java.util.ArrayList;

import com.gaver.util.TextUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.util.Base64Codec;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cUid;
	private ArrayList<BasicDBObject> userPoints;
	private ArrayList<UserPoint> uPoints;
	
	public User(){
		userPoints = new ArrayList<BasicDBObject>();
		uPoints = new ArrayList<UserPoint>();
	}
	
	public static User parseUserByTxt(String data) {
		if (!TextUtil.isValid(data)) {
			return null;
		}
		User user = new User();
		String[] sp = data.split("\t");
		user.cUid =sp[0];
		String[] points = sp[1].split("\\|");
		for (int i = 0; i < points.length; i++) {
			UserPoint point = UserPoint.parseUserPointByTxt(points[i]);
			user.uPoints.add(point);
			user.userPoints.add(point.retDBObject());
		}
		return user;
	}

	public BasicDBObject retDBObject(){
		BasicDBObject document = new BasicDBObject();
		document.put("CUID", cUid);
		document.put("points", userPoints);
		return document;
	}
	public String getcUid() {
		return cUid;
	}

	public void setcUid(String cUid) {
		this.cUid = cUid;
	}

	public ArrayList<BasicDBObject> getUserPoints() {
		return userPoints;
	}

	public void setUserPoints(ArrayList<BasicDBObject> userPoints) {
		this.userPoints = userPoints;
	}

	public ArrayList<UserPoint> getuPoints() {
		return uPoints;
	}

	public void setuPoints(ArrayList<UserPoint> uPoints) {
		this.uPoints = uPoints;
	}
	
}
