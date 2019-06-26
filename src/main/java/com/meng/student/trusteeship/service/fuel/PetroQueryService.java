package com.meng.student.trusteeship.service.fuel;

import com.meng.student.trusteeship.entity.fuel.FuelCard;

/**
 * 中石化查询服务接口
 *
 * @author weiyangjun
 * @date 2018/03/21 11:13
 */
public interface PetroQueryService {


    /**
     * 获取中石油首页
     *
     * @return 0号位置是交易记录，1号位置是圈存记录
     */
    String[] petroQueryWithDay(FuelCard fuelCard, String startTime, String endTime);
}
