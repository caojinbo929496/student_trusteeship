package com.meng.student.trusteeship.service.mq.receive;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DriverTimeMqTest {

    @Autowired
    private DriverTimeMq driverTimeMq;

    @Test
    public void driverTime() throws Exception {
        String message = "{\"carNumber\":\"皖AT8A93\",\"patenteNumber\":\"42050419940509401X\",\"status\":0}";
        /*driverTimeMq.driverTime(message);*/
    }

}