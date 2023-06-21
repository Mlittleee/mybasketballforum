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

import java.util.*;

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
    @Override
    public String getCategoryDescription(String categoryName) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getCategoryName, categoryName);
        Category category = categoryMapper.selectOne(wrapper);
        return category.getDescription();
    }

    @Override
    public Integer getCategoryHeatOrder(String categoryName) {
        List<String> fixedCategories = Arrays.asList("NBA", "CBA", "CUBA", "野球帝");
        //实现根据发帖数来对各板块排序
        fixedCategories.sort(Comparator.comparingInt(category -> getPostCount(category)));
        System.out.println(fixedCategories);
        //返回热度值
        for (int i = 0; i < fixedCategories.size(); i++) {
            if (fixedCategories.get(i).equals(categoryName)) {
                return i + 2;
            }
        }
        return 0;
    }

    //得到发帖数（HeatOrder调用的方法）
    private int getPostCount(Object categoryName) {
        LambdaQueryWrapper<Post> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Post::getCategoryName, categoryName);
        return Math.toIntExact(postMapper.selectCount(wrapper));
    }

    //更改板块描述信息
    @Override
    public boolean updateCategoryDescription(String categoryName, String description) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getCategoryName, categoryName);
        Category category = categoryMapper.selectOne(wrapper);
        category.setDescription(description);
        return categoryMapper.updateById(category) == 1;
    }

}
