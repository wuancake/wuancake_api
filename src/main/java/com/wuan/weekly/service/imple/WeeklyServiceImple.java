package com.wuan.weekly.service.imple;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.wuan.weekly.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuan.weekly.entity.Report;
import com.wuan.weekly.entity.maggic.Reports;
import com.wuan.weekly.mapper.WeeklyDao;
import com.wuan.weekly.service.WeeklyService;

@Service
public class WeeklyServiceImple implements WeeklyService {

	// 用于切割或合并周报
	private static final String SPLIT = "<br>";
	// 用于设置周报状态为已提交
	private static final int SUBMITTED = 2;

	@Autowired
	private WeeklyDao weeklyDao;

	/***
	 * 提交周报
	 * 
	 * @param userId
	 *            用户ID
	 * @param groupId
	 *            分组ID
	 * @param complete
	 *            本周完成的任务
	 * @param trouble
	 *            本周遇到的困难
	 * @param plan
	 *            下周的计划
	 * @param url
	 *            作品链接（选填）
	 * @return true:提交成功 ;false:提交失败
	 */
	public Boolean reportWeekly(int userId, int groupId, String complete, String trouble, String plan, String url) {
		// 生成周报提交时间
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		Date date = calendar.getTime();
		// 周报内容
		String content = complete + SPLIT + trouble + SPLIT + plan + SPLIT + url + SPLIT;
		// 周数
		int week = (int) ((date.getTime() - Utils.FIRSTDAY.getTime()) / (7 * 24 * 60 * 60 * 1000));
		// 生成周报对象
		Report report = new Report(week, userId, groupId, content, SUBMITTED, date);
		Boolean result = false;
		try {
			result = weeklyDao.SubmitReport(report);
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	/**
	 * 
	 * @param userId
	 *            用户id
	 * @param groupId
	 *            分组id
	 * @param currentPage
	 *            当前页数
	 * @param num
	 *            每页条数
	 * @return 周报记录
	 */
	public Reports getWeekly(int userId, int groupId, int currentPage, int num) {
		// 总共total条周报
		int total = weeklyDao.getCountOfReport(userId);
		if (num == 1) {
			Report report = weeklyDao.GetReport(userId, currentPage);
			List<Report> list = new ArrayList<Report>();
			list.add(report);
			Reports reps = new Reports(list, total);
			return reps;
		} else {

			// 分页查询，第start条数据开始
			int start = total - (currentPage * num);
			if (start < 0) {
				start = 0;
			}
			List<Report> reportList = weeklyDao.GetReportList(userId, start, num);
			for (Report rep : reportList) {
				cutReport(rep);
			}
			Reports reps = new Reports(reportList, total);
			return reps;
		}
	}
	
	// 切割周报
	private void cutReport(Report rep) {
		if (rep.getStatus() == 3) {
			return;
		}
		String reportTemp[] = rep.getText().split("<br>");
		// 本周完成
		rep.setComplete(reportTemp[0]);
		// 所遇困难
		rep.setTrouble(reportTemp[1]);
		// 下周计划
		rep.setPlane(reportTemp[2]);
		// 作品地址
		if (reportTemp.length == 4) {
			rep.setUrl(reportTemp[3]);
		}
	}
}
