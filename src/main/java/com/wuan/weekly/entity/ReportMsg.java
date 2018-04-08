package com.wuan.weekly.entity;

/**
 * 查询周报后，返回给前端的信息
 * @author Maggic
 */
public class ReportMsg extends Msg {
	private int weekNum;
	private String complete;
	private String trouble;
	private String plane;
	private String url;
	
	public int getWeekNum() {
		return weekNum;
	}
	public void setWeekNum(int weekNum) {
		this.weekNum = weekNum;
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
