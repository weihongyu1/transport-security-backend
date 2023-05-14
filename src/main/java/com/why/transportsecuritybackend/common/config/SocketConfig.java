package com.why.transportsecuritybackend.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 监听tcp配置
 *
 * @author why
 * @date 2023/04/29 21:14
 **/
@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "socket")
public class SocketConfig {
    private Integer port;
    private Integer poolKeep;
    private Integer poolCore;
    private Integer poolMax;
    private Integer poolQueueInit;
}
