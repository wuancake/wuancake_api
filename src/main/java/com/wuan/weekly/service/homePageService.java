package com.wuan.weekly.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wuan.weekly.entity.Leave;
import com.wuan.weekly.entity.Main;
import com.wuan.weekly.mapper.homePageMapper;

@Service
public class homePageService {

	@Autowired
	private homePageMapper mapper;
	
	public List<Leave> loadLeave(){
		return null;
	}
	
	
	/*public Leave LeaveWeekly(
			@RequestParam("groupId") int groupId,
			@RequestParam("userId") int userId,
			@RequestParam("status") int status,
			@RequestParam("leaveNum" )int leave_num,
			@RequestParam("reason") String reason) {
		Leave leave = new Leave();
		leave.setGroupId(groupId);
		leave.setUserId(userId);
		leave.setStatus(status);
		leave.setLeaveNum(leave_num);
		leave.setReason(reason);
		
		Leave li = new Leave();
		li.setWeekNum(0);
		li.setStatus(3);
		li.setInfoText("请假成功");
		li.setInfoCode(200);
		return li;
		
	}*/
	/**
	 * 向数据库提交请假周报
	 */
	public void leaveWeekly(Leave[] leave) {
		for(int i = 0; i < leave.length; i++) {
			//请假
			mapper.leaveWeekly(leave[i],new Date());
		}
	}
	
	public void cancelLeave(int userId, int thisWeek) {
		mapper.cancelLeave(userId,thisWeek);
	}
	
	
	public void page() {
		Main main = new Main();
		main.setWeekNum(0);
		main.setStatus(0);
		System.out.println(main);
	}
	
	
	public Main m(int userId) {
		
		int status = mapper.getStatus(userId);
//		System.out.println(status);
//		int status = id%2;
		
		Main ma = new Main();

		if (status == 1) {
			ma.setWeekNum(0);
			ma.setStatus(status);
			ma.setInfoText("未提交");
			ma.setInfoCode(200);
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
