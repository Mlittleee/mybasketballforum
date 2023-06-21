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
@TableName("thumb")
public class Thumb implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 点赞id
     */
    @TableId(value = "thumb_id", type = IdType.AUTO)
    private Integer thumbId;

    /**
     * 点赞所属用户id
     */
    private Integer userId;

    /**
     * 帖子id
     */
    private Integer postId;


}
