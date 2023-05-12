package com.why.transportsecuritybackend.common.utils;

import com.why.transportsecuritybackend.dao.pojo.VehicleType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisUtilsTest {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    void get() {
        System.out.println(redisUtils.get("transport-user-why"));
        System.out.println(redisUtils.get("wyy123"));
    }

    @Test
    void set() {
        redisUtils.set("wyy", "456");
    }

    @Test
    void testSet() {
        redisUtils.set("wyy123", "456", 1000L);
    }

    @Test
    void sSet() {
        VehicleType vehicleType = new VehicleType();
        vehicleType.setId(1);
        vehicleType.setVehicleType("小汽车");
        VehicleType vehicleType2 = new VehicleType();
        vehicleType2.setId(2);
        vehicleType2.setVehicleType("货车");
        redisUtils.sSet("why-ss", vehicleType);
        redisUtils.sSet("why-ss", vehicleType2);
        Set<Object> members = redisUtils.members("why-ss");
        System.out.println(members);
    }
}
