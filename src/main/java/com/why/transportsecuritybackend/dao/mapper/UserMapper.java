package com.why.transportsecuritybackend.dao.mapper;

import com.why.transportsecuritybackend.dao.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户密码Mapper
 *
 * @author why
 * @date 2023/04/29 11:32
 **/
@Mapper
public interface UserMapper {
    String TABLE = "tbl_user";
    String FIELDS = "id, account, password, salt, roles";
    String INSERT_FIELDS = "account, password, salt, roles";

    /**
     * 新增用户
     * @param user 用户数据
     */
    @Insert(
            "INSERT INTO " + TABLE + " (" + INSERT_FIELDS + ") " +
            "VALUES (#{user.account}, #{user.password}, #{user.salt}, #{user.roles})"
    )
    void insert(@Param("user") User user);

    /**
     * 根据用户名查询用户数据
     * @param account 用户名
     * @return 用户数据
     */
    @Select(
            "SELECT " + FIELDS + " FROM " + TABLE + " WHERE account=#{account}"
    )
    User selectByAccount(@Param("account") String account);
}
