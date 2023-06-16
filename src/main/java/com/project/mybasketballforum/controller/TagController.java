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
    public Result addTag(@RequestBody Tag tag) {
        if (tagServiceImpl.addTag(tag)) {
            return Result.success("新建标签成功");
        } else {
            return Result.error("新建标签失败");
        }
    }

    //删除标签
    @PostMapping("/deleteTag")
    public Result deleteTag(@RequestBody Tag tag) {
        if (tagServiceImpl.deleteTag(tag.getTagId())) {
            return Result.success("删除标签成功");
        } else {
            return Result.error("删除标签失败");
        }
    }

}

