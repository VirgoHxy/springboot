package com.hxy.boot.common.utils.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTException extends RuntimeException implements IBaseException {
    private final String code;
    private final String msg;

    public JWTException(String msg) {
        this.code = ExceptionEnum.SIGNATURE_NOT_MATCH.getCode();
        this.msg = msg;
    }

    public JWTException() {
        this.code = ExceptionEnum.SIGNATURE_NOT_MATCH.getCode();
        this.msg = ExceptionEnum.SIGNATURE_NOT_MATCH.getMsg();
    }
}