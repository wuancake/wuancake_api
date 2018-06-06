package com.wuan.weekly.entity.maggic;

import java.util.Set;

public class Reports {

	
	private Set<Report> reports;
	//总的周报数
	private int count;

	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Set<Report> getReports() {
		return reports;
	}
	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}
	
}
