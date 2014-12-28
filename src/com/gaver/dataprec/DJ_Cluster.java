package com.gaver.dataprec;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gaver.domain.Point;
import com.gaver.domain.User;
import com.gaver.domain.UserPoint;

public class DJ_Cluster {

	public List<Cluster> doDJClusterAnalysis(List<Point> dataPoints,
			double radius, int ObjectNum) {
		List<Cluster> clusterList = new ArrayList<Cluster>();
		int count = 0;
		for (int i = 0; i < dataPoints.size(); i++) {
			Point dp = dataPoints.get(i);
			List<Point> arrivableObjects = getNeighborhood(dp, dataPoints,
					radius, ObjectNum);
			if (arrivableObjects != null) {
				boolean flag = false;
				for (int j = 0; j < clusterList.size(); j++) {
					Cluster clusterB = clusterList.get(j);
					List<Point> dpsB = clusterB.getDataPoints();
					flag = mergeList(dpsB,arrivableObjects);
					if (flag) {
						break;
					}
				}
				if (!flag) {
					Cluster tempCluster = new Cluster();
					tempCluster.setClusterName("Cluster " + (count++));
					tempCluster.setDataPoints(arrivableObjects);
					clusterList.add(tempCluster);
				}
			}
		}

		return clusterList;
	}

	/**
	 * 合并两个簇
	 * @param dps1 主簇
	 * @param dps2 后面加的簇
	 * @return
	 */
	private boolean mergeList(List<Point> dps1, List<Point> dps2) {
		boolean flag = false;
		if (dps1 == null || dps2 == null || dps1.size() == 0
				|| dps2.size() == 0) {
			return flag;
		}
		for (Point dp : dps2) {
			if (isContain(dp, dps1)) {
				flag = true;
				break;
			}
		}
		if(flag){
	           for(Point dp:dps2){
	              if(!isContain(dp,dps1)){
	                  dps1.add(dp);
	              }
	           }
	       }
		return flag;
	}

	private boolean isContain(Point dp, List<Point> dps1) {
		boolean flag = false;
		for (Point tempDp : dps1) {
			if (dp.equals(tempDp)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	private List<Point> getNeighborhood(Point point, List<Point> dataPoints,
			double radius, int objectNum) {
		List<Point> arrivableObjects = new ArrayList<Point>(); // 用来存储所有直接密度可达对象

		for (Point dp : dataPoints) {
			double distance = point.realDistanceWithPoint(dp);
			if (distance <= radius) {
				dp.setHasCatch(true);
				arrivableObjects.add(dp);
			}
		}

		if (arrivableObjects.size() >= objectNum) {
			return arrivableObjects;
		}

		return null;
	}

	public void displayCluster(List<Cluster> clusterList) {
		if (clusterList != null) {
			int count = 0;
			for (Cluster tempCluster : clusterList) {
				if (tempCluster.getDataPoints() != null
						&& tempCluster.getDataPoints().size() > 0) {
					count += tempCluster.getDataPoints().size();
//					System.out.println("----------"
//							+ tempCluster.getClusterName() + "----------");
					for (Point dp : tempCluster.getDataPoints()) {
						System.out.println(dp.toString());
					}
				}
			}
			System.out.println("PointSize:" + count + ",and Cluster count is:"
					+ clusterList.size());
		}
	}

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		ArrayList<Point> dpoints2 = Test("C:\\Users\\Administrator\\Desktop\\LBS_DATASET\\LBS_DATASET\\traj_hefei.txt");
//		for (Point point : dpoints2) {
//			System.out.println(point.toString());
//		}
		DJ_Cluster ca = new DJ_Cluster();
		List<Cluster> clusterList = ca.doDJClusterAnalysis(dpoints2, 15, 20);
		ca.displayCluster(clusterList);
//		RoadCenterLineAnalysis analysis = new RoadCenterLineAnalysis();
//		List<Cluster> list = analysis.clusterPoint(dpoints2, 25, 2);
//		Cluster cluster = null;
//		for (int i = 0; i < list.size(); i++) {
//			cluster = list.get(i);
//			System.out.println(cluster.getPoint().toString());
//		}
		long endTime = System.currentTimeMillis();
		System.out.println("耗时共:" + (endTime - startTime) / 1000.0 + "s");
	}

	public static ArrayList<Point> Test(String filepath) {
		BufferedReader bReader = null;
		ArrayList<Point> dpoints = new ArrayList<Point>();
		try {

			bReader = new BufferedReader(new FileReader(filepath));
			String temp;
			int count = 0;
			User user = null;
			while ((temp = bReader.readLine()) != null) {
				user = User.parseUserByTxt(temp);
				PointFilter filter = new PointFilter(user);
				filter.setMinV(0.5);
				filter.setMaxV(15);
				filter.run();
				for (UserPoint point : user.getuPoints()) {
					dpoints.add(point.getPoint());
					count++;
				}
				if (count>10000) {
					break;
				}
			}
			System.out.println("All point count is : " + count);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bReader != null) {
				try {
					bReader.close();
				} catch (IOException e) {
				}
			}
		}
		return dpoints;
	}
}
