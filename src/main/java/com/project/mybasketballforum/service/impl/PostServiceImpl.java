package com.project.mybasketballforum.service.impl;

import com.project.mybasketballforum.pojo.Post;
import com.project.mybasketballforum.mapper.PostMapper;
import com.project.mybasketballforum.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
