package com.sugar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sugar.entity.Message;
import com.sugar.mapper.MessageMapper;
import com.sugar.service.MessageService;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
}
