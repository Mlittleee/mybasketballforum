package com.project.mybasketballforum.mapper;

import com.project.mybasketballforum.pojo.Thumb;
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
public interface ThumbMapper extends BaseMapper<Thumb> {

    //获取userNumber为指定值的实体类
    Thumb getLikeByUserNumber(Integer userId);

}
