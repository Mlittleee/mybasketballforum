package com.project.mybasketballforum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.project.mybasketballforum.dto.TagDto;
import com.project.mybasketballforum.mapper.PostcardMapper;
import com.project.mybasketballforum.pojo.Post;
import com.project.mybasketballforum.pojo.Postcard;
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
}
