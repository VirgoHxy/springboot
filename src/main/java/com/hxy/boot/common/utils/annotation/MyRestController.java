package com.hxy.boot.common.utils.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping
@RestController
@Validated
public @interface MyRestController {
    @AliasFor("path")
    String[] value() default {};

    @AliasFor("value")
    String[] path() default {};

    boolean required() default true;
}