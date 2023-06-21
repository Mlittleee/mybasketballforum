package com.project.mybasketballforum.service;

import com.project.mybasketballforum.dto.CategoryDto;
import com.project.mybasketballforum.dto.CategoryInfoDto;
import com.project.mybasketballforum.dto.PostCardListDto;
import com.project.mybasketballforum.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.mybasketballforum.universal.QueryPageParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mhc
 * @since 2023-06-15
 */
public interface CategoryService extends IService<Category> {

    // 管理员新增板块
    boolean addCategory(Category category);

    //管理员删除板块
    boolean deleteCategory(Integer categoryId);

    //管理员修改板块
    boolean updateCategory(Category category);

    //查询所有分类
    List<CategoryDto> selectAllCategory();

    //按板块名查询板块内容
    CategoryInfoDto getCategoryInfo(String categoryName);

    //按板块名查询板块内容
    String getCategoryDescription(String categoryName);

    //查询板块热度排序
    Integer getCategoryHeatOrder(String categoryName);

    //更改板块描述信息
    boolean updateCategoryDescription(String categoryName,String description);
}
