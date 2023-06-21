package com.project.mybasketballforum.controller;


import com.project.mybasketballforum.service.impl.LikeServiceImpl;
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
@RequestMapping("/like")
@CrossOrigin
public class LikeController {

    @Autowired
    private LikeServiceImpl likeServiceimpl;

    //添加点赞功能
    @GetMapping("/addLike")
    public Result<String> addLike(@RequestParam Integer userId, @RequestParam Integer postId){
        if(likeServiceimpl.addLike(userId, postId)){
            return Result.success("点赞成功");
        }else {
            return Result.error("点赞失败");
        }
    }

    //返回所有用户点赞过的帖子
    @GetMapping("/listLiked")
    public Result<String> listLiked(@RequestParam Integer userId){
        if(likeServiceimpl.listLiked(userId) == null){
            return Result.error("该用户没有点赞过任何帖子");
        }else {
            return Result.success("查询成功");
        }
    }
}

