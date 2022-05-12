package com.hxy.boot.common.controller;

import com.hxy.boot.common.vo.ApiResponseVo;
import org.apache.ibatis.binding.BindingException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

@RestControllerAdvice
public class CommonController {

    /**
     * 实体对象前不加@RequestBody注解,单个对象内属性校验未通过抛出的异常类型
     */
    @ExceptionHandler(BindingException.class)
    public ApiResponseVo<Object> exceptionHandler(BindingException e) {
        return new ApiResponseVo<>(false, null, e.getCause().getMessage());
    }

    /**
     * 实体对象前不加@RequestBody注解,校验方法参数或方法返回值时,未校验通过时抛出的异常
     */
    @ExceptionHandler(ValidationException.class)
    public ApiResponseVo<Object> exceptionHandler(ValidationException e) {
        return new ApiResponseVo<>(false, null, e.getCause().getMessage());
    }

    /**
     * 实体对象前加@RequestBody注解,抛出的异常为该类异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponseVo<Object> exceptionHandler(MethodArgumentNotValidException e) {
        return new ApiResponseVo<>(false, null, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 字段校验
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResponseVo<Object> exceptionHandler(ConstraintViolationException e) {
        return new ApiResponseVo<>(false, null, e.getMessage());
    }


    /**
     * 全局exception处理
     */
    @ExceptionHandler(Exception.class)
    public ApiResponseVo<Object> exceptionHandler(Exception e) {
        // 自定义异常
        return new ApiResponseVo<>(false, null, e.getMessage());
    }
}


