package com.hxy.boot.common.utils.interceptor;

import com.hxy.boot.common.controller.ResponseController;
import com.hxy.boot.common.utils.annotation.MyApiResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class ResponseInterceptor implements HandlerInterceptor {

    /**
     * 请求预处理，判断是否使用了MyApiResponse注解
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 请求的接口方法
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> klass = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            // 在请求中设置需要进行响应包装的属性标志，在ResponseBodyAdvice增强中进行处理
            if (klass.isAnnotationPresent(MyApiResponse.class)) {
                // 在类对象上加了注解
                request.setAttribute(ResponseController.RESPONSE_ANNOTATION, klass.getAnnotation(MyApiResponse.class));
            } else if (method.isAnnotationPresent(MyApiResponse.class)) {
                // 在方法上加了注解
                request.setAttribute(ResponseController.RESPONSE_ANNOTATION, method.getAnnotation(MyApiResponse.class));
            }
        }
        return true;
    }
}