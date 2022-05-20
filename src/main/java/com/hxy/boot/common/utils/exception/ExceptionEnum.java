package com.hxy.boot.common.utils.exception;

import lombok.Getter;

/**
 * 异常处理枚举类
 */
@Getter
public enum ExceptionEnum implements IBaseException {

    // 返回错误定义
    SUCCESS("2000", "成功!"),
    BUSINESS_ERROR("2008", "业务错误!"),
    BODY_NOT_MATCH("4000","请求的数据或格式不符合!"),
    SIGNATURE_NOT_MATCH("4001","请求的签名不匹配!"),
    NOT_FOUND("4004", "未找到该资源!"),
    INTERNAL_SERVER_ERROR("5000", "服务器内部错误!"),
    SERVER_BUSY("5003","服务器正忙，请稍后再试!"),
    NULL_ERROR("5011","空指针异常!"),
    CAN_NOT_CONVERT("5012","类型转换异常!");

    /**
     * 错误码
     */
    private final String code;

    /**
     * 错误描述
     */
    private final String msg;

    ExceptionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
