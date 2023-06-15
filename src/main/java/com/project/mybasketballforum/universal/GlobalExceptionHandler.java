package com.project.mybasketballforum.universal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
/************************
 * Manage-System
 * com.project.mybasketballforum.universal
 * MHC
 * author : mhc
 * date:  2023/6/13 18:44
 * description : 避免在Controller层中出现大量的try-catch块，
 *         使代码更加简洁、清晰。同时也可以实现异常信息的统一处理和格式化，
 *         便于前端开发人员对错误进行定位和处理。
 ************************/
@ControllerAdvice(annotations = {RestController.class, Controller.class})   //全局异常处理
@ResponseBody                                                               //返回json数据
public class GlobalExceptionHandler {
    @ExceptionHandler(FileNotFoundException.class)                          //捕获指定异常
    public Result<String> fileNotFoundException(FileNotFoundException exception){
        String message=exception.getMessage();
        return Result.error(message);
    }

    @ExceptionHandler(IOException.class)
    public Result<String> IOException(IOException exception){
        String message=exception.getMessage();
        return Result.error(message);
    }


    @ExceptionHandler(RuntimeException.class)
    public Result<String> RuntimeException(RuntimeException exception){
        String message=exception.getMessage();
        return Result.error(message);
    }
}

