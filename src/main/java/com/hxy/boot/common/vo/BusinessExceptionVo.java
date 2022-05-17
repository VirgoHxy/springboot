package com.hxy.boot.common.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessExceptionVo extends RuntimeException implements IBaseExceptionVo {
    private final String code;
    private final String msg;

    public BusinessExceptionVo(String msg) {
        this.code = ExceptionEnumVo.BUSINESS_ERROR.getCode();
        this.msg = msg;
    }
}