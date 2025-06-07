package com.bazi.common.exception;


import com.bazi.common.result.ResultEnum;
import lombok.Getter;

/**
 * 业务异常类
 *
 * @author skm1229
 * @version 1.0.0
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 结果枚举
     */
    private final ResultEnum resultEnum;

    /**
     * 构造方法
     *
     * @param resultEnum 结果枚举
     */
    public BusinessException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.resultEnum = resultEnum;
    }

    /**
     * 构造方法
     *
     * @param resultEnum 结果枚举
     * @param message    异常信息
     */
    public BusinessException(ResultEnum resultEnum, String message) {
        super(message);
        this.resultEnum = resultEnum;
    }

    /**
     * 构造方法
     *
     * @param resultEnum 结果枚举
     * @param message    异常信息
     * @param cause      异常原因
     */
    public BusinessException(ResultEnum resultEnum, String message, Throwable cause) {
        super(message, cause);
        this.resultEnum = resultEnum;
    }

    // ================================ 静态工厂方法 ================================

    /**
     * 创建数据不存在异常
     */
    public static BusinessException dataNotFound(String dataType) {
        return new BusinessException(ResultEnum.DATA_NOT_FOUND, dataType + "不存在");
    }

    /**
     * 创建用户不存在异常
     */
    public static BusinessException userNotFound() {
        return new BusinessException(ResultEnum.USER_NOT_FOUND);
    }

    /**
     * 创建记录不存在异常
     */
    public static BusinessException recordNotFound() {
        return new BusinessException(ResultEnum.RECORD_NOT_FOUND);
    }

    /**
     * 创建八字排盘失败异常
     */
    public static BusinessException baziError() {
        return new BusinessException(ResultEnum.BAZI_ERROR);
    }

    /**
     * 创建奇门遁甲排盘失败异常
     */
    public static BusinessException qimenError() {
        return new BusinessException(ResultEnum.QIMEN_ERROR);
    }

    /**
     * 创建紫微斗数排盘失败异常
     */
    public static BusinessException ziweiError() {
        return new BusinessException(ResultEnum.ZIWEI_ERROR);
    }

    /**
     * 创建六爻排盘失败异常
     */
    public static BusinessException liuyaoError() {
        return new BusinessException(ResultEnum.LIUYAO_ERROR);
    }

    /**
     * 创建梅花易数排盘失败异常
     */
    public static BusinessException meihuaError() {
        return new BusinessException(ResultEnum.MEIHUA_ERROR);
    }
}
