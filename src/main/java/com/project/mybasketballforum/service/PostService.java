package com.project.mybasketballforum.service;

import com.project.mybasketballforum.pojo.Post;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mhc
 * @since 2023-06-15
 */
public interface PostService extends IService<Post> {

    //发布（新增）帖子
    boolean addPost(Post post);

    //根据帖子标题查询帖子
    Post selectPostByTitle(String title);

}
