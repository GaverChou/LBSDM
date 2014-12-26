package com.gaver.domain;

import java.io.Serializable;

import com.gaver.util.TextUtil;
import com.mongodb.BasicDBObject;

public class UserPoint implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long time;
	private Point point;
	
	public static UserPoint parseUserPointByTxt(String data){
		if (!TextUtil.isValid(data)) {
			return null;
		}
		UserPoint up = new UserPoint();
		String[] userPoint = data.split(",");
		up.time = Long.parseLong(userPoint[0]);
		up.point = new Point(Double.parseDouble(userPoint[1]),
		Double.parseDouble(userPoint[2]));
		return up;
	}
	public double speed(UserPoint p2){
		return point.realDistanceWithPoint(p2.getPoint())/Math.abs(time-p2.time);
	}
	
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public BasicDBObject retDBObject() {
		BasicDBObject document = new BasicDBObject();
		document.put("loc", point.retDBObject());
		document.put("time", time);
		return document;
	}
	
	
}
