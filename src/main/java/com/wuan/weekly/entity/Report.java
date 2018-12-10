package com.wuan.weekly.entity;

import java.util.Date;

public class Report {

	/**
	 * 周
	 */
	private int week_num;

	/**
	 * 用户id
	 */
	private int user_id;
	
	/**
	 * 分组id
	 */
	private int group_id;
	
	/**
	 * 周报内容
	 */
	private String text;
		
	/**
	 * 周报状态
	 */
	private int status;
	
	/**
	 * 提交时间
	 */
	private Date reply_time;
	
	/**
	 * 本周完成
	 */
	private String complete;
	
	/**
	 * 所遇困难
	 */
	private String trouble;
	
	/**
	 * 下周计划
	 */
	private String plane;
	
	/**
	 * 作品链接
	 */
	private String url;
	
	public int getWeek_num() {
		return week_num;
	}

	public void setWeek_num(int week_num) {
		this.week_num = week_num;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
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

	public Date getReply_time() {
		return reply_time;
	}

	public void setReply_time(Date reply_time) {
		this.reply_time = reply_time;
	}

	public Report(int week_num, int user_id, int group_id, String text, int status, Date reply_time) {
		super();
		this.week_num = week_num;
		this.user_id = user_id;
		this.group_id = group_id;
		this.text = text;
		this.status = status;
		this.reply_time = reply_time;
	}

	public Report() {
		super();
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
	
	
}
