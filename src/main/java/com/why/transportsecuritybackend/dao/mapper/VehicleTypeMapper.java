package com.why.transportsecuritybackend.dao.mapper;

import com.why.transportsecuritybackend.dao.pojo.VehicleType;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 车辆类型mapper
 *
 * @author why
 * @date 2023/04/30 15:33
 **/
@Mapper
public interface VehicleTypeMapper {

    String TABLE = "tbl_vehicle_type";
    String FIELDS = "id, vehicle_type, create_time, update_time";

    /**
     * 查询所有车辆类型
     * @return 车辆类型
     */
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "vehicleType", column = "vehicle_type"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    @Select("SELECT " + FIELDS + " FROM " + TABLE)
    List<VehicleType> selectVehicleType();

    /**
     * 根据id查询车辆类型
     * @param id id
     * @return 车辆类型
     */
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "vehicleType", column = "vehicle_type"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    @Select("SELECT " + FIELDS + " FROM " + TABLE + " WHERE id = #{id}")
    VehicleType selectById(@Param("id") Integer id);
}
