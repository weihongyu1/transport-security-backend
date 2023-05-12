package com.why.transportsecuritybackend.common.selfenum;

/**
 * 事故处理状态枚举
 *
 * @author why
 * @date 2023/05/05 23:30
 **/
public enum AccidentResolveStateEnum {

    /**
     * 处理状态枚举
     */
    NONE_STATE(0, "待处理"),
    ALREADY_STATE(1, "已处理"),
    WORKING_STATE(2, "处理中");

    private Integer code;
    private String msg;

    AccidentResolveStateEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
