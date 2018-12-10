package com.wuan.weekly.entity.maggic;

import java.util.Date;

public class Report implements Comparable<Report> {

	//用户id
	private int userId;
	//分组id
	private int groupId;
	//本周完成
	private String complete;
	//所遇困难
	private String trouble;
	//下周计划
	private String plane;
	private String url;
	//周报内容
	private String text;
	//周报状态
	private int status;
	//回复时间
	private Date replyTime;
	//周报星期数
	private int weekNum;
	
	
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
	public String getComplete() {
		return complete;
	}
	public void setComplete(String complete) {
		this.complete = complete;
	}
	public String getTrouble() {
		return trouble;
	}
	public void setTrouble(String trouble) {
		this.trouble = trouble;
	}
	public String getPlane() {
		return plane;
	}
	public void setPlane(String plane) {
		this.plane = plane;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public int getWeekNum() {
		return weekNum;
	}
	public void setWeekNum(int weekNum) {
		this.weekNum = weekNum;
	}
	public Report() {
		super();
		// TODO 自动生成的构造函数存根
	}
	@Override
	public String toString() {
		return "Report [userId=" + userId + ", groupId=" + groupId + ", complete=" + complete + ", trouble=" + trouble
				+ ", plane=" + plane + ", url=" + url + ", text=" + text + ", status=" + status + ", replyTime="
				+ replyTime + ", weekNum=" + weekNum + "]";
	}

	@Override
	public int compareTo(Report o) {
		return o.weekNum > this.weekNum ? 1 : o.weekNum < this.weekNum ? -1 : 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Report other = (Report) obj;
		if (complete == null) {
			if (other.complete != null)
				return false;
		} else if (!complete.equals(other.complete))
			return false;
		if (groupId != other.groupId)
			return false;
		if (plane == null) {
			if (other.plane != null)
				return false;
		} else if (!plane.equals(other.plane))
			return false;
		if (replyTime == null) {
			if (other.replyTime != null)
				return false;
		} else if (!replyTime.equals(other.replyTime))
			return false;
		if (status != other.status)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (trouble == null) {
			if (other.trouble != null)
				return false;
		} else if (!trouble.equals(other.trouble))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (userId != other.userId)
			return false;
		if (weekNum != other.weekNum)
			return false;
		return true;
	}

}
