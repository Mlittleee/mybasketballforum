package com.project.mybasketballforum.pojo;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.sql.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author mhc
 * @since 2023-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("post")
public class Post implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 帖子id
     */
    @TableId(value = "post_id", type = IdType.AUTO)
    private Integer postId;

    /**
     * 帖子描述
     */
    private String description;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 浏览量
     */
    private Integer viewCount;

    /**
     * 点赞量
     */
    private Integer likeCount;

    /**
     * 所属板块名称
     */
    private String categoryName;

    /**
     * 帖子所属用户id
     */
    private Integer userId;

    /**
     * 帖子的创建时间
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;
}
