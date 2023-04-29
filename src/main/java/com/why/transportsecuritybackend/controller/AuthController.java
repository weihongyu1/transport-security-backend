package com.why.transportsecuritybackend.controller;

import com.why.transportsecuritybackend.common.response.Result;
import com.why.transportsecuritybackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 权限验证controller
 *
 * @author why
 * @date 2023/04/29 14:06
 **/
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 登录接口
     * @param account 用户名
     * @param password 密码
     * @return 响应信息，登录成功返回token
     */
    @GetMapping("/login")
    public Result login(@RequestParam("account") String account, @RequestParam("password") String password) {
        return userService.login(account, password);
    }

    /**
     * 注册
     * @param account 用户名
     * @param password 密码
     * @return 注册成功返回200状态
     */
    @GetMapping("/register")
    public Result register(@RequestParam("account") String account, @RequestParam("password") String password) {
        return userService.register(account, password);
    }
}
