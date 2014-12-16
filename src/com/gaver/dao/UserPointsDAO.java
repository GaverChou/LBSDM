package com.gaver.dao;

import com.gaver.domain.UserPoint;

public interface UserPointsDAO {

	public boolean addUserPoint(UserPoint up);
	
	public UserPoint getUserPoint();
	
	public void updateUserPoint(String uid);
}
