package com.project.mybasketballforum.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.mybasketballforum.dto.CategoryDto;
import com.project.mybasketballforum.dto.CategoryInfoDto;
import com.project.mybasketballforum.dto.PostCardListDto;
import com.project.mybasketballforum.mapper.PostcardMapper;
import com.project.mybasketballforum.pojo.Category;
import com.project.mybasketballforum.pojo.Postcard;
import com.project.mybasketballforum.service.PostcardService;
import com.project.mybasketballforum.service.impl.CategoryServiceImpl;
import com.project.mybasketballforum.universal.QueryPageParam;
import com.project.mybasketballforum.universal.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    private PostcardService postcardService;

    @Resource
    private PostcardMapper postcardMapper;

    @Autowired
    private CategoryServiceImpl categoryServiceimpl;

    // 管理员新增板块
    @PostMapping("/addCategory")
    public Result<String> addCategory(@RequestBody Category category) {
       if (categoryServiceimpl.addCategory(category)) {
           return Result.success("新增板块成功");
       } else {
           return Result.error("新增板块失败");
       }
    }

    // 管理员删除板块
    @GetMapping("/deleteCategory")
    public Result<String> deleteCategory(@RequestParam Integer categoryId) {
        if (categoryServiceimpl.deleteCategory(categoryId)) {
            return Result.success("删除板块成功");
        } else {
            return Result.error("删除板块失败");
        }
    }

    // 管理员修改板块
    @PostMapping("/updateCategory")
    public Result<String> updateCategory(@RequestBody Category category) {
        if (categoryServiceimpl.updateCategory(category)) {
            return Result.success("修改板块成功");
        } else {
            return Result.error("修改板块失败");
        }
    }

    // 查询所有分类
    @GetMapping("/selectAllCategory")
    public Result<List<CategoryDto>> selectAllCategory() {
        if(categoryServiceimpl.selectAllCategory() != null) {
            return Result.success(categoryServiceimpl.selectAllCategory());
        } else {
            return Result.error("查询所有分类失败");
        }
    }

    // 分页查询帖子（根据板块进行查询）
    @PostMapping("/findPostByCategory")
    public Result<List<PostCardListDto>> getPostList(@RequestBody QueryPageParam query) {

        HashMap param = query.getParam();
        Page<Postcard> postcardPage = new Page<>();
        postcardPage.setSize(query.getPageSize());
        postcardPage.setCurrent(query.getPageNum());

        //因为在首页的分页数据渲染的时候已经将post的数据转换为postcard的数据了，所以这里直接查询postcard就可以了
        LambdaQueryWrapper<Postcard> wrapper = new LambdaQueryWrapper<>();
        if (param != null) {
            String categoryName = (String) param.get("categoryName");
            if (StrUtil.isNotBlank(categoryName) && !categoryName.equals("null")) {
                wrapper.eq(Postcard::getCategoryName, categoryName);
            }
        }
        IPage result = postcardService.page(postcardPage, wrapper);
        //获取总记录条数total
        long total = result.getTotal();
        //如果非空，则返回
        if (result.getRecords().size() > 0) {
            return Result.success(result.getRecords(), total);
        } else {
            return Result.error("没有查询到该板块下的帖子");
        }
    }

    //按板块名查询简介
    @GetMapping("/getCategoryInfo")
    public Result<CategoryInfoDto> getCategoryInfo(@RequestParam String categoryName) {
        if (categoryServiceimpl.getCategoryInfo(categoryName) != null) {
            return Result.success(categoryServiceimpl.getCategoryInfo(categoryName));
        } else {
            return Result.error("查询板块信息失败");
        }
    }


    //按照板块名称查询简介
    @GetMapping("/getCategoryDescription")
    public Result<String> getCategoryDescription(@RequestParam String categoryName) {
        if (categoryServiceimpl.getCategoryDescription(categoryName) != null) {
            return Result.success(categoryServiceimpl.getCategoryDescription(categoryName));
        } else {
            return Result.error("查询板块简介失败");
        }
    }

    //按照板块名称查询热度(返回[2,5]的热度值）
    @GetMapping("/getCategoryHeatOrder")
    public Result<Integer> getCategoryHeatOrder(@RequestParam String categoryName) {
        if (categoryServiceimpl.getCategoryHeatOrder(categoryName) != null) {
            return Result.success(categoryServiceimpl.getCategoryHeatOrder(categoryName));
        } else {
            return Result.error("查询板块简介失败");
        }
    }

    //更改板块描述信息
    @GetMapping("/updateCategoryDescription")
    public Result<String> updateCategoryDescription(@RequestParam String categoryName, @RequestParam String description) {
        if (categoryServiceimpl.updateCategoryDescription(categoryName, description)) {
            return Result.success("修改板块简介成功");
        } else {
            return Result.error("修改板块简介失败");
        }
    }

    //根据板块名称返回帖子百分比和用户百分比
    @GetMapping("/getCategoryPercentage")
    public Result<List<Integer>> getCategoryPercentage(@RequestParam String categoryName) {
        CategoryInfoDto categoryInfoDto = categoryServiceimpl.getCategoryInfo(categoryName);
        Long postcardNum = categoryInfoDto.getPostCount();
        Long allPost = postcardMapper.selectCount(null);
        Integer postPercentage = (int) (postcardNum / allPost * 100);
        //获取板块总用户数
        Long userNum = categoryInfoDto.getUserCount();
        Long allUser = postcardMapper.selectCount(null);
        Integer userPercentage = (int) (userNum / allUser * 100);
        List<Integer> list = new ArrayList<>();
        list.add(postPercentage);
        list.add(userPercentage);
        return Result.success(list);
    }
}

