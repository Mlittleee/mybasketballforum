package com.project.mybasketballforum.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/************************
 * mybasketballforum
 * com.project.mybasketballforum.dto
 * MHC
 * author : mhc
 * date:  2023/6/17 19:19
 * description : 用来在前端只展示id和name的CategoryDto
 ************************/
@Data
public class CategoryDto {
    private Integer categoryId;
    private String name;
}
