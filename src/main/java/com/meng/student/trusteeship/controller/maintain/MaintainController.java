package com.meng.student.trusteeship.controller.maintain;

import com.meng.student.trusteeship.entity.QueryCondition;
import com.meng.student.trusteeship.entity.maintain.MaintainVO;
import com.meng.student.trusteeship.entity.maintain.query.MaintainQueryCondition;
import com.meng.student.trusteeship.entity.result.BaseResult;
import com.meng.student.trusteeship.service.maintain.MaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * @author fengqigui
 * @description 维修的controller
 * @date 2018/03/19 14:01
 */
@RestController
public class MaintainController {


    @Autowired
    private MaintainService maintainService;

    @RequestMapping(value = "/maintainList", method = RequestMethod.PUT)
    public BaseResult maintainList(@RequestBody QueryCondition queryCondition) {

        longConvertToDate(queryCondition);
        Map<String, Object> map = maintainService.listMaintain(queryCondition);
        return BaseResult.getResult(map);

    }

    @RequestMapping(value = "/maintain/detail/{maintainId}", method = RequestMethod.GET)
    public BaseResult maintainDetail(@PathVariable String maintainId) {

        MaintainVO maintain = maintainService.getMaintainById(maintainId);
        return BaseResult.getResult(maintain);

    }


    @RequestMapping(value = "/queryKeyWordsMaintain", method = RequestMethod.PUT)
    public BaseResult queryKeyWordsMaintain(@RequestBody QueryCondition queryCondition) {

        longConvertToDate(queryCondition);
        Map<String, Object> map = maintainService.listQueryKeyWords(queryCondition);
        return BaseResult.getResult(map);

    }

    /**
     * 通过车牌号或者维修人的驾照编号返回维修信息
     *
     * @param maintainQueryCondition 查询条件
     * @return List<MaintainVO>
     */
    @RequestMapping(value = "/remoteQueryMaintainByCarNumberAndPatenteNumber", method = RequestMethod.POST)
    public List<MaintainVO> listMaintainByCarNumberAndPatenteNumber(@RequestBody MaintainQueryCondition maintainQueryCondition) {
        List<MaintainVO> maintainVOS = maintainService.listMaintainByCarNumberAndPatenteNumber(maintainQueryCondition);
        return maintainVOS;
    }

    private void longConvertToDate(QueryCondition queryCondition) {
        if (queryCondition.getStartDateView() != null) {
            queryCondition.setStartDate(new Date(queryCondition.getStartDateView()));
        }
        if (queryCondition.getEndDateView() != null) {
            queryCondition.setEndDate(new Date(queryCondition.getEndDateView()));
        }
    }


}
