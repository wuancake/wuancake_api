package com.wuan.weekly.entity.maggic;

import java.util.List;

public class Reports {

	
	private List<Report> reports;
	//总的周报数
	private int count;
	public List<Report> getReports() {
		return reports;
	}
	public void setReports(List<Report> reports) {
		this.reports = reports;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
