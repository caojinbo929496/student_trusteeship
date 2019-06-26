package com.meng.student.trusteeship.config;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meng.student.trusteeship.constant.ViolationConstant;
import com.meng.student.trusteeship.dao.patente.PatenteMapper;
import com.meng.student.trusteeship.entity.patente.PatentePO;
import com.meng.student.trusteeship.entity.vehicle.Car;
import com.meng.student.trusteeship.entity.vehicle.Violation;
import com.meng.student.trusteeship.service.vehicle.CarService;
import com.meng.student.trusteeship.service.violation.ViolationService;
import com.meng.student.trusteeship.util.fuel.HttpUtils;
import com.meng.student.trusteeship.dao.drivertime.VehicleDrivingTimeInformationMapper;
import com.meng.student.trusteeship.util.UuidUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 车辆违章定点查询
 */
@Configuration
public class ViolationQuartz {
    @Autowired
    private CarService carService;

    @Autowired
    private ViolationService violationService;

    @Autowired
    private VehicleDrivingTimeInformationMapper vehicleDrivingTimeInformationMapper;

    @Autowired
    private PatenteMapper patenteMapper;

    @Autowired
    private YiYuanConf yiYuanConf;


    @Autowired
    private ObjectMapper objectMapper;

    public static final Logger LOGGER = LoggerFactory.getLogger(ViolationQuartz.class);

    @Scheduled(cron = "${violation.pattern}")
    public void queryViolation() {
        LOGGER.info("违章定时查询任务开始");
        List<Car> cars = carService.listAll();
        for (Car car : cars) {
            List<Violation> violations = query(car);
            violationService.updateAllStatus(car.getCarNumber());
            save(violations);
            LOGGER.info("车牌号：{}，违章定时查询任务结束", car.getCarNumber());
        }
        LOGGER.info("违章定时查询任务全部结束");
    }

    private void save(List<Violation> violations) {
        if (null == violations) {
            return;
        }
        for (Violation violation : violations) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("carNumber", violation.getCarNumber());
            map.put("time", new Timestamp(violation.getViolationTime().getTime()));
            Violation returnViolation = violationService.getViolationByCarNumberAndTime(map);
            if (null != returnViolation) {

                violationService.updateStatus(violation);
            } else {
                violation.setId(UuidUtils.getUuid());
                violationService.save(violation);
            }
        }
    }

    /**
     * 只能查询未处理的违章记录
     *
     * @param car 车辆基本信息
     * @return 车辆违章集合
     */
    public List<Violation> query(Car car) {
        String host = yiYuanConf.getHost();
        String path = yiYuanConf.getPath();
        String method = yiYuanConf.getMethod();
        String appcode = yiYuanConf.getAppcode();
        String carNumber = car.getCarNumber();
        String carId = car.getId();
        Map<String, String> headers = new HashMap<>(5);
        headers.put("Authorization", "APPCODE " + appcode);
        headers.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        Map<String, String> querys = new HashMap<>();
        Map<String, String> bodys = new HashMap<>(5);
        bodys.put("carCode", car.getFrameNumber());
        bodys.put("carEngineCode", car.getEngineNumber());
        bodys.put("carNumber", carNumber);
        String responseBody = null;
        Map<String, Object> returnResult = null;

        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            responseBody = EntityUtils.toString(response.getEntity());
            if ("".equals(responseBody) || null == responseBody) {
                return null;
            }
            returnResult = objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            LOGGER.error("车牌号：{}JSON:{}转换异常{}", car.getCarNumber(), responseBody, e);
            return null;
        }

        Map showApiResBody = (Map) returnResult.get(ViolationConstant.SHOWAPIRESBODY);
        Boolean restult = (Boolean) showApiResBody.get(ViolationConstant.FLAG);
        if (!restult) {
            String errorMsg = (String) showApiResBody.get(ViolationConstant.ERRORMSG);
            LOGGER.error("车牌号：{}违章查询失败{}", car.getCarNumber(), errorMsg);
            return null;
        }
        Integer count = (Integer) showApiResBody.get(ViolationConstant.COUNT);
        if (count.equals(0)) {
            return null;
        }
        List records = (List) showApiResBody.get(ViolationConstant.RECORDS);
        List<Violation> violations = new ArrayList<>();
        for (Object record : records) {

            try {
                Violation violation = getViolation(record, carNumber, carId);
                violations.add(violation);
            } catch (Exception e) {
                LOGGER.error("违章查询成功：获取返回的数据:{},设置到{}失败，原因{}", car.getCarNumber(), record.toString(), e);
            }
        }
        return violations;
    }

    private Violation getViolation(Object record, String carNumber, String carId) throws Exception {
        Map<String, Object> recordMap = (Map<String, Object>) record;
        Violation violation = new Violation();
        violation.setCarNumber(carNumber);
        violation.setCarId(carId);
        violation.setDeductMark(Integer.valueOf(String.valueOf(recordMap.get(ViolationConstant.DEGREE))));
        BigDecimal bigDecimal = new BigDecimal(Integer.valueOf(String.valueOf(recordMap.get(ViolationConstant.MONEY))));
        violation.setPenalty(bigDecimal);
        violation.setProcessingUnit((String) recordMap.get(ViolationConstant.DEPARTMENT));
        violation.setStatus(false);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(String.valueOf(recordMap.get(ViolationConstant.TIME)));
        violation.setViolationTime(date);
        violation.setViolationInformation(String.valueOf(recordMap.get(ViolationConstant.REASON)));
        violation.setViolationPlace(String.valueOf(recordMap.get(ViolationConstant.ADDRESS)));

        HashMap<String, Object> map = new HashMap<>();
        map.put("carNumber", carNumber);
        map.put("violationTime", new Timestamp((date.getTime())));
        String offenderId = vehicleDrivingTimeInformationMapper.getOffenderByCarNumberAndTime(map);
        if (null != offenderId) {
            PatentePO patentePO = patenteMapper.getByPrimaryKey(offenderId);
            violation.setOffenderId(offenderId);
            violation.setOffender(patentePO.getName());
        }
        return violation;
    }

}
