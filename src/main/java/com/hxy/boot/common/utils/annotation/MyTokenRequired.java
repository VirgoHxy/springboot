package com.hxy.boot.common.utils.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyTokenRequired {
    // 是否进行校验
    boolean required() default true;
}