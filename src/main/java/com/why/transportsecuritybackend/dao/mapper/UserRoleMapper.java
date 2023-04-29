package com.why.transportsecuritybackend.dao.mapper;

import com.why.transportsecuritybackend.dao.pojo.UserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户角色mapper
 *
 * @author why
 * @date 2023/04/29 12:23
 **/
@Mapper
public interface UserRoleMapper {

    String TABLE = "tbl_user_role";
    String FIELDS = "id, role_name";
    String INSERT_FIELDS = "role_name";

    /**
     * 新增角色名称
     * @param roleName 角色名称
     */
    @Insert(
            "INSERT INTO " + TABLE + " (" + INSERT_FIELDS + ") VALUES (#{roleName})"
    )
    void insert(@Param("roleName") String roleName);

    /**
     * 根据角色名称查找用户角色
     * @param roleName 角色名称
     * @return 用户角色
     */
    @Select(
            "SELECT " + FIELDS + " FROM " + TABLE + " WHERE role_name = #{roleName}"
    )
    UserRole selectByRoleName(@Param("roleName") String roleName);
}
