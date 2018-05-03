package com.wuan.weekly.service;

import com.wuan.weekly.entity.maggic.SaveReport;

public interface WeeklyService {
	
	public void reportWeekly(SaveReport report);

	public SaveReport getReportByWeekNum(int weekNum, int userId);

	
}
