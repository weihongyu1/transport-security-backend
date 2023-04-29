package com.why.transportsecuritybackend.controller;

import com.why.transportsecuritybackend.common.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试controller
 *
 * @author why
 * @date 2023/04/29 13:22
 **/
@RestController
@RequestMapping("/test")
public class TestController {
    private final RedisTemplate redisTemplate;

    @Autowired
    public TestController(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/save")
    public Result save(){
        String key = "why";
        String value = "wyy";
        redisTemplate.opsForValue().set(key, value);
        System.out.println(redisTemplate.opsForValue().get(key));
        return Result.success();
    }
}
