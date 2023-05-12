package com.why.transportsecuritybackend.dao.mapper;

import com.why.transportsecuritybackend.dao.pojo.Accident;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 事故信息Mapper
 *
 * @author why
 * @date 2023/05/05 23:14
 **/
@Mapper
public interface AccidentMapper {

    String TABLE = "tbl_accident";
    String FIELDS = "id, lng, lat, vehicle_id, resolve_state, create_time, update_time";

    /**
     * 查询事故信息列表
     * @param state 处理状态
     * @param start 开始索引
     * @param limit 数量
     * @return 事故信息
     */
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "lng", property = "lng"),
            @Result(column = "lat", property = "lat"),
            @Result(column = "vehicle_id", property = "vehicleId"),
            @Result(column = "resolve_state", property = "resolveState"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Select("SELECT " + FIELDS + " FROM " + TABLE + " WHERE resolve_state = #{state} LIMIT #{start}, #{limit}")
    List<Accident> selectAccident(@Param("state") Integer state, @Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * 获取指定某天的事故数量
     * @param date 日期
     * @return 某天的事故数量
     */
    @Select("SELECT COUNT(id) FROM " + TABLE + " WHERE ( datediff ( update_time , #{date} ) = 0 )")
    Integer selectDateCount(@Param("date") LocalDate date);

    /**
     * 获取处理状态数据数量
     * @param state 状态
     * @return 数量
     */
    @Select("SELECT COUNT(id) FROM " + TABLE + " WHERE resolve_state = #{state}")
    Integer selectStateCount(@Param("state") Integer state);

    /**
     * 获取所有数据量
     * @return 数据量
     */
    @Select("SELECT COUNT(id) FROM " + TABLE)
    Integer selectCount();

    /**
     * 分页查询数据
     * @param start 开始索引
     * @param limit 数量
     * @param state 状态
     * @return 事故数据
     */
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "lng", property = "lng"),
            @Result(column = "lat", property = "lat"),
            @Result(column = "vehicle_id", property = "vehicleId"),
            @Result(column = "resolve_state", property = "resolveState"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Select("SELECT " + FIELDS + " FROM " + TABLE + " ORDER by resolve_state = #{state} DESC LIMIT #{start}, #{limit}")
    List<Accident> selectAccidentInfo(@Param("start") Integer start, @Param("limit") Integer limit, @Param("state") Integer state);

    /**
     * 根据id查询事故信息
     * @param id id
     * @return 事故信息
     */
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "lng", property = "lng"),
            @Result(column = "lat", property = "lat"),
            @Result(column = "vehicle_id", property = "vehicleId"),
            @Result(column = "resolve_state", property = "resolveState"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Select("SELECT " + FIELDS + " FROM " + TABLE + " WHERE id = #{id}")
    Accident selectAccidentById(@Param("id") Integer id);

    /**
     * 根据车辆id查询事故信息
     * @param vehicleId 车辆id
     * @return 事故信息
     */
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "lng", property = "lng"),
            @Result(column = "lat", property = "lat"),
            @Result(column = "vehicle_id", property = "vehicleId"),
            @Result(column = "resolve_state", property = "resolveState"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Select("SELECT " + FIELDS + " FROM " + TABLE + " WHERE vehicle_id = #{vehicleId}")
    List<Accident> selectAccidentByVehicleId(@Param("vehicleId") Integer vehicleId);

    /**
     * 更新事故状态
     * @param state 事故状态
     * @param accidentId 事故id
     */
    @Update("UPDATE " + TABLE + " SET resolve_state = #{state} WHERE id = #{accidentId}")
    void updateAccidentState(@Param("state") Integer state, @Param("accidentId") Integer accidentId);
}
