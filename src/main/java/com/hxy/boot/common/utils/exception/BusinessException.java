package com.hxy.boot.common.utils.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException implements IBaseException {
    private final String code;
    private final String msg;

    public BusinessException(String msg) {
        this.code = ExceptionEnum.BUSINESS_ERROR.getCode();
        this.msg = msg;
    }
}