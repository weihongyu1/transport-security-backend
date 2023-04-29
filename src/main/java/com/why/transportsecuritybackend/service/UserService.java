package com.why.transportsecuritybackend.service;

import com.why.transportsecuritybackend.common.response.Result;

/**
 * 用户数据业务接口
 *
 * @author why
 * @date 2023/04/29 11:44
 **/
public interface UserService {

    /**
     * 用户注册
     * @param account 用户名
     * @param password 密码
     * @return 响应信息
     */
    Result register(String account, String password);

    /**
     * 用户登录
     * @param account 用户名
     * @param password 密码
     * @return 响应信息
     */
    Result login(String account, String password);
}
