package com.project.mybasketballforum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.project.mybasketballforum.mapper.PostMapper;
import com.project.mybasketballforum.mapper.PostcardMapper;
import com.project.mybasketballforum.pojo.Postcard;
import com.project.mybasketballforum.pojo.Thumb;
import com.project.mybasketballforum.mapper.ThumbMapper;
import com.project.mybasketballforum.pojo.Post;
import com.project.mybasketballforum.service.ThumbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class ThumbServiceImpl extends ServiceImpl<ThumbMapper, Thumb> implements ThumbService {

    @Resource
    private ThumbMapper thumbMapper;

    @Autowired
    private PostcardMapper postcardMapper;

    //添加点赞功能
    public Boolean addLike(Integer userId, Integer postId){
        LambdaQueryWrapper<Thumb> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Thumb::getUserId,userId);
        queryWrapper.eq(Thumb::getPostId,postId);
        Thumb thumb = thumbMapper.selectOne(queryWrapper);
        if(thumb == null){
            Thumb newThumb = new Thumb();
            newThumb.setUserId(userId);
            newThumb.setPostId(postId);
            return(this.save(newThumb));
        }
        return true;
    }

   //返回所有用户点赞过的帖子
    public List<Postcard> listLiked(Integer userId){
        LambdaQueryWrapper<Thumb> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Thumb::getUserId,userId);
        List<Thumb> thumbList = thumbMapper.selectList(queryWrapper);
        List<Postcard> postList = new ArrayList<>();
        if(thumbList == null)
            return null;
        for (Thumb thumb : thumbList) {
            postList.add(postcardMapper.selectById(thumb.getPostId()));
        }
        return postList;
    }
}
