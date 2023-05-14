package com.why.transportsecuritybackend.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 事故excel DTO
 *
 * @author why
 * @date 2023/05/13 12:23
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccidentExcelDTO {
    @ExcelProperty("车主姓名")
    private String vehicleOwner;
    @ExcelProperty("车牌号")
    private String vehicleNumber;
    @ExcelProperty("车辆类型")
    private String vehicleType;
    @ExcelProperty("驾驶员伤情")
    private String accidentLevel;
    @ExcelProperty("后排乘客伤情")
    private String passengerAccidentLevel;
    @ExcelProperty("事故时间")
    private String accidentDate;
    @ExcelProperty("经度")
    private Double lng;
    @ExcelProperty("纬度")
    private Double lat;
    @ExcelProperty("事故地址")
    private String address;
    @ExcelProperty("碰撞方向")
    private String direction;
    @ExcelProperty("安全气囊是否弹开")
    private String pop;
    @ExcelProperty("横向加速度")
    private List<Double> axList;
    @ExcelProperty("纵向加速度")
    private List<Double> ayList;
}
