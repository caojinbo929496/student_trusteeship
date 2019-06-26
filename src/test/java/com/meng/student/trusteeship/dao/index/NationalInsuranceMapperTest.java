package com.meng.student.trusteeship.dao.index;

import com.meng.student.trusteeship.entity.index.po.NationalInsurancePO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: 吴宸煊
 * @Description: 车辆保险 单元测试dao层
 * @Date: Created in 17:00 2018/3/27
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class NationalInsuranceMapperTest {
    @Autowired
    private NationalInsuranceMapper nationalInsuranceMapper;

    @Test
    public void getSixthDaysExpiredInsurance() {
        NationalInsurancePO nationalInsurancePO = nationalInsuranceMapper.getSixthDaysExpiredInsurance();
        Assert.assertEquals(new Integer("2"), nationalInsurancePO.getNumberInsurance());
    }

    @Test
    public void getThirtyDaysExpiredInsurance() {
        NationalInsurancePO nationalInsurancePO = nationalInsuranceMapper.getThirtyDaysExpiredInsurance();
        Assert.assertEquals(new Integer("1"), nationalInsurancePO.getNumberInsurance());
    }

    @Test
    public void getNowExpiredInsurance() {
        NationalInsurancePO nationalInsurancePO = nationalInsuranceMapper.getNowExpiredInsurance();
        Assert.assertEquals(new Integer("0"), nationalInsurancePO.getNumberInsurance());
    }

}