package com.project.mybasketballforum.service;

import com.project.mybasketballforum.pojo.Like;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.mybasketballforum.pojo.Post;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mhc
 * @since 2023-06-15
 */
public interface LikeService extends IService<Like> {

    //添加点赞功能
    Boolean addLike(Integer userId, Integer postId);

    //返回所有用户点赞过的帖子
    List<Post> listLiked(Integer userId);

}
