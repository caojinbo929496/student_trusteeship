package com.meng.student.trusteeship.service.fuel;

/**
 * 定时查询加油卡交易记录的定时器接口
 *
 * @author weiyangjun
 * @date 2018/03/30 11:13
 */
public interface RefuelingRecordQueryTimingService {


    /**
     * 按照properties中配置的模式查询加油卡交易记录
     */
    void querySinopeRefuelRecordPerTime();

}
