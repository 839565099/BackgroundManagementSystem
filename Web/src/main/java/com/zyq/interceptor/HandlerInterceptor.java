package com.zyq.interceptor;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 作者：张翼麒
 * 自定义拦截器
 */
public class HandlerInterceptor implements org.springframework.web.servlet.HandlerInterceptor {
    /**
     * 预处处理：Controller方法执行前执行   return true 放行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    return true;

    }


    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            // System.out.println("后处理~~~~");
    }


    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
          //System.out.println("响应页面之后处理~~~~");
    }
}
