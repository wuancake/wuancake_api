package com.wuan.weekly.entity.maggic;

import java.util.Date;

/**
 * 保存的周报
 * @author Maggic
 *
 */
public class SaveReport {
	
	//周报星期数
	private int weekNum;
	//用户id
	private int userId;
	//分组id
	private int groupId;
	//周报内容
	private String text;
	//周报状态
	private int status;
	//回复时间
	private Date replyTime;
	
	
	public SaveReport(ReciveReport ReciveReport) {
		this.userId = ReciveReport.getUserId();
		this.groupId = ReciveReport.getGroupId();
	}
	
	
	public SaveReport() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public int getWeekNum() {
		return weekNum;
	}
	public void setWeekNum(int weekNum) {
		this.weekNum = weekNum;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	
	@Override
	public String toString() {
		return "Report [weekNum=" + weekNum + ", userId=" + userId + ", groupId=" + groupId + ", text=" + text
				+ ", status=" + status + ", replyTime=" + replyTime + "]";
	}
	
}
