package com.why.transportsecuritybackend.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 主页展示事故数据
 *
 * @author why
 * @date 2023/05/05 23:24
 **/
@Data
public class AccidentHomeDTO {

    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private LocalDateTime date;

    private String vehicleNumber;

    private String accidentLevel;

    private Integer resolveState;
}
