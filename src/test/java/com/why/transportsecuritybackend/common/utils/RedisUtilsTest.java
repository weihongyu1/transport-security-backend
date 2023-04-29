package com.why.transportsecuritybackend.common.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisUtilsTest {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    void get() {
        System.out.println(redisUtils.get("transport-user-why"));
        System.out.println(redisUtils.get("wyy123"));
    }

    @Test
    void set() {
        redisUtils.set("wyy", "456");
    }

    @Test
    void testSet() {
        redisUtils.set("wyy123", "456", 1000L);
    }
}
