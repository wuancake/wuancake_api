package com.wuan.weekly.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuan.weekly.entity.maggic.SaveReport;
import com.wuan.weekly.mapper.WeeklyDao;
import com.wuan.weekly.service.WeeklyService;

@Service
public class WeeklyServiceImple implements WeeklyService {
	
	@Autowired
	private WeeklyDao weeklyDao;
	
	@Override
	public void reportWeekly(SaveReport report) {
		// TODO 自动生成的方法存根
		weeklyDao.reportWeekly(report);
	}

	@Override
	public SaveReport getReportByWeekNum(int weekNum,int userId) {
		// TODO 自动生成的方法存根
		SaveReport report = weeklyDao.getReportByWeekNum(weekNum,userId);
		return report;
	}

}
