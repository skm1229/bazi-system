package com.bazi.common.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 结果响应类
 */
@Data
@Schema(description = "统一响应结果")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -1L;

    @Schema(description = "响应数据")
    private T data;

    @Schema(description = "状态码", example = "200")
    private Integer code;

    @Schema(description = "响应信息", example = "操作成功")
    private String msg;

//--------------------------------------------------------------------------------------------------------------------------------

    /**
     * 响应结果
     *
     * @param resultEnum 返回参数结果枚举
     * @param <T>                数据类型
     * @return 响应结果
     */
    public static <T> Result<T> result(ResultEnum resultEnum) {

        Result<T> r = new Result<>();
        r.code = resultEnum.getCode();
        r.msg = resultEnum.getMsg();
        return r;

    }

    /**
     * 响应结果
     *
     * @param resultEnum 返回参数结果枚举
     * @param data               数据
     * @param <T>                数据类型
     * @return 响应结果
     */
    public static <T> Result<T> result(ResultEnum resultEnum, T data) {

        Result<T> r = new Result<>();
        r.code = resultEnum.getCode();
        r.msg = resultEnum.getMsg();
        r.data = data;
        return r;

    }

    /**
     * 响应结果
     *
     * @param resultEnum 返回参数结果枚举
     * @param msg                响应信息
     * @param <T>                数据类型
     * @return 响应结果
     */
    public static <T> Result<T> result(ResultEnum resultEnum, String msg) {

        Result<T> r = new Result<>();
        r.code = resultEnum.getCode();
        r.msg = msg;
        return r;

    }

    /**
     * 响应结果
     *
     * @param resultEnum 返回参数结果枚举
     * @param msg                响应信息
     * @param data               数据
     * @param <T>                数据类型
     * @return 响应结果
     */
    public static <T> Result<T> result(ResultEnum resultEnum, String msg, T data) {

        Result<T> r = new Result<>();
        r.code = resultEnum.getCode();
        r.msg = msg;
        r.data = data;
        return r;

    }


}
