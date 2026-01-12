package com.work.interceptor;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.work.domain.pojo.User;
import com.work.utils.UserContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.work.utils.RedisConstants.LOGIN_USER_KEY;

/**
 * @Author Dosphy
 * @Date 2026/1/13 00:39
 */
public class RefreshTokenInterceptor implements HandlerInterceptor {

    private StringRedisTemplate stringRedisTemplate;

    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取请求头中的token
        String token = request.getHeader("authorization");
        if (StrUtil.isBlank(token)) {
            return true;
        }

        //2.基于token获取redis中的用户信息
        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(LOGIN_USER_KEY + token);

        //3.判断用户是否存在
//        if (userMap.isEmpty()) {
//            return true;
//        }

        //4.查询到的Hash用户数据转为User类型
        User user = BeanUtil.fillBeanWithMap(userMap, new User(), false);

        //5.存在保存用户信息到ThreadLocal
        UserContextHolder.setUser(user);

        //6.刷新redis中用户的token有效期
        stringRedisTemplate.expire(LOGIN_USER_KEY + token, 30L, TimeUnit.MINUTES);

        //7.放行
        return true;
    }
}