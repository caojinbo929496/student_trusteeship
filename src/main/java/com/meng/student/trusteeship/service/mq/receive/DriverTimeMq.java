package com.meng.student.trusteeship.service.mq.receive;


import com.google.gson.Gson;
import com.meng.student.trusteeship.dao.car.CarMapper;
import com.meng.student.trusteeship.dao.patente.PatenteMapper;
import com.meng.student.trusteeship.dao.drivertime.VehicleDrivingTimeInformationMapper;
import com.meng.student.trusteeship.entity.patente.PatentePO;
import com.meng.student.trusteeship.entity.vehicle.SimpleDriverTimeInfo;
import com.meng.student.trusteeship.entity.vehicle.VehicleDrivingTimeInformation;
import com.meng.student.trusteeship.entity.vehicle.po.CarPO;
import com.meng.student.trusteeship.entity.vehicle.po.VehicleDrivingTimeInformationPO;
import com.meng.student.trusteeship.util.ConvertUtils;
import com.meng.student.trusteeship.util.UuidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Date;

@Component
public class DriverTimeMq {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverTimeMq.class);

    @Autowired
    private Gson gson;

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private VehicleDrivingTimeInformationMapper vehicleDrivingTimeInformationMapper;

    @Autowired
    private PatenteMapper patenteMapper;

    /**
     * 传入车牌号和驾驶人id记录车辆驾驶记录
     *
     * @param simpleDriverTimeInfo 上车下车记录的基本信息
     */
    @RabbitListener(queues = "${mq.trading.carManagement.driverTime}")
    public void driverTime(Message simpleDriverTimeInfo) {
        try {
            String message = new String(simpleDriverTimeInfo.getBody(), "UTF-8");
            SimpleDriverTimeInfo driverTimeInfo = gson.fromJson(message, SimpleDriverTimeInfo.class);
            String carNumber = driverTimeInfo.getCarNumber();
            String patenteNumber = driverTimeInfo.getPatenteNumber();
            Byte status = driverTimeInfo.getStatus();
            Assert.hasLength(carNumber, "车牌号不能为空");
            Assert.hasLength(patenteNumber, "驾驶证号不能为空");
            Assert.hasLength(String.valueOf(status), "驾驶员ID不能为空");
            //状态0：表示上车 ; 1: 表示下车
            PatentePO patentePO = patenteMapper.getByNumber(patenteNumber);
            if (null == patentePO) {
                LOGGER.info("数据库中没有该驾驶证号：{}", patenteNumber);
                return;
            }
            String patenteId = patentePO.getUuid();
            VehicleDrivingTimeInformation vehicleDrivingTimeInformation = getVehicleDrivingTimeInformation(carNumber, patenteId, patenteNumber);

            if (status == 0) {
                VehicleDrivingTimeInformationPO lastInfo = vehicleDrivingTimeInformationMapper.getLastInfoByPatenteId(patenteId);
                if (null != lastInfo && null == lastInfo.getStopTime()) {
                    lastInfo.setStopTime(new Date());
                    vehicleDrivingTimeInformationMapper.updateVehicleDrivingTime(lastInfo);
                }
                vehicleDrivingTimeInformation.setStartTime(new Date());
                vehicleDrivingTimeInformationMapper.save(ConvertUtils.convert(vehicleDrivingTimeInformation));
            }

            if (status == 1) {
                VehicleDrivingTimeInformationPO vehicleDrivingTimeInformationPO = vehicleDrivingTimeInformationMapper.getTodayRecordByCarNumber(carNumber);
                Assert.notNull(vehicleDrivingTimeInformationPO, "本次行程，车牌号:" + carNumber + "没有上车记录");

                vehicleDrivingTimeInformationPO.setStopTime(new Date());
                vehicleDrivingTimeInformationMapper.updateVehicleDrivingTime(vehicleDrivingTimeInformationPO);
            }

            LOGGER.info("车辆上车下车mq接收成功，接收字段：{}", simpleDriverTimeInfo);
        } catch (Exception e) {
            LOGGER.error("车辆上车下车mq接收发生错误，接收字段：{}；异常：{}", simpleDriverTimeInfo, e);
        }

    }

    private VehicleDrivingTimeInformation getVehicleDrivingTimeInformation(String carNumber, String patenteId, String patenteNumber) {
        CarPO carPO = carMapper.getCarByCarNumber(carNumber);
        Assert.notNull(carPO, "数据库中没有对应的车");

        VehicleDrivingTimeInformation vehicleDrivingTimeInformation = new VehicleDrivingTimeInformation();
        vehicleDrivingTimeInformation.setId(UuidUtils.getUuid());
        vehicleDrivingTimeInformation.setCarId(carPO.getId());
        vehicleDrivingTimeInformation.setCarNumber(carNumber);
        vehicleDrivingTimeInformation.setPatenteId(patenteId);
        vehicleDrivingTimeInformation.setPatenteNumber(patenteNumber);
        return vehicleDrivingTimeInformation;
    }
}
