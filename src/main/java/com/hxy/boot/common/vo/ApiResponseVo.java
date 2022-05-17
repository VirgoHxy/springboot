package com.hxy.boot.common.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class ApiResponseVo<T> {
    private Boolean status;
    private String code;
    private String msg;
    private T data;

    public ApiResponseVo() {
    }

    public ApiResponseVo(String code, String msg) {
        this.status = false;
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public ApiResponseVo(IBaseExceptionVo errorInfo) {
        this.status = false;
        this.code = errorInfo.getCode();
        this.msg = errorInfo.getMsg();
        this.data = null;
    }

    public ApiResponseVo(String code, T data, String msg) {
        this.status = Objects.equals(code, ExceptionEnumVo.SUCCESS.getCode());
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ApiResponseVo(T data) {
        this.status = true;
        this.code = ExceptionEnumVo.SUCCESS.getCode();
        this.msg = null;
        this.data = data;
    }
}
