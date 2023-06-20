package com.project.mybasketballforum.controller;


import com.project.mybasketballforum.service.CommentService;
import com.project.mybasketballforum.service.FavorService;
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
@RequestMapping("/favor")
@CrossOrigin
public class FavorController {

    @Autowired
    private FavorService favorService;

    @GetMapping("/addFavor")
    public Result<String> addFavor(Integer userId,Integer postId) {
        //传入收藏帖子id和用户id 完成收藏功能
        if (favorService.addFavor(userId, postId)) {
            return Result.success("收藏成功");
        } else {
            return Result.error("已有重复收藏，收藏失败");
        }
    }

    @GetMapping("/delFavor")
    public Result<String> delFavor(Integer userId,Integer postId) {
        //传入收藏帖子id和用户id 完成取消收藏功能
        if (favorService.delFavor(userId, postId)) {
            return Result.success("取消收藏成功");
        } else {
            return Result.error("取消收藏失败");
        }
    }

}

