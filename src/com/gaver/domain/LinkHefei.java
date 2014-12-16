package com.gaver.domain;

public class LinkHefei {

	private long road_id;
	private String road_name;
	private int road_rank;
	private double road_length;
	private String road_type;
	private Point start_point;
	private Point end_point;
	
	public static LinkHefei parseLinkHefeiByTxt(String data) {
		LinkHefei linkHefei = new LinkHefei();
		String[] roadpor = data.split("\t");
		linkHefei.road_id = Long.parseLong(roadpor[0]);
		linkHefei.road_name = roadpor[1];
		linkHefei.road_rank = Integer.parseInt(roadpor[2]);
		linkHefei.road_length = Double.parseDouble(roadpor[3]);
		linkHefei.road_type = roadpor[4];
		linkHefei.start_point = new Point(Double.parseDouble(roadpor[5]),
						Double.parseDouble(roadpor[6]));
		linkHefei.end_point = new Point(Double.parseDouble(roadpor[7]),
		 				Double.parseDouble(roadpor[8]));
		return linkHefei;
	}
	public long getRoad_id() {
		return road_id;
	}
	public void setRoad_id(long roadId) {
		road_id = roadId;
	}
	public String getRoad_name() {
		return road_name;
	}
	public void setRoad_name(String roadName) {
		road_name = roadName;
	}
	public int getRoad_rank() {
		return road_rank;
	}
	public void setRoad_rank(int roadRank) {
		road_rank = roadRank;
	}
	public double getRoad_length() {
		return road_length;
	}
	public void setRoad_length(double roadLength) {
		road_length = roadLength;
	}
	public String getRoad_type() {
		return road_type;
	}
	public void setRoad_type(String roadType) {
		road_type = roadType;
	}
	public Point getStart_point() {
		return start_point;
	}
	public void setStart_point(Point startPoint) {
		start_point = startPoint;
	}
	public Point getEnd_point() {
		return end_point;
	}
	public void setEnd_point(Point endPoint) {
		end_point = endPoint;
	}
}
