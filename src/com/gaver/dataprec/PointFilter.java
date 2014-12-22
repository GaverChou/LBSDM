package com.gaver.dataprec;

import java.util.ArrayList;
import java.util.Iterator;

import com.gaver.domain.Point;
import com.gaver.domain.User;
import com.gaver.domain.UserPoint;

public class PointFilter implements Runnable {

	private User user;
	private double minDistance;

	public PointFilter() {
		super();
	}

	public PointFilter(User user) {
		this.user = user;
	}

	public void setMinDistance(double minDistance) {
		this.minDistance = minDistance;
	}

	@SuppressWarnings("unused")
	private void stopPointFilt() {
		ArrayList<UserPoint> userPoints = user.getuPoints();
		ArrayList<UserPoint> newuserPoints = new ArrayList<UserPoint>();
		newuserPoints.add(userPoints.get(0));
		for (int i = 0; i < userPoints.size() - 1; i++) {
			for (int j = i + 1; j < userPoints.size(); j++) {
				Point p1 = userPoints.get(i).getPoint();
				Point p2 = userPoints.get(i + 1).getPoint();
				if (p1.realDistanceWithPoint(p2) > minDistance) {
					newuserPoints.add(userPoints.get(i + 1));
					break;
				}else {
					i++;
				}
			}
		}
		user.setuPoints(newuserPoints);
	}

	@Override
	public void run() {
		stopPointFilt();
		@SuppressWarnings("unused")
		int cout =user.getuPoints().size();
	}
}
