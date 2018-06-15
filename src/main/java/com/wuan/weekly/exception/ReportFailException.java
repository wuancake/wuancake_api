package com.wuan.weekly.exception;

/**
 * 提交周报发生异常
 */
public class ReportFailException extends WuanWeeklyException {	

	private static final long serialVersionUID = -4031428632082701899L;

	public ReportFailException() {
		super();
	}

	public ReportFailException(String errorMessage) {
		super(errorMessage);
	}
	
}
