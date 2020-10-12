package com.sugar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sugar.entity.Tag;
import com.sugar.mapper.TagMapper;
import com.sugar.service.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
}
