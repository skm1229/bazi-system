package com.bazi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * 用户实体
 *
 * @author skm1229
 * @version 1.0.0
 */
@Data
@TableName("users")
@Schema(description = "用户实体")
public class User {

    @Schema(description = "用户ID")
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Schema(description = "用户登录名", example = "zhangsan")
    @NotBlank(message = "用户名不能为空")
    @Size(min = 3, max = 20, message = "用户名长度必须在3-20位之间")
    private String username;

    @Schema(description = "用户密码", example = "123456")
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须在6-20位之间")
    private String password;

    @Schema(description = "用户昵称", example = "张三")
    @Size(max = 50, message = "昵称长度不能超过50位")
    private String nickname;

    @Schema(description = "用户邮箱", example = "zhangsan@example.com")
    @Email(message = "邮箱格式不正确")
    @Size(max = 100, message = "邮箱长度不能超过100位")
    private String email;

    @Schema(description = "用户登录令牌")
    private String token;

    @Schema(description = "最后登录时间")
    private LocalDateTime loginAt;

    @Schema(description = "注册时间")
    private LocalDateTime createdAt;
}
