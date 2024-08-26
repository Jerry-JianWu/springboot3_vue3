package com.itteach.service;

import com.itteach.pojo.User;

public interface UserService {
    // 根据用户名查询用户
    User findByUserName(String username);
    void register(String username, String password);

    // 更新
    void update(User user);

    void updateAvatar(String avatarUrl);
}
