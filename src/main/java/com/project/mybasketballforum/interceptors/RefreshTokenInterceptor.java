package com.project.mybasketballforum.interceptors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.project.mybasketballforum.universal.UserHolder;
import com.project.mybasketballforum.dto.UserDto;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.project.mybasketballforum.utils.RedisConstants.LOGIN_USER_KEY;
import static com.project.mybasketballforum.utils.RedisConstants.LOGIN_USER_TTL;


public class RefreshTokenInterceptor implements HandlerInterceptor {
    private final StringRedisTemplate stringRedisTemplate;

    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate=stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取token及redis中的用户
        String token = request.getHeader("Authorization");
        //System.out.println("你这里也拿到token啦 token:"+token);
        if(StringUtils.isEmpty(token)){
            //为什么这里的token为空？？？
            //System.out.println("token为空");
            //System.out.println("token:"+token);
            return true;
        }
        //调试用
        //System.out.println("你已经通过啦token:"+token);

        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(LOGIN_USER_KEY+token);
        //判断用户是否存在(登录的时候自动进行注册)
        if(userMap.isEmpty()){
            //调试用
            //System.out.println("userMap为空");
            return true;
        }
        //存在就将用户信息保存到ThreadLocal中
        UserDto userDto = BeanUtil.fillBeanWithMap(userMap, new UserDto(), false,
                CopyOptions.create().setIgnoreNullValue(true).setFieldValueEditor((fieldName, fieldValue)->fieldValue.toString()));
        UserHolder.saveUser(userDto);

        //调试用
        //System.out.println("拦截器中的userDto:"+userDto);
        //System.out.println("这是取出来的"+ UserHolder.getUser());

        //刷新token时间
        stringRedisTemplate.expire(LOGIN_USER_KEY+token,LOGIN_USER_TTL, TimeUnit.MINUTES);
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
