package com.wuan.weekly.service;

public interface WeeklyService {

	Boolean reportWeekly(int userId, int groupId, String complete, String trouble, String plan, String url);
	
}
