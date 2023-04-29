package com.why.transportsecuritybackend.dao.pojo;

import com.why.transportsecuritybackend.common.constants.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户密码数据表
 *
 * @author why
 * @date 2023/04/29 11:18
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 角色
     */
    private String roles;

    public User(String account, String password, String salt, String roles) {
        this.account = account;
        this.password = password;
        this.salt = salt;
        this.roles = roles;
    }

    public List<Integer> getRoleList() {
        List<String> rolesStr = Arrays.asList(this.roles.split(CommonConstants.COMMA_SPILT));
        return rolesStr.stream().map(Integer::parseInt).collect(Collectors.toList());
    }
}
