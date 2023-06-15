package com.project.mybasketballforum.universal;

import com.project.mybasketballforum.dto.UserDto;

/************************
 * ManageSystem
 * com.project.mybasketballforum.universal
 * MHC
 * author : mhc
 * date:  2023/6/11 12:02
 * description : 线程安全的用户信息保存类
 ************************/
public class UserHolder {
    private static final ThreadLocal<UserDto> tl = new ThreadLocal<>();

    // 保存用户信息
    public static void saveUser(UserDto user){
        tl.set(user);
    }
    // 获取用户信息
    public static UserDto getUser(){
        return tl.get();
    }
    // 删除用户信息
    public static void removeUser(){ tl.remove();
    }
}
