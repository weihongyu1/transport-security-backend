package com.why.transportsecuritybackend.common.constants;

/**
 * 用户权限相关常量
 *
 * @author why
 * @date 2023/04/29 14:09
 **/
public final class UserConstants {
    public static final String ENCRYPT_KEY = "BRYSJhhrhl@!hsiu";

    public static final String JWT_SECURITY_KEY = "MYJSHbjwqtbZtSgQjjjxxxssshhhnnn@!";

    public static final long TOKEN_TIMEOUT_MILLS = 1000 * 60 * 60 * 24L;

    public static final String ACCOUNT_EXIST = "用户名已存在";

    public static final String ACCOUNT_OR_PASSWORD_ERROR = "用户名或密码错误";

    public static final String DEFAULT_USER_ROLE_NAME = "user";

    private UserConstants() {
    }
}
