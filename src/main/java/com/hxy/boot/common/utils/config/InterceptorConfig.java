package com.hxy.boot.common.utils.config;

import com.hxy.boot.common.utils.interceptor.ResponseInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    ResponseInterceptor responseInterceptor;
    @Autowired
    com.hxy.boot.common.utils.interceptor.JWTInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // jwt验证拦截器
        registry.addInterceptor(jwtInterceptor);
        // 响应统一格式拦截器
        registry.addInterceptor(responseInterceptor);
    }
}

