package com.work.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.work.domain.pojo.LoginForm;
import com.work.domain.pojo.RegisterForm;
import com.work.domain.pojo.User;

public interface UserService extends IService<User> {
    /**
     * 登录
     * @param loginForm 登录表单
     * @return 用户信息
     */
    User login(LoginForm loginForm);

    /**
     * 注册
     * @param registerForm 注册表单
     * @return 用户信息
     */
    User register(RegisterForm registerForm);

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    User findByUsername(String username);
}