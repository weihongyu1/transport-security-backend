package com.why.transportsecuritybackend.dao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 事故信息
 *
 * @author why
 * @date 2023/04/30 01:08
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accident {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 经度
     */
    private double lng;

    /**
     * 纬度
     */
    private double lat;

    /**
     * 车辆信息id
     */
    private Integer vehicleId;

    /**
     * 处理状态
     */
    private Integer resolveState;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public Accident(double lng, double lat, Integer vehicleId, Integer resolveState) {
        this.lng = lng;
        this.lat = lat;
        this.vehicleId = vehicleId;
        this.resolveState = resolveState;
    }
}
