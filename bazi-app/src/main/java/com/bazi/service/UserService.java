package com.bazi.service;

import com.bazi.dto.user.UserDto;
import com.bazi.vo.user.UserVo;

/**
 * 用户管理服务接口
 *
 * @author skm1229
 * @version 1.0.0
 */
public interface UserService {

    /**
     * 根据用户ID查询用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    UserVo getUserInfo(Long userId);

    /**
     * 更新用户信息
     *
     * @param userId 用户ID
     * @param userDto 更新信息
     * @return 更新后的用户信息
     */
    UserVo updateUserInfo(Long userId, UserDto userDto);

    /**
     * 修改密码
     *
     * @param userId 用户ID
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void changePassword(Long userId, String oldPassword, String newPassword);
}
