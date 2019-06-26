package com.meng.student.trusteeship.dao.index;

import com.meng.student.trusteeship.entity.index.po.NationalVehicleViolationPO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: 吴宸煊
 * @Description:
 * @Date: Created in 17:34 2018/4/2
 */
public class NationalVehicleViolationMapperTest {

    @Autowired
    private NationalVehicleViolationMapper nationalVehicleViolationMapper;

    @Test
    public void getNationViolation() {
        NationalVehicleViolationPO nationalVehicleViolationPO = nationalVehicleViolationMapper.getNationViolation();
        Assert.assertEquals(new Integer("2"), nationalVehicleViolationPO.getUnTreated());
    }

    @Test
    public void getThisMonthViolation() {
        NationalVehicleViolationPO nationalVehicleViolationPO = nationalVehicleViolationMapper.getThisMonthViolation();
        Assert.assertEquals(new Integer("0"), nationalVehicleViolationPO.getUnTreated());
    }

    @Test
    public void getThirtyDays() {
        NationalVehicleViolationPO nationalVehicleViolationPO = nationalVehicleViolationMapper.getThirtyDays();
        Assert.assertEquals(new Integer("3"), nationalVehicleViolationPO.getUnTreated());
    }

    @Test
    public void getSixtyDays() {
        NationalVehicleViolationPO nationalVehicleViolationPO = nationalVehicleViolationMapper.getSixtyDays();
        Assert.assertEquals(new Integer("3"), nationalVehicleViolationPO.getUnTreated());
    }
}