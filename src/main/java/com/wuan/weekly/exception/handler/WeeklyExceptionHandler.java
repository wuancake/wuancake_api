package com.wuan.weekly.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuan.weekly.entity.maggic.Msg;

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
	public Msg createSubmitReportFailMsg() {
		Msg msg = new Msg();
		msg.setInfoCode(500);
		msg.setInfoText("提交周报失败");
		return msg;	
	}

	/**
	 * 返回查看周报失败的信息
	 */
	@ResponseBody
	@ExceptionHandler(value=com.wuan.weekly.exception.CheckReportFailException.class)
	public Msg createCheckReportFailMsg() {
		Msg msg = new Msg();
		msg.setInfoText("查看周报失败");
		msg.setInfoCode(500);
		return msg;
	}
	
	/**
	 * 返回请求参数不正确的错误信息
	 */
	@ResponseBody
	@ExceptionHandler(value=com.wuan.weekly.exception.ParamFormatException.class)
	public Msg createParamError() {
		Msg msg = new Msg();
		msg.setInfoText("传入参数错误！");
		msg.setInfoCode(500);
		return msg;
	}
	
	/**
	 * 返回必填项为空的错误
	 */
	@ResponseBody
	@ExceptionHandler(value=com.wuan.weekly.exception.NullTextException.class)
	public Msg createNullMsgError() {
		Msg msg = new Msg();
		msg.setInfoText("必填项不能为空！");
		msg.setInfoCode(500);
		return msg;
	}
	
	/**
	 * 返回必填项为空的错误
	 */
	@ResponseBody
	@ExceptionHandler(value=com.wuan.weekly.exception.NotLoginException.class)
	public Msg notLoginError() {
		Msg msg = new Msg();
		msg.setInfoText("请先登录！");
		msg.setInfoCode(500);
		return msg;
	}
}
