package com.wuan.weekly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import com.wuan.weekly.entity.Leave;
import com.wuan.weekly.entity.Main;
import com.wuan.weekly.mapper.homePageMapper;

public class homePageService {

	@Autowired
	homePageMapper mapper;
	
	public List<Leave> loadLeave(){
		return null;
	}
	
	
	public Leave LeaveWeekly(
			@RequestParam("groupId") int group_id,
			@RequestParam("userId") int user_id,
			@RequestParam("status") int status,
			@RequestParam("leaveNum" )int leave_num,
			@RequestParam("reason") String reason) {
		Leave leave = new Leave();
		leave.setGroupId(group_id);
		leave.setUserId(user_id);
		leave.setStatus(status);
		leave.setLeaveNum(leave_num);
		leave.setReason(reason);
		
		Leave li = new Leave();
		li.setWeekNum(0);
		li.setStatus(3);
		li.setInfoText("请假成功");
		li.setInfoCode(200);
		return li;
		
	}
	
	
	
	public void page() {
		Main main = new Main();
		main.setWeekNum(0);
		main.setStatus(0);
		System.out.println(main);
	}
	
	
	public Main m(int user_id) {
		
		int status = mapper.getStatus(user_id);
//		System.out.println(status);
//		int status = id%2;
		
		Main ma = new Main();

		if (status == 1) {
			ma.setWeekNum(0);
			ma.setStatus(status);
			ma.setInfoText("未提交");
			ma.setInfoCode(301);
		} else {
			if (status == 2) {
				ma.setWeekNum(0);
				ma.setStatus(status);
				ma.setInfoText("已提交");
				ma.setInfoCode(200);
			} else {
				ma.setWeekNum(0);
				ma.setStatus(status);
				ma.setInfoText("已请假");
				ma.setInfoCode(200);
			}
		}
		return ma;	
	}
}
