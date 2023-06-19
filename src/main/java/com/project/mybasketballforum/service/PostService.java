package com.project.mybasketballforum.service;

import com.project.mybasketballforum.dto.PostCardDto;
import com.project.mybasketballforum.dto.PostCardListDto;
import com.project.mybasketballforum.dto.PostViewDto;
import com.project.mybasketballforum.pojo.Post;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    //查找最后一条帖子的id
    Integer selectLastPostId();

    //返回一条帖子的信息（测试用）
    PostCardDto getOnePost();

    //返回帖子列表
    List<PostCardListDto> getPostList();

    //根据帖子id返回帖子信息
    PostViewDto getPostViewById(Integer postId);

    //根据帖子id增加帖子浏览量
    boolean updateViewCount(Integer postId);

}
