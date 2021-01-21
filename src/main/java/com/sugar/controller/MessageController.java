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
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage, Integer pageSize) {
        Page page = new Page(currentPage, pageSize);
        IPage pageData = messageService.page(page, new QueryWrapper<Message>().orderByDesc("created").eq("status", 1));
        return Result.success(pageData);
    }

    @ApiOperation("Post-根据ID删除留言")
    @PostMapping("/message/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        Message m = new Message();
        m.setId(id).setStatus(0);
        messageService.updateById(m);
        return Result.success(null);
    }

    @ApiOperation("Post-添加留言")
    @PostMapping("/message/add")
    public Result add(@Validated @RequestBody Message message) {
        messageService.saveOrUpdate(message);
        return Result.success(null);
    }

}
