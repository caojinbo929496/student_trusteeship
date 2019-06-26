package com.meng.student.trusteeship.service.traffic.impl;

import com.meng.student.trusteeship.dao.traffic.VehicleMonitorMapper;
import com.meng.student.trusteeship.entity.traffic.DriverMonitor;
import com.meng.student.trusteeship.entity.traffic.VehicleMonitor;
import com.meng.student.trusteeship.entity.traffic.po.DriverMonitorPO;
import com.meng.student.trusteeship.entity.traffic.po.VehicleMonitorPO;
import com.meng.student.trusteeship.entity.traffic.OilWearMonitor;
import com.meng.student.trusteeship.service.traffic.VehicleMonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: 吴宸煊
 * @Description: 车辆监控 实现service接口
 * @Date: Created in 10:59 2018/3/17
 */
@Service
public class VehicleMonitoringServiceImpl implements VehicleMonitoringService {

    @Autowired
    private VehicleMonitorMapper vehicleMonitoringMapper;


    @Override
    public List<VehicleMonitor> listVehicle(String city) {
        List<VehicleMonitorPO> vehicleMonitorPOS = vehicleMonitoringMapper.listVehicle(city);
        List<VehicleMonitor> collect = vehicleMonitorPOS.stream().map(p -> this.convertToVehicleMonitorVO(p)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<DriverMonitor> listDriver(String city) {
        List<DriverMonitorPO> driverMonitorPOS = vehicleMonitoringMapper.listDriver(city);
        List<DriverMonitor> result = driverMonitorPOS.stream().map(p -> this.convertToDriverMonitorVO(p)).collect(Collectors.toList());
        return result;
    }


    @Override
    public List<OilWearMonitor> listOilWear(Date startTime, Date endTime, String city) {
        if ("null".equals(city)) {
            city = "";
        }
        if (null == endTime) {
            endTime = new Date();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("city", city);
        List<OilWearMonitor> oilWearMonitors = vehicleMonitoringMapper.listOilWearMonitor(map);
        if (null == oilWearMonitors) {
            return null;
        }
        // 拿到所有的车牌号码
        List<String> collectCarNumber = oilWearMonitors.stream().map(OilWearMonitor::getCarNumber).collect(Collectors.toList());
        // 进行统计计算
        List<OilWearMonitor> result = statistics(collectCarNumber, oilWearMonitors);
        return result;

    }

    /**
     * @param collectCarNumber 所有车牌号码的集合
     * @param oilWearMonitors  所有的数据
     * @return 计算的结果
     * @author fengqigui
     * @date 2018年03月24日 15:30:27
     */
    private List<OilWearMonitor> statistics(List<String> collectCarNumber, List<OilWearMonitor> oilWearMonitors) {

        collectCarNumber = distinctCarNumber(collectCarNumber).stream().filter(p -> p != null).collect(Collectors.toList());
        List<OilWearMonitor> resultList = new ArrayList<>();
        for (String carNumber : collectCarNumber) {
            List<OilWearMonitor> collect1 = oilWearMonitors.stream().filter(p -> p.getCarNumber() != null).filter(p -> p.getDate() != null).collect(Collectors.toList());
            List<OilWearMonitor> collect = collect1.stream().filter(p -> carNumber.equals(p.getCarNumber())).sorted(Comparator.comparing(OilWearMonitor::getDate).reversed()).collect(Collectors.toList());
            computeMileage(collect);
            computeFree(collect);
            OilWearMonitor oilWear = collect.get(0);
            resultList.add(oilWear);
        }
        return resultList;

    }

    /**
     * 去除重复的牌照号码
     *
     * @param
     * @return
     * @author fengqigui
     * @date 2018年03月24日 15:46:41
     */
    private List<String> distinctCarNumber(List<String> collect) {
        Iterator<String> iterator = collect.iterator();
        List<String> list1 = new ArrayList<>();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (!list1.contains(next)) {
                list1.add(next);
            }
        }
        return list1;
    }

    /**
     * @param collect 待计算时间内的里程数的集合
     * @return
     * @author fengqigui
     * @date 2018年03月24日 14:00:07
     */
    private void computeMileage(List<OilWearMonitor> collect) {
        if (collect.size() < 2) {
            collect.get(0).setTotalMileage(new BigDecimal("0.0"));
            return;
        }
        BigDecimal startMileage = collect.get(0).getMileage();
        BigDecimal endMileage = collect.get(collect.size() - 1).getMileage();
        BigDecimal decimal = startMileage.subtract(endMileage);
        for (OilWearMonitor oilWearMonitor : collect) {
            oilWearMonitor.setTotalMileage(decimal);
        }
    }

    /**
     * @param collect 带计算的总计价格的集合
     * @return
     * @author fengqigui
     * @date 2018年03月24日 11:42:17
     */
    private BigDecimal computeFree(List<OilWearMonitor> collect) {

        BigDecimal totalFree = new BigDecimal("0.0");
        for (int i = 1; i < collect.size(); i++) {
            totalFree = totalFree.add(collect.get(i).getFree());
        }
        for (OilWearMonitor oilWearMonitor : collect) {
            oilWearMonitor.setTotalFree(totalFree);
        }
        return totalFree;

    }


    /**
     * 司机违章（DriverMonitorPO）的PO 转换为VO
     *
     * @param driverMonitorPO
     * @return DriverMonitor
     */
    private DriverMonitor convertToDriverMonitorVO(DriverMonitorPO driverMonitorPO) {

        if (null == driverMonitorPO) {
            return null;
        }
        DriverMonitor driverMonitor = new DriverMonitor(driverMonitorPO);
        return driverMonitor;
    }


    /**
     * 车辆违章（VehicleMonitorPO）的 PO 转 VO
     *
     * @param vehicleMonitorPO
     * @return VehicleMonitor
     */
    private VehicleMonitor convertToVehicleMonitorVO(VehicleMonitorPO vehicleMonitorPO) {

        if (null == vehicleMonitorPO) {
            return null;
        }
        VehicleMonitor vehicleMonitor = new VehicleMonitor(vehicleMonitorPO);
        return vehicleMonitor;
    }


}

