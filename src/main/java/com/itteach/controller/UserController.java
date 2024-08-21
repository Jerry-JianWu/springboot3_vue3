package com.itteach.controller;

import com.itteach.pojo.Result;
import com.itteach.pojo.User;
import com.itteach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result register(String username, String password){
        // finByUserName
        User u = userService.findByUserName(username);
        if(u == null){
            // 没有占用
            // 注册
            userService.register(username, password);
            return Result.success();

        }else{
            return Result.error("用户名已被占用");
        }
    }
}
