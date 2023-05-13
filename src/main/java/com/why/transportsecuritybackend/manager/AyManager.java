package com.why.transportsecuritybackend.manager;

import com.why.transportsecuritybackend.dao.mapper.AyMapper;
import com.why.transportsecuritybackend.dao.pojo.Ay;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Ay管理类
 *
 * @author why
 * @date 2023/05/05 23:51
 **/
@Component
public class AyManager {
    private final AyMapper ayMapper;

    public AyManager(AyMapper ayMapper) {
        this.ayMapper = ayMapper;
    }

    /**
     * 获取纵向加速度
     * @param accidentId 事故id
     * @return 纵向加速度
     */
    public List<Ay> selectAy(Integer accidentId) {
        return ayMapper.selectAy(accidentId);
    }

    /**
     * 批量新增数据
     * @param ays 纵向加速度
     */
    public void insertBatch(List<Ay> ays) {
        ayMapper.insertBatch(ays);
    }
}
