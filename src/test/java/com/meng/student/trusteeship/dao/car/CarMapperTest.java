package com.meng.student.trusteeship.dao.car;

import com.meng.student.trusteeship.entity.vehicle.CarAllInfo;
import com.meng.student.trusteeship.entity.vehicle.po.CarAllInfoPO;
import com.meng.student.trusteeship.util.ConvertUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CarMapperTest {

    @Autowired
    private CarMapper carMapper;

    @Test
    public void getConut() throws Exception {
        int conut = carMapper.conutCar();
        System.out.println(conut);
    }

    @Test
    public void listCarAllInfo() {
        List<CarAllInfoPO> carAllInfoPOS = carMapper.listCarAllInfo(new HashMap());
        System.out.println(carAllInfoPOS);
    }

    @Test
    public void count() {
        Integer integer = carMapper.countCarAllInfo(new HashMap());
        System.out.println(integer);
    }

    @Test
    public void listCarAllInfoInterface() {
        Map map = new HashMap();
        map.put("city", "武汉");
        map.put("queryStatus", "1");
        List<CarAllInfo> carAllInfos = carMapper.listCarAllInfo(map).stream().map(ConvertUtils::convert).collect(Collectors.toList());
        System.out.println(carAllInfos);
    }

}