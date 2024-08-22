package com.itteach.controller;

import com.itteach.pojo.Result;
import com.itteach.pojo.User;
import com.itteach.service.UserService;
import com.itteach.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){
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
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){
        // 根据用户名查询用户
        User loginUser = userService.findByUserName(username);
        //判断该用户是否存在
        if(loginUser == null){
            return Result.error("用户名错误");
        }
        //判断密码是否正确,loginUser对象中的password是密文，所以同样要对这个password加密在判断
        if(Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            // 登陆成功
            return Result.success("jwt token令牌：");
        }

        return Result.error("密码错误");

    }
}
