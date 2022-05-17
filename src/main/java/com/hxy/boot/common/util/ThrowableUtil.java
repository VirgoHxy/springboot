package com.hxy.boot.common.util;

public class ThrowableUtil{

    private ThrowableUtil() {}

    /**
     * 获取以指定包名为前缀的堆栈信息
     */
    public static String getStackTrace(Throwable ex, String packagePrefix) {

        return getStackTraceFn(ex, packagePrefix);
    }

    /**
     * 获取以指定包名为前缀的堆栈信息,默认为com.hxy.boot
     */
    public static String getStackTrace(Throwable ex) {
        return getStackTraceFn(ex, "com.hxy.boot.");
    }

    public static String getStackTraceFn(Throwable ex, String packagePrefix) {
        StringBuilder s = new StringBuilder("\n").append(ex);
        for (StackTraceElement traceElement : ex.getStackTrace()) {
            if (!traceElement.getClassName().startsWith(packagePrefix)) {
                break;
            }
            s.append("\n\tat ").append(traceElement);
        }
        return s.toString();
    }
}
