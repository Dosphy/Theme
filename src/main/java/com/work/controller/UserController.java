package com.work.controller;

import com.work.domain.pojo.LoginForm;
import com.work.domain.pojo.RegisterForm;
import com.work.domain.pojo.Result;
import com.work.domain.pojo.User;
import com.work.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录接口
     * @param loginForm 登录表单
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm) {
        User user = userService.login(loginForm);
        if (user == null) {
            return Result.fail("用户名或密码错误");
        }
        // 登录成功，返回用户信息
        return Result.ok(user);
    }

    /**
     * 注册接口
     * @param registerForm 注册表单
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result register(@RequestBody RegisterForm registerForm) {
        User user = userService.register(registerForm);
        if (user == null) {
            return Result.fail("用户名已存在");
        }
        // 注册成功，返回用户信息
        return Result.ok(user);
    }
}