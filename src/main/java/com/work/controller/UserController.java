package com.work.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import com.work.domain.pojo.LoginForm;
import com.work.domain.pojo.RegisterForm;
import com.work.domain.pojo.Result;
import com.work.domain.pojo.User;
import com.work.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.work.utils.RedisConstants.LOGIN_USER_KEY;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
        String token = UUID.randomUUID().toString(true);
        Map<String, Object> userMap = BeanUtil.beanToMap(user,new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName,fieldValue) -> fieldValue.toString())

        );
        stringRedisTemplate.opsForHash().putAll(LOGIN_USER_KEY+token,userMap);
        //设置token有效期为30分钟
        stringRedisTemplate.expire(LOGIN_USER_KEY+token,30L, TimeUnit.MINUTES);
        // 将token和用户信息一起返回
        Result result = Result.ok(user);
        result.setData(new LoginResponse(user, token));
        return result;
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

    /**
     * 登录响应类
     */
    public static class LoginResponse {
        private User user;
        private String token;

        public LoginResponse(User user, String token) {
            this.user = user;
            this.token = token;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}