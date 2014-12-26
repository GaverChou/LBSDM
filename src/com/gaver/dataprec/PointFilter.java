package com.gaver.dataprec;

import java.util.ArrayList;

import com.gaver.domain.User;
import com.gaver.domain.UserPoint;

public class PointFilter {

	private User user;
	private double minV;
	private double maxV;

	public PointFilter() {
		super();
	}

	public PointFilter(User user) {
		this.user = user;
	}

	public double getMinV() {
		return minV;
	}

	public void setMinV(double minV) {
		this.minV = minV;
	}

	public double getMaxV() {
		return maxV;
	}

	public void setMaxV(double maxV) {
		this.maxV = maxV;
	}

	private void stopPointFilt() {
		ArrayList<UserPoint> userPoints = user.getuPoints();
		ArrayList<UserPoint> newuserPoints = new ArrayList<UserPoint>();
		newuserPoints.add(userPoints.get(0));
		System.out.println(userPoints.size());
		for (int i = 0; i < userPoints.size() - 1; i++) {
			for (int j = i + 1; j < userPoints.size(); j++) {
				UserPoint p1 = userPoints.get(i);
				UserPoint p2 = userPoints.get(i + 1);
				double speed = p1.speed(p2);
				System.out.println(speed);
				if (speed > minV && speed < maxV) {
					newuserPoints.add(userPoints.get(i + 1));
					break;
				} else {
					i++;
				}
			}
		}
		user.setuPoints(newuserPoints);
	}

	public void run() {
		stopPointFilt();
		int cout = user.getuPoints().size();
	}
}
