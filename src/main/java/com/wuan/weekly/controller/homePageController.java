package com.wuan.weekly.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuan.weekly.entity.Info;
import com.wuan.weekly.entity.Leave;
import com.wuan.weekly.entity.Main;
import com.wuan.weekly.service.homePageService;
import com.wuan.weekly.util.Utils;

@RestController
public class homePageController {

	@Autowired
	private homePageService hps;

	@GetMapping("/")
	public Main home(
			@RequestParam(
					required=false, 
					name="id", 
					defaultValue="2") int user_id) {

		return hps.m(user_id);
	}


	@PostMapping("/leave")
	public Info leave(@RequestBody Leave li) {
		//请了多少周假
		int weekNum = li.getLeaveNum();
		//请假理由
		String reason = li.getReason()+"<br>";
		//用户id
		int userId = li.getUserId();
		//分组id
		int groupId = li.getGroupId();
		//生成请假周报
		Leave[] leaveReport = createLeaveReport(weekNum,reason,userId,groupId);
		try {
			hps.leaveWeekly(leaveReport);
		} catch (Exception e) {
			//如果在往数据库里更新请假出现问题，事务回滚
			//当前周数
			int thisWeek = (int) ((new Date().getTime() - Utils.FIRSTDAY.getTime()) / (7*24*60*60*1000));
			hps.cancelLeave(userId, thisWeek);
			return new Info("请假失败",500);
		}		
		//成功请假	
		return new Info("请假成功",200);		
	}

	@RequestMapping(value="/cancelLeave",method=RequestMethod.POST)
	public Info cancelLeave(@RequestBody Map<String,Object>  param) {
		int userId = (int) param.get("userId");
		//当前周数
		int thisWeek = (int) ((new Date().getTime() - Utils.FIRSTDAY.getTime()) / (7*24*60*60*1000));
		try { 
			hps.cancelLeave(userId,thisWeek);
		} catch (Exception e) {
			e.printStackTrace();
			return new Info("取消请假失败",500);
		}
		return new Info("取消请假成功",200);
	}




	private Leave[] createLeaveReport(int weekNum, String reason, int userId, int groupId) {
		Leave[] leaveReport = new Leave[weekNum];
		//周报状态  3为请假
		int status = 3;
		//当前周数
		int thisWeek = (int) ((new Date().getTime() - Utils.FIRSTDAY.getTime()) / (7*24*60*60*1000));
		for(int i = 0; i < weekNum; i++) {
			leaveReport[i] = new Leave(thisWeek,userId,groupId,status,reason);
			thisWeek++;
		}
		return leaveReport;
	}

}
