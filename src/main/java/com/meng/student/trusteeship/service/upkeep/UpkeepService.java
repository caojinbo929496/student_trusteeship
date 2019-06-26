package com.meng.student.trusteeship.service.upkeep;


import com.meng.student.trusteeship.entity.QueryCondition;
import com.meng.student.trusteeship.entity.upkeep.UpkeepVO;
import com.meng.student.trusteeship.entity.upkeep.query.UpkeepQueryCondition;

import java.util.List;
import java.util.Map;

/**
 * @author fengqigui
 * @description 车辆保养
 * @date 2018/03/16 09:22
 */
public interface UpkeepService {


    /**
     * 关键字查询
     *
     * @param queryCondition 查询的条件
     * @return
     */
    Map<String, Object> queryKeyWords(QueryCondition queryCondition);

    /**
     * 根据ID 获得 Upkeep
     *
     * @param upkeepId
     * @return
     */
    UpkeepVO getUpKeepDetail(String upkeepId);

    /**
     * 通过汽车牌照，或者是驾照编号查询保养记录
     *
     * @param upkeepQueryCondition 查询条件
     * @return
     */
    List<UpkeepVO> listUpkeepByCarNumberAndPatenteNumber(UpkeepQueryCondition upkeepQueryCondition);


}
