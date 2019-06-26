package com.meng.student.trusteeship.service.vehicle.impl;

import com.meng.student.trusteeship.entity.vehicle.Car;
import com.meng.student.trusteeship.entity.vehicle.dto.ManagerCarSendDTO;
import com.meng.student.trusteeship.entity.vehicle.dto.ManegerCarAcceptDTO;
import com.meng.student.trusteeship.service.vehicle.CarService;
import com.meng.student.trusteeship.util.UuidUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceImplTest {

    @Autowired
    private CarService carService;

    @Test
    public void saveCar() throws Exception {
        int i = 0;
        int j = 1;
        for (i = 0; i < j; i++) {
            Car car = new Car();
            car.setBrandModelNumber("五菱002" + i);
            car.setCarNumber("鄂B992" + i);
            car.setCity("大冶");
            car.setEngineNumber("111111");
            car.setFrameNumber("222222222222222222");
            car.setId(UuidUtils.getUuid() + i);
            car.setOwners("王老五");
            car.setRegistrationTime(new Date("2009/1/1"));
            car.setValidityDate(new Date());
            car.setStatus(false);
            car.setVehiclePrice(BigDecimal.valueOf(9999.99));
            car.setVehicleType("货车");
            car.setWarehouse("仓库一");
            car.setAddress("地址");
            car.setNatureOfUse("natureOfUse");
            car.setDareOfIssue(new Date());
            carService.saveCar(car);
        }
    }

    @Test
    public void getCarById() throws Exception {
        Car car = carService.getCarById("123456789");
    }

    @Test
    public void listWarehouseInfo() throws Exception {
        ManagerCarSendDTO managerCarSendDTO = new ManagerCarSendDTO();
        managerCarSendDTO.setCity("武汉");
        managerCarSendDTO.setWarehouse("仓库一");
        List<ManegerCarAcceptDTO> manegerCarAcceptDTOS = carService.listWarehouseInfo(managerCarSendDTO);

    }

    @Test
    public void listCar() throws Exception {
        String s = String.valueOf(null);
        System.out.println(s.length());
    }

    @Test
    public void getCarAllInfo() throws Exception {
        Map map = carService.getCarAllInfo("鄂A0021");
        System.out.println(map);
    }

    @Test
    public void redisTest(){
        RedisTemplate redisTemplate = new RedisTemplate();
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put("a", "b", "c");
    }

}