package com.project.mybasketballforum.utils;

/************************
 * ManageSystem
 * com.project.managesystem.global
 * MHC
 * author : mhc
 * date:  2023/6/11 11:44
 * description :定义 Redis 中使用的常量，包括存储登录用户信息的 key 值的前缀和登录用户信息的过期时间
 ************************/
public class RedisConstants {
    public static final String LOGIN_USER_KEY = "login:token:";
    public static final Long LOGIN_USER_TTL = 30L;
}
