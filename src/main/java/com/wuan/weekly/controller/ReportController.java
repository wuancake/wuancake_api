package com.wuan.weekly.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import com.wuan.weekly.exception.NullTextException;
import com.wuan.weekly.exception.ParamFormatException;
import com.wuan.weekly.exception.ReportFailException;
import com.wuan.weekly.service.imple.WeeklyServiceImple;
import com.wuan.weekly.util.Utils;

@RestController
public class ReportController {

	@Autowired
	private WeeklyServiceImple weeklyServiceImple;
	private static final String SPLIT = "<br>";
	private static final int SUBMITTED = 2;

	/**
	 * 提交周报
	 */
	@ResponseBody
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public Msg reportWeekly(@RequestBody Report reciveReport, HttpServletRequest request) throws ParamFormatException, NullTextException, ReportFailException {
		//检查用户id和分组id是否合法
		checkParam("用户ID或分组ID不正确！",reciveReport.getUserId(),reciveReport.getGroupId());
		//检查必填项是否为空
		checkContent(reciveReport.getComplete(),reciveReport.getTrouble(),reciveReport.getPlane());
		//生成提交周报时间
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		Date dt = calendar.getTime();
		//根据字段生成周报内容
		String text = reciveReport.getComplete() + SPLIT + reciveReport.getTrouble() + SPLIT + reciveReport.getPlane() + SPLIT + reciveReport.getUrl() + SPLIT;
		//周数算出来 提交时间-第一周的时间/一周的时间
		int weekNum = (int) ((dt.getTime() - Utils.FIRSTDAY.getTime()) / (7 * 24 * 60 * 60 * 1000));
		//向周报设置其他信息
		reciveReport.setStatus(SUBMITTED);
		reciveReport.setWeekNum(weekNum);
		reciveReport.setText(text);
		reciveReport.setReplyTime(dt);
		try {
			//向数据库提交周报
			weeklyServiceImple.reportWeekly(reciveReport);
		} catch(ReportFailException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			//提交周报时发生了异常
			e.printStackTrace();
			throw new ReportFailException("向数据库提交周报时发生错误");
		}
		//正确提交，向前端响应消息
		Msg msg = new Msg("成功提交周报", 200);
		return msg;
	}

	//检查必填项是否为空
	private void checkContent(String...content) throws NullTextException {
		for(String str : content) {
			if (str == null || "".equals(str)) {
				throw new NullTextException("必填项不能为空！");
			}
		}	
	}

	//检查用户id和分组id等...是否合法
	private void checkParam(String errorMsg, Integer...id) throws ParamFormatException {
		for(Integer i : id) {
			if (i <= 0) {
				throw new ParamFormatException(errorMsg);
			}
		}
	}

	/**
	 * 查看周报
	 */
	@ResponseBody
	@RequestMapping(value = "/myweekly", method = RequestMethod.POST)
	public Object getReport(@RequestBody Map<String, Object> page) throws ParamFormatException, CheckReportFailException {
		//当前第几页（页数从1开始）
		int pageNum = (int) page.get("pageNum");
		//每页几份周报
		int weekNum = (int) page.get("weekNum");
		//用户id
		int userId = (int) page.get("userId");
		//分组id
		int groupId = (int) page.get("groupId");
		//验证请求参数是否合法
		checkParam("用户ID或分组ID不正确或请求页数不正确或周报份数不正确！",pageNum,weekNum,userId,groupId);
		List<Report> report = null;
		Reports reports = new Reports();
		try {
			//用户总共有多少周周报
			int count = weeklyServiceImple.getCountOfReport(userId, groupId);
			//正数第k周,例：总共100周周报（1-100），一周10页，第9页第一条就是正数第11周
			int index = count - (pageNum * weekNum);
			int limit = index;
			if (index < 0) {
				limit = 0;
				weekNum += index;
			}
			report = weeklyServiceImple.getReportByWeekNum(userId, groupId, limit, weekNum);
			System.out.println(report);
			//按周数降序排列
			Set<Report> set = new TreeSet<>();
			for (Report rep : report) {
				cuttingReport(rep);
				set.add(rep);
			}
			//向前端返回
			reports.setReports(set);
			reports.setCount(count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CheckReportFailException("查看周报失败！");
		}
		//找不到周报
		if (report.isEmpty()) {
			Msg msg = new Msg("未提交", 200);
			return msg;
		}
		return reports;
	}

	//切割周报
	private void cuttingReport(Report rep) {
		if(rep.getStatus() == 3) {
			return;
		}
		System.out.println(rep.getText());
		String reportTemp[] = rep.getText().split("<br>");
		//本周完成
		rep.setComplete(reportTemp[0]);
		//所遇困难
		rep.setTrouble(reportTemp[1]);
		//下周计划
		rep.setPlane(reportTemp[2]);
		//作品地址
		if (reportTemp.length == 4) {
			rep.setUrl(reportTemp[3]);
		}
	}

}
