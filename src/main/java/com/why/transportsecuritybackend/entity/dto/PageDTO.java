package com.why.transportsecuritybackend.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * 分页数据
 *
 * @author why
 * @date 2023/04/30 14:58
 **/
@Data
public class PageDTO {

    /**
     * 数据总量
     */
    private Integer total;

    /**
     * 当前页
     */
    private Integer index;

    /**
     * 每页数量
     */
    private Integer count;

    private Object data;

    public PageDTO() {
    }

    public PageDTO(Integer total, Integer index, Integer count, Object data) {
        this.total = total;
        this.index = index;
        this.count = count;
        this.data = data;
    }
}
