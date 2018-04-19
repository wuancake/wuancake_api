package com.wuan.weekly.exception;

public class CheckReportFailException extends RuntimeException{

	private static final long serialVersionUID = -1885834430508450265L;

	public CheckReportFailException() {
		super();
		System.out.println("查看周报失败");
	}

	public CheckReportFailException(Exception e) {
		System.out.println("查看周报失败");
		e.printStackTrace();	
	}

}
