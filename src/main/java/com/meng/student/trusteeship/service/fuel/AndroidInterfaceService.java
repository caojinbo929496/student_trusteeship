package com.meng.student.trusteeship.service.fuel;

import com.meng.student.trusteeship.entity.fuel.RefuelingRecordDTO;
import com.meng.student.trusteeship.entity.fuel.RefuelingRecordForRefuelingRecordPage;

import java.util.List;

/**
 * 安卓客户端http接口的服务类接口
 *
 * @author weiyangjun
 * @date 2018/03/21 11:13
 */
public interface AndroidInterfaceService {
    /**
     * 返回上次的里程数、加油金额、加油时间
     *
     * @param carNumber 车牌号
     * @return
     */
    RefuelingRecordDTO androidforRefuelingRecordDTO(String carNumber);

    /**
     * 通过城市和仓库为参数查询加油记录封装成列表返回给安卓端
     *
     * @param city      城市
     * @param warehouse 仓库
     * @return 加油记录的列表
     */
    List<RefuelingRecordForRefuelingRecordPage> getRefuelingRecordForAndroidWithCityAndWarehouse(String city, String warehouse);
}
