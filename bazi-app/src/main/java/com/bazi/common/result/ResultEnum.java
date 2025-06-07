package com.bazi.common.result;

import lombok.Getter;

/**
 * 结果响应枚举类
 *
 * <p>定义了系统中所有可能的响应状态码和消息</p>
 * <p>状态码遵循HTTP标准，便于前端处理</p>
 *
 * @author skm1229
 * @version 1.0.0
 */
@Getter
public enum ResultEnum {

    // ================================ 成功状态 2xx ================================

    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 创建成功
     */
    CREATED(201, "创建成功"),

    /**
     * 更新成功
     */
    UPDATED(200, "更新成功"),

    /**
     * 删除成功
     */
    DELETED(200, "删除成功"),

    // ================================ 客户端错误 4xx ================================

    /**
     * 参数错误
     */
    PARAM_ERROR(400, "参数错误"),

    /**
     * 请求参数缺失
     */
    PARAM_MISSING(400, "请求参数缺失"),

    /**
     * 参数格式错误
     */
    PARAM_FORMAT_ERROR(400, "参数格式错误"),

    /**
     * 用户名或密码错误
     */
    LOGIN_ERROR(401, "用户名或密码错误"),

    /**
     * 未登录或登录已过期
     */
    UNAUTHORIZED(401, "未登录或登录已过期"),

    /**
     * 该日期无法排盘
     */
    DATE_ERROR(401, "该日期无法排盘"),

    /**
     * 权限不足
     */
    FORBIDDEN(403, "权限不足"),

    /**
     * 数据不存在
     */
    DATA_NOT_FOUND(404, "数据不存在"),

    /**
     * 用户不存在
     */
    USER_NOT_FOUND(404, "用户不存在"),

    /**
     * 记录不存在
     */
    RECORD_NOT_FOUND(404, "记录不存在"),

    /**
     * 请求方法不支持
     */
    METHOD_NOT_ALLOWED(405, "请求方法不支持"),

    /**
     * 用户名已存在
     */
    USERNAME_EXISTS(409, "用户名已存在"),

    /**
     * 邮箱已被注册
     */
    EMAIL_EXISTS(409, "邮箱已被注册"),

    /**
     * 数据已存在
     */
    DATA_EXISTS(409, "数据已存在"),

    /**
     * 旧密码错误
     */
    OLD_PASSWORD_ERROR(422, "旧密码错误"),

    /**
     * 数据验证失败
     */
    VALIDATION_ERROR(422, "数据验证失败"),

    /**
     * 请求过于频繁
     */
    TOO_MANY_REQUESTS(429, "请求过于频繁，请稍后重试"),

    // ================================ 服务器错误 5xx ================================

    /**
     * 系统内部错误
     */
    ERROR(500, "系统内部错误"),

    /**
     * 系统繁忙，请稍后重试
     */
    SYSTEM_ERROR(500, "系统繁忙，请稍后重试"),

    /**
     * 数据库操作失败
     */
    DATABASE_ERROR(500, "数据库操作失败"),

    /**
     * 文件操作失败
     */
    FILE_ERROR(500, "文件操作失败"),

    /**
     * 网络连接失败
     */
    NETWORK_ERROR(500, "网络连接失败"),

    /**
     * 服务不可用
     */
    SERVICE_UNAVAILABLE(503, "服务不可用"),

    /**
     * 网关超时
     */
    GATEWAY_TIMEOUT(504, "网关超时"),

    // ================================ 业务错误 6xx ================================

    /**
     * 排盘失败
     */
    PAIPAN_ERROR(600, "排盘失败"),

    /**
     * 八字排盘失败
     */
    BAZI_ERROR(601, "八字排盘失败"),

    /**
     * 奇门遁甲排盘失败
     */
    QIMEN_ERROR(602, "奇门遁甲排盘失败"),

    /**
     * 紫微斗数排盘失败
     */
    ZIWEI_ERROR(603, "紫微斗数排盘失败"),

    /**
     * 六爻排盘失败
     */
    LIUYAO_ERROR(604, "六爻排盘失败"),

    /**
     * 梅花易数排盘失败
     */
    MEIHUA_ERROR(605, "梅花易数排盘失败"),

    /**
     * 日期计算错误
     */
    DATE_CALCULATE_ERROR(610, "日期计算错误"),

    /**
     * 干支计算错误
     */
    GANZHI_CALCULATE_ERROR(611, "干支计算错误"),

    /**
     * 节气计算错误
     */
    JIEQI_CALCULATE_ERROR(612, "节气计算错误");

//********************************************************************************************************************************

    /**
     * 状态码
     */
    int code;

    /**
     * 响应信息
     */
    String msg;

//--------------------------------------------------------------------------------------------------------------------------------

    /**
     * 构造
     *
     * @param code 状态码
     * @param msg  响应信息
     */
    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    // ================================ 实用方法 ================================

    /**
     * 判断是否为成功状态
     *
     * @return true-成功，false-失败
     */
    public boolean isSuccess() {
        return this.code >= 200 && this.code < 300;
    }

    /**
     * 判断是否为客户端错误
     *
     * @return true-客户端错误，false-非客户端错误
     */
    public boolean isClientError() {
        return this.code >= 400 && this.code < 500;
    }

    /**
     * 判断是否为服务器错误
     *
     * @return true-服务器错误，false-非服务器错误
     */
    public boolean isServerError() {
        return this.code >= 500 && this.code < 600;
    }

    /**
     * 判断是否为业务错误
     *
     * @return true-业务错误，false-非业务错误
     */
    public boolean isBusinessError() {
        return this.code >= 600;
    }

    /**
     * 根据状态码获取对应的枚举
     *
     * @param code 状态码
     * @return 对应的枚举，如果不存在则返回null
     */
    public static ResultEnum getByCode(int code) {
        for (ResultEnum resultEnum : values()) {
            if (resultEnum.getCode() == code) {
                return resultEnum;
            }
        }
        return null;
    }

    /**
     * 根据消息获取对应的枚举
     *
     * @param msg 消息
     * @return 对应的枚举，如果不存在则返回null
     */
    public static ResultEnum getByMsg(String msg) {
        for (ResultEnum resultEnum : values()) {
            if (resultEnum.getMsg().equals(msg)) {
                return resultEnum;
            }
        }
        return null;
    }
}
