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

    //在个人中心处的个人简介的个人信息修改
    Boolean updateUserInfo(Integer userId, String userName, String sign, Integer gender, String email);

    //根据id查询用户名称
    String selectUserById(Integer id);

    //根据userId查询发帖量
    Integer getPostCount(Integer userId);

    //根据userId查询获赞量
    Integer getLikeCount(Integer userId);

}
