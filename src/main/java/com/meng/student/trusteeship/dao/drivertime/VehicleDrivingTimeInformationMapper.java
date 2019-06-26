package com.meng.student.trusteeship.dao.drivertime;


import com.meng.student.trusteeship.entity.vehicle.po.VehicleDrivingTimeInformationPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface VehicleDrivingTimeInformationMapper {

    /**
     * 保存一条行驶记录
     *
     * @param vehicleDrivingTimeInformationPO 行驶记录基本信息
     */
    void save(VehicleDrivingTimeInformationPO vehicleDrivingTimeInformationPO);

    /**
     * 通过车牌号查询当天车辆基本状态,而且车辆
     *
     * @param carNumber 车牌号
     * @return 车辆基本信息
     */
    VehicleDrivingTimeInformationPO getTodayRecordByCarNumber(String carNumber);

    /**
     * 通过驾驶证id获取上一次行车的车辆信息
     *
     * @param patenteId 驾驶证id
     * @return 车辆行驶信息
     */
    VehicleDrivingTimeInformationPO getLastInfoByPatenteId(String patenteId);

    /**
     * 更新车辆最后的行驶记录
     *
     * @param lastInfo 车辆最后的行驶记录信息
     */
    void updateVehicleDrivingTime(VehicleDrivingTimeInformationPO lastInfo);

    /**
     * 通过车牌号和违章时间查询违章人
     *
     * @param map 车牌号和违章时间
     * @return 违章人id
     */
    String getOffenderByCarNumberAndTime(Map map);
}
