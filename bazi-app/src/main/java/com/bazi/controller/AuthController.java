package com.bazi.controller;

import com.bazi.common.result.Result;
import com.bazi.common.result.ResultEnum;
import com.bazi.dto.user.UserDto;
import com.bazi.entity.User;
import com.bazi.service.AuthService;
import com.bazi.vo.user.UserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 认证控制器
 *
 * @author skm1229
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "认证管理", description = "用户登录、注册、登出相关接口")
public class AuthController {

    @Resource
    private AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "用户使用用户名和密码登录系统")
    public Result<UserVo> login(@RequestParam @NotBlank(message = "用户名不能为空") String username,
                               @RequestParam @NotBlank(message = "密码不能为空") String password) {
        User user = authService.login(username, password);

        // 转换为UserVo返回
        UserVo userVo = new UserVo();
        org.springframework.beans.BeanUtils.copyProperties(user, userVo);
        return Result.result(ResultEnum.SUCCESS, "登录成功", userVo);
    }

    @PostMapping("/register")
    @Operation(summary = "用户注册", description = "新用户注册账号")
    public Result<UserVo> register(@Valid @RequestBody User registerUser) {
        User user = authService.register(registerUser);

        // 转换为UserVo返回
        UserVo userVo = new UserVo();
        org.springframework.beans.BeanUtils.copyProperties(user, userVo);
        return Result.result(ResultEnum.SUCCESS, "注册成功", userVo);
    }

    @PostMapping("/logout")
    @Operation(summary = "用户登出", description = "用户退出登录")
    public Result<String> logout(@RequestParam Long userId) {
        authService.logout(userId);
        return Result.result(ResultEnum.SUCCESS, "登出成功");
    }

}
