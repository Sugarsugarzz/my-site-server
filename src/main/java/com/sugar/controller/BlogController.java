package com.sugar.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sugar.common.lang.Result;
import com.sugar.entity.Blog;
import com.sugar.service.BlogService;
import com.sugar.utils.ShiroUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Api(tags = "Blog博客相关类")
@RestController
public class BlogController {

    @Autowired
    BlogService blogService;

    @ApiOperation("Get-分页获取博文基本信息")
    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage, Integer pageSize) {
        Page page = new Page(currentPage, pageSize);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().select("id", "user_id", "title", "description", "category", "tags", "created")
                                                                        .eq("status", 1)
                                                                        .orderByDesc("created"));
        return Result.success(pageData);
    }

    @ApiOperation("Get-根据ID获取博文")
    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable("id") Long id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "该博客已被删除");
        return Result.success(blog);
    }

    @ApiOperation("Get-根据CategoryID获取博文")
    @GetMapping("/blog/category/{id}")
    public Result category_list(@PathVariable("id") Long id) {
        List<Blog> blogs = blogService.list(new QueryWrapper<Blog>().eq("category", id));
        return Result.success(blogs);
    }

    @ApiOperation("Post-根据ID删除博文")
    @PostMapping("/blog/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        Blog b = new Blog();
        b.setId(id).setStatus(0);
        blogService.updateById(b);
        return Result.success(null);
    }

    @ApiOperation("Post-发布/编辑博文")
    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result addOrUpdate(@Validated @RequestBody Blog blog) {

        Blog temp;
        // 如果有id，则为编辑状态；无id，则为添加状态
        if (blog.getId() != null) {
            temp = blogService.getById(blog.getId());
            // 只能编辑自己的文章
            Assert.isTrue(temp.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(), "没有权限编辑");
        } else {
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
        }
        BeanUtil.copyProperties(blog, temp, "id", "userId");
        blogService.saveOrUpdate(temp);

        return Result.success(null);
    }

}
