package com.hxy.boot.common.controller;

import com.hxy.boot.common.utils.exception.BusinessException;
import com.hxy.boot.common.utils.exception.ExceptionEnum;
import com.hxy.boot.common.utils.exception.JWTException;
import com.hxy.boot.common.utils.util.ThrowableUtil;
import com.hxy.boot.common.vo.ApiResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.BindingException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ExceptionController {
    /**
     * 实体对象前不加@RequestBody注解,单个对象内属性校验未通过抛出的异常类型
     */
    @ExceptionHandler(BindingException.class)
    public ApiResponseVO<Object> exceptionHandler(BindingException ex) {
        return new ApiResponseVO<>(ExceptionEnum.BODY_NOT_MATCH.getCode(), ex.getMessage());
    }

    /**
     * 实体对象前不加@RequestBody注解,校验方法参数或方法返回值时,未校验通过时抛出的异常
     */
    @ExceptionHandler(ValidationException.class)
    public ApiResponseVO<Object> exceptionHandler(ValidationException ex) {
        return new ApiResponseVO<>(ExceptionEnum.BODY_NOT_MATCH.getCode(), ex.getMessage());
    }

    /**
     * 实体对象前加@RequestBody注解,抛出的异常为该类异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponseVO<Object> exceptionHandler(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fieldErrors.size(); i++) {
            FieldError fe = fieldErrors.get(i);
            sb.append(fe.getField());
            sb.append(fe.getDefaultMessage());
            if (i != fieldErrors.size() - 1) {
                sb.append(",");
            } else {
                sb.append(";");
            }
        }
        return new ApiResponseVO<>(ExceptionEnum.BODY_NOT_MATCH.getCode(), sb.toString());
    }

    /**
     * 实体对象前加@RequestParam 参数缺失
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiResponseVO<Object> exceptionHandler(MissingServletRequestParameterException ex) {
        return new ApiResponseVO<>(ExceptionEnum.BODY_NOT_MATCH.getCode(), ex.getMessage());
    }

    /**
     * 实体对象前加@RequestHeader 请求头缺失
     */
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ApiResponseVO<Object> exceptionHandler(MissingRequestHeaderException ex) {
        return new ApiResponseVO<>(ExceptionEnum.BODY_NOT_MATCH.getCode(), ex.getMessage());
    }

    /**
     * 字段校验
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResponseVO<Object> exceptionHandler(ConstraintViolationException ex) {
        return new ApiResponseVO<>(ExceptionEnum.BODY_NOT_MATCH.getCode(), ex.getMessage());
    }

    /**
     * 数据格式异常,例如: json无法序列化
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResponseVO<Object> exceptionHandler(HttpMessageNotReadableException ex) {
        return new ApiResponseVO<>(ExceptionEnum.BODY_NOT_MATCH.getCode(), ex.getMessage());
    }

    /**
     * JWT异常
     */
    @ExceptionHandler(JWTException.class)
    public ApiResponseVO<Object> exceptionHandler(JWTException ex) {
        return new ApiResponseVO<>(ex);
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ApiResponseVO<Object> exceptionHandler(BusinessException ex) {
        return new ApiResponseVO<>(ex);
    }

    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public ApiResponseVO<Object> exceptionHandler(NullPointerException ex) {
        log.error("空指针异常: {}", ThrowableUtil.getStackTrace(ex));
        return new ApiResponseVO<>(ExceptionEnum.NULL_ERROR);
    }

    /**
     * 数字转换异常
     */
    @ExceptionHandler(NumberFormatException.class)
    public ApiResponseVO<Object> exceptionHandler(NumberFormatException ex) {
        log.error("数字转换异常: {}", ThrowableUtil.getStackTrace(ex));
        return new ApiResponseVO<>(ExceptionEnum.CAN_NOT_CONVERT);
    }

    /**
     * 全局exception处理
     */
    @ExceptionHandler(Exception.class)
    public ApiResponseVO<Object> exceptionHandler(Exception ex) {
        log.error("未知异常: {}", ThrowableUtil.getStackTrace(ex));
        return new ApiResponseVO<>(ExceptionEnum.INTERNAL_SERVER_ERROR);
    }
}


