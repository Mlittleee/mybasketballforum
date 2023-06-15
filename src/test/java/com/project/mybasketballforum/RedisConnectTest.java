package com.project.mybasketballforum;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/************************
 * Manage-System
 * com.project.managementsystem
 * MHC
 * author : mhc
 * date:  2023/6/13 20:13
 * description : Redis连接测试
 ************************/
@SpringBootTest
class RedisDemoApplicationTests {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void contextLoads() {
        //向redis中添加数据
        redisTemplate.opsForValue().set("keys", "value值");
        //根据键值取出数据
        System.out.println(redisTemplate.opsForValue().get("keys"));
    }

}