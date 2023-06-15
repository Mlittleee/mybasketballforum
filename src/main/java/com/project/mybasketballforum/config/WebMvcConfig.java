package com.project.mybasketballforum.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.project.mybasketballforum.interceptors.LoginInterceptor;
import com.project.mybasketballforum.interceptors.RefreshTokenInterceptor;
import com.project.mybasketballforum.universal.JacksonObjectTrans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/************************
 * Manage-System
 * com.project.mybasketballforum.config
 * MHC
 * author : mhc
 * date:  2023/6/13 19:06
 * description : 配置Web MVC框架，支持拦截器、消息转换器,
 ************************/
@Slf4j
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 扩展MVC框架的消息转换器
     * @param converters MVC原先默认的转换器
     * 这里使用了自己定义的消息转换器JacksonObjectTrans
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建消息转换器对象
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器，底层使用Jackson将java对象转为json
        converter.setObjectMapper(new JacksonObjectTrans());
        //将这个消息转换器追加到默认的转换器中
        converters.add(0,converter);
    }

    /**
     * 配置拦截器：通过addInterceptors()方法，添加两个拦截器，
     * LoginInterceptor用于拦截需要登录才能访问的URL请求，
     * RefreshTokenInterceptor用于验证用户的身份信息
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).excludePathPatterns("/user/login","/Index","/js/**","/css/**","/img/**"
                ,"/webjars/**"
                ,"/v2/**").order(1);
        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate)).addPathPatterns("/**").order(0);
    }

    /**
     * 配置消息转换器：通过configureMessageConverters()方法，
     * 添加FastJsonHttpMessageConverter消息转换器，以支持多种类型的数据格式，如JSON、XML、CBOR等
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        List<MediaType> mediaTypes = new ArrayList<>(16);
        mediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        mediaTypes.add(MediaType.APPLICATION_CBOR);
        mediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        mediaTypes.add(MediaType.APPLICATION_JSON);
        mediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        converter.setSupportedMediaTypes(mediaTypes);
        converters.add(converter);
    }
}


