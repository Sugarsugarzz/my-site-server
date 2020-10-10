package com.sugar.service.impl;

import com.sugar.entity.User;
import com.sugar.mapper.UserMapper;
import com.sugar.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
