package com.why.transportsecuritybackend.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 加速度DTO
 *
 * @author why
 * @date 2023/05/08 23:03
 **/
@Data
public class AccelerationDTO {

    @JsonFormat(pattern = "HH:mm:ss", timezone="GMT+8")
    private List<LocalDateTime> date;

    private List<Double> value;
}
