package com.project.mybasketballforum.controller;


import com.project.mybasketballforum.pojo.Category;
import com.project.mybasketballforum.service.CategoryService;
import com.project.mybasketballforum.universal.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private CategoryService categoryService;

    // 管理员新增板块
    @PostMapping("/addCategory")
    public Result<String> addCategory(@RequestBody Category category) {
       if (categoryService.addCategory(category)) {
           return Result.success("新增板块成功");
       } else {
           return Result.error("新增板块失败");
       }
    }

    // 管理员删除板块
    @GetMapping("/deleteCategory")
    public Result<String> deleteCategory(@RequestParam Integer categoryId) {
        if (categoryService.deleteCategory(categoryId)) {
            return Result.success("删除板块成功");
        } else {
            return Result.error("删除板块失败");
        }
    }

    // 管理员修改板块
    @PostMapping("/updateCategory")
    public Result<String> updateCategory(@RequestBody Category category) {
        if (categoryService.updateCategory(category)) {
            return Result.success("修改板块成功");
        } else {
            return Result.error("修改板块失败");
        }
    }

    // 查询所有分类
    @GetMapping("/selectAllCategory")
    public Result<List<Category>> selectAllCategory() {
        return Result.success(categoryService.selectAllCategory());
    }

}

