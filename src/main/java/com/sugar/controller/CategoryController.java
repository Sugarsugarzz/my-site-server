package com.sugar.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sugar.common.lang.Result;
import com.sugar.entity.Category;
import com.sugar.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Category类别处理类")
@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @ApiOperation("Get-获取所有类别")
    @GetMapping("/categories")
    public Result list() {
        List<Category> categories = categoryService.list(new QueryWrapper<Category>().eq("status", 1));
        return Result.success(categories);
    }

    @ApiOperation("Post-根据ID删除类别")
    @PostMapping("/category/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        Category c = new Category();
        c.setId(id).setStatus(0);
        categoryService.updateById(c);
        return Result.success(null);
    }

    @ApiOperation("Post-添加类别")
    @PostMapping("/category/add")
    public Result add(@Validated @RequestBody Category category) {
        Category c = categoryService.getOne(new QueryWrapper<Category>().eq("name", category.getName()));
        if (c == null) {
            categoryService.saveOrUpdate(category);
        } else {
            c.setStatus(1);
            categoryService.updateById(c);
        }
        return Result.success(null);
    }
}
