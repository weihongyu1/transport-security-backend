package com.why.transportsecuritybackend.entity.dto;

import lombok.Data;

/**
 * 事故车辆信息
 *
 * @author why
 * @date 2023/05/12 22:22
 **/
@Data
public class AccidentVehicleDTO {
    /**
     * 车牌号
     */
    private String vehicleNumber;

    /**
     * 车主姓名
     */
    private String vehicleOwner;

    /**
     * 车辆类型
     */
    private Integer vehicleType;
}
