package com.hxy.boot.common.utils.interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hxy.boot.common.utils.annotation.MyTokenRequired;
import com.hxy.boot.common.utils.exception.JWTException;
import com.hxy.boot.common.utils.util.JWTUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class JWTInterceptor implements HandlerInterceptor {

    private void verifyToken(HttpServletRequest request) throws JWTException {
        final String authHeader = request.getHeader(JWTUtil.AUTH_HEADER_KEY);
        if (StringUtils.isBlank(authHeader)) {
            throw new JWTException();
        }
        String token = !authHeader.startsWith(JWTUtil.TOKEN_PREFIX) ? authHeader : authHeader.substring(7);
        boolean isValid = JWTUtil.verifyTokenVaild(token);
        if (!isValid) {
            throw new JWTException();
        }
    }

    /**
     * 请求预处理，判断是否使用了MyTokenRequired注解
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 请求的接口方法
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> klass = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            boolean tokenRequired = false;
            if (klass.isAnnotationPresent(MyTokenRequired.class)) {
                // 在类对象上加了注解
                tokenRequired = klass.getAnnotation(MyTokenRequired.class).required();
            } else if (method.isAnnotationPresent(MyTokenRequired.class)) {
                // 在方法上加了注解
                tokenRequired = method.getAnnotation(MyTokenRequired.class).required();
            }
            if (tokenRequired) { verifyToken(request); }
        }
        return true;
    }
}