package com.project.mybasketballforum.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.mybasketballforum.dto.CategoryDto;
import com.project.mybasketballforum.dto.CategoryInfoDto;
import com.project.mybasketballforum.mapper.CommentMapper;
import com.project.mybasketballforum.mapper.PostMapper;
import com.project.mybasketballforum.pojo.Category;
import com.project.mybasketballforum.mapper.CategoryMapper;
import com.project.mybasketballforum.pojo.Post;
import com.project.mybasketballforum.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.mybasketballforum.universal.QueryPageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mhc
 * @since 2023-06-15
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private PostMapper postMapper;

    // 管理员新增板块
    @Override
    public boolean addCategory(Category category) {
        return this.save(category);
    }

    //管理员删除板块
    @Override
    public boolean deleteCategory(Integer categoryId) {
        return this.removeById(categoryId);
    }

    //管理员修改板块
    @Override
    public boolean updateCategory(Category category) {
        return this.updateById(category);
    }

    //查询所有分类
    @Override
    public List<CategoryDto> selectAllCategory() {
        //获取数据库中的所有分类
        List<Category> categoryList = this.baseMapper.selectList(null);
        List<CategoryDto> categoryDtoList= new ArrayList<>();
        //遍历分类列表
        for(Category category : categoryList){
            //将分类转换为分类传输对象
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setCategoryId(category.getCategoryId());
            categoryDto.setName(category.getCategoryName());
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }

    //按板块名查询板块信息
    @Override
    public CategoryInfoDto getCategoryInfo(String categoryName) {
        return categoryMapper.getCategoryInfo(categoryName);
    }

    //按板块名查询帖子描述
    //按板块名查询
    @Override
    public String getCategoryDescription(String categoryName) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getCategoryName, categoryName);
        Category category = categoryMapper.selectOne(wrapper);
        return category.getDescription();
    }

}
