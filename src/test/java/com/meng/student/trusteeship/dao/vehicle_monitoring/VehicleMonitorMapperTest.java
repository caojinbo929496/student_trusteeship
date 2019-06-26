package com.meng.student.trusteeship.dao.vehicle_monitoring;

import com.meng.student.trusteeship.dao.traffic.VehicleMonitorMapper;
import com.meng.student.trusteeship.entity.traffic.OilWearMonitor;


import com.meng.student.trusteeship.service.traffic.VehicleMonitoringService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author fengqigui
 * @description
 * @date 2018/03/24 12:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleMonitorMapperTest {

    @Autowired
    private VehicleMonitorMapper vehicleMonitorMapper;

    @Autowired
    private VehicleMonitoringService vehicleMonitoringService;

    @Test
    public void listOilWearMonitor() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("startTime", new Date(new Date().getTime() - 568800000L));
        map.put("endTime", new Date());
        map.put("city", "");
        List<OilWearMonitor> oilWearMonitors = vehicleMonitorMapper.listOilWearMonitor(map);
        Assert.assertEquals(4, oilWearMonitors.size());
        System.out.println("-----:" + oilWearMonitors);

    }

    @Test
    public void dateTest() {
        List<OilWearMonitor> oilWearMonitors = vehicleMonitoringService.listOilWear(new Date(), new Date(), "");
        Date date = new Date(new Date().getTime() - 700000000L);
        System.out.println("-----:" + date);
    }


}