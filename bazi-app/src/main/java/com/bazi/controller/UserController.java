package com.bazi.controller;

import com.bazi.common.result.Result;
import com.bazi.common.result.ResultEnum;
import com.bazi.dto.user.UserDto;
import com.bazi.service.UserService;
import com.bazi.vo.user.UserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 用户管理控制器
 *
 * @author skm1229
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/user")
@Tag(name = "用户管理", description = "用户账户管理相关接口")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/info")
    @Operation(summary = "查询用户信息", description = "获取当前用户信息")
    public Result<UserVo> getUserInfo(@RequestParam Long userId) {
        UserVo userVo = userService.getUserInfo(userId);
        return Result.result(ResultEnum.SUCCESS, "查询成功", userVo);
    }

    @PutMapping("/info")
    @Operation(summary = "更新用户信息", description = "更新当前用户信息")
    public Result<UserVo> updateUserInfo(@RequestParam Long userId,
                                        @Valid @RequestBody UserDto userDto) {
        UserVo userVo = userService.updateUserInfo(userId, userDto);
        return Result.result(ResultEnum.SUCCESS, "更新成功", userVo);
    }

    @PostMapping("/changePassword")
    @Operation(summary = "修改密码", description = "用户修改登录密码")
    public Result<String> changePassword(@RequestParam Long userId,
                                       @RequestParam @NotBlank(message = "原密码不能为空") String oldPassword,
                                       @RequestParam @NotBlank(message = "新密码不能为空") String newPassword) {
        userService.changePassword(userId, oldPassword, newPassword);
        return Result.result(ResultEnum.SUCCESS, "密码修改成功，请重新登录");
    }
}
