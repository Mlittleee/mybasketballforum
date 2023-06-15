package com.project.mybasketballforum.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/************************
 * Manage-System
 * com.project.mybasketballforum.config
 * MHC
 * author : mhc
 * date:  2023/6/13 19:04
 * description : WebConfig 配置类中的 onStartup() 方法的实现，
 * 它的作用是在 Servlet 容器启动时，为 Spring MVC 配置
 * DispatcherServlet，以便能够处理 Web 请求
 ************************/
public class WebConfig implements WebApplicationInitializer {
    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx=new AnnotationConfigWebApplicationContext();
        ctx.register(WebMvcConfig.class);//注册SpringMvc的配置类WebMvcConfig
        ctx.setServletContext(servletContext);//和当前ServletContext关联
        /**
         * 注册SpringMvc的DispatcherServlet
         */
        ServletRegistration.Dynamic servlet=servletContext.addServlet("dispatcher", (Servlet) new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }
}
