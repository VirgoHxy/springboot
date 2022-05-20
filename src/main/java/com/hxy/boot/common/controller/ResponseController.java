package com.hxy.boot.common.controller;

import com.hxy.boot.common.utils.annotation.MyApiResponse;
import com.hxy.boot.common.vo.ApiResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ResponseController implements ResponseBodyAdvice<Object> {

    /**
     * 标记位，标记请求的controller类或方法上使用了到了自定义注解，返回数据需要被包装
     */
    public static final String RESPONSE_ANNOTATION = "RESPONSE_ANNOTATION";

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ra != null) {
            // 查询是否需要进行响应包装的标志
            HttpServletRequest sr = ra.getRequest();
            MyApiResponse responseResult = (MyApiResponse) sr.getAttribute(RESPONSE_ANNOTATION);
            return responseResult != null;
        }
        return false;
    }

    @Override
    public ApiResponseVO<Object> beforeBodyWrite(Object responseBody, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return new ApiResponseVO<>(responseBody);
    }
}