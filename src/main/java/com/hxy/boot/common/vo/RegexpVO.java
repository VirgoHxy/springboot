package com.hxy.boot.common.vo;


/**
 * 异常处理枚举类
 */
public class RegexpVO {

    private RegexpVO() {}
    public static final String ONLY_NUMBER_LETTER_REGEXP = "^[A-Za-z\\d]+$";
    public static final String ONLY_NUMBER_LETTER_MESSAGE = "仅能包含字母和数字";

    public static final String CAN_NOT_ILLEGAL_CHAR_REGEXP = "[^\\`\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\_\\=\\+\\[\\{\\]\\}\\\\\\|\\;\\:\\'\\\"\\,\\<\\.\\>\\/\\?\\·\\！\\￥\\…\\（\\）\\—\\【\\】\\、\\；\\：\\‘\\’\\“\\”\\，\\《\\。\\》\\？]+";
    public static final String CAN_NOT_ILLEGAL_CHAR_MESSAGE = "不能包含非法字符";

}
