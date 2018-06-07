package com.wuan.weekly.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wuan.weekly.entity.maggic.Msg;
import com.wuan.weekly.entity.maggic.Report;
import com.wuan.weekly.entity.maggic.Reports;
import com.wuan.weekly.exception.CheckReportFailException;
import com.wuan.weekly.exception.NotLoginException;
import com.wuan.weekly.exception.NullTextException;
import com.wuan.weekly.exception.ParamFormatException;
import com.wuan.weekly.exception.ReportFailException;
import com.wuan.weekly.service.imple.WeeklyServiceImple;
import com.wuan.weekly.util.Utils;

@RestController
public class ReportController {

	@Autowired
	private WeeklyServiceImple weeklyServiceImple;

	/**
	 * 提交周报
	 * @throws NotLoginException 
	 */
	@ResponseBody
	@RequestMapping(value="/submit",method=RequestMethod.POST)
	public Msg reportWeekly(@RequestBody Report reciveReport,HttpServletRequest request) throws ParamFormatException, NullTextException {
		//对请求参数进行检查
		if(reciveReport.getUserId() < 0 || reciveReport.getGroupId() < 0) {
			throw new ParamFormatException("用户ID或分组ID不正确！");
		}
		if ("".equals(reciveReport.getComplete()) || null == reciveReport || "".equals(reciveReport.getTrouble()) || null == reciveReport.getTrouble() || "".equals(reciveReport.getPlane()) || null == reciveReport.getPlane()) {
			throw new NullTextException("必填项不能为空！");
		}

		//设置周报状态为已提交
		final int status = 2;
		//生成提交周报时间
		Date dt = new Date();
		//根据字段生成周报内容
		String text = reciveReport.getComplete() + reciveReport.getTrouble() + reciveReport.getPlane() + reciveReport.getUrl();	
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
			//提交周报时发生了异常
			throw new ReportFailException(e);
		}
		//向前端响应消息
		Msg msg = new Msg("成功提交周报",200);
		return msg;
	}

	/**
	 * 查看周报
	 */
	@ResponseBody
	@RequestMapping(value="/myweekly",method=RequestMethod.POST)
	public Object getReport(@RequestBody Map<String,Object> page) throws ParamFormatException {
		//当前第几页（页数从1开始）
		int pageNum = (int) page.get("pageNum");
		//每页几份周报
		int weekNum = (int) page.get("weekNum");
		//用户id
		int userId = (int) page.get("userId");
		//分组id
		int groupId = (int) page.get("groupId");
		//验证请求参数是否合法
		if (groupId < 0 || userId < 0 || pageNum <= 0 || weekNum < 0) {
			throw new ParamFormatException("用户ID或分组ID不正确或请求页数不正确或周报份数不正确！");
		}
		List<Report> report = null;
		Reports reports = new Reports();
		try {
			//用户总共有多少周周报
			int count = weeklyServiceImple.getCountOfReport(userId,groupId);
			//正数第k周,例：总共100周周报（1-100），一周10页，第9页第一条就是正数第11周
			int index = count - (pageNum * weekNum);
			int limit = index;
			if (index < 0) {
				limit = 0;
				weekNum += index;
			}
			report = weeklyServiceImple.getReportByWeekNum(userId,groupId,limit,weekNum);
			//按周数降序排列
			Set<Report> set = new TreeSet<>();
			for(Report rep : report) {
				set.add(rep);
			}
			//向前端返回
			reports.setReports(set);
			reports.setCount(count);
		} catch(Exception e) {
			throw new CheckReportFailException();
		}
		//找不到周报
		if(report.isEmpty()) {
			Msg msg = new Msg("未提交",200);
			return msg;
		}
		return reports;	
	}
}
