package com.meng.student.trusteeship.service.maintain;

import com.meng.student.trusteeship.entity.QueryCondition;
import com.meng.student.trusteeship.entity.maintain.MaintainVO;
import com.meng.student.trusteeship.entity.maintain.query.MaintainQueryCondition;

import java.util.List;
import java.util.Map;

/**
 * @author fengqigui
 * @description 维修的接口
 * @date 2018/03/19 14:05
 */
public interface MaintainService {


    /**
     * 维修记录首页
     *
     * @param queryCondition 条件：当前页，页面尺寸，等信息
     * @return
     */
    Map<String, Object> listMaintain(QueryCondition queryCondition);

    /**
     * 根据 Id 来获的maintain
     *
     * @param maintainId
     * @return
     */
    MaintainVO getMaintainById(String maintainId);

    /**
     * 关键字模糊查询
     *
     * @param queryCondition
     * @return
     */
    Map<String, Object> listQueryKeyWords(QueryCondition queryCondition);

    /**
     * 通过车辆牌照或者驾照编号查询维修记录
     *
     * @param maintainQueryCondition 查询条件
     * @return
     */
    List<MaintainVO> listMaintainByCarNumberAndPatenteNumber(MaintainQueryCondition maintainQueryCondition);


}
