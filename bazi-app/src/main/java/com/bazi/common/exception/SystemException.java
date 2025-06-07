package com.bazi.common.exception;


import com.bazi.common.result.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 系统异常处理器
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemException extends RuntimeException {

    /**
     * 返回参数结果枚举
     */
    private ResultEnum resultEnum;

//**********************************************************************************************************************

    public SystemException(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }


}
