package com.project.mybasketballforum.controller;

import com.project.mybasketballforum.pojo.Postcard;
import com.project.mybasketballforum.service.impl.PostcardServiceImpl;
import com.project.mybasketballforum.universal.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author MHC
 * @version 1.0
 * @project mybasketballforum
 * @package com.project.mybasketballforum.controller
 * @date 2023/6/18 21:21:11
 * {@code @description：}
 */
@RestController
@RequestMapping("/postcard")
@CrossOrigin
public class PostcardController {

    @Autowired
    private PostcardServiceImpl postcardServiceimpl;

    //添加浏览量
    @GetMapping("/addViewCount")
    public Result<String> addViewCount(@RequestParam Integer postId){
        if(postcardServiceimpl.addViewCount(postId)){
            return Result.success("添加成功");
        }
        return Result.error("添加失败");
    }

    //添加点赞量
    @GetMapping("/addLikeCount")
    public Result<String> addLikeCount(@RequestParam Integer postId){
        if(postcardServiceimpl.addLikeCount(postId)){
            return Result.success("添加成功");
        }
        return Result.error("添加失败");
    }

    //更新帖子
    @PostMapping("/updatePostcard")
    public Result<String> updatePostcard(@RequestBody Postcard postcard){
        if(postcardServiceimpl.updatePostcard(postcard)){
            return Result.success("更新成功");
        }
        return Result.error("更新失败");
    }

    //根据帖子id来获取帖子
    @GetMapping("/getPostcardById")
    public Result<Postcard> getPostcardById(@RequestParam Integer postId){
        Postcard postcard = postcardServiceimpl.getPostcardById(postId);
        if(postcard != null){
            return Result.success(postcard);
        }
        return Result.error("获取失败");
    }

    //根据帖子id来删除帖子
    @GetMapping("/deletePost")
    public Result<String> deletePostcardById(@RequestParam Integer id){
        if(postcardServiceimpl.deletePostcard(id)){
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }
}
