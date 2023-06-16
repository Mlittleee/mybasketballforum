package com.project.mybasketballforum.service.impl;

import com.project.mybasketballforum.pojo.Tag;
import com.project.mybasketballforum.mapper.TagMapper;
import com.project.mybasketballforum.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;

    //获取所有标签
    @Override
    public List<Tag> getAllTags() {
        return tagMapper.listAll();
    }

    //新建标签
    @Override
    public boolean addTag(Tag tag) {
        return tagMapper.insert(tag) > 0;
    }

    //删除标签
    @Override
    public boolean deleteTag(Integer tagId) {
        return tagMapper.deleteById(tagId) > 0;
    }

    //修改标签
    @Override
    public boolean updateTag(Tag tag) {
        return tagMapper.updateById(tag) > 0;
    }


}
