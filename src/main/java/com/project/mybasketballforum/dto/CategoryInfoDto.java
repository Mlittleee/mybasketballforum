package com.project.mybasketballforum.dto;

import lombok.Data;

/************************
 * mybasketballforum
 * com.project.mybasketballforum.dto
 * Clement
 * author : Clement
 * date:  2023/6/21 10:35
 * description : 用来在前端管理员后台返回展示板块简介、创建者、文章数、活跃用户和热度的dto
 ************************/

@Data
public class CategoryInfoDto {
    private String description;

    private Integer userId;

    private Long postCount;

    private Long userCount;
}
