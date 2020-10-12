package com.sugar.controller;

import cn.hutool.core.bean.BeanUtil;
import com.sugar.common.lang.Result;
import com.sugar.entity.Tag;
import com.sugar.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Tag标签处理类")
@RestController
public class TagController {

    @Autowired
    TagService tagService;

    @ApiOperation("Get-获取所有标签")
    @GetMapping("/tags")
    public Result list() {
        List<Tag> tags = tagService.list();
        return Result.success(tags);
    }

    @ApiOperation("Post-根据ID删除标签")
    @PostMapping("/tag/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        tagService.removeById(id);
        return Result.success(null);
    }

    @ApiOperation("Post-添加标签")
    @PostMapping("/tag/add")
    public Result add(@Validated @RequestBody Tag tag) {
        Tag t = new Tag();
        BeanUtil.copyProperties(tag, t);
        tagService.saveOrUpdate(t);
        return Result.success(null);
    }
}
