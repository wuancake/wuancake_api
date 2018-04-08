package com.wuan.weekly.entity;

public class Main extends Info{

	public int weekNum;
	public static int status;
	private String url;
	
	public int getWeekNum() {
		return weekNum;
	}
	public void setWeekNum(int weekNum) {
		this.weekNum = weekNum;
	}
	public static int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public Main(int weekNum, String url) {
		super();
		this.weekNum = weekNum;
		this.url = url;
	}
	
	
	public Main() {
		super();
	}
	
	
	@Override
	public String toString() {
		return "main [weekNum=" + weekNum + ", status=" + status + ", url=" + url + "]";
	}
}
