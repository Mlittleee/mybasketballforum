package com.project.mybasketballforum.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.project.mybasketballforum.dto.PostCardDto;
import com.project.mybasketballforum.dto.PostCardListDto;
import com.project.mybasketballforum.pojo.Post;
import com.project.mybasketballforum.pojo.User;
import com.project.mybasketballforum.service.impl.PostServiceImpl;
import com.project.mybasketballforum.universal.QueryPageParam;
import com.project.mybasketballforum.universal.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/post")
@CrossOrigin
public class PostController {

    @Autowired
    private PostServiceImpl postServiceimpl;

    //发布（新增）帖子
    @PostMapping("/addPost")
    public Result<String> addPost(@RequestBody Post post) {
        if (postServiceimpl.addPost(post)) {
            return Result.success("发布帖子成功");
        } else {
            return Result.error("发布帖子失败");
        }
    }

    //根据帖子标题查询帖子
    @GetMapping("/selectPostByTitle")
    public Result<Post> selectPostByTitle(@RequestParam String title) {
        Post post = postServiceimpl.selectPostByTitle(title);
        if (post != null) {
            return Result.success(post);
        } else {
            return Result.error("没有查询到该帖子");
        }
    }

    //查找最后一条帖子的id
    @GetMapping("/selectLastPostId")
    public Result<Integer> selectLastPostId() {
        Integer postId = postServiceimpl.selectLastPostId();
        if (postId != null) {
            return Result.success(postId);
        } else {
            return Result.error("没有查询到最后一条帖子的id");
        }
    }

    //返回一条帖子的信息（测试用）
    @GetMapping("/getOnePost")
    public Result<PostCardDto> getOnePost() {
        PostCardDto postcard = postServiceimpl.getOnePost();
        if (postcard != null) {
            return Result.success(postcard);
        } else {
            return Result.error("没有查询到该帖子");
        }
    }

    //返回帖子列表，不进行标题的查询，查询后面在进行时实现
    /*@GetMapping("/getPostListPage")
    public Result<List<PostCardListDto>> getPostList(@RequestBody QueryPageParam query) {
        //使用pageHelper进行分页
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<PostCardListDto> postcardlist = postServiceimpl.getPostList();

    }*/

}
