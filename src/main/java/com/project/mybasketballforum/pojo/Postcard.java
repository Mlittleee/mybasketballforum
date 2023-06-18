package com.project.mybasketballforum.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.mybasketballforum.dto.TagDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author MHC
 * @version 1.0
 * @project mybasketballforum
 * @package com.project.mybasketballforum.pojo
 * @date 2023/6/18 21:09:32
 * {@code @description：为了在前端展示帖子卡片而被迫建的新表}
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("postcard")
public class Postcard implements Serializable {

    private Integer id;

    private String title;

    private String description;

    private String userName;

    private String categoryName;

    private Integer viewCount;

    private Integer likeCount;

    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;

    private String tags;

}
