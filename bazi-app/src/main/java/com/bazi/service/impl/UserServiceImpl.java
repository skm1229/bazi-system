package com.bazi.service.impl;

import com.bazi.common.exception.BusinessException;
import com.bazi.common.result.ResultEnum;
import com.bazi.dto.user.UserDto;
import com.bazi.entity.User;
import com.bazi.mapper.UserMapper;
import com.bazi.service.AuthService;
import com.bazi.service.UserService;
import com.bazi.vo.user.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 用户管理服务实现类
 *
 * @author skm1229
 * @version 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private AuthService authService;

    @Override
    public UserVo getUserInfo(Long userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new BusinessException(ResultEnum.USER_NOT_FOUND);
        }

        // 转换为UserVo，不返回密码信息
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }

    @Override
    @Transactional
    public UserVo updateUserInfo(Long userId, UserDto userDto) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new BusinessException(ResultEnum.USER_NOT_FOUND);
        }

        // 更新用户信息（只更新非空字段）
        if (userDto.getNickname() != null) {
            user.setNickname(userDto.getNickname());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }

        // 保存到数据库
        userMapper.updateById(user);

        // 返回更新后的用户信息
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }

    @Override
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        // 委托给AuthService处理密码修改
        authService.changePassword(userId, oldPassword, newPassword);
    }
}
