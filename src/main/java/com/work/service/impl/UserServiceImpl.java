package com.work.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.mapper.UserMapper;
import com.work.domain.pojo.LoginForm;
import com.work.domain.pojo.RegisterForm;
import com.work.domain.pojo.User;
import com.work.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User login(LoginForm loginForm) {
        // 根据用户名查询用户
        User user = this.lambdaQuery()
                .eq(User::getUsername, loginForm.getUsername())
                .one();
        if (user == null) {
            // 用户不存在
            return null;
        }
        // 验证密码
        String encryptedPassword = SecureUtil.md5(loginForm.getPassword());
        if (!encryptedPassword.equals(user.getPassword())) {
            // 密码不正确
            return null;
        }
        //TODO后续添加token到redis

        return user;
    }

    @Override
    public User register(RegisterForm registerForm) {
        // 检查用户名是否已存在
        User existingUser = this.findByUsername(registerForm.getUsername());
        if (existingUser != null) {
            // 用户名已存在
            return null;
        }
        // 创建新用户
        User user = new User();
        user.setUsername(registerForm.getUsername());
        // 密码加密
        user.setPassword(SecureUtil.md5(registerForm.getPassword()));
        // 设置邮箱
        user.setEmail(registerForm.getEmail());
        // 设置默认昵称
        user.setNickname(registerForm.getUsername());
        user.setStatus(1); // 启用状态
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        // 保存用户
        this.save(user);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return this.lambdaQuery()
                .eq(User::getUsername, username)
                .one();
    }
}