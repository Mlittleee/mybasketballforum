package com.project.mybasketballforum.service.impl;

import com.project.mybasketballforum.pojo.Comment;
import com.project.mybasketballforum.mapper.CommentMapper;
import com.project.mybasketballforum.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mhc
 * @since 2023-06-15
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public boolean addComment(String content, Integer upperId, Integer userId) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUpperId(upperId);
        comment.setUserId(userId);
        return(this.save(comment));
    }
}
