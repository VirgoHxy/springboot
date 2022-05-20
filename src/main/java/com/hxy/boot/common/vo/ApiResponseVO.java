package com.hxy.boot.common.vo;

import com.hxy.boot.common.utils.exception.ExceptionEnum;
import com.hxy.boot.common.utils.exception.IBaseException;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class ApiResponseVO<T> {
    private Boolean status;
    private String code;
    private String msg;
    private T data;

    public ApiResponseVO() {
    }

    public ApiResponseVO(String code, String msg) {
        this.status = false;
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public ApiResponseVO(IBaseException errorInfo) {
        this.status = false;
        this.code = errorInfo.getCode();
        this.msg = errorInfo.getMsg();
        this.data = null;
    }

    public ApiResponseVO(String code, T data, String msg) {
        this.status = Objects.equals(code, ExceptionEnum.SUCCESS.getCode());
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ApiResponseVO(T data) {
        this.status = true;
        this.code = ExceptionEnum.SUCCESS.getCode();
        this.msg = null;
        this.data = data;
    }
}
