package com.wuan.weekly.service;

import java.util.List;

import com.wuan.weekly.entity.maggic.Report;
import com.wuan.weekly.exception.ReportFailException;

public interface WeeklyService {

	List<Report> getReportByWeekNum(int weekNum, int userId, int i, int weekNum2);

	void reportWeekly(Report saveReport) throws ReportFailException;


    Integer findStatusByUserIdAndMaxWeekNum(Integer userId, Integer maxWeekNum);
}
