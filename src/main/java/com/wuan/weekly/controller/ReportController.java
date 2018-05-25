package com.wuan.weekly.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wuan.weekly.entity.maggic.Msg;
import com.wuan.weekly.entity.maggic.Report;
import com.wuan.weekly.entity.maggic.Reports;
import com.wuan.weekly.exception.CheckReportFailException;
import com.wuan.weekly.exception.ReportFailException;
import com.wuan.weekly.service.imple.WeeklyServiceImple;
import com.wuan.weekly.util.Utils;

@RestController
public class ReportController {

	@Autowired
	private WeeklyServiceImple weeklyServiceImple;

	/**
	 * 提交周报
	 */
	@RequestMapping(value="/submit",method=RequestMethod.POST)
	public Msg reportWeekly(@RequestBody Report reciveReport) {
		//设置周报状态为已提交
		final int status = 2;
		//生成提交周报时间
		Date dt = new Date();
		//根据字段生成周报内容
		String text = reciveReport.getComplete() + reciveReport.getPlane() + reciveReport.getUrl();	
		//周数算出来 提交时间-第一周的时间/一周的时间 
		int weekNum = (int)((dt.getTime() - Utils.FIRSTDAY.getTime()) / (7*24*60*60*1000));
		
		reciveReport.setStatus(status);
		reciveReport.setWeekNum(weekNum);
		reciveReport.setText(text);
		reciveReport.setReplyTime(dt);

		try {
			//像数据库提交周报
			weeklyServiceImple.reportWeekly(reciveReport);
		} catch(Exception e) {
			throw new ReportFailException(e);
		}
		//像前端响应消息
		Msg msg = new Msg();
		msg.setInfoCode(200);
		msg.setInfoText("成功提交周报");
		return msg;
	}

	/**
	 * 查看周报
	 */
	@RequestMapping(value="/myweekly",method=RequestMethod.POST)
	public Object getReport(@RequestBody Map<String,Object> page) {
		//当前第几页
		int pageNum = (int) page.get("pageNum");
		//每页几份周报
		int weekNum = (int) page.get("weekNum");
		//用户id
		int userId = (int) page.get("userId");
		//分组id
		int groupId = (int) page.get("groupId");
		
		List<Report> report = null;
		Reports reports = new Reports();
		try {
			report = weeklyServiceImple.getReportByWeekNum(userId,groupId,pageNum*weekNum,weekNum);
			//总的周报数
			int count = weeklyServiceImple.getCountOfReport(userId,groupId); 
			reports.setReports(report);
			reports.setCount(count);
		} catch(Exception e) {
			throw new CheckReportFailException(e);
		}
		//找不到周报
		if(report.isEmpty()) {
			Msg msg = new Msg();
			msg.setInfoText("未提交");
			msg.setInfoCode(200);
			return msg;
		}
		return reports;	
	}
}
