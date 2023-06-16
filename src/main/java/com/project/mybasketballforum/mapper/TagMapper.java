package com.project.mybasketballforum.mapper;

import com.project.mybasketballforum.pojo.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.mybasketballforum.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mhc
 * @since 2023-06-15
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    List<Tag> listAll();

}
