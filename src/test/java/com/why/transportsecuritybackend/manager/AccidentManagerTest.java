package com.why.transportsecuritybackend.manager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccidentManagerTest {

    @Autowired
    private AccidentManager accidentManager;

    @Test
    void selectAccident() {
    }

    @Test
    void selectDateCount() {
        Integer integer = accidentManager.selectDateCount(LocalDate.now());
        System.out.println(integer);
    }
}
