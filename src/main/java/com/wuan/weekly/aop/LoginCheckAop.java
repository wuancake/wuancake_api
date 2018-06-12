package com.wuan.weekly.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import com.wuan.weekly.controller.UserController;
import com.wuan.weekly.exception.NotLoginException;

/**
 * 调用接口前检查是否登录
 * @author Administrator
 *
 */
@SuppressWarnings("unused")
@Aspect
//@Component
public class LoginCheckAop {

	@Autowired
	private	HttpServletRequest request;

	@Pointcut("execution(* com.wuan.weekly.controller.homePageController.*(..)) || execution(* com.wuan.weekly.controller.ReportController.*(..))")
	public void executeService(){}


	@Before("executeService()")
	public void checkLogin() throws NotLoginException {
		if (request.getSession().getAttribute(UserController.SESSION_NAME) == null) {
			throw new NotLoginException();
		}
	}
}
