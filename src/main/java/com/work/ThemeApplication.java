package com.work;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author Dosphy
 * @Date 2026/1/3 13:12
 */
//开启AOP
//@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
@MapperScan("com.work.mapper")
public class ThemeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThemeApplication.class, args);
    }
}
