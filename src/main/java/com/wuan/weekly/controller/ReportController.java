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
import com.wuan.weekly.entity.maggic.ReciveReport;
import com.wuan.weekly.entity.maggic.SaveReport;
import com.wuan.weekly.entity.maggic.SubmitMsg;
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
	public Msg reportWeekly(@RequestBody ReciveReport reciveReport) {
		//设置周报状态为已提交
		int status = 2;
		//生成提交周报时间
		Date dt = new Date();
		//根据字段生成周报内容
		String text = reciveReport.getComplete() + reciveReport.getPlane() + reciveReport.getUrl();	
		//周数算出来 提交时间-第一周的时间/一周的时间 
		int weekNum = (int)((dt.getTime() - Utils.FIRSTDAY.getTime()) / (7*24*60*60*1000));
		//生成周报
		SaveReport report = new SaveReport(reciveReport);
		report.setWeekNum(weekNum);
		report.setText(text);
		report.setStatus(status);
		report.setReplyTime(dt);

		try {
			//像数据库提交周报
			weeklyServiceImple.reportWeekly(report);
		} catch(Exception e) {
			throw new ReportFailException(e);
		}
		//像前端响应消息
		Msg msg = new SubmitMsg();
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
		
		Msg msg = null;
		List<SaveReport> report = null;
		try {
			report = weeklyServiceImple.getReportByWeekNum(userId,groupId,pageNum*weekNum,weekNum);
		} catch(Exception e) {
			throw new CheckReportFailException(e);
		}
		//找不到周报
		if(report.isEmpty()) {
			msg = new Msg();
			msg.setInfoText("未提交");
			msg.setInfoCode(200);
			return msg;
		}
		/*switch(report.getStatus()) {
		//查询的周数未提交
		case 1: msg = new Msg();
		msg.setInfoText("未提交");
		msg.setInfoCode(200); break; 
		//已提交
		case 2:msg = createReportMsg(report, "成功返回周报", 200); break; 
		//查询的周数已请假
		case 3: msg = new Msg();
		msg.setInfoText("已请假");
		msg.setInfoCode(200); break;
		}*/
		return report;	
	}

	/**
	 * 创建周报
	 */
	/*private ReportMsg createReportMsg(SaveReport report, String infoText, int infoCode) {
		ReportMsg msg = new ReportMsg();
		String[] text = report.getText().split("<br>");
		msg.setWeekNum(report.getWeekNum());
		msg.setComplete(text[0]);
		msg.setTrouble(text[1]);
		msg.setPlane(text[2]);
		msg.setUrl(text[3]);
		msg.setDate(report.getReplyTime());
		msg.setInfoText(infoText);
		msg.setInfoCode(infoCode);
		return msg;
	}*/

}
