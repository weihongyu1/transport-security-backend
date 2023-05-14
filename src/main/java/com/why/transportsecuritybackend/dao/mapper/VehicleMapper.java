package com.why.transportsecuritybackend.dao.mapper;

import com.why.transportsecuritybackend.dao.pojo.Vehicle;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 车辆信息mapper
 *
 * @author why
 * @date 2023/04/30 01:38
 **/
@Mapper
public interface VehicleMapper {

    String TABLE = "tbl_vehicle";
    String FIELDS = "id, vehicle_number, vehicle_owner, vehicle_type, vehicle_desc, create_time, update_time";
    String INSERT_FIELDS = "vehicle_number, vehicle_owner, vehicle_type, vehicle_desc";

    /**
     * 新增/更新车辆信息
     * @param vehicle 车辆信息
     */
    @Insert(
            "INSERT INTO " + TABLE + "(" + INSERT_FIELDS + ") " +
            "VALUES (#{vehicle.vehicleNumber}, #{vehicle.vehicleOwner}, #{vehicle.vehicleType}, #{vehicle.vehicleDesc})"
    )
    void insert(@Param("vehicle") Vehicle vehicle);

    /**
     * 更新车辆信息
     * @param vehicle 车辆信息
     */
    @Update("UPDATE " + TABLE + " " +
            "SET vehicle_owner = #{vehicle.vehicleOwner}, vehicle_type = #{vehicle.vehicleType}, vehicle_desc = #{vehicle.vehicleDesc} " +
            "WHERE id=#{vehicle.id}")
    void update(@Param("vehicle") Vehicle vehicle);

    /**
     * 分页查询
     * @param start 开始索引
     * @param limit 查询数量
     * @return 车辆信息
     */
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "vehicleNumber", column = "vehicle_number"),
            @Result(property = "vehicleOwner", column = "vehicle_owner"),
            @Result(property = "vehicleType", column = "vehicle_type"),
            @Result(property = "vehicleDesc", column = "vehicle_desc"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    @Select("SELECT " + FIELDS + " FROM " + TABLE + " LIMIT #{start}, #{limit}")
    List<Vehicle> selectPage(@Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * 根据id查询车辆信息
     * @param id id
     * @return 车辆信息
     */
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "vehicleNumber", column = "vehicle_number"),
            @Result(property = "vehicleOwner", column = "vehicle_owner"),
            @Result(property = "vehicleType", column = "vehicle_type"),
            @Result(property = "vehicleDesc", column = "vehicle_desc"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    @Select("SELECT " + FIELDS + " FROM " + TABLE + " WHERE id = #{id}")
    Vehicle selectById(@Param("id") Integer id);

    /**
     * 根据车牌号查询车辆信息
     * @param vehicleNumber 车牌号
     * @return 车辆信息
     */
    @Select("SELECT id FROM " + TABLE + " WHERE vehicle_number = #{vehicleNumber}")
    Integer selectByVehicleNumber(@Param("vehicleNumber") String vehicleNumber);

    /**
     * 查询数据总量
     * @return
     */
    @Select("SELECT COUNT(id) FROM " + TABLE)
    Integer getCount();

    /**
     * 根据id删除车辆信息
     * @param id id
     */
    @Delete("DELETE FROM " + TABLE + " WHERE id = #{id}")
    void delete(@Param("id") Integer id);
}
