package com.project.mybasketballforum.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.project.mybasketballforum.dto.PostCardDto;
import com.project.mybasketballforum.dto.PostCardListDto;
import com.project.mybasketballforum.dto.PostViewDto;
import com.project.mybasketballforum.pojo.Post;
import com.project.mybasketballforum.pojo.Postcard;
import com.project.mybasketballforum.pojo.User;
import com.project.mybasketballforum.service.PostService;
import com.project.mybasketballforum.service.PostcardService;
import com.project.mybasketballforum.service.impl.PostServiceImpl;
import com.project.mybasketballforum.service.impl.PostcardServiceImpl;
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

    @Autowired
    private PostService ipostService;

    @Autowired
    private PostcardServiceImpl postcardServiceimpl;

    @Autowired
    private PostcardService ipostcardService;

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

    //返回帖子列表，可以根据标题进行，因为自己封装的dto类无法使用QueryWrapper,所以将额外数据的封装交给后端去做
    //分页查询
    @PostMapping("/findPostCardPage")
    public Result<List<PostCardListDto>> getPostList(@RequestBody QueryPageParam query) {

        HashMap param = query.getParam();
        Page<Postcard> postcardPage = new Page<>();
        postcardPage.setSize(query.getPageSize());
        postcardPage.setCurrent(query.getPageNum());

        LambdaQueryWrapper<Post> wrapper1 = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Postcard> wrapper2 = new LambdaQueryWrapper<>();
        if (param != null) {
            String title = (String) param.get("title");
            if (StrUtil.isNotBlank(title) && !title.equals("null")) {
                wrapper1.like(Post::getTitle, title);
                //将wrapper1转换为List
                List<Post> postList = ipostService.list(wrapper1);
                postcardServiceimpl.postToPostcard(postList);
                wrapper2.like(Postcard::getTitle, title);
            }
        }
        IPage result = ipostcardService.page(postcardPage, wrapper2);
        //获取总记录条数total
        long total = result.getTotal();
        //如果非空，则返回
        if (result.getRecords().size() > 0) {
            return Result.success(result.getRecords(), total);
        } else {
            return Result.error("没有查询到帖子");
        }
    }

    //根据帖子id返回帖子详情
    @GetMapping("/getPostDetail")
    public Result<PostViewDto> getPostById(@RequestParam Integer postId) {
        PostViewDto postcard = postServiceimpl.getPostViewById(postId);
        if (postcard != null) {
            return Result.success(postcard);
        } else {
            return Result.error("没有查询到该帖子");
        }
    }
}
