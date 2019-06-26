package com.meng.student.trusteeship.controller.fuel;

import com.meng.student.trusteeship.entity.fuel.RefuelingRecordDTO;
import com.meng.student.trusteeship.entity.fuel.RefuelingRecordForRefuelingRecordPage;
import com.meng.student.trusteeship.service.fuel.AndroidInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 给安卓端提供http接口的controller
 *
 * @author weiyangjun
 * @date 2018/03/21 14:48
 */
@RestController
public class AndroidRefuelingController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AndroidInterfaceService androidInterfaceService;

    /**
     * 给安卓端返回最新一次加油卡交易记录
     *
     * @param carNumber 车牌号
     * @return RefuelingRecordDTO
     */
    @RequestMapping(value = "androidRefuelingInterface/getrefuelingrecordbyPlate/{carNumber}", method = RequestMethod.GET)
    public RefuelingRecordDTO refuelRecord(@PathVariable("carNumber") String carNumber) {
        return androidInterfaceService.androidforRefuelingRecordDTO(carNumber);
    }

    /**
     * 通过城市和仓库为参数查询特定加油记录给安卓端
     *
     * @param city      城市
     * @param warehouse 仓库
     * @return 返回给安卓端的加油记录列表
     */
    @RequestMapping(value = "androidRefuelingInterface/getRefuelingRecordForAndroidWithCityAndWarehouse/{city}/{warehouse}", method = RequestMethod.GET)
    List<RefuelingRecordForRefuelingRecordPage> getRefuelingRecordForAndroidWithCityAndWarehouse(@PathVariable("city") String city, @PathVariable("warehouse") String warehouse) {
        return androidInterfaceService.getRefuelingRecordForAndroidWithCityAndWarehouse(city, warehouse);
    }

}
