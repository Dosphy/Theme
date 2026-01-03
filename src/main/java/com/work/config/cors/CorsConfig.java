package com.work.config.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Dosphy
 * @Date 2026/1/3 13:28
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 放行所有接口的跨域请求
        registry.addMapping("/**")
                // 允许的跨域来源：*表示所有前端域名都可访问
                .allowedOriginPatterns("*")
                // 允许的请求方式：GET/POST/PUT/DELETE等所有请求方法
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // 允许的请求头：*表示放行所有请求头（如token、Content-Type）
                .allowedHeaders("*")
                // 是否允许前端携带Cookie：true=允许（前后端分离带登录态必须开）
                .allowCredentials(true)
                // 预检请求有效期（单位秒）：3600秒内，同一请求无需重复发送预检OPTIONS请求
                .maxAge(3600);

    }
}
