package com.project.mybasketballforum.universal;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/************************
 * ManageSystem
 * com.project.mybasketballforum.global
 * MHC
 * author : mhc
 * date:  2023/6/11 11:44
 * description : 这是一个与前端交接的通用结果返回类
 ************************/
@Data
public class Result<T> {
    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //信息

    private long total; //总记录数

    private T data; //数据

    private Map<String, Object> map = new HashMap<>(); //动态数据

    //无参数传递
    public static <T> Result<T> success() {
        Result<T> r = new Result<>();
        r.code = 200;
        r.total = 0L;
        return r;
    }

    //只有对象数据的时候
    public static <T> Result<T> success(T object) {
        Result<T> r = new Result<>();
        r.data = object;
        r.code = 200;
        r.total = 0L;
        return r;
    }

    //加上total数
    public static <T> Result<T> success(T object, long total) {
        Result<T> r = new Result<>();
        r.data = object;
        r.code = 200;
        r.total = total;
        return r;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> r = new Result<>();
        r.msg = msg;
        r.code = 0;
        r.total = 0L;
        return r;
    }

    public Result<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}