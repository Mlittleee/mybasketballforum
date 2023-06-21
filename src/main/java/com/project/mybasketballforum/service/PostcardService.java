package com.project.mybasketballforum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.mybasketballforum.pojo.Post;
import com.project.mybasketballforum.pojo.Postcard;

import java.util.List;

/**
 * @author MHC
 * @version 1.0
 * @project mybasketballforum
 * @package com.project.mybasketballforum.service
 * @date 2023/6/18 21:23:46
 * {@code @description：}
 */
public interface PostcardService extends IService<Postcard> {

    //将List<post>转换为List<postcard>
    void postToPostcard(List<Post> postList);

    //添加浏览量
    boolean addViewCount(Integer postId);

    //添加点赞量
    boolean addLikeCount(Integer postId);

    //删除帖子
    boolean deletePostcard(Integer id);
}
