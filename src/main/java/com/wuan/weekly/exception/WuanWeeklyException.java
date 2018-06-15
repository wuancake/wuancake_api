package com.wuan.weekly.exception;

public class WuanWeeklyException extends Exception {
	
	private static final long serialVersionUID = 7017281547776300829L;
	private String errorMessage;

	public WuanWeeklyException(String errorMessage) {
		super();
		this.setErrorMessage(errorMessage);
	}

	public WuanWeeklyException() {
		super();
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}	
}
