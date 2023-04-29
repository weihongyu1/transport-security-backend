package com.why.transportsecuritybackend.common.response;

import com.why.transportsecuritybackend.common.constants.ResponseConstants;
import lombok.Data;

/**
 * 统一封装返回
 *
 * @author why
 * @date 2023/04/29 11:55
 **/
@Data
public class Result {

    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private Object data;

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 响应成功
     * @return 响应信息
     */
    public static Result success() {
        return new Result(ResponseConstants.SUCCESS, ResponseConstants.MSG_SUCCESS, null);
    }

    /**
     * 有数据的响应
     * @param data 数据
     * @return 响应信息
     */
    public static Result success(Object data) {
        return new Result(ResponseConstants.SUCCESS, ResponseConstants.MSG_SUCCESS, data);
    }

    /**
     * 响应失败
     * @return 响应信息
     */
    public static Result error() {
        return new Result(ResponseConstants.ERROR, ResponseConstants.MSG_ERROR, null);
    }

    /**
     * 响应失败
     * @param msg 响应失败信息
     * @return 响应信息
     */
    public static Result error(String msg) {
        return new Result(ResponseConstants.ERROR, msg, null);
    }

    /**
     * 响应失败
     *
     * @param code 响应状态码
     * @param msg 响应失败信息
     * @return 响应信息
     */
    public static Result error(Integer code, String msg) {
        return new Result(code, msg, null);
    }
}
