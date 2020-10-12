package com.sugar.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sugar.common.lang.Result;
import com.sugar.entity.Message;
import com.sugar.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Message留言处理类")
@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    @ApiOperation("Get-分页获取留言")
    @GetMapping("/messages")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {
        Page page = new Page(currentPage, 10);
        IPage pageData = messageService.page(page, new QueryWrapper<Message>().orderByDesc("created"));
        return Result.success(pageData);
    }

    @ApiOperation("Post-根据ID删除留言")
    @PostMapping("/message/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        messageService.removeById(id);
        return Result.success(null);
    }

    @ApiOperation("Post-添加留言")
    @PostMapping("/message/add")
    public Result add(@Validated @RequestBody Message message) {
        Message msg = new Message();
        BeanUtil.copyProperties(message, msg);
        messageService.saveOrUpdate(msg);
        return Result.success(null);
    }

}