package com.project.mybasketballforum.interceptors;

import com.project.mybasketballforum.universal.UserHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/************************
 * Manage-System
 * com.project.mybasketballforum.config
 * MHC
 * author : mhc
 * date:  2023/6/13 19:06
 * description :登录拦截器
 ************************/
public class LoginInterceptor implements HandlerInterceptor {

    //前用户不存在，则表示没有登录，需要拦截该请求
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断用户是否登录
        if(UserHolder.getUser()==null){
            //没有,需要拦截
            response.setStatus(408);
            return false;
        }
        System.out.println("登录拦截器通过");
        System.out.println(UserHolder.getUser());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
