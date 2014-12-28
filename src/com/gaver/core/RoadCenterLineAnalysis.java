package com.gaver.core;

import java.util.ArrayList;
import java.util.List;

import com.gaver.dataprec.Cluster;
import com.gaver.domain.Point;

public class RoadCenterLineAnalysis {

	public List<Cluster> clusterPoint(List<Point> dataPoints,
			double eps, int minPnt){
		List<Cluster> clusterList = new ArrayList<Cluster>();
		Cluster tempCluster = null;
		int count = 0;
		for (int i = 0; i < dataPoints.size(); i++) {
			Point dp = dataPoints.get(i);
			if (dp.isHasCatch()) {
				continue;
			}
			 tempCluster = calcularNeighborhood(dp, dataPoints,eps,minPnt);
			if (tempCluster != null) {
				tempCluster.setClusterName("Cluster " + (count++));
				clusterList.add(tempCluster);
			}
		}
		return clusterList;
	}
	
	private Cluster calcularNeighborhood(Point point, List<Point> dataPoints,
			double eps, int minPnt) {
		Cluster cluster = new Cluster();
		double x=0,y=0;
		int size = 0;
		for (Point dp : dataPoints) {
			double distance = point.realDistanceWithPoint(dp);
			if (distance <= eps) {
				dp.setHasCatch(true);
				x+=dp.getX();
				y+=dp.getY();
				size++;
			}
		}
		if (size >= minPnt) {
			cluster.setPoint(new Point(x/size,y/size));
			return cluster;
		}
		return null;
	}
	
	public void dividePoint(List<Cluster> C,double angle,double dis){
		
	}
}
