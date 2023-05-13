package com.why.transportsecuritybackend.dao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 纵向加速度
 *
 * @author why
 * @date 2023/04/30 01:34
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ay {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 纵向加速度
     */
    private double ay;

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

    public Ay(double ay, Integer accidentId) {
        this.ay = ay;
        this.accidentId = accidentId;
    }
}
