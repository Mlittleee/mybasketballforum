package com.project.mybasketballforum.service;

import com.project.mybasketballforum.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;

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


}
