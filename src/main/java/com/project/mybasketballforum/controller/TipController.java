package com.project.mybasketballforum.controller;

import com.project.mybasketballforum.pojo.Tip;
import com.project.mybasketballforum.service.TipService;
import com.project.mybasketballforum.service.impl.TipServiceImpl;
import com.project.mybasketballforum.universal.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/************************
 * mybasketballforum
 * com.project.mybasketballforum.controller
 * MHC
 * author : mhc
 * date:  2023/6/17 10:54
 * description : 每日一句的控制类
 ************************/
@RestController
@RequestMapping("/tip")
@CrossOrigin
public class TipController {

    @Autowired
    private TipServiceImpl tipServiceimpl;

    @GetMapping("/tip")
    public Result<Tip> getTip(){
        Tip tip = tipServiceimpl.getTip();
        if (tip == null){
            return Result.error("获取每日一句失败");
        }else{
            return Result.success(tip);
        }
    }
}
