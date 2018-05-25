package com.wuan.weekly.service;

import java.util.List;

import com.wuan.weekly.entity.maggic.Report;

public interface WeeklyService {

	List<Report> getReportByWeekNum(int weekNum, int userId, int i, int weekNum2);

	void reportWeekly(Report saveReport);

	
}
