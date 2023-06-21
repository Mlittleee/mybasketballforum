package com.project.mybasketballforum.mapper;

import com.project.mybasketballforum.dto.CategoryInfoDto;
import com.project.mybasketballforum.pojo.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mhc
 * @since 2023-06-15
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    CategoryInfoDto getCategoryInfo(String categoryName);
}
