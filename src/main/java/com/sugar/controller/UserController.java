package com.sugar.controller;


import com.sugar.common.lang.Result;
import com.sugar.entity.User;
import com.sugar.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequiresAuthentication
    @GetMapping("/index")
    public Result index() {
        User user = userService.getById(1L);
        return Result.success(200, "操作成功", user);
    }

    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        return Result.success(user);
    }

}
