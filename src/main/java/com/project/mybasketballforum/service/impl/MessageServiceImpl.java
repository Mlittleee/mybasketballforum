package com.project.mybasketballforum.service.impl;

import com.project.mybasketballforum.pojo.Message;
import com.project.mybasketballforum.mapper.MessageMapper;
import com.project.mybasketballforum.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
