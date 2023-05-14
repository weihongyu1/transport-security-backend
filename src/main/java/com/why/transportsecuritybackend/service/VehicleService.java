package com.why.transportsecuritybackend.service;

import com.why.transportsecuritybackend.common.response.Result;
import com.why.transportsecuritybackend.entity.dto.VehicleDTO;

/**
 * 车辆信息业务接口
 *
 * @author why
 * @date 2023/04/30 15:11
 **/
public interface VehicleService {

    /**
     * 车辆信息更新操作
     * @param vehicleDTO 车辆信息
     */
    void update(VehicleDTO vehicleDTO);

    /**
     * 分页查询车辆数据
     * @param index
     * @param limit
     * @return
     */
    Result selectPage(Integer index, Integer limit);

    /**
     * 获取所有车辆类型
     * @return 车辆类型
     */
    Result getVehicleTypes();

    /**
     * 根据id删除车辆信息
     * @param id id
     * @return 响应信息
     */
    Result deleteVehicle(Integer id);
}
