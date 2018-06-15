package com.wuan.weekly.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuan.weekly.entity.maggic.Msg;
import com.wuan.weekly.exception.CheckReportFailException;
import com.wuan.weekly.exception.NotLoginException;
import com.wuan.weekly.exception.NullTextException;
import com.wuan.weekly.exception.ParamFormatException;
import com.wuan.weekly.exception.ReportFailException;

/**
 * 对周报发生的异常进行统一处理
 */
@ControllerAdvice
public class WeeklyExceptionHandler {
	
	/**
	 * 返回提交周报失败的信息
	 */
	@ResponseBody
	@ExceptionHandler(value=com.wuan.weekly.exception.ReportFailException.class)
	public Msg createSubmitReportFailMsg(ReportFailException e) {
		Msg msg = new Msg();
		msg.setInfoCode(500);
		msg.setInfoText(e.getErrorMessage());
		return msg;	
	}

	/**
	 * 返回查看周报失败的信息
	 */
	@ResponseBody
	@ExceptionHandler(value=com.wuan.weekly.exception.CheckReportFailException.class)
	public Msg createCheckReportFailMsg(CheckReportFailException e) {
		Msg msg = new Msg();
		msg.setInfoText(e.getErrorMessage());
		msg.setInfoCode(500);
		return msg;
	}
	
	/**
	 * 返回请求参数不正确的错误信息
	 */
	@ResponseBody
	@ExceptionHandler(value=com.wuan.weekly.exception.ParamFormatException.class)
	public Msg createParamError(ParamFormatException e) {
		Msg msg = new Msg();
		msg.setInfoText(e.getErrorMessage());
		msg.setInfoCode(500);
		return msg;
	}
	
	/**
	 * 返回必填项为空的错误
	 */
	@ResponseBody
	@ExceptionHandler(value=com.wuan.weekly.exception.NullTextException.class)
	public Msg createNullMsgError(NullTextException e) {
		Msg msg = new Msg();
		msg.setInfoText(e.getErrorMessage());
		msg.setInfoCode(500);
		return msg;
	}
	
	/**
	 * 返回必填项为空的错误
	 */
	@ResponseBody
	@ExceptionHandler(value=com.wuan.weekly.exception.NotLoginException.class)
	public Msg notLoginError(NotLoginException e) {
		Msg msg = new Msg();
		msg.setInfoText(e.getErrorMessage());
		msg.setInfoCode(500);
		return msg;
	}
}
