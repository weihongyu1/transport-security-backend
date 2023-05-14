package com.why.transportsecuritybackend.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 事故信息DTO
 *
 * @author why
 * @date 2023/05/08 23:32
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccidentInfoDTO {
    private Integer id;
    private String vehicleOwner;
    private String vehicleNumber;
    private Integer vehicleType;
    private String accidentAddress;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private LocalDateTime accidentDate;
    private Integer resolveState;
}
