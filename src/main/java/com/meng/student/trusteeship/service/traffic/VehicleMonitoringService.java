package com.meng.student.trusteeship.service.traffic;


import com.meng.student.trusteeship.entity.traffic.DriverMonitor;
import com.meng.student.trusteeship.entity.traffic.VehicleMonitor;
import com.meng.student.trusteeship.entity.traffic.OilWearMonitor;

import java.util.Date;
import java.util.List;

/**
 * @Author: 吴宸煊
 * @Description: 车辆监控 service接口
 * @Date: Created in 10:59 2018/3/17
 */
public interface VehicleMonitoringService {

    /**
     * 查询所有的  车辆违章信息
     *
     * @param city
     * @return List
     */
    List<VehicleMonitor> listVehicle(String city);

    /**
     * 查询所有的 司机违章信息
     *
     * @param city
     * @return List
     */
    List<DriverMonitor> listDriver(String city);


    /**
     * 车辆油耗监控
     *
     * @param startTime 起止时间
     * @param endTime   结束时间
     * @return 统计的list的集合
     * @afengqiguigqigui listOilWear2018年03月24日年14:30:220:16:07
     */
    List<OilWearMonitor> listOilWear(Date startTime, Date endTime, String city);


}
