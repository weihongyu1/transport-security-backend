package com.why.transportsecuritybackend.service.impl;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.why.transportsecuritybackend.common.constants.RedisConstants;
import com.why.transportsecuritybackend.common.constants.UserConstants;
import com.why.transportsecuritybackend.common.response.Result;
import com.why.transportsecuritybackend.common.utils.JwtUtils;
import com.why.transportsecuritybackend.common.utils.RedisUtils;
import com.why.transportsecuritybackend.dao.pojo.User;
import com.why.transportsecuritybackend.dao.pojo.UserRole;
import com.why.transportsecuritybackend.manager.UserManager;
import com.why.transportsecuritybackend.service.UserService;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * 用户数据业务接口实现类
 *
 * @author why
 * @date 2023/04/29 11:47
 **/
@Service
public class UserServiceImpl implements UserService {

    private final UserManager userManager;
    private final RedisUtils redisUtils;

    public UserServiceImpl(UserManager userManager, RedisUtils redisUtils) {
        this.userManager = userManager;
        this.redisUtils = redisUtils;
    }

    @Override
    public Result register(String account, String password) {
        User userOriginal = userManager.selectUserByAccount(account);
        if (ObjectUtil.isNotEmpty(userOriginal)) {
            return Result.error(UserConstants.ACCOUNT_EXIST);
        }
        // 生成 salt
        UUID salt = UUID.randomUUID();
        // 处理密码，先加密后hash
        SymmetricCrypto sm4 = SmUtil.sm4(UserConstants.ENCRYPT_KEY.getBytes());
        String encryptPassword = sm4.encryptHex(password + salt.toString());
        // 查找普通用户角色id，默认赋值
        UserRole userRole = userManager.selectUserRole(UserConstants.DEFAULT_USER_ROLE_NAME);
        // 存储数据
        User user = new User(account, encryptPassword, salt.toString(), userRole.getId().toString());
        userManager.insertUser(user);
        return Result.success();
    }

    @Override
    public Result login(String account, String password) {
        User user = userManager.selectUserByAccount(account);
        if (ObjectUtil.isEmpty(user)) {
            return Result.error(UserConstants.ACCOUNT_OR_PASSWORD_ERROR);
        }
        // 处理密码，先加密后hash
        SymmetricCrypto sm4 = SmUtil.sm4(UserConstants.ENCRYPT_KEY.getBytes());
        String encryptPassword = sm4.encryptHex(password + user.getSalt());
        if (!encryptPassword.equals(user.getPassword())) {
            return Result.error(UserConstants.ACCOUNT_OR_PASSWORD_ERROR);
        }
        // 生成token
        String token = JwtUtils.createJWT(UserConstants.JWT_SECURITY_KEY, UserConstants.TOKEN_TIMEOUT_MILLS, account);
        // 加入redis
        String s = RedisConstants.REDIS_USER_PREFIX + account;
        redisUtils.set(RedisConstants.REDIS_USER_PREFIX + account, token, UserConstants.TOKEN_TIMEOUT_MILLS);
        return Result.success(token);
    }
}
