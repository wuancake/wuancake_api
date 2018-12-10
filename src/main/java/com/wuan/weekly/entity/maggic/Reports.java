package com.wuan.weekly.entity.maggic;

import java.util.List;

public class Reports {

	private List<com.wuan.weekly.entity.Report> reports;
	// 总的周报数
	private int count;

	public Reports() {
		super();
	}

	public Reports(List<com.wuan.weekly.entity.Report> reportList, int total) {
		super();
		this.reports = reportList;
		this.count = total;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<com.wuan.weekly.entity.Report> getReports() {
		return reports;
	}

	public void setReports(List<com.wuan.weekly.entity.Report> reports) {
		this.reports = reports;
	}

}
