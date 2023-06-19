package com.project.mybasketballforum.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.mybasketballforum.dto.CategoryDto;
import com.project.mybasketballforum.pojo.Category;
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

    // 按板块名查询
    @PostMapping("/getCategoryListPage")
    public Result<List<Category>> getCategoryListPage(@RequestBody QueryPageParam query) {
        List<Category> categoryList = categoryServiceimpl.getCategoryListPage(query);
        if (categoryList != null) {
            return Result.success(categoryList);
        } else {
            return Result.error("没有查询到该分类");
        }
    }

}

