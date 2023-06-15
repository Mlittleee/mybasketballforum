package com.project.mybasketballforum.pojo;

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
@TableName("message")
public class Message implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 私信id
     */
    private Integer messageId;

    /**
     * 发送用户id
     */
    private Integer senderId;

    /**
     * 接收用户id
     */
    private Integer receiverId;

    /**
     * 私信内容
     */
    private String content;


}
