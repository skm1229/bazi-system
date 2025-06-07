package com.bazi.vo.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户视图对象
 *
 * @author skm1229
 * @version 1.0.0
 */
@Data
@Schema(description = "用户视图对象")
public class UserVo {

    @Schema(description = "用户ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Schema(description = "用户登录名", example = "zhangsan")
    private String username;

    @Schema(description = "用户昵称", example = "张三")
    private String nickname;

    @Schema(description = "用户邮箱", example = "zhangsan@example.com")
    private String email;

    @Schema(description = "用户登录令牌")
    private String token;

    @Schema(description = "最后登录时间")
    private LocalDateTime loginAt;

    @Schema(description = "注册时间")
    private LocalDateTime createdAt;
}
