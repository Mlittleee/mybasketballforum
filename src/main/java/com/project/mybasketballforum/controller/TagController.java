package com.project.mybasketballforum.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.mybasketballforum.pojo.User;
import com.project.mybasketballforum.service.TagService;
import com.project.mybasketballforum.service.impl.TagServiceImpl;
import com.project.mybasketballforum.universal.QueryPageParam;
import com.project.mybasketballforum.universal.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.mybasketballforum.pojo.Tag;

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
@RequestMapping("/tag")
@CrossOrigin
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private TagServiceImpl tagServiceImpl;

    //获取所有标签
    @GetMapping("/getAllTags")
    public Result<List<Tag>> getAllTags() {
        return Result.success(tagServiceImpl.getAllTags());
    }

    //标签查询(模糊查询)
    @PostMapping("/query")
    public Result<List<Tag>> queryUser(@RequestBody String tagName){
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Tag::getName,tagName);
        List<Tag> TagList= tagService.list(wrapper);
        if (TagList.size()>0){
            return Result.success(TagList);
        }else {
            return Result.error("没有查询到标签");
        }
    }

    //分页获取所有标签
    @PostMapping("/findPage")
    public Result<List<Tag>> findPage(@RequestBody QueryPageParam query) {

        HashMap param = query.getParam();
        Page<Tag> useInfoPage = new Page<>();
        useInfoPage.setSize(query.getPageSize());
        useInfoPage.setCurrent(query.getPageNum());

        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        if (param != null) {
            String tagName = (String) param.get("name");
            if (StrUtil.isNotBlank(tagName) && !tagName.equals("null")) {
                wrapper.like(Tag::getName, tagName);
            }
        }
        IPage result = tagService.page(useInfoPage, wrapper);
        //获取总记录条数total
        long total = result.getTotal();
        //如果非空，则返回
        if (result.getRecords().size() > 0) {
            return Result.success(result.getRecords(), total);
        } else {
            return Result.error("没有查询到用户");
        }
    }

    //新建标签
    @PostMapping("/addTag")
    public Result<String> addTag(@RequestBody Tag tag) {
        if (tagServiceImpl.addTag(tag)) {
            return Result.success("新建标签成功");
        } else {
            return Result.error("新建标签失败");
        }
    }

    //删除标签
    @PostMapping("/deleteTag")
    public Result<String> deleteTag(@RequestBody Tag tag) {
        if (tagServiceImpl.deleteTag(tag.getTagId())) {
            return Result.success("删除标签成功");
        } else {
            return Result.error("删除标签失败");
        }
    }

    //修改标签
    @PostMapping("/updateTag")
    public Result<String> updateTag(@RequestBody Tag tag) {
        if (tagServiceImpl.updateTag(tag)) {
            return Result.success("修改标签成功");
        } else {
            return Result.error("修改标签失败");
        }
    }

    //批量新建标签
    @PostMapping("/addTags")
    public Result<String> addTags(String tags) throws Exception {
        if (tagServiceImpl.addTags(tags)) {
            return Result.success("批量新建标签成功");
        } else {
            return Result.error("批量新建标签失败");
        }
    }

    //批量删除标签
    @PostMapping("/deleteTags")
    public Result<String> deleteTags(String ids) {
        //将ids转换为数组
        String[] idArray = ids.split(",");
        //遍历数组
        for (String id : idArray) {
            //将id转换为Integer类型
            Integer tagId = Integer.parseInt(id);
            //调用删除方法
            tagServiceImpl.deleteTag(tagId);
        }
        return Result.success("批量删除标签成功");
    }
}

