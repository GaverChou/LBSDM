package com.gaver.domain;

import com.mongodb.BasicDBObject;
import static com.gaver.util.PointOperation.*;

public class Point {

	private double x;
	private double y;
	private boolean hasCatch;

	public Point() {
		super();
	}

	public boolean isHasCatch() {
		return hasCatch;
	}

	public void setHasCatch(boolean hasCatch) {
		this.hasCatch = hasCatch;
	}

	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double distanceWithPoint(Point point) {
		return Math.sqrt((Math.pow(x - point.x, 2) + Math.pow(y - point.y, 2)));
	}

	public double realDistanceWithPoint(Point point) {
		double radLat1 = rad(x);
		double radLat2 = rad(point.x);
		double a = radLat1 - radLat2;
		double b = rad(y) - rad(point.y);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS * 1000;
		return s;
	}

	public BasicDBObject retDBObject() {
		BasicDBObject document = new BasicDBObject();
		document.put("x", x);
		document.put("y", y);
		return document;
	}

	@Override
	public String toString() {
		return x + "," + y;
	}
}
