package com.meng.student.trusteeship.service.mq.receive;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.meng.student.trusteeship.dao.car.CarMapper;
import com.meng.student.trusteeship.entity.vehicle.Car;
import com.meng.student.trusteeship.entity.vehicle.dto.CarDTO;
import com.meng.student.trusteeship.entity.vehicle.po.CarPO;
import com.meng.student.trusteeship.util.ConvertUtils;
import com.meng.student.trusteeship.util.UuidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;

@Component
public class VehicleInfoMq {

    private final static Logger LOGGER = LoggerFactory.getLogger(VehicleInfoMq.class);


    @Autowired
    private CarMapper carMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = {"${mq.trading.carManagement.vehicle}"})
    public void getVehicleInfo(Message vehicleInfo) {
        try {
            String message = new String(vehicleInfo.getBody(), "UTF-8");
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss aa", Locale.ENGLISH);
            objectMapper.setDateFormat(dateFormat);
            CarDTO carDTO = objectMapper.readValue(message, CarDTO.class);
            checkCar(carDTO);
            Car car = ConvertUtils.convert(carDTO);
            CarPO carPO = ConvertUtils.convert(car);
            String carNumber = carPO.getCarNumber();

            CarPO carCheck = carMapper.getCarByCarNumber(carNumber);
            if (carPO.getRegistrationTime() == null) {
                carPO.setRegistrationTime(carDTO.getRegistrationTime());
            }
            if (null != carCheck) {
                changeStatus(carNumber);
            }
            carPO.setId(UuidUtils.getUuid());
            carMapper.insertCar(carPO);
            LOGGER.info("车辆更新消费成功，消息：{}", vehicleInfo);
        } catch (Exception e) {
            LOGGER.error("车辆更新消费失败，消息：{}， 异常:{}", vehicleInfo, e);
        }
    }

    private void changeStatus(String carNumber) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("carNumber", carNumber);
        map.put("annualInspectionStatus", "已过期");
        carMapper.changeYearCheckStatus(map);
    }

    private void checkCar(CarDTO carDTO) {
        Assert.notNull(carDTO, "车辆基本状态不能为空");
        Assert.hasLength(carDTO.getCity(), "车辆所属城市不能为空");
        Assert.hasLength(carDTO.getLicensePlate(), "车牌号不能为空");
        Assert.hasLength(carDTO.getWarehouse(), "车辆所属仓库不能为空");
        Assert.notNull(carDTO.getCarClass(), "车辆类型不能为空");
        Assert.notNull(carDTO.getState(), "车辆状态不能为空");
        Assert.hasLength(carDTO.getEngineNo(), "车辆引擎号不能为空");
        Assert.hasLength(carDTO.getVin(), "车辆识别号不能为空");

        if (null != carDTO.getRegistrationTime()) {
            Assert.notNull(carDTO.getRegistrationTime(), "行驶证注册日期不能为空");
            Assert.notNull(carDTO.getBrandModelNumber(), "行驶证车标不能为空");
            Assert.notNull(carDTO.getValidityDate(), "行驶证有效日期不能为空");
            Assert.hasLength(carDTO.getCarOwner(), "行驶证车主不能为空");
            Assert.hasLength(carDTO.getNatureOfUse(), "行驶证使用性质");
        }
    }
}
