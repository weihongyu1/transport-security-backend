package com.why.transportsecuritybackend.controller;

import com.why.transportsecuritybackend.common.response.Result;
import com.why.transportsecuritybackend.entity.dto.VehicleDTO;
import com.why.transportsecuritybackend.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 车辆信息controller
 *
 * @author why
 * @date 2023/04/30 15:55
 **/
@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * 车辆信息更新操作
     * @param vehicleDTO 车辆信息
     * @return 响应信息
     */
    @PostMapping("/update")
    public Result update(@RequestBody VehicleDTO vehicleDTO) {
        vehicleService.update(vehicleDTO);
        return Result.success();
    }

    /**
     * 分页查询车辆信息
     * @param index 当前页
     * @param limit 每页数量
     * @return 响应信息
     */
    @GetMapping("/select")
    public Result selectPage(@RequestParam("index") Integer index, @RequestParam("limit") Integer limit) {
        return vehicleService.selectPage(index, limit);
    }

    /**
     * 获取所有车辆类型
     * @return 车辆类型数据
     */
    @GetMapping("/select_vehicle_type")
    public Result selectVehicleType() {
        return vehicleService.getVehicleTypes();
    }

    /**
     * 根据id删除
     * @param id id
     * @return 响应信息
     */
    @GetMapping("/deleteVehicle")
    public Result deleteVehicle(@RequestParam("id") Integer id) {
        return vehicleService.deleteVehicle(id);
    }
}
