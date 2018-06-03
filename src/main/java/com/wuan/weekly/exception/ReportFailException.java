package com.wuan.weekly.exception;

/**
 * 提交周报发生异常
 */
public class ReportFailException extends RuntimeException {	

	private static final long serialVersionUID = -4031428632082701899L;

	public ReportFailException(Exception e) {
		System.err.println("提交周报失败");
		e.printStackTrace();	
	}
	
	public ReportFailException() {
		super();
		System.err.println("提交周报失败");
	}
}
