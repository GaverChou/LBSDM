package com.gaver.domain;

public class RefPoint {

	private int type_id;
	private long road_id;
	private int road_num;
	private Point point;

	public static RefPoint parseRefPointByTxt(String data) {
		if ("".equals(data)||data==null) {
			return null;
		}
		RefPoint refPoint = new RefPoint();
		String[] pointStr = data.split("\t");
//		System.out.println(pointStr[0]+"\t"+pointStr[1]+"\t"+pointStr[2]+"\t"+pointStr[3]);
		refPoint.type_id = Integer.parseInt(pointStr[0]);
		refPoint.road_id = Long.parseLong(pointStr[1]);
		refPoint.road_num = Integer.parseInt(pointStr[2]);
		refPoint.point = new Point(Double.parseDouble(pointStr[3]),
		 Double.parseDouble(pointStr[4]));
		return refPoint;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int typeId) {
		type_id = typeId;
	}
	
	public long getRoad_id() {
		return road_id;
	}

	public void setRoad_id(long roadId) {
		road_id = roadId;
	}

	public int getRoad_num() {
		return road_num;
	}

	public void setRoad_num(int roadNum) {
		road_num = roadNum;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

}
