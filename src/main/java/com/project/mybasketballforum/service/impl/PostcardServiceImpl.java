package com.project.mybasketballforum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.project.mybasketballforum.dto.TagDto;
import com.project.mybasketballforum.mapper.PostcardMapper;
import com.project.mybasketballforum.pojo.Post;
import com.project.mybasketballforum.pojo.Postcard;
import com.project.mybasketballforum.pojo.Tag;
import com.project.mybasketballforum.pojo.Thumb;
import com.project.mybasketballforum.service.PostcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MHC
 * @version 1.0
 * @project mybasketballforum
 * @package com.project.mybasketballforum.service.impl
 * @date 2023/6/18 21:22:15
 * {@code @description：}
 */
@Service
public class PostcardServiceImpl extends ServiceImpl<PostcardMapper, Postcard> implements PostcardService {

    @Autowired
    private UserServiceImpl userServiceimpl;

    @Autowired
    private ThumbServiceImpl thumbServiceimpl;

    @Autowired
    private PostServiceImpl postServiceimpl;

    @Autowired
    private TagServiceImpl tagServiceimpl;

    //将List<Post>转换为List<Postcard>
    @Override
    public void postToPostcard(List<Post> posts) {

        for (Post post : posts) {
            String isExist = post.getTitle();
            //查找数据库中是否有相同名字的帖子
            LambdaQueryWrapper<Postcard> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Postcard::getTitle,isExist);
            Postcard postcard1 = this.baseMapper.selectOne(queryWrapper);
            if(postcard1 != null){
                continue;
            }
            Postcard postcard = new Postcard();
            postcard.setId(post.getPostId());
            postcard.setTitle(post.getTitle());
            postcard.setDescription(post.getDescription());
            postcard.setUserName(userServiceimpl.selectUserById(post.getUserId()));
            postcard.setCategoryName(post.getCategoryName());
            postcard.setCreateTime(post.getCreateTime());
            postcard.setViewCount(post.getViewCount());
            postcard.setLikeCount(post.getLikeCount());
            List<TagDto> tagDtoList = tagServiceimpl.getTagsByPostId(post.getPostId());
            //将tagDtoList转换为字符串
            StringBuilder stringBuilder = new StringBuilder();
            for (TagDto tagDto : tagDtoList) {
                stringBuilder.append(tagDto.getName());
                stringBuilder.append("//");
                //去掉最后一个逗号
                stringBuilder.deleteCharAt(stringBuilder.length()-1);
            }
            postcard.setTags(stringBuilder.toString());
            //将postcard存入数据库
            this.save(postcard);
        }
    }

    //删除帖子，同时删除和post相关的所有数据
    @Override
    public boolean deletePostcard(Integer id) {
        //删除postcard
        this.removeById(id);
        //删除post
        LambdaQueryWrapper<Post> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Post::getPostId,id);
        Post post = postServiceimpl.getOne(queryWrapper);
        postServiceimpl.removeById(post.getPostId());
        //删除tag
        LambdaQueryWrapper<Tag> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Tag::getPostId,id);
        tagServiceimpl.remove(queryWrapper1);
        //删除thumb
        LambdaQueryWrapper<Thumb> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(Thumb::getPostId,id);
        thumbServiceimpl.remove(queryWrapper2);
        //删除comment
        /*LambdaQueryWrapper<Comment> queryWrapper3 = new LambdaQueryWrapper<>();
        queryWrapper3.eq(Comment::getPostId,id);
        commentServiceimpl.remove(queryWrapper3);*/
        return true;
    }


    //添加浏览量
    @Override
    public boolean addViewCount(Integer postId) {
        Postcard postcard = this.getById(postId);
        postcard.setViewCount(postcard.getViewCount()+1);
        return(this.updateById(postcard));
    }

    //添加点赞量
    @Override
    public boolean addLikeCount(Integer postId) {
        Postcard postcard = this.getById(postId);
        postcard.setLikeCount(postcard.getLikeCount()+1);
        return(this.updateById(postcard));
    }

    //更新帖子
    @Override
    public boolean updatePostcard(Postcard postcard) {
        return(this.updateById(postcard));
    }

    //根据帖子id来获取帖子
    @Override
    public Postcard getPostcardById(Integer postId) {
        return(this.getById(postId));
    }
}
