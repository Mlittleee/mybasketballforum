package com.project.mybasketballforum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.mybasketballforum.dto.PostCardDto;
import com.project.mybasketballforum.pojo.Post;
import com.project.mybasketballforum.mapper.PostMapper;
import com.project.mybasketballforum.service.PostService;
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
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    @Autowired
    private UserServiceImpl userServiceimpl;

    //发布（新增）帖子
    @Override
    public boolean addPost(Post post) {
        //先查询数据库中是否有相同名字的帖子
        String Title = post.getTitle();
        LambdaQueryWrapper<Post> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Post::getTitle,Title);
        Post post1 = this.baseMapper.selectOne(queryWrapper);
        if(post1 != null){
            return false;
        }else{
            return this.save(post);
        }
    }

    //根据帖子标题查询帖子
    @Override
    public Post selectPostByTitle(String title) {
        LambdaQueryWrapper<Post> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Post::getTitle,title);
        return this.baseMapper.selectOne(queryWrapper);
    }

    //查找最后一条帖子的id
    @Override
    public Integer selectLastPostId() {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("post_id");
        queryWrapper.last("limit 1");
        Post post = this.baseMapper.selectOne(queryWrapper);
        return post.getPostId();
    }

    //返回一条帖子的信息（测试用）
    @Override
    public PostCardDto getOnePost() {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("post_id");
        queryWrapper.last("limit 1");
        Post post = this.baseMapper.selectOne(queryWrapper);
        PostCardDto postCardDto = new PostCardDto();
        postCardDto.setId(post.getPostId());
        postCardDto.setTitle(post.getTitle());
        postCardDto.setDescription(post.getDescription());
        postCardDto.setCreateTime(post.getCreateTime());
        postCardDto.setUserName(userServiceimpl.selectUserById(post.getUserId()));
        postCardDto.setLikeCount(post.getLikeCount());
        postCardDto.setViewCount(post.getViewCount());
        postCardDto.setCategoryName(post.getCategoryName());
        return postCardDto;
    }
}
