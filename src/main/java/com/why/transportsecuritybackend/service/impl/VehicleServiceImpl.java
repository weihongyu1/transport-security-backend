package com.why.transportsecuritybackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.why.transportsecuritybackend.common.response.Result;
import com.why.transportsecuritybackend.dao.pojo.Accident;
import com.why.transportsecuritybackend.dao.pojo.Vehicle;
import com.why.transportsecuritybackend.dao.pojo.VehicleType;
import com.why.transportsecuritybackend.entity.dto.PageDTO;
import com.why.transportsecuritybackend.entity.dto.VehicleDTO;
import com.why.transportsecuritybackend.manager.AccidentManager;
import com.why.transportsecuritybackend.manager.VehicleManager;
import com.why.transportsecuritybackend.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 车辆信息业务接口实现类
 *
 * @author why
 * @date 2023/04/30 15:17
 **/
@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleManager vehicleManager;
    private final AccidentManager accidentManager;

    @Autowired
    public VehicleServiceImpl(VehicleManager vehicleManager, AccidentManager accidentManager) {
        this.vehicleManager = vehicleManager;
        this.accidentManager = accidentManager;
    }

    @Override
    public void update(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        BeanUtil.copyProperties(vehicleDTO, vehicle);
        vehicleManager.update(vehicle);
    }

    @Override
    public Result selectPage(Integer index, Integer limit) {
        List<Vehicle> vehicles = vehicleManager.selectPage(index, limit);
        Integer total = vehicleManager.getCount();
        PageDTO pageDTO = new PageDTO(total, index, limit, vehicles);
        return Result.success(pageDTO);
    }

    @Override
    public Result getVehicleTypes() {
        List<VehicleType> vehicleTypes = vehicleManager.selectAllVehicleType();
        return Result.success(vehicleTypes);
    }

    @Override
    public Result deleteVehicle(Integer id) {
        List<Accident> accidents = accidentManager.selectAccidentByVehicleId(id);
        if (!CollectionUtils.isEmpty(accidents)) {
            return Result.error("该车辆事故信息不为空，不能删除！");
        }
        vehicleManager.deleteVehicle(id);
        return Result.success();
    }
}
