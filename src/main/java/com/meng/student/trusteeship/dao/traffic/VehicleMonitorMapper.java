package com.meng.student.trusteeship.dao.traffic;


import com.meng.student.trusteeship.entity.traffic.po.DriverMonitorPO;
import com.meng.student.trusteeship.entity.traffic.po.VehicleMonitorPO;
import com.meng.student.trusteeship.entity.traffic.OilWearMonitor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: 吴宸煊
 * @Description: 车辆监控 dao层
 * @Date: Created in 10:49 2018/3/17
 */
@Mapper
public interface VehicleMonitorMapper {

    /**
     * 查找出所有的未处理的车辆违章信息
     *
     * @param
     * @return VehicleMonitoringP0
     */
    List<VehicleMonitorPO> listVehicle(@Param("city") String city);

    /**
     * 查找出所有的未处理的司机违章信息
     *
     * @param city
     * @return DriverMonitorPO
     */
    List<DriverMonitorPO> listDriver(@Param("city") String city);


    /**
     * 车辆油耗监控
     *
     * @param map 查询的时间段
     * @return
     */
    List<OilWearMonitor> listOilWearMonitor(Map<String, Object> map);


}
