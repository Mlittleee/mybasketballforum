package com.project.mybasketballforum.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.mybasketballforum.dto.PostCardListDto;
import com.project.mybasketballforum.pojo.Comment;
import com.project.mybasketballforum.pojo.Postcard;
import com.project.mybasketballforum.service.CommentService;
import com.project.mybasketballforum.universal.QueryPageParam;
import com.project.mybasketballforum.universal.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
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

    //修改评论内容
    @GetMapping("/updateComment")
    public Result<String> updateComment(@RequestParam Integer commentId, @RequestParam String content) {
        //传入评论id和修改后的内容来完成修改
        if (commentService.updateComment(commentId, content)) {
            return Result.success("评论修改成功");
        } else {
            return Result.error("评论修改失败");
        }
    }

    // 分页展示评论
    @PostMapping("/listCommentsByPage")
    public Result<List<PostCardListDto>> getPostList(@RequestBody QueryPageParam query) {

        HashMap param = query.getParam();
        Page<Comment> commentPage = new Page<>();
        commentPage.setSize(query.getPageSize());
        commentPage.setCurrent(query.getPageNum());

        //因为在首页的分页数据渲染的时候已经将post的数据转换为postcard的数据了，所以这里直接查询postcard就可以了
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        if (param != null) {
            String userName = (String) param.get("userName");
            if (StrUtil.isNotBlank(userName) && !userName.equals("null")) {
                wrapper.eq(Comment::getUserName, userName);
            }
        }
        IPage result = commentService.page(commentPage, wrapper);
        //获取总记录条数total
        long total = result.getTotal();
        //如果非空，则返回
        if (result.getRecords().size() > 0) {
            return Result.success(result.getRecords(), total);
        } else {
            return Result.error("没有查询到该板块下的帖子");
        }
    }

}

