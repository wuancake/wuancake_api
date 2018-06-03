package com.wuan.weekly.exception;

/**
 * 请求文本为空报的异常
 */
public class NullTextException extends Exception {

	private static final long serialVersionUID = 3166966078200281638L;

	public NullTextException(String message) {
		System.err.println(message);
	}

	
}
