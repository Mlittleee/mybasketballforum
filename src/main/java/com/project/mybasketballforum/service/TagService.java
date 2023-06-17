package com.project.mybasketballforum.service;

import com.project.mybasketballforum.pojo.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mhc
 * @since 2023-06-15
 */
public interface TagService extends IService<Tag> {

    //获取所有标签
    List<Tag> getAllTags();

    //新建标签
    boolean addTag(Tag tag);

    //删除标签
    boolean deleteTag(Integer tagId);

    //修改标签
    boolean updateTag(Tag tag);

    //根据标签名获取标签
    //直接在controller层中实现

    //批量新建标签
    boolean addTags(List<Tag> tagList) throws Exception;

}
