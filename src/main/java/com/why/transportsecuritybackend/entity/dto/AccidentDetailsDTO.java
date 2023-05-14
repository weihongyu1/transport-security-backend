package com.why.transportsecuritybackend.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 事故详细信息DTO
 *
 * @author why
 * @date 2023/05/12 22:24
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccidentDetailsDTO {
    /**
     * 损伤程度
     */
    private String degreeInjury;

    /**
     * 安全气囊是否弹开
     */
    private String isPop;

    /**
     * 事故时间
     */
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime accidentDate;

    /**
     * 事故处理状态
     */
    private Integer accidentState;
}
