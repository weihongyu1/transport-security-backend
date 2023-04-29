package com.why.transportsecuritybackend.common.filter;

import com.why.transportsecuritybackend.common.config.AuthWhiteUriConfig;
import com.why.transportsecuritybackend.common.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.stereotype.Component;

import javax.servlet.*;

/**
 * 权限验证拦截器
 *
 * @author why
 * @date 2023/04/29 13:51
 **/
@Component
public class AuthFilter extends FilterRegistrationBean<Filter> {

    private final RedisUtils redisUtils;
    private final AuthWhiteUriConfig authWhiteUriConfig;

    @Autowired
    public AuthFilter(RedisUtils redisUtils, AuthWhiteUriConfig authWhiteUriConfig) {
        this.redisUtils = redisUtils;
        this.authWhiteUriConfig = authWhiteUriConfig;
    }

    @Override
    public Filter getFilter() {
        return new AuthFilterBean(redisUtils, authWhiteUriConfig);
    }
}
