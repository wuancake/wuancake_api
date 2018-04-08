package com.wuan.weekly.service;

import com.wuan.weekly.entity.Report;

public interface WeeklyService {
	
	public void reportWeekly(Report report);

	public Report getReportByWeekNum(int weekNum, int userId);

	
}
