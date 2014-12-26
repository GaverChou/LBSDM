package com.gaver.dataprec;

import java.util.ArrayList;
import java.util.List;

import com.gaver.domain.Point;

public class Cluster {

	private List<Point> dataPoints = new ArrayList<Point>(); // ����е�������
	private String clusterName; // ����
	private Point point = new Point();

	public List<Point> getDataPoints() {
		return dataPoints;
	}

	public void setDataPoints(List<Point> dataPoints) {
		this.dataPoints = dataPoints;
	}

	public Point getPoint() {
		return point;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public void calculateCoordinate(){
		double x=0,y=0;
		Point point = null;
		int count = dataPoints.size();
		for (int i = 0; i < count; i++) {
			point = dataPoints.get(i);
			x+=point.getX();
			y+=point.getY();
		}
		this.point.setX(x/count);
		this.point.setY(y/count);
	}
}
