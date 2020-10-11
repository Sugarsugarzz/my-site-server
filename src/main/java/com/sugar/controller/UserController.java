package com.sugar.controller;


import com.sugar.common.lang.Result;
import com.sugar.entity.User;
import com.sugar.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "User用户类")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("Get-根据ID获取用户")
    @RequiresAuthentication
    @GetMapping("/index")
    public Result index(Long id) {
        User user = userService.getById(id);
        return Result.success(200, "操作成功", user);
    }

    @ApiOperation("Post-添加用户")
    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        return Result.success(user);
    }

}
