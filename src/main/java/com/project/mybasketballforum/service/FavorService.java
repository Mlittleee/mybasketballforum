package com.project.mybasketballforum.service;

import com.project.mybasketballforum.pojo.Favor;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mhc
 * @since 2023-06-15
 */
public interface FavorService extends IService<Favor> {

    //增加收藏帖子功能
    boolean addFavor(Integer userId,Integer postId);
}
