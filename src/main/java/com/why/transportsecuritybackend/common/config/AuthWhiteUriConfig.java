package com.why.transportsecuritybackend.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 权限验证白名单
 *
 * @author why
 * @date 2023/04/29 14:02
 **/
@Configuration
@ConfigurationProperties(prefix = "auth")
public class AuthWhiteUriConfig {

    private List<String> whiteUris;

    public List<String> getWhiteUris() {
        return whiteUris;
    }

    public void setWhiteUris(List<String> whiteUris) {
        this.whiteUris = whiteUris;
    }
}
