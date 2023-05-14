package com.why.transportsecuritybackend.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 事故地址信息
 *
 * @author why
 * @date 2023/05/12 22:28
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccidentAddressDTO {
    /**
     * 经度
     */
    private Double lng;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 事故地址
     */
    private String address;
}
