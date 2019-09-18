package com.zyq.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 作者：张翼麒
 * 异常处理器
 */
@Component
public class ErrorResolver implements HandlerExceptionResolver {

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Error error = null;
        if (ex instanceof Error) {
            error = (Error) ex;
        } else {
            error = new Error("系统正在维护~~");
            System.out.println(ex.getMessage());
            System.out.println(ex.toString());
        }
        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("err", error.getException());
        //配置跳转
        modelAndView.setViewName("404");
        return modelAndView;
    }
}
