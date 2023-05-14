package com.why.transportsecuritybackend.manager;

import com.why.transportsecuritybackend.dao.mapper.AccidentMapper;
import com.why.transportsecuritybackend.dao.pojo.Accident;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * 事故信息管理类
 *
 * @author why
 * @date 2023/05/05 23:19
 **/
@Component
public class AccidentManager {

    private final AccidentMapper accidentMapper;

    public AccidentManager(AccidentMapper accidentMapper) {
        this.accidentMapper = accidentMapper;
    }

    /**
     * 分页查询事故信息
     * @param state 状态
     * @param index 当前页
     * @param limit 数量
     * @return 事故信息
     */
    public List<Accident> selectAccident(Integer state, Integer index, Integer limit) {
        Integer start = (index - 1) * limit;
        return accidentMapper.selectAccident(state, start, limit);
    }

    /**
     * 获取指定某天的事故数量
     * @param date 时间
     * @return 事故数量
     */
    public Integer selectDateCount(LocalDate date) {
        return accidentMapper.selectDateCount(date);
    }

    /**
     * 获取处理状态数据数量
     * @param state 状态
     * @return 数量
     */
    public Integer selectStateCount(Integer state) {
        return accidentMapper.selectStateCount(state);
    }

    /**
     * 获取处理状态数据数量
     * @return 数量
     */
    public Integer selectCount() {
        return accidentMapper.selectCount();
    }

    /**
     * 查询事故信息
     * @param index 当前页
     * @param limit 数量
     * @param state 状态
     * @return 事故信息
     */
    public List<Accident> selectAccidentInfo(Integer index, Integer limit, Integer state) {
        Integer start = (index - 1) * limit;
        return accidentMapper.selectAccidentInfo(start, limit, state);
    }

    /**
     * 根据id查询事故信息
     * @param id id
     * @return 事故信息
     */
    public Accident selectAccidentById(Integer id) {
        return accidentMapper.selectAccidentById(id);
    }

    /**
     * 更新事故状态
     * @param state 事故状态
     * @param accidentId 事故id
     */
    public void updateAccidentState(Integer state, Integer accidentId) {
        accidentMapper.updateAccidentState(state, accidentId);
    }

    /**
     * 根据车辆id查询事故信息
     * @param vehicleId 车辆id
     * @return 事故信息
     */
    public List<Accident> selectAccidentByVehicleId(Integer vehicleId) {
        return accidentMapper.selectAccidentByVehicleId(vehicleId);
    }

    /**
     * 新增事故信息
     * @param accident 事故数据
     * @return id
     */
    public Integer insertAccident(Accident accident) {
        return accidentMapper.insert(accident);
    }
}
