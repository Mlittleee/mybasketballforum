package com.project.mybasketballforum.service.impl;

import com.project.mybasketballforum.dto.CategoryDto;
import com.project.mybasketballforum.pojo.Category;
import com.project.mybasketballforum.mapper.CategoryMapper;
import com.project.mybasketballforum.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

}
