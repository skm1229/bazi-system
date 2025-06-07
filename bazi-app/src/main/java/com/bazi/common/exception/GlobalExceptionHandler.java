package com.bazi.common.exception;

import com.bazi.common.result.Result;
import com.bazi.common.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;


/**
 * 全局异常处理器
 */
@Slf4j
@ResponseBody
@ControllerAdvice(annotations = {RestController.class})
public class GlobalExceptionHandler {

    /**
     * 处理系统异常
     *
     * @param ex 异常
     * @return 异常数据
     */
    @ExceptionHandler(SystemException.class)
    public Result<String> doSystemException(SystemException ex) {
        log.error(String.valueOf(ex));
        return Result.result(ex.getResultEnum());
    }

    /**
     * 处理业务异常
     *
     * @param ex 异常
     * @return 异常数据
     */
    @ExceptionHandler(BusinessException.class)
    public Result<String> exceptionHandler(BusinessException ex) {
        // 业务异常不记录日志，直接返回结果
        return Result.result(ex.getResultEnum());
    }

    /**
     * 处理@Valid验证异常 - 用于@RequestBody参数验证
     *
     * @param ex 验证异常
     * @return 异常数据
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        StringBuilder errorMsg = new StringBuilder();

        for (int i = 0; i < fieldErrors.size(); i++) {
            FieldError error = fieldErrors.get(i);
            errorMsg.append(error.getDefaultMessage());
            if (i < fieldErrors.size() - 1) {
                errorMsg.append("; ");
            }
        }

        log.warn("参数验证失败: {}", errorMsg.toString());
        return Result.result(ResultEnum.VALIDATION_ERROR, errorMsg.toString());
    }

    /**
     * 处理@Valid验证异常 - 用于表单参数验证
     *
     * @param ex 验证异常
     * @return 异常数据
     */
    @ExceptionHandler(BindException.class)
    public Result<String> handleBindException(BindException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        StringBuilder errorMsg = new StringBuilder();

        for (int i = 0; i < fieldErrors.size(); i++) {
            FieldError error = fieldErrors.get(i);
            errorMsg.append(error.getDefaultMessage());
            if (i < fieldErrors.size() - 1) {
                errorMsg.append("; ");
            }
        }

        log.warn("参数验证失败: {}", errorMsg.toString());
        return Result.result(ResultEnum.VALIDATION_ERROR, errorMsg.toString());
    }

    /**
     * 处理@Valid验证异常 - 用于单个参数验证
     *
     * @param ex 验证异常
     * @return 异常数据
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> handleConstraintViolationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        StringBuilder errorMsg = new StringBuilder();

        int i = 0;
        for (ConstraintViolation<?> violation : violations) {
            errorMsg.append(violation.getMessage());
            if (i < violations.size() - 1) {
                errorMsg.append("; ");
            }
            i++;
        }

        log.warn("参数验证失败: {}", errorMsg.toString());
        return Result.result(ResultEnum.VALIDATION_ERROR, errorMsg.toString());
    }

    /**
     * 处理不可预知异常
     *
     * @param ex 异常
     * @return 异常数据
     */
    @ExceptionHandler(Exception.class)
    public Result<String> doOtherException(Exception ex) {
        log.error("系统异常: ", ex);

        // 根据异常类型返回不同的错误码
        String exceptionName = ex.getClass().getSimpleName();
        switch (exceptionName) {
            case "SQLException":
            case "DataAccessException":
                return Result.result(ResultEnum.DATABASE_ERROR, "数据库操作失败");
            case "ConnectException":
            case "SocketTimeoutException":
                return Result.result(ResultEnum.NETWORK_ERROR, "网络连接失败");
            case "FileNotFoundException":
            case "IOException":
                return Result.result(ResultEnum.FILE_ERROR, "文件操作失败");
            default:
                return Result.result(ResultEnum.SYSTEM_ERROR, "系统内部错误");
        }
    }


}
