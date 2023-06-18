package com.project.mybasketballforum.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author MHC
 * @version 1.0
 * @project mybasketballforum
 * @package com.project.mybasketballforum.dto
 * @date 2023/6/19 01:33:24
 * {@code @description：用来展示文章详情的dto}
 */
@Data
public class PostViewDto {

    private String title;

    private String content;

    private String userName;

    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;

    private String categoryName;
}
