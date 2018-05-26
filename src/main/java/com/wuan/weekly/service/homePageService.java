package com.wuan.weekly.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wuan.weekly.entity.Leave;
import com.wuan.weekly.entity.Main;
import com.wuan.weekly.mapper.homePageMapper;
import com.wuan.weekly.util.Utils;

@Service
public class homePageService {

	@Autowired
	private homePageMapper mapper;
	
	public List<Leave> loadLeave(){
		return null;
	}
	
	
	/*public Leave LeaveWeekly(
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
	
	
	public Main m(int user_id) {
		//当前周数
        int thisWeek = (int) ((new Date().getTime() - Utils.FIRSTDAY.getTime()) / (7 * 24 * 60 * 60 * 1000));
		int status = 0;
        try {
        	status = mapper.selectStatus(user_id,thisWeek);
        } catch(NullPointerException e) {
        	status = 1;
        }
		System.out.println(status);
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
			}
			else if(status == 0) {
				ma.setInfoCode(500);
				ma.setInfoText("服务器错误");
			}
			else {
				ma.setWeekNum(0);
				ma.setStatus(status);
				ma.setInfoText("已请假");
				ma.setInfoCode(200);
			}
		}
		return ma;	
	}
}
