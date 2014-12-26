package com.gaver.core;

import java.util.ArrayList;
import java.util.List;

import com.gaver.dataprec.Cluster;
import com.gaver.domain.Point;

public class RoadCenterLineAnalysis {

	public List<Cluster> clusterPoint(List<Point> dataPoints,
			double eps, int minPnt){
		List<Cluster> clusterList = new ArrayList<Cluster>();
		for (int i = 0; i < dataPoints.size(); i++) {
			Point dp = dataPoints.get(i);
			if (dp.isHasCatch()) {
				continue;
			}
			List<Point> arrivableObjects = getNeighborhood(dp, dataPoints,eps,minPnt);
			if (arrivableObjects != null) {
				Cluster tempCluster = new Cluster();
				tempCluster.setClusterName("Cluster " + i);
				tempCluster.setDataPoints(arrivableObjects);
				tempCluster.calculateCoordinate();
				clusterList.add(tempCluster);
			}
		}
		return clusterList;
	}
	
	private List<Point> getNeighborhood(Point point, List<Point> dataPoints,
			double eps, int minPnt) {
		List<Point> arrivableObjects = new ArrayList<Point>(); // 用来存储所有直接密度可达对象

		for (Point dp : dataPoints) {
			double distance = point.realDistanceWithPoint(dp);
			if (distance <= eps) {
				dp.setHasCatch(true);
				arrivableObjects.add(dp);
			}
		}

		if (arrivableObjects.size() >= minPnt) {
			return arrivableObjects;
		}
		return null;
	}
	
	public void dividePoint(List<Cluster> C,double angle,double dis){
		
	}
}
