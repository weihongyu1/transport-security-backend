package com.why.transportsecuritybackend.dao.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 车辆信息实体类
 *
 * @author why
 * @date 2023/04/30 00:59
 **/
@Data
public class Vehicle {

    /**
     * 主键id
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

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime updateTime;
}
