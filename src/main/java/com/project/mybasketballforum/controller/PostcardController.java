package com.project.mybasketballforum.controller;

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
}
