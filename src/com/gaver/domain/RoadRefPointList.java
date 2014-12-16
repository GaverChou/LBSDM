package com.gaver.domain;

import java.util.ArrayList;

public class RoadRefPointList {

	private long road_id;
	private ArrayList<RefPoint> points = new ArrayList<RefPoint>();
	
	public static RoadRefPointList parseRefPointListByTxt(String data){
		if("".equals(data)||data==null){
			return null;
		}
		RoadRefPointList list = new RoadRefPointList();
		String[] loads = data.split("\n");
		for (int i = 0; i < loads.length; i++) {
			RefPoint linkHefei = RefPoint.parseRefPointByTxt(loads[i]);
			list.points.add(linkHefei);
		}
		return list;	
	}

	public long getRoad_id() {
		return road_id;
	}

	public void setRoad_id(long roadId) {
		road_id = roadId;
	}

	public ArrayList<RefPoint> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<RefPoint> points) {
		this.points = points;
	}
}
