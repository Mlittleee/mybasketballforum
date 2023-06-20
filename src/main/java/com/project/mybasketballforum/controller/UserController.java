package com.project.mybasketballforum.controller;

import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.mybasketballforum.dto.UserDto;
import com.project.mybasketballforum.mapper.UserMapper;
import com.project.mybasketballforum.pojo.User;
import com.project.mybasketballforum.service.UserService;
import com.project.mybasketballforum.service.impl.UserServiceImpl;
import com.project.mybasketballforum.universal.QueryPageParam;
import com.project.mybasketballforum.universal.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/************************
 * Manage-System
 * com.project.mybasketballforum.controller
 * MHC
 * author : mhc
 * date:  2023/6/11 15:23
 * description : 
 ************************/
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService iuserService;

    @Autowired
    private UserServiceImpl userServiceimpl;



    //用户登录(自动注册)
    @PostMapping("/login")
    public Result<UserDto> login(@RequestBody Map<String, String> map, HttpSession session, ServletRequest servletRequest){
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        UserDto userDto = userServiceimpl.login(map, session);
        if (userDto!=null){
            return Result.success(userDto);
        }else{
            return Result.error("登录失败");
        }
    }

    //用户新增
    @PostMapping("/save")
    public Result<User> addUser(@RequestBody User user){
        Boolean isSave = iuserService.save(user);
        if (isSave){
            return Result.success(user);
        }else {
            return Result.error("新增用户失败");
        }
    }

    //用户修改
   @PostMapping("/update")
    public Result<User> updateUser(@RequestBody User user){
        boolean isUpdate = iuserService.updateById(user);
        if (isUpdate){
            return Result.success(user);
        }else {
            return Result.error("修改用户失败");
        }
    }

    //用户新增或者修改
    @PostMapping("/addOrUpdate")
    public Result<User> addUserOrUpdateUser(@RequestBody User user){
        boolean isAddOrUpdate = iuserService.saveOrUpdate(user);
        if (isAddOrUpdate){
            return Result.success(user);
        }else {
            return Result.error("新增或者修改用户失败");
        }
    }

    //用户删除
    @GetMapping("/delete")
    public Result<String> deleteUser(Integer id){
        boolean isDelete = iuserService.removeById(id);
        System.out.println(isDelete);
        if (isDelete){
            //返回json数据
            return Result.success("删除用户成功");
        }else {
            return Result.error("删除用户失败");
        }
    }

    //用户查询(模糊查询)
    @GetMapping("/query")
    public Result<List<User>> queryUser(@RequestParam String UserName){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(User::getUserName,UserName);
        List<User> UserList= iuserService.list(wrapper);
        if (UserList.size()>0){
            return Result.success(UserList);
        }else {
            return Result.error("没有查询到用户");
        }
    }

    //列出所有用户
    @GetMapping("/listAll")
    public Result<List<User>> listAll(){
        List<User> UserList = iuserService.list();
        if (UserList.size()>0){
            return Result.success(UserList);
        }else {
            return Result.error("没有查询到用户");
        }
    }

    //分页查询
    @PostMapping("/findPage")
    public Result<List<User>> findPage(@RequestBody QueryPageParam query) {

        HashMap param = query.getParam();
        Page<User> useInfoPage = new Page<>();
        useInfoPage.setSize(query.getPageSize());
        useInfoPage.setCurrent(query.getPageNum());

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if (param != null) {
            String userName = (String) param.get("userName");
            if (StrUtil.isNotBlank(userName) && !userName.equals("null")) {
                wrapper.like(User::getUserName, userName);
            }
        }
        IPage result = iuserService.page(useInfoPage, wrapper);
        //获取总记录条数total
        long total = result.getTotal();
        //如果非空，则返回
        if (result.getRecords().size() > 0) {
            return Result.success(result.getRecords(), total);
        } else {
            return Result.error("没有查询到用户");
        }
    }

    //根据id查询用户名称
    @GetMapping("/getUserNameById")
    public Result<String> getUserNameById(Integer id){
        User user = iuserService.getById(id);
        if (user!=null){
            return Result.success(user.getUserName());
        }else {
            return Result.error("没有查询到用户");
        }
    }

    //根据id查询用户
    @GetMapping("/getUserById")
    public Result<User> getUserById(@RequestParam Integer id){
        User user = iuserService.getById(id);
        if (user!=null){
            return Result.success(user);
        }else {
            return Result.error("没有查询到用户");
        }
    }

    //查询用户发帖量
    @GetMapping("/getPostCount")
    public Result<Integer> getPostCount(Integer id){
        Integer postCount = userServiceimpl.getPostCount(id);
        if (postCount!=null){
            return Result.success(postCount);
        }else {
            return Result.error("没有查询到发帖量");
        }
    }

    //查询用户获赞量
    @GetMapping("/getLikeCount")
    public Result<Integer> getLikeCount(Integer id){
        Integer likeCount = userServiceimpl.getLikeCount(id);
        if (likeCount!=null){
            return Result.success(likeCount);
        }else {
            return Result.error("没有查询到获赞量");
        }
    }

}