package com.why.transportsecuritybackend.controller;

import com.why.transportsecuritybackend.common.response.Result;
import com.why.transportsecuritybackend.entity.dto.AccidentTrendDTO;
import com.why.transportsecuritybackend.service.AccidentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 事故信息controller
 *
 * @author why
 * @date 2023/05/06 21:08
 **/
@RestController
@RequestMapping("/accident")
public class AccidentController {

    private final AccidentService accidentService;

    @Autowired
    public AccidentController(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    /**
     * 获取首页展示的事故数据
     * @param state 处理状态
     * @param index 当前页
     * @param limit 数量
     * @return 首页展示的事故数据
     */
    @GetMapping("/home")
    public Result accidentHome(
            @Param("state") Integer state,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    ) {
        return accidentService.selectAccidentHome(state, index, limit);
    }

    /**
     * 获取趋势数据
     * @return 趋势数据
     */
    @GetMapping("/trend")
    public Result getAccidentTrend() {
        List<LocalDate> date = accidentService.get30Date();
        List<Integer> nCount = accidentService.getNCount(date);
        AccidentTrendDTO accidentTrendDTO = new AccidentTrendDTO(date, nCount);
        return Result.success(accidentTrendDTO);
    }

    /**
     * 获取状态数据
     * @return 状态数据
     */
    @GetMapping("/state")
    public Result getAccidentState() {
        return Result.success(accidentService.getStateCount());
    }

    /**
     * 获取横向加速度
     * @param accidentId 事故id
     * @return ax
     */
    @GetMapping("/ax")
    public Result getAx(@RequestParam("accidentId") Integer accidentId) {
        return Result.success(accidentService.getAx(accidentId));
    }

    /**
     * 获取横向加速度
     * @param accidentId 事故id
     * @return ay
     */
    @GetMapping("/ay")
    public Result getAy(@RequestParam("accidentId") Integer accidentId) {
        return Result.success(accidentService.getAy(accidentId));
    }

    /**
     * 获取事故信息分页数据
     * @param index 当前页
     * @param limit 数量
     * @return 响应信息
     */
    @GetMapping("/getPage")
    public Result getAccidentInfo(@RequestParam("index") Integer index, @RequestParam("limit") Integer limit) {
        return accidentService.AccidentInfo(index, limit);
    }

    /**
     * 获取事故车辆信息
     * @param accidentId 事故id
     * @return 事故车辆信息
     */
    @GetMapping("/getAccidentVehicle")
    public Result getAccidentVehicle(@RequestParam("accidentId") Integer accidentId) {
        return accidentService.getAccidentVehicle(accidentId);
    }

    /**
     * 获取事故地址信息
     * @param accidentId 事故id
     * @return 事故地址信息
     */
    @GetMapping("/getAccidentDetails")
    public Result getAccidentDetails(@RequestParam("accidentId") Integer accidentId) {
        return accidentService.getAccidentDetails(accidentId);
    }

    /**
     * 获取事故地址信息
     * @param accidentId 事故id
     * @return 事故地址信息
     */
    @GetMapping("/getAccidentAddress")
    public Result getAccidentAddress(@RequestParam("accidentId") Integer accidentId) {
        return accidentService.getAccidentAddress(accidentId);
    }

    /**
     * 更新事故状态
     * @param state 状态
     * @return 响应信息
     */
    @GetMapping("/updateState")
    public Result updateAccidentState(@RequestParam("state") Integer state, @Param("accdientId") Integer accidentId) {
        return accidentService.updateAccidentState(state,accidentId);
    }
}
