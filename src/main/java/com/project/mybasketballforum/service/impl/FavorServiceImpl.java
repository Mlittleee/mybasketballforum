package com.project.mybasketballforum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.project.mybasketballforum.mapper.CategoryMapper;
import com.project.mybasketballforum.pojo.Favor;
import com.project.mybasketballforum.mapper.FavorMapper;
import com.project.mybasketballforum.pojo.Post;
import com.project.mybasketballforum.service.FavorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mhc
 * @since 2023-06-15
 */
@Service
public class FavorServiceImpl extends ServiceImpl<FavorMapper, Favor> implements FavorService {

    @Autowired
    private FavorMapper favorMapper;

    //实现增加收藏功能
    @Override
    public boolean addFavor(Integer userId, Integer postId) {
        LambdaQueryWrapper<Favor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Favor::getUserId,userId).eq(Favor::getPostId,postId);
        if(queryWrapper == null) {
            Favor favor = new Favor();
            favor.setUserId(userId);
            favor.setPostId(postId);
            return (this.save(favor));
        }else{
            return false;
        }
    }
    //实现取消收藏功能
    @Override
    public boolean delFavor(Integer userId, Integer postId) {
        LambdaQueryWrapper<Favor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Favor::getUserId,userId).eq(Favor::getPostId,postId);
        return removeById(this.baseMapper.selectOne(queryWrapper));
    }
}
