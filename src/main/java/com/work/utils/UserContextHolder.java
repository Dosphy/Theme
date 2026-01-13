package com.work.utils;

/**
 * @Author Dosphy
 * @Date 2026/1/11 23:34
 */

import com.work.domain.pojo.User;

/**
 * ThreadLocal用户上下文工具类
 * 每个请求线程独立存储一份用户信息，线程之间互不干扰
 */
public class UserContextHolder {

    // 定义ThreadLocal，泛型为用户上下文类
    // InheritableThreadLocal可以自动将父线程的 ThreadLocal 数据传递给子线程
    private static final ThreadLocal<User> USER_CONTEXT = new InheritableThreadLocal<>();

    /**
     * 存入用户信息
     */
    public static void setUser(User User) {
        USER_CONTEXT.set(User);
    }

    /**
     * 获取当前线程的用户信息
     */
    public static User getUser() {
        // 避免空指针，无数据时返回空对象而非null
        return USER_CONTEXT.get() == null ? new User() : USER_CONTEXT.get();
    }

    /**
     * 获取当前登录用户ID（简化方法，避免到处getUserId()）
     */
    public static Long getCurrentUserId() {
        return getUser().getId();
    }

    /**
     * 获取当前登录用户名
     */
    public static String getCurrentUsername() {
        return getUser().getUsername();
    }

    /**
     * 清除当前线程的用户信息（必须调用，否则内存泄漏）
     */
    public static void clear() {
        USER_CONTEXT.remove();
    }
}