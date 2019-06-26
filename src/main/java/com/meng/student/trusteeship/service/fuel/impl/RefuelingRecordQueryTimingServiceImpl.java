package com.meng.student.trusteeship.service.fuel.impl;

import com.meng.student.trusteeship.entity.fuel.FuelCard;
import com.meng.student.trusteeship.entity.fuel.GetFuelCardParams;
import com.meng.student.trusteeship.service.fuel.FuelCardManageService;
import com.meng.student.trusteeship.service.fuel.RefuelingRecordQueryTimingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

/**
 * 定时查询加油卡交易记录的定时器
 *
 * @author weiyangjun
 * @date 2018/03/30 11:13
 */
@Component
public class RefuelingRecordQueryTimingServiceImpl implements RefuelingRecordQueryTimingService {
    Logger LOGGER = LoggerFactory.getLogger(RefuelingRecordQueryTimingServiceImpl.class);

    @Autowired
    private FuelCardManageService fuelCardManageService;

    /**
     * 查询最近两天的加油记录
     */
    @Override
    @Scheduled(cron = "${sinopeQuery.pattern}")
    public void querySinopeRefuelRecordPerTime() {
        LOGGER.info("加油卡定时查询任务开始");
        Calendar calendar = Calendar.getInstance();
        String startTime = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + (calendar.get(Calendar.DAY_OF_MONTH) - 2);
        String endTime = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
        List<FuelCard> fuelCardList = fuelCardManageService.getFuelCardWithMultipleParams(new GetFuelCardParams());
        for (int i = 0; i < fuelCardList.size(); i++) {
            fuelCardManageService.fuelCardQueryFromCardMerchant(fuelCardList.get(i), startTime, endTime);
        }
        LOGGER.info("加油卡定时查询任务结束");
    }

}
