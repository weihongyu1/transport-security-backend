package com.why.transportsecuritybackend.common.constants;

/**
 * 响应相关常量
 *
 * @author why
 * @date 2023/04/29 11:57
 **/
public final class ResponseConstants {

    public static final Integer SUCCESS = 200;
    public static final Integer ERROR = 500;
    public static final Integer AUTH_ERROR = 403;

    public static final String MSG_SUCCESS = "success";
    public static final String MSG_ERROR = "error";
    public static final String MSG_AUTH_ERROR = "auth error";

    private ResponseConstants() {
    }
}
