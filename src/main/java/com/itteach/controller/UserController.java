package com.itteach.controller;

import com.itteach.pojo.Result;
import com.itteach.pojo.User;
import com.itteach.service.UserService;
import com.itteach.utils.JwtUtil;
import com.itteach.utils.Md5Util;
import com.itteach.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }

        return Result.error("密码错误");

    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/){
        //根据用户名查询用户
        /*Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");*/

        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }
}
