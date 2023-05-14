package com.why.transportsecuritybackend.dao.pojo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 车辆类型实体类
 *
 * @author why
 * @date 2023/04/30 01:04
 **/
@Data
public class VehicleType {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 车辆类型
     */
    private String vehicleType;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
