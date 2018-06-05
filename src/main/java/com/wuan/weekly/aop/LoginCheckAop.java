package com.wuan.weekly.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoginCheckAop {

	@Before(value="execution(* )")
	public void checkLogin() {
		
	}
}
