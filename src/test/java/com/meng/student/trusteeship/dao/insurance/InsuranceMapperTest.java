package com.meng.student.trusteeship.dao.insurance;

import com.meng.student.trusteeship.entity.vehicle.po.InsuranceAndImagesPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InsuranceMapperTest {
    @Autowired
    private InsuranceMapper insuranceMapper;

    @Test
    public void getInsuranceAndImages() throws Exception {
        List<InsuranceAndImagesPO> insuranceAndImagesPOS = insuranceMapper.getInsuranceAndImages("鄂A0021");
    }

}