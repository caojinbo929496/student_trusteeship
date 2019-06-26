package com.meng.student.trusteeship.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author fengqigui
 * @description
 * @date 2018/04/23 09:55
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PatenteQuartzTest {

    @Autowired
    private PatenteQuartz patenteQuartz;

    @Test
    public void updatePatente() throws Exception {
        patenteQuartz.updatePatente();
    }

}