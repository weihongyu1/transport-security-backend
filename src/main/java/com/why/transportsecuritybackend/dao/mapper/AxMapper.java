package com.why.transportsecuritybackend.dao.mapper;

import com.why.transportsecuritybackend.dao.pojo.Ax;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Ax Mapper
 *
 * @author why
 * @date 2023/05/05 23:44
 **/
@Mapper
public interface AxMapper {

    String TABLE = "tbl_ax";
    String FIELDS = "id, ax, accident_id, create_time, update_time";
    String INSERT_FIELDS = "ax, accident_id";


    /**
     * 获取事故ax
     * @param accidentId 事故id
     * @return ax
     */
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "ax", property = "ax"),
            @Result(column = "accident_id", property = "accidentId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
    })
    @Select("SELECT " + FIELDS + " FROM " + TABLE + " WHERE accident_id = #{accidentId} ORDER BY create_time")
    List<Ax> selectAx(@Param("accidentId") Integer accidentId);

    /**
     * 批量新增数据
     * @param axes 横向加速度
     */
    @Insert("<script>" +
            "INSERT INTO " + TABLE + "(" + INSERT_FIELDS + ") VALUES " +
            "<foreach collection='axes' item='item' index='index' separator=','>" +
            "(#{item.ax}, #{item.accidentId})" +
            "</foreach>" +
            "</script>")
    void insertBatch(@Param("axes") List<Ax> axes);
}
