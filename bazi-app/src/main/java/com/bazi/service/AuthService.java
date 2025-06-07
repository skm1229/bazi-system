package com.bazi.service;

import com.bazi.entity.User;

/**
 * 认证服务接口
 *
 * @author skm1229
 * @version 1.0.0
 */
public interface AuthService {

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果（包含token）
     */
    User login(String username, String password);

    /**
     * 用户注册
     *
     * @param user 注册用户信息
     * @return 注册结果（包含token）
     */
    User register(User user);

    /**
     * 用户登出
     *
     * @param userId 用户ID
     */
    void logout(Long userId);

    /**
     * 修改密码
     *
     * @param userId 用户ID
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void changePassword(Long userId, String oldPassword, String newPassword);
}
