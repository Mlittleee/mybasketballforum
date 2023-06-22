package com.project.mybasketballforum.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

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
@TableName("comment")
public class Comment implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 评论id
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 点赞数
     */
    private Long likeCount;

    /**
     * 所属帖子id
     */
    private Integer postId;

    /**
     * 上一级评论id
     */
    private Integer upperId;

    /**
     * 评论所属用户名字
     */
    private String userName;

    /**
     * 评论创建的时间
     */
    private String createTime;

}
