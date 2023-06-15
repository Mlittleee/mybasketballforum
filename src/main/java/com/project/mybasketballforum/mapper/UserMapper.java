package com.project.mybasketballforum.mapper;

import com.project.mybasketballforum.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author MHC
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-06-11 14:47:39
* @Entity .pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> listAll();
}




