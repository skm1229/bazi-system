package com.bazi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bazi.common.exception.BusinessException;
import com.bazi.common.result.ResultEnum;
import com.bazi.entity.User;
import com.bazi.mapper.UserMapper;
import com.bazi.service.AuthService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.UUID;
/**
 * 认证服务实现类
 *
 * @author skm1229
 * @version 1.0.0
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private UserMapper userMapper;

    // 密码加密盐值
    private static final String PASSWORD_SALT = "bazi_system_2026_secure_salt";
    private static final String PASSWORD_SALT_FALLBACK = "bazi_system_2026";

    // Token前缀
    private static final String TOKEN_PREFIX = "bazi_";

    @Override
    @Transactional
    public User login(String username, String password) {
        // 先根据用户名查询用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username);

        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new BusinessException(ResultEnum.LOGIN_ERROR);
        }

        // 验证密码（对输入密码进行加密后与数据库中的密码比较）
        String encryptedPassword = encryptPassword(password);
        if (!encryptedPassword.equals(user.getPassword())) {
            throw new BusinessException(ResultEnum.LOGIN_ERROR);
        }

        // 生成新token并更新到数据库
        String newToken = generateToken(user.getId());
        user.setToken(newToken);
        user.setLoginAt(LocalDateTime.now());
        userMapper.updateById(user);

        return user;
    }

    @Override
    @Transactional
    public User register(User user) {
        // 检查用户名是否已存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getUsername, user.getUsername());

        User existingUser = userMapper.selectOne(queryWrapper);
        if (existingUser != null) {
            throw new BusinessException(ResultEnum.USERNAME_EXISTS);
        }

        // 加密密码
        user.setPassword(encryptPassword(user.getPassword()));

        // 设置注册和登录时间
        user.setCreatedAt(LocalDateTime.now());
        user.setLoginAt(LocalDateTime.now());

        // 保存用户，处理数据库约束异常
        try {
            userMapper.insert(user);
            // 保存成功后生成token并更新到数据库
            String newToken = generateToken(user.getId());
            user.setToken(newToken);
            userMapper.updateById(user);
        } catch (DuplicateKeyException e) {
            // 根据异常信息判断是用户名还是邮箱重复
            String errorMessage = e.getMessage();
            if (errorMessage != null && errorMessage.contains("uk_username")) {
                throw new BusinessException(ResultEnum.USERNAME_EXISTS);
            }
            if (errorMessage != null && errorMessage.contains("uk_email")) {
                throw new BusinessException(ResultEnum.EMAIL_EXISTS);
            }
            throw new BusinessException(ResultEnum.SYSTEM_ERROR);
        }

        return user;
    }

    @Override
    @Transactional
    public void logout(Long userId) {
        // 查询用户是否存在
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new BusinessException(ResultEnum.USER_NOT_FOUND);
        }

        // 清除数据库中的token
        user.setToken(null);
        userMapper.updateById(user);
    }

    @Override
    @Transactional
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        // 查询用户
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new BusinessException(ResultEnum.USER_NOT_FOUND);
        }

        // 验证旧密码
        String encryptedOldPassword = encryptPassword(oldPassword);
        if (!encryptedOldPassword.equals(user.getPassword())) {
            throw new BusinessException(ResultEnum.OLD_PASSWORD_ERROR);
        }

        // 加密新密码并更新
        String encryptedNewPassword = encryptPassword(newPassword);
        user.setPassword(encryptedNewPassword);

        // 清除token，要求重新登录
        user.setToken(null);

        userMapper.updateById(user);
    }

    /**
     * 密码加密
     * 使用SHA-256 + 固定盐值加密（比MD5更安全）
     */
    private String encryptPassword(String password) {
        try {
            // 使用SHA-256 + 固定盐值，确保同一密码每次加密结果一致
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String saltedPassword = password + PASSWORD_SALT;
            byte[] hash = digest.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));

            // 转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // 如果SHA-256不可用，降级使用MD5
            return DigestUtils.md5DigestAsHex((password + PASSWORD_SALT_FALLBACK).getBytes());
        }
    }

    /**
     * 生成用户登录token
     * 使用UUID + 用户ID + 时间戳 + 随机数增强安全性
     */
    private String generateToken(Long userId) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String timestamp = String.valueOf(System.currentTimeMillis());
        String userIdStr = userId != null ? userId.toString() : "0";
        String randomSuffix = UUID.randomUUID().toString().substring(0, 8);
        return TOKEN_PREFIX + userIdStr + "_" + uuid + "_" + timestamp + "_" + randomSuffix;
    }
}
