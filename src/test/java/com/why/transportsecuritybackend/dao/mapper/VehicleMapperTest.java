package com.why.transportsecuritybackend.dao.mapper;

import com.why.transportsecuritybackend.dao.pojo.Vehicle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VehicleMapperTest {

    @Autowired
    private VehicleMapper vehicleMapper;

    @Test
    void selectPage() {
        List<Vehicle> vehicles = vehicleMapper.selectPage(0, 10);
        System.out.println(vehicles);
    }

    @Test
    void selectById() {
        Vehicle vehicle = vehicleMapper.selectById(3);
        System.out.println(vehicle);
    }

    @Test
    void getCount() {
    }
}
