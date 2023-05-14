package com.why.transportsecuritybackend.manager;

import com.why.transportsecuritybackend.dao.mapper.AxMapper;
import com.why.transportsecuritybackend.dao.pojo.Ax;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ax管理类
 *
 * @author why
 * @date 2023/05/05 23:48
 **/
@Component
public class AxManager {

    private final AxMapper axMapper;

    public AxManager(AxMapper axMapper) {
        this.axMapper = axMapper;
    }

    public List<Ax> selectAx(Integer accidentId) {
        return axMapper.selectAx(accidentId);
    }

    /**
     * 批量新增数据
     * @param axes 横向加速度
     */
    public void insertBatch(List<Ax> axes) {
        axMapper.insertBatch(axes);
    }
}
