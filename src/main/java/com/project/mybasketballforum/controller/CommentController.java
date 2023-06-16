package com.project.mybasketballforum.controller;


import com.project.mybasketballforum.pojo.Comment;
import com.project.mybasketballforum.service.CommentService;
import com.project.mybasketballforum.universal.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/addComment")
    public Result<String> addComment(String content, Integer upperId, Integer userId) {
        if (commentService.addComment(content, upperId, userId)) {
            return Result.success("评论成功");
        } else {
            return Result.error("评论失败");
        }
    }
}

