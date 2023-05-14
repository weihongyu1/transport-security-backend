package com.why.transportsecuritybackend.manager;

import cn.hutool.core.util.ObjectUtil;
import com.why.transportsecuritybackend.dao.mapper.VehicleMapper;
import com.why.transportsecuritybackend.dao.mapper.VehicleTypeMapper;
import com.why.transportsecuritybackend.dao.pojo.Vehicle;
import com.why.transportsecuritybackend.dao.pojo.VehicleType;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 车辆信息管理类
 *
 * @author why
 * @date 2023/04/30 15:00
 **/
@Component
public class VehicleManager {

    private final VehicleMapper vehicleMapper;
    private final VehicleTypeMapper vehicleTypeMapper;

    public VehicleManager(VehicleMapper vehicleMapper, VehicleTypeMapper vehicleTypeMapper) {
        this.vehicleMapper = vehicleMapper;
        this.vehicleTypeMapper = vehicleTypeMapper;
    }

    /**
     * 车辆信息更新操作
     * @param vehicle 车辆信息
     */
    public void update(Vehicle vehicle) {
        Vehicle vehicleOrigin = vehicleMapper.selectById(vehicle.getId());
        if (ObjectUtil.isNotEmpty(vehicleOrigin)) {
            vehicleMapper.update(vehicle);
        } else {
            vehicleMapper.insert(vehicle);
        }
    }

    /**
     * 分页查询车辆西悉尼
     * @param index 当前页
     * @param limit 每页数量
     * @return 车辆信息
     */
    public List<Vehicle> selectPage(Integer index, Integer limit) {
        Integer start = (index - 1) * limit;
        return vehicleMapper.selectPage(start, limit);
    }

    /**
     * 根据id查询车辆信息
     * @param id id
     * @return 车辆信息
     */
    public Vehicle selectById(Integer id) {
        return vehicleMapper.selectById(id);
    }

    /**
     * 获取数据总量
     * @return 数据总量
     */
    public Integer getCount() {
        return vehicleMapper.getCount();
    }

    /**
     * 获取所有车辆类型数据
     * @return 车辆类型数据
     */
    public List<VehicleType> selectAllVehicleType() {
        return vehicleTypeMapper.selectVehicleType();
    }

    /**
     * 根据id删除车辆信息
     * @param id id
     */
    public void deleteVehicle(Integer id) {
        vehicleMapper.delete(id);
    }

    /**
     * 根据id查询车辆类型
     * @param id id
     * @return 车辆类型
     */
    public VehicleType selectVehicleTypeById(Integer id) {
        return vehicleTypeMapper.selectById(id);
    }

    /**
     * 根据车牌号查询车辆信息
     * @param vehicleNumber 车牌号
     * @return 车辆信息
     */
    public Integer selectVehicleByNumber(String vehicleNumber) {
        return vehicleMapper.selectByVehicleNumber(vehicleNumber);
    }
}
