package com.why.transportsecuritybackend.dao.pojo;

import lombok.Data;

/**
 * 用户角色实体类
 *
 * @author why
 * @date 2023/04/29 12:22
 **/
@Data
public class UserRole {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;
}
