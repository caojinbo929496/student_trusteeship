package com.meng.student.trusteeship.service.fuel.impl;

import com.meng.student.trusteeship.dao.fuel.RefuelingRecordMapper;
import com.meng.student.trusteeship.entity.fuel.RefuelingRecord;
import com.meng.student.trusteeship.entity.fuel.RefuelingRecordDTO;
import com.meng.student.trusteeship.entity.fuel.RefuelingRecordForRefuelingRecordPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.meng.student.trusteeship.service.fuel.AndroidInterfaceService;

import java.util.List;

/**
 * 安卓客户端http接口的服务类
 *
 * @author weiyangjun
 * @date 2018/03/21 11:13
 */
@Service
public class AndroidInterfaceServiceImpl implements AndroidInterfaceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AndroidInterfaceServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RefuelingRecordMapper refuelingRecordMapper;

    @Override
    public RefuelingRecordDTO androidforRefuelingRecordDTO(String carNumber) {

        //查询获得最新的一条加油记录
        RefuelingRecord refuelingRecord = refuelingRecordMapper.getARefuelingRecordByBundlePlateNumber(carNumber);
        //如果没有记录，返回null
        if (null == refuelingRecord) {
            return null;
        }

        //如果有记录，转成DTO类
        RefuelingRecordDTO refuelingRecordDTO = new RefuelingRecordDTO();
        refuelingRecordDTO.setTradingMoney(refuelingRecord.getTradingMoney());
        refuelingRecordDTO.setTradingTime(refuelingRecord.getTradingTime().getTime());
        refuelingRecordDTO.setMilege(refuelingRecord.getCurrentMileage());

        return refuelingRecordDTO;
    }


    @Override
    public List<RefuelingRecordForRefuelingRecordPage> getRefuelingRecordForAndroidWithCityAndWarehouse(String city, String warehouse) {
        List<RefuelingRecordForRefuelingRecordPage> refuelingRecordForRefuelingRecordPageList = refuelingRecordMapper.getRefuelingRecordForAndroidWithCityAndWarehouse(city, warehouse);
        RefuelingRecordForRefuelingRecordPage perRefuelingRecordForRefuelingRecordPage = null;
        for (int i = 0; i < refuelingRecordForRefuelingRecordPageList.size(); i++) {
            perRefuelingRecordForRefuelingRecordPage = refuelingRecordForRefuelingRecordPageList.get(i);

            perRefuelingRecordForRefuelingRecordPage.setLastMileage(refuelingRecordMapper.getLastMileageByFalseTradingTime(perRefuelingRecordForRefuelingRecordPage.getFalseRefuelingTime(), perRefuelingRecordForRefuelingRecordPage.getCarNumber()));
        }
        return refuelingRecordForRefuelingRecordPageList;
    }
}
