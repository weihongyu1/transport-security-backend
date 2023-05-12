package com.why.transportsecuritybackend.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 车辆信息DTO
 *
 * @author why
 * @date 2023/04/30 15:12
 **/
@Data
public class VehicleDTO {

    /**
     * 车辆id
     */
    private Integer id;
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

    /**
     * 车辆描述
     */
    private String vehicleDesc;
}
