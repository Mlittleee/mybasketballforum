package com.project.mybasketballforum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.mybasketballforum.pojo.User;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
* @author MHC
* @description 针对表【user】的数据库操作Service
* @createDate 2023-06-11 14:47:39
*/
public interface UserService extends IService<User> {
    //用户登录
    User login(@RequestBody Map<String,String> map, HttpSession session);

    List<User> listAll();

    //根据id查询用户名称
    String selectUserById(Integer id);
}
