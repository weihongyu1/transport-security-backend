package com.why.transportsecuritybackend.dao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 横向加速度
 *
 * @author why
 * @date 2023/04/30 01:17
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ax {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 横向加速度
     */
    private double ax;

    /**
     * 事故id
     */
    private Integer accidentId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public Ax(double ax, Integer accidentId) {
        this.ax = ax;
        this.accidentId = accidentId;
    }
}
