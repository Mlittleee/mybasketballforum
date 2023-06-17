package com.project.mybasketballforum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.mybasketballforum.pojo.Tip;

/************************
 * mybasketballforum
 * com.project.mybasketballforum.service
 * MHC
 * author : mhc
 * date:  2023/6/17 10:48
 * description : 
 ************************/
public interface TipService extends IService<Tip> {

    //根据数据库中的数据来随机获取每日一句
    Tip getTip();
}
