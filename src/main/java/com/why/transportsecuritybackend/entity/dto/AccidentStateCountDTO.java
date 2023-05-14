package com.why.transportsecuritybackend.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 事故状态数量DTO
 *
 * @author why
 * @date 2023/05/07 14:00
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccidentStateCountDTO {
    private Integer total;
    private Integer already;
    private Integer working;
    private Integer none;
}
