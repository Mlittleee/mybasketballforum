package com.project.mybasketballforum.service.impl;

import com.project.mybasketballforum.pojo.Tip;
import com.project.mybasketballforum.mapper.TipMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.mybasketballforum.service.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mhc
 * @since 2023-06-15
 */

@Service
public class TipServiceImpl extends ServiceImpl<TipMapper, Tip> implements TipService {

    @Autowired
    private TipMapper tipMapper;

    //根据数据库中的数据来随机获取每日一句
    @Override
    public Tip getTip() {
        Long count = tipMapper.selectCount(null);
        //根据数据库中记录数生成随机数
        int random = (int) (Math.random() * count + 1);
        //根据随机数来获取每日一句
        Tip tip = tipMapper.selectById(random);
        return tip;
    }
}
