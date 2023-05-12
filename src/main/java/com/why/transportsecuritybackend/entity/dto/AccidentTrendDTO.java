package com.why.transportsecuritybackend.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

/**
 * 事故趋势变化DTO
 *
 * @author why
 * @date 2023/05/07 11:58
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccidentTrendDTO {
    private List<LocalDate> date;
    private List<Integer> value;
}
