package com.project.mybasketballforum.controller;


import com.project.mybasketballforum.pojo.Comment;
import com.project.mybasketballforum.service.CommentService;
import com.project.mybasketballforum.universal.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mhc
 * @since 2023-06-15
 */
@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {

    @Resource
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/addComment")
    public Result<String> addComment(@RequestParam String content,@RequestParam Integer postId,@RequestParam String userName) {
        //传入评论内容 附属id和用户id完成评论
        if (commentService.addComment(content, postId, userName)) {
            return Result.success("评论成功");
        } else {
            return Result.error("评论失败");
        }
    }

    @GetMapping("/delComment")
    public Result<String> deleteComment(@RequestParam Integer commentId) {
        //传入删除评论的id来完成删除
        if (commentService.deleteComment(commentId)) {
            return Result.success("评论删除成功");
        } else {
            return Result.error("评论删除失败");
        }
    }

    @GetMapping("/deleteComments")
    public Result<String> deleteComments(@RequestParam String commentIds) {
        //传入批量删除的评论的id数组来完成删除
        if (commentService.deleteComments(commentIds)) {
            return Result.success("评论批量删除成功");
        } else {
            return Result.error("评论批量删除失败");
        }
    }

    @GetMapping("/listAllComments")
    public Result<List<Comment>> listAllComments(@RequestParam Integer postId) {
        //传入帖子id来列出所有评论
        List<Comment> comments = commentService.listAllComments(postId);
        if (comments != null) {
            return Result.success(comments);
        } else {
            return Result.error("还没有评论哦");
        }
    }

}

