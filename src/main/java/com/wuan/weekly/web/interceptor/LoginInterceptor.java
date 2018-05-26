package com.wuan.weekly.web.interceptor;

import com.wuan.weekly.entity.JsonBean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Ericheel
 * @Description: 登录验证
 * @date 2018/5/2618:07
 */
@Component
public class LoginInterceptor extends WebMvcConfigurationSupport implements HandlerInterceptor {
    private static final String SESSION_CHECK = "9527";

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration ir = registry.addInterceptor(new LoginInterceptor());
        ir.addPathPatterns("/**");
        ir.excludePathPatterns("/regist", "/login", "/group");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        JsonBean jsonBean = new JsonBean();
        if (request.getSession().getAttribute(SESSION_CHECK) == null) {
            return false;
        }
        return true;
    }
}
