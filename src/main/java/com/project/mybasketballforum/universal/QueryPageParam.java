package com.project.mybasketballforum.universal;

import lombok.Data;

import java.util.HashMap;

/************************
 * Manage-System
 * com.project.mybasketballforum.universal
 * MHC
 * author : mhc
 * date:  2023/6/11 16:59
 * description : 分页功能的封装参数类
 ************************/
@Data
public class QueryPageParam {
    //默认值
    private static int DEFAULT_PAGE_SIZE = 20;
    private static int DEFAULT_PAGE_NUM = 1;

    private int pageSize = DEFAULT_PAGE_SIZE;
    private int pageNum = DEFAULT_PAGE_NUM;

    //还有其他需要传递的参数可以通过这个map传递
    private HashMap param = new HashMap<>();
}
