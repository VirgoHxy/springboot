package com.hxy.boot.common.utils.exception;

public interface IBaseException {
    /**
     * 错误码
     */
    String getCode();

    /**
     * 错误描述
     */
    String getMsg();
}
