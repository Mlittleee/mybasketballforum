package com.project.mybasketballforum.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/************************
 * mybasketballforum
 * com.project.mybasketballforum.pojo
 * MHC
 * author : mhc
 * date:  2023/6/17 10:37
 * description : 每日一句
 ************************/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tip")
public class Tip implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 每日一句id
     */
    @TableId(value = "tip_id", type = IdType.AUTO)
    private Integer tipId;

    /**
     * 内容
     */
    private String content;

    /**
     * 作者
     */
    private String author;
}
