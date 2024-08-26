package com.itteach.service.impl;

import com.itteach.mapper.UserMapper;
import com.itteach.pojo.User;
import com.itteach.service.UserService;
import com.itteach.utils.Md5Util;
import com.itteach.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        User u = userMapper.findByUserName(username);

        return u;
    }
    @Override
    public void register(String username, String password) {
        // 加密密码
        String md5String = Md5Util.getMD5String(password);
        // 添加
        userMapper.add(username, md5String);

    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);

    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map =  ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl, id);
    }

}
