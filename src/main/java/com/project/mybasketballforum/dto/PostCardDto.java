package com.project.mybasketballforum.dto;

import cn.hutool.core.date.DateTime;
import lombok.Data;

/**
 * @author MHC
 * @version 1.0
 * @project mybasketballforum
 * @package com.project.mybasketballforum.dto
 * @date 2023/6/18 15:21:15
 * @description：用来存储帖子卡片的信息
 */
@Data
public class PostCardDto {
    private Integer id;
    private String title;
    private String description;
    private String userName;
    private String categoryName;
    private Integer viewCount;
    private Integer likeCount;
    private DateTime createTime;
}
