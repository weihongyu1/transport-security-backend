package com.why.transportsecuritybackend.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数字工具类
 *
 * @author why
 * @date 2023/05/13 15:31
 **/
public class NumberUtils {

    private static final Pattern pattern = Pattern.compile("-?[0-9]+.*[0-9]*");

    /**
     * 字符串转换为double
     *
     * @param str
     * @return
     */
    public static double strToDoubleValue(String str) {
        double result = 0.0;
        if (isNum(str))
            result = Double.valueOf(str);
        return result;
    }

    /**
     * 判断是否为数字 包括正数，负数，小数
     *
     * @param str
     * @return 是纯数字则返回true
     */
    public static boolean isNum(String str) {
        boolean result = false;
        try {
            if (str != null) {
                Matcher isNum = pattern.matcher(str);
                result = isNum.matches();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
