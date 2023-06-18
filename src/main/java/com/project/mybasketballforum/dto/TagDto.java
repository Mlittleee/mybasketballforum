package com.project.mybasketballforum.dto;

import lombok.Data;

/**
 * @author MHC
 * @version 1.0
 * @project mybasketballforum
 * @package com.project.mybasketballforum.dto
 * @date 2023/6/18 13:08:27
 * @description 用来在controller层和前端进行数据传输，只需要tag的name即可
 */
@Data
public class TagDto {

    private String name;
}
