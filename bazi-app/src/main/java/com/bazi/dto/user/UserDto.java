package com.bazi.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 用户数据传输对象
 *
 * @author skm1229
 * @version 1.0.0
 */
@Data
@Schema(description = "用户数据传输对象")
public class UserDto {

    @Schema(description = "用户登录名", example = "zhangsan")
    @Size(min = 3, max = 20, message = "用户名长度必须在3-20位之间")
    private String username;

    @Schema(description = "用户密码", example = "123456")
    @Size(min = 6, max = 20, message = "密码长度必须在6-20位之间")
    private String password;

    @Schema(description = "用户昵称", example = "张三")
    @Size(max = 50, message = "昵称长度不能超过50位")
    private String nickname;

    @Schema(description = "用户邮箱", example = "zhangsan@example.com")
    @Email(message = "邮箱格式不正确")
    @Size(max = 100, message = "邮箱长度不能超过100位")
    private String email;
}
