package com.project.mybasketballforum.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.mybasketballforum.dto.PostCardListDto;
import com.project.mybasketballforum.mapper.TipMapper;
import com.project.mybasketballforum.pojo.Post;
import com.project.mybasketballforum.pojo.Postcard;
import com.project.mybasketballforum.pojo.Tip;
import com.project.mybasketballforum.service.TipService;
import com.project.mybasketballforum.service.impl.TipServiceImpl;
import com.project.mybasketballforum.universal.QueryPageParam;
import com.project.mybasketballforum.universal.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

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

    @Resource
    private TipService tipService;

    @GetMapping("/tip")
    public Result<Tip> getTip(){
        Tip tip = tipServiceimpl.getTip();
        if (tip == null){
            return Result.error("获取每日一句失败");
        }else{
            return Result.success(tip);
        }
    }

    @PostMapping("/findPage")
    public Result<List<Tip>> getPostList(@RequestBody QueryPageParam query) {

        HashMap param = query.getParam();
        Page<Tip> tipPage = new Page<>();
        tipPage.setSize(query.getPageSize());
        tipPage.setCurrent(query.getPageNum());

        LambdaQueryWrapper<Tip> wrapper = new LambdaQueryWrapper<>();
        if (param != null) {
            String author = (String) param.get("author");
            if (StrUtil.isNotBlank(author) && !author.equals("null")) {
                wrapper.like(Tip::getAuthor, author);
            }
        }
        IPage result = tipService.page(tipPage, wrapper);
        //获取总记录条数total
        long total = result.getTotal();
        //如果非空，则返回
        if (result.getRecords().size() > 0) {
            return Result.success(result.getRecords(), total);
        } else {
            return Result.error("没有查询到帖子");
        }
    }

    //新增每日一句
    @PostMapping("/addTip")
    public Result<String> addTip(@RequestBody Tip tip){
        if(tipServiceimpl.addTip(tip)){
            return Result.success("新增每日一句成功");
        }else {
            return Result.error("新增每日一句失败");
        }
    }

    //编辑每日一句
    @PostMapping("/editTip")
    public Result<String> editTip(@RequestBody Tip tip){
        if(tipServiceimpl.editTip(tip)){
            return Result.success("编辑每日一句成功");
        }else {
            return Result.error("编辑每日一句失败");
        }
    }

    //删除每日一句
    @GetMapping("/deleteTip")
    public Result<String> deleteTip(Integer tipId){
        if(tipServiceimpl.deleteTip(tipId)){
            return Result.success("删除每日一句成功");
        }else {
            return Result.error("删除每日一句失败");
        }
    }
}
