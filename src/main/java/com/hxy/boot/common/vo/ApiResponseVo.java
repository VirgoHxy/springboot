package com.hxy.boot.common.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseVo<T> {
    private Boolean status;
    private String message;
    private T data;

    public ApiResponseVo() {
    }

    public ApiResponseVo(T data) {
        this.status = true;
        this.message = "SUCCESS";
        this.data = data;
    }

    public ApiResponseVo(Boolean status, T data, String message) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
