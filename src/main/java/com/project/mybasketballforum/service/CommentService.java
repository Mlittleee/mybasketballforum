package com.project.mybasketballforum.service;

import com.project.mybasketballforum.pojo.Comment;
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
public interface CommentService extends IService<Comment> {



    /**
     * 添加评论
     * @param content   评论内容
     * @param upperId   上一级评论id（或评论的帖子）
     * @param userId    评论所属用户id
     * @return 是否添加成功
     */
    boolean addComment(String content, Integer upperId, Integer userId);

    /**
     * 删除评论
     * @param commentId 评论id
     * @return 是否删除成功
     */
    boolean deleteComment(Integer commentId);

    /**
     * 批量删除评论
     * @param commentIds 评论id列表（数组）
     * @return 是否批量删除成功
     */
    boolean deleteComments(List<Integer> commentIds);

}
