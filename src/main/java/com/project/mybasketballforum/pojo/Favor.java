package com.project.mybasketballforum.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

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
@TableName(value ="favor")
public class Favor implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 收藏id
     */
    @TableId(value = "favor_id", type = IdType.AUTO)
    private Integer favorId;

    /**
     * 收藏所属用户id
     */
    private Integer userId;

    /**
     * 帖子id
     */
    private Integer postId;


}
