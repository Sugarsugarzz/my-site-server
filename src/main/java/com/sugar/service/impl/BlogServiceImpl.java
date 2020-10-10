package com.sugar.service.impl;

import com.sugar.entity.Blog;
import com.sugar.mapper.BlogMapper;
import com.sugar.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
