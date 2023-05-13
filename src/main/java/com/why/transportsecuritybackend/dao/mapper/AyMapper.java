package com.why.transportsecuritybackend.dao.mapper;

import com.why.transportsecuritybackend.dao.pojo.Ax;
import com.why.transportsecuritybackend.dao.pojo.Ay;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Ay Mapper
 *
 * @author why
 * @date 2023/05/05 23:49
 **/
@Mapper
public interface AyMapper {
    String TABLE = "tbl_ay";
    String FIELDS = "id, ay, accident_id, create_time, update_time";
    String INSERT_FIELDS = "ay, accident_id";

    /**
     * 获取事故ax
     * @param accidentId 事故id
     * @return ay
     */
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "ay", property = "ay"),
            @Result(column = "accident_id", property = "accidentId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
    })
    @Select("SELECT " + FIELDS + " FROM " + TABLE + " WHERE accident_id = #{accidentId} ORDER BY create_time")
    List<Ay> selectAy(@Param("accidentId") Integer accidentId);

    /**
     * 批量新增数据
     * @param ays 纵向加速度
     */
    @Insert("<script>" +
            "INSERT INTO " + TABLE + "(" + INSERT_FIELDS + ") VALUES " +
            "<foreach collection='ays' item='item' index='index' separator=','>" +
            "(#{item.ay}, #{item.accidentId})" +
            "</foreach>" +
            "</script>")
    void insertBatch(@Param("ays") List<Ay> ays);
}
