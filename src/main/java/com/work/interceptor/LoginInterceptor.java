package com.work.interceptor;

import com.work.domain.pojo.User;
import com.work.utils.UserContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.TimeUnit;

import static com.work.utils.RedisConstants.LOGIN_USER_KEY;

/**
 * 登录拦截器
 * 用于在请求开始时从Redis中获取用户信息并存储到ThreadLocal中
 * 在请求结束时清除ThreadLocal中的信息，避免内存泄漏
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public LoginInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("走拦截器了...");
        //1.判断是否拦截
        if(UserContextHolder.getUser() == null){
            System.out.println("没找到用户...");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        String token = LOGIN_USER_KEY + request.getHeader("authorization");
        if(stringRedisTemplate.getExpire(token, TimeUnit.SECONDS) > 0){
            //放行
            System.out.println("判断token了...");
            return true;
        }

        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求结束后，清除ThreadLocal中的信息，避免内存泄漏
        UserContextHolder.clear();
    }
}