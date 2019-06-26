package com.meng.student.trusteeship.dao.index;

import com.meng.student.trusteeship.entity.index.po.NationalVehicleInspectionPO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: 吴宸煊
 * @Description: 全国车辆年检 单元测试dao层
 * @Date: Created in 17:53 2018/3/27
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class NationalVehicleInspectionMapperTest {
    @Autowired
    private NationalVehicleInspectionMapper nationalVehicleInspectionMapper;

    @Test
    public void getNowExpiredInspection() {
        NationalVehicleInspectionPO nationalVehicleInspectionPO = nationalVehicleInspectionMapper.getNowExpiredInspection();
        Assert.assertEquals(new Integer("2"), nationalVehicleInspectionPO.getNumberInspection());
    }

    @Test
    public void getThirtyDaysExpiredInspection() {
        NationalVehicleInspectionPO nationalVehicleInspectionPO = nationalVehicleInspectionMapper.getThirtyDaysExpiredInspection();
        Assert.assertEquals(new Integer("4"), nationalVehicleInspectionPO.getNumberInspection());
    }

    @Test
    public void getSixtyDaysExpiredInspection() {
        NationalVehicleInspectionPO nationalVehicleInspectionPO = nationalVehicleInspectionMapper.getSixtyDaysExpiredInspection();
        Assert.assertEquals(new Integer("5"), nationalVehicleInspectionPO.getNumberInspection());
    }

}