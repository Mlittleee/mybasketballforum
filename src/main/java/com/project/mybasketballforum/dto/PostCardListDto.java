package com.project.mybasketballforum.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @author MHC
 * @version 1.0
 * @project mybasketballforum
 * @package com.project.mybasketballforum.dto
 * @date 2023/6/18 16:34:17
 * {@code @description：多个卡片，同时包含多个标签}
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PostCardListDto extends PostCardDto{

    private Integer id;

    private String title;

    private String description;

    private String userName;

    private String categoryName;

    private Integer viewCount;

    private Integer likeCount;

    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;

    private List<TagDto> tagDtoList;
}
