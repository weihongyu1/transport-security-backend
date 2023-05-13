package com.why.transportsecuritybackend.service;

import com.why.transportsecuritybackend.common.response.Result;
import com.why.transportsecuritybackend.entity.dto.AccelerationDTO;
import com.why.transportsecuritybackend.entity.dto.AccidentStateCountDTO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;

/**
 * 事故信息业务接口
 *
 * @author why
 * @date 2023/05/05 23:41
 **/
public interface AccidentService {

    /**
     * 查询事故主页展示信息
     * @param state 事故处理状态
     * @param index 当前页
     * @param limit 查询数量
     * @return 主页事故信息
     */
    Result selectAccidentHome(Integer state, Integer index, Integer limit);

    /**
     * 获取近30天的日期字符串
     * @return 30天日期字符串
     */
    List<LocalDate> get30Date();

    /**
     * 获取近n天事故数量
     * @param localDates 日期
     * @return 30天事故数量列表
     */
    List<Integer> getNCount(List<LocalDate> localDates);

    /**
     * 获取事故数据数量
     * @return 状态数据数量
     */
    AccidentStateCountDTO getStateCount();

    /**
     * 根据事故获取横向加速度
     * @param accidentId 事故id
     * @return 横向加速度
     */
    AccelerationDTO getAx(Integer accidentId);

    /**
     * 根据事故获取纵向加速度
     * @param accidentId 事故id
     * @return 纵向加速度
     */
    AccelerationDTO getAy(Integer accidentId);

    /**
     * 获取事故信息分页数据
     * @param index 当前页
     * @param limit 数量
     * @return 事故信息分页数据
     */
    Result AccidentInfo(Integer index, Integer limit);

    /**
     * 获取事故车辆信息
     * @param accidentId 事故id
     * @return 事故车辆信息
     */
    Result getAccidentVehicle(Integer accidentId);

    /**
     * 获取事故信息
     * @param accidentId 事故id
     * @return 事故信息
     */
    Result getAccidentDetails(Integer accidentId);

    /**
     * 获取事故地址信息
     * @param accidentId 事故id
     * @return 事故地址信息
     */
    Result getAccidentAddress(Integer accidentId);

    /**
     * 更新事故状态
     * @param state 事故状态
     * @param accidentId 事故id
     * @return 响应信息
     */
     Result updateAccidentState(Integer state, Integer accidentId);

    /**
     * 事故数据下载
     * @param accidentId 事故id
     * @param response http response
     */
    void downloadAccident(Integer accidentId, HttpServletResponse response);

    /**
     * 新增事故信息
     * @param info 信息
     */
    void insert(String info);
}
