package com.why.transportsecuritybackend.service.impl;

import com.why.transportsecuritybackend.common.response.Result;
import com.why.transportsecuritybackend.service.AccidentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccidentServiceImplTest {

    @Autowired
    private AccidentService accidentService;

    @Test
    void selectAccidentHome() {
        Result result = accidentService.selectAccidentHome(1, 1, 10);
        System.out.println(result);
    }

    @Test
    void downloadAccident() throws IOException {

    }
}
