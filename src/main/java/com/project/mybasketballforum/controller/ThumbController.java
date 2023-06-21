package com.project.mybasketballforum.controller;


import com.project.mybasketballforum.pojo.Post;
import com.project.mybasketballforum.pojo.Postcard;
import com.project.mybasketballforum.service.impl.ThumbServiceImpl;
import com.project.mybasketballforum.universal.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/like")
@CrossOrigin
public class ThumbController {

    @Autowired
    private ThumbServiceImpl thumbServiceimpl;

    //添加点赞功能
    @GetMapping("/addLike")
    public Result<String> addLike(@RequestParam Integer userId, @RequestParam Integer postId){
        if(thumbServiceimpl.addLike(userId, postId)){
            return Result.success("点赞成功");
        }else {
            return Result.error("点赞失败");
        }
    }

    //返回所有用户点赞过的帖子
    @GetMapping("/listLiked")
    public Result<List<Postcard>> listLiked(@RequestParam Integer userId){
        if(thumbServiceimpl.listLiked(userId) == null){
            return Result.error("该用户没有点赞过任何帖子");
        }else {
            return Result.success(thumbServiceimpl.listLiked(userId));
        }
    }
}

