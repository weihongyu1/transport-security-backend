package com.why.transportsecuritybackend.manager;

import com.why.transportsecuritybackend.dao.mapper.UserMapper;
import com.why.transportsecuritybackend.dao.mapper.UserRoleMapper;
import com.why.transportsecuritybackend.dao.pojo.User;
import com.why.transportsecuritybackend.dao.pojo.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户管理类
 *
 * @author why
 * @date 2023/04/29 11:41
 **/
@Component
public class UserManager {

    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;

    @Autowired
    public UserManager(UserMapper userMapper, UserRoleMapper userRoleMapper) {
        this.userMapper = userMapper;
        this.userRoleMapper = userRoleMapper;
    }

    /**
     * 新增用户数据
     * @param user 用户数据
     */
    public void insertUser(User user) {
        userMapper.insert(user);
    }

    /**
     * 根据用户名查询用户数据
     * @param account 用户名
     * @return 用户数据
     */
    public User selectUserByAccount(String account) {
        return userMapper.selectByAccount(account);
    }

    /**
     * 新增角色
     * @param roleName 角色名称
     */
    public void insertUserRole(String roleName) {
        userRoleMapper.insert(roleName);
    }

    /**
     * 根据用户角色查询用户角色
     * @param roleName 角色名称
     * @return 用户角色
     */
    public UserRole selectUserRole(String roleName) {
        return userRoleMapper.selectByRoleName(roleName);
    }
}
