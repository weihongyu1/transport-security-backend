package com.why.transportsecuritybackend.common.utils.accident;

import java.util.Iterator;
import java.util.List;

/**
 * 事故信息工具类
 *
 * @author why
 * @date 2023/05/05 23:11
 **/
public final class AccidentUtils {

    /**
     * 计算vx
     *
     * @param ax 横向加速度
     * @return vx
     */
    public static double vx(List<Double> ax) {
        double vx = 0.00;
        Iterator<Double> iterator = ax.iterator();
        while (iterator.hasNext()) {
            Double next = iterator.next();
            vx = vx + next;
        }
        return vx * 0.011;
    }

    /**
     * 计算vy
     *
     * @param ay 纵向加速度
     * @return vy
     */
    public static double vy(List<Double> ay) {
        double vy = 0.00;
        Iterator<Double> iterator = ay.iterator();
        while (iterator.hasNext()) {
            Double next = iterator.next();
            vy = vy + next;
        }
        return vy * 0.021;
    }

    /**
     * 计算pdof
     *
     * @param vx 横向速度
     * @param vy 纵向速度
     * @return podf
     */
    public static double pdof(double vx, double vy) {
        if (vx == 0.00) {
            throw new RuntimeException("分母不能为零");
        }
        double pdof = 0.00;
        double temp = Math.toDegrees(Math.atan(vy / vx));
        if (vx >= 0 && vy <= 0) {
            pdof = temp + 180;
        } else if (vx >= 0 && vy >= 0) {
            pdof = temp - 180;
        } else if (vx < 0) {
            pdof = temp;
        }
        return pdof;
    }

    /**
     * 计算碰撞方向
     * 1：正面碰撞，2：左侧碰撞，3：追尾碰撞，4：右侧碰撞
     *
     * @param pdof podf
     * @return 碰撞方向
     */
    public static int direction(double pdof) {
        int flag = 0;
        if (pdof >= -45 && pdof < 45) {
            flag = 1;
        } else if (pdof >= -135 && pdof < -45) {
            flag = 2;
        } else if (pdof >= -135 && pdof < 135) {
            flag = 3;
        } else {
            flag = 4;
        }
        return flag;
    }

    /**
     * 计算驾驶员损伤程度
     *
     * @param direction 碰撞方向
     * @param isBounce  安全气囊是否弹开，true：弹开，false：未弹开
     * @return true：严重损伤，false：一般损伤
     */
    public static boolean driverDegreeOfDamage(int direction, boolean isBounce, double vx) {
        double p = 0.00;
        // 正面碰撞
        if (direction == 1) {
            // 安全气囊弹开
            if (isBounce) {
                p = 1.000 / (1.000 + Math.exp(7.91 + 0.145 * vx));
            } else {
                // 安全气囊未弹开
                p = 1.000 / (1.000 + Math.exp(5.43 + 0.145 * vx));
            }
        } else if (direction == 2) {
            if (isBounce) {
                // 安全气囊弹开
                p = 1.000 / (1.000 + Math.exp(5.134 + 0.145 * vx));
            } else {
                // 安全气囊未弹开
                p = 1.000 / (1.000 + Math.exp(2.654 + 0.145 * vx));
            }
        }

        return p > 0.189;
    }

    /**
     * 计算驾驶员伤情
     * @param ax 横向加速度
     * @param ay 纵向加速度
     * @param isBounce 是否系安全带
     * @return true：严重损伤，false：一般损伤
     */
    public static boolean driverDegreeAccidentLevel(List<Double> ax, List<Double> ay, boolean isBounce) {
        double vx = vx(ax);
        double vy = vy(ay);
        double pdof = pdof(vx, vy);
        int direction = direction(pdof);
        return driverDegreeOfDamage(direction, isBounce, vx);
    }

    /**
     * 后排乘客损伤程度
     *
     * @param vx 横向速度
     * @return true：严重损伤，false：一般损伤
     */
    public static boolean passengerDegreeOfDamage(double vx) {
        if (vx == 0.000) {
            throw new RuntimeException("分母不能为0");
        }
        double p = 1.000 / (1.000 + Math.exp(10.614 - 3.477 * (1.000 / vx) + 0.2128 * vx));
        return p > 0.29;
    }

    private AccidentUtils() {
    }
}
