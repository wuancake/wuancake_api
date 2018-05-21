package com.wuan.weekly.service;

import java.util.List;

import com.wuan.weekly.entity.maggic.SaveReport;

public interface WeeklyService {
	
	void reportWeekly(SaveReport report);

	List<SaveReport> getReportByWeekNum(int weekNum, int userId, int i, int weekNum2);

	
}
