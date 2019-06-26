package com.meng.student.trusteeship.service.fuel;

import com.meng.student.trusteeship.entity.fuel.RefuelingRecordDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class AndroidInterfaceServiceTest {

    @Autowired
    private AndroidInterfaceService androidInterfaceService;

    @Test
    public void androidForFuelCardDTO() throws Exception {
        RefuelingRecordDTO refuelingRecordDTO = androidInterfaceService.androidforRefuelingRecordDTO("A0021");
        System.out.println(refuelingRecordDTO);
    }

}