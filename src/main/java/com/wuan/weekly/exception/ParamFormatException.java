package com.wuan.weekly.exception;

/**
 * 参数错误
 */
public class ParamFormatException extends WuanWeeklyException {

	private static final long serialVersionUID = 2649325224538647378L;

	public ParamFormatException() {
		super();
	}

	public ParamFormatException(String errorMessage) {
		super(errorMessage);
	}


}
