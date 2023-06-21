package com.project.mybasketballforum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.project.mybasketballforum.mapper.PostMapper;
import com.project.mybasketballforum.pojo.Like;
import com.project.mybasketballforum.mapper.LikeMapper;
import com.project.mybasketballforum.pojo.Post;
import com.project.mybasketballforum.service.LikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like> implements LikeService {

    @Autowired
    private LikeMapper likeMapper;

    @Autowired
    private PostMapper postMapper;

    //添加点赞功能
    public Boolean addLike(Integer userId, Integer postId){
        Like like = new Like();
        LambdaQueryWrapper<Like> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Like::getPostId,postId).eq(Like::getUserId,userId);
        List<Like> likeList = likeMapper.selectList(queryWrapper);
        if (likeList == null) {
            like.setUserId(userId);
            like.setPostId(postId);
            return this.save(like);
        }
        return true;
    }

   //返回所有用户点赞过的帖子
    public List<Post> listLiked(Integer userId){
        LambdaQueryWrapper<Like> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Like::getUserId,userId);
        List<Like> likeList = likeMapper.selectList(queryWrapper);
        List<Post> postList = new ArrayList<>();
        if(likeList == null)
            return null;
        for (Like like: likeList) {
            postList.add(postMapper.selectById(like.getPostId()));
        }
        return postList;
    }
}
