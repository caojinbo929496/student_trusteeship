package com.meng.student.trusteeship.controller.traffic;


import com.meng.student.trusteeship.entity.traffic.DriverMonitor;
import com.meng.student.trusteeship.entity.traffic.VehicleMonitor;
import com.meng.student.trusteeship.service.traffic.VehicleMonitoringService;
import com.meng.student.trusteeship.entity.result.BaseResult;
import com.meng.student.trusteeship.entity.traffic.OilWearMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author: 吴宸煊
 * @Description: 车辆违章controller层
 * @Date: Created in 11:22 2018/3/17
 */
@Controller
public class VehicleMonitoringController {

    @Autowired
    private VehicleMonitoringService vehicleMonitoringService;


    @RequestMapping(value = "/oilWearList/{startTime}/{endTime}/{city}", method = RequestMethod.GET)
    public @ResponseBody
    BaseResult vehicleMonitor(@PathVariable Date startTime, @PathVariable Date endTime, @PathVariable String city) {

        List<OilWearMonitor> oilWearMonitors = vehicleMonitoringService.listOilWear(startTime, endTime, city);
        return BaseResult.getResult(oilWearMonitors);

    }


    /**
     * 根据城市查询 违章信息
     *
     * @param city
     * @return String
     */
    @RequestMapping("/vel/{city}")
    public @ResponseBody
    BaseResult list(@PathVariable String city) {
        List<VehicleMonitor> vehicleMonitors = vehicleMonitoringService.listVehicle(city);
        List<DriverMonitor> driverMonitors = vehicleMonitoringService.listDriver(city);
        Map<String, Object> map = new HashMap<>(2);
        map.put("vehicleMonitors", vehicleMonitors);
        map.put("driverMonitors", driverMonitors);
        return BaseResult.getResult(map);
    }

}
