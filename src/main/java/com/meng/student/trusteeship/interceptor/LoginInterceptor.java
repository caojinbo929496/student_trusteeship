package com.meng.student.trusteeship.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 吴宸煊
 * @Description: 设置未登录 无法访问
 * @Date: Created in 10:18 2018/3/14
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        /**
         * 未登录时，不拦截页面图片
         */
        String soureceUrl = request.getRequestURI();
        if (soureceUrl.indexOf("2.jpg") >= 0) {
            return true;
        }
        if (request.getSession().getAttribute("administrator") == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }


}
