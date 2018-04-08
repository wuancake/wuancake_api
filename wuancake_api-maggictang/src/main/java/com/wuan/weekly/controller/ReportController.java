package com.wuan.weekly.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuan.weekly.entity.Msg;
import com.wuan.weekly.entity.Report;
import com.wuan.weekly.entity.ReportMsg;
import com.wuan.weekly.entity.SubmitMsg;
import com.wuan.weekly.service.imple.WeeklyServiceImple;

@RestController
public class ReportController {

	/** 
	 * 第一周第一天的日期
	 */
	private static final Date FIRSTDAY;

	static { 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date d = null;
		try {
			d  = format.parse("2015-10-26 00:00:00");
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
		FIRSTDAY = d;
	}


	@Autowired
	private WeeklyServiceImple weeklyServiceImple;

	/**
	 * 提交周报
	 * @return 
	 */
	@RequestMapping(value="/submit",method=RequestMethod.POST,params= {"userId","groupId","complete","trouble","plane","url"})
	public Msg reportWeekly(@RequestParam("userId") int userId,
							@RequestParam("groupId") int groupId,
							@RequestParam("complete") String complete,
							@RequestParam("trouble") String trouble,
							@RequestParam("plane") String plane,
							@RequestParam("url") String url) {
		//设置周报状态为已提交
		int status = 1;
		//生成提交周报时间
		Date dt = new Date();
		//根据字段生成周报内容
		String text = complete + trouble + plane + url;	
		//周数算出来 提交时间-第一周的时间/一周的时间 
		int weekNum = (int)((dt.getTime() - FIRSTDAY.getTime()) / (7*24*60*60*1000));
		//生成周报
		Report report = new Report();
		report.setWeekNum(weekNum);
		report.setUserId(userId);
		report.setGroupId(groupId);
		report.setText(text);
		report.setStatus(status);
		report.setReplyTime(dt);
		try {
			//像数据库提交周报
			weeklyServiceImple.reportWeekly(report);
		} catch(Exception e) {
			Msg msg = new SubmitMsg();
			msg.setInfoCode(500);
			msg.setInfoText("提交周报失败");
			return msg;
		}
		//像前端相应消息
		Msg msg = new SubmitMsg();
		msg.setInfoCode(200);
		msg.setInfoText("成功提交周报");
		return msg;
	}

	@RequestMapping(value="/myweekly",method=RequestMethod.POST,params= {"userId","weekNum"})
	public Msg getReport(@RequestParam("userId") int userId,@RequestParam("weekNum") int weekNum) {
		Msg msg = null;
		Report report = null;
		try {
			report = weeklyServiceImple.getReportByWeekNum(weekNum, userId);
		} catch(Exception e) {
			msg = new Msg();
			msg.setInfoText("查看周报失败");
			msg.setInfoCode(500);
			return msg;
		}
		//找不到周报
		if(report == null) {
			msg = new Msg();
			msg.setInfoText("未提交");
			msg.setInfoCode(200);
			return msg;
		}
		switch(report.getStatus()) {
		//查询的周数有周报
		case 1: msg = createReportMsg(report, "成功返回周报", 200); break;
		//查询的周数已请假
		case 2: msg = new Msg();
		msg.setInfoText("已请假");
		msg.setInfoCode(200); break;
		//查询的周数未提交
		case 3: msg = new Msg();
		msg.setInfoText("未提交");
		msg.setInfoCode(200); break;
		}
		return msg;	
	}

	/**
	 * 创建周报
	 */
	private ReportMsg createReportMsg(Report report, String infoText, int infoCode) {
		ReportMsg msg = new ReportMsg();
		String[] text = report.getText().split("<br>");
		msg.setWeekNum(report.getWeekNum());
		msg.setComplete(text[0]);
		msg.setTrouble(text[1]);
		msg.setPlane(text[2]);
		msg.setUrl(text[3]);
		msg.setInfoText(infoText);
		msg.setInfoCode(infoCode);
		return msg;
	}

}
