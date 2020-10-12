package com.sugar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sugar.entity.Category;
import com.sugar.mapper.CategoryMapper;
import com.sugar.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
