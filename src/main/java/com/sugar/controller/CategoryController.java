package com.sugar.controller;

import cn.hutool.core.bean.BeanUtil;
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
        List<Category> categories = categoryService.list();
        return Result.success(categories);
    }

    @ApiOperation("Post-根据ID删除类别")
    @PostMapping("/category/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        categoryService.removeById(id);
        return Result.success(null);
    }

    @ApiOperation("Post-添加类别")
    @PostMapping("/category/add")
    public Result add(@Validated @RequestBody Category category) {
        Category c = new Category();
        BeanUtil.copyProperties(category, c);
        categoryService.saveOrUpdate(c);
        return Result.success(null);
    }
}
