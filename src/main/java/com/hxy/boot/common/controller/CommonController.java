package com.hxy.boot.common.controller;

import com.hxy.boot.common.util.ThrowableUtil;
import com.hxy.boot.common.vo.ApiResponseVo;
import com.hxy.boot.common.vo.BusinessExceptionVo;
import com.hxy.boot.common.vo.ExceptionEnumVo;
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
public class CommonController {
    /**
     * 实体对象前不加@RequestBody注解,单个对象内属性校验未通过抛出的异常类型
     */
    @ExceptionHandler(BindingException.class)
    public ApiResponseVo<Object> exceptionHandler(BindingException ex) {
        return new ApiResponseVo<>(ExceptionEnumVo.BODY_NOT_MATCH.getCode(), ex.getMessage());
    }

    /**
     * 实体对象前不加@RequestBody注解,校验方法参数或方法返回值时,未校验通过时抛出的异常
     */
    @ExceptionHandler(ValidationException.class)
    public ApiResponseVo<Object> exceptionHandler(ValidationException ex) {
        return new ApiResponseVo<>(ExceptionEnumVo.BODY_NOT_MATCH.getCode(), ex.getMessage());
    }

    /**
     * 实体对象前加@RequestBody注解,抛出的异常为该类异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponseVo<Object> exceptionHandler(MethodArgumentNotValidException ex) {
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
        return new ApiResponseVo<>(ExceptionEnumVo.BODY_NOT_MATCH.getCode(), sb.toString());
    }

    /**
     * 实体对象前加@RequestParam 参数缺失
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiResponseVo<Object> exceptionHandler(MissingServletRequestParameterException ex) {
        return new ApiResponseVo<>(ExceptionEnumVo.BODY_NOT_MATCH.getCode(), ex.getMessage());
    }

    /**
     * 实体对象前加@RequestHeader 请求头缺失
     */
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ApiResponseVo<Object> exceptionHandler(MissingRequestHeaderException ex) {
        return new ApiResponseVo<>(ExceptionEnumVo.BODY_NOT_MATCH.getCode(), ex.getMessage());
    }

    /**
     * 字段校验
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResponseVo<Object> exceptionHandler(ConstraintViolationException ex) {
        return new ApiResponseVo<>(ExceptionEnumVo.BODY_NOT_MATCH.getCode(), ex.getMessage());
    }

    /**
     * 数据格式异常,例如: json无法序列化
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResponseVo<Object> exceptionHandler(HttpMessageNotReadableException ex) {
        return new ApiResponseVo<>(ExceptionEnumVo.BODY_NOT_MATCH.getCode(), ex.getMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessExceptionVo.class)
    public ApiResponseVo<Object> exceptionHandler(BusinessExceptionVo ex) {
        return new ApiResponseVo<>(ex);
    }

    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public ApiResponseVo<Object> exceptionHandler(NullPointerException ex) {
        log.error("空指针异常: {}", ThrowableUtil.getStackTrace(ex));
        return new ApiResponseVo<>(ExceptionEnumVo.NULL_ERROR);
    }

    /**
     * 数字转换异常
     */
    @ExceptionHandler(NumberFormatException.class)
    public ApiResponseVo<Object> exceptionHandler(NumberFormatException ex) {
        log.error("数字转换异常: {}", ThrowableUtil.getStackTrace(ex));
        return new ApiResponseVo<>(ExceptionEnumVo.CAN_NOT_CONVERT);
    }

    /**
     * 全局exception处理
     */
    @ExceptionHandler(Exception.class)
    public ApiResponseVo<Object> exceptionHandler(Exception ex) {
        log.error("未知异常: {}", ThrowableUtil.getStackTrace(ex));
        return new ApiResponseVo<>(ExceptionEnumVo.INTERNAL_SERVER_ERROR);
    }
}


