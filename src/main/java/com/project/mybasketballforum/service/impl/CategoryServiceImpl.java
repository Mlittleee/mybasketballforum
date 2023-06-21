package com.project.mybasketballforum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.project.mybasketballforum.dto.CategoryDto;
import com.project.mybasketballforum.dto.CategoryInfoDto;
import com.project.mybasketballforum.mapper.PostMapper;
import com.project.mybasketballforum.pojo.Category;
import com.project.mybasketballforum.mapper.CategoryMapper;
import com.project.mybasketballforum.pojo.Post;
import com.project.mybasketballforum.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    @Override
    public String getCategoryDescription(String categoryName) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getCategoryName, categoryName);
        Category category = categoryMapper.selectOne(wrapper);
        return category.getDescription();
    }

    //按板块名查询板块信息
    @Override
    public CategoryInfoDto getCategoryInfo(String categoryName) {
        return categoryMapper.getCategoryInfo(categoryName);
    }

    @Override
    public Integer getCategoryHeatOrder(String categoryName) {
        List<String> fixedCategories = Arrays.asList("NBA", "CBA", "CUBA", "野球帝");
        //实现根据发帖数来对各板块排序
        fixedCategories.sort(Comparator.comparingInt(category -> getPostCount(category)).reversed());

        //返回热度值
        for (int i = 0; i < fixedCategories.size(); i++) {
            if (fixedCategories.get(i).equals(categoryName)) {
                return i + 1;
            }
        }
        return 0;
    }

    //得到发帖数
    private int getPostCount(Object categoryName) {
        LambdaQueryWrapper<Post> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Post::getCategoryName, categoryName);
        return Math.toIntExact(postMapper.selectCount(wrapper));
    }

}
