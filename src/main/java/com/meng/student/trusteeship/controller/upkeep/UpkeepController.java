package com.meng.student.trusteeship.controller.upkeep;

import com.meng.student.trusteeship.entity.QueryCondition;
import com.meng.student.trusteeship.entity.result.BaseResult;
import com.meng.student.trusteeship.entity.upkeep.UpkeepVO;
import com.meng.student.trusteeship.entity.upkeep.query.UpkeepQueryCondition;
import com.meng.student.trusteeship.service.upkeep.UpkeepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * @author fengqigui
 * @description 车辆保养的控制器
 * @date 2018/03/16 09:04
 */
@RestController
public class UpkeepController {

    @Autowired
    private UpkeepService upkeepService;

    @RequestMapping(value = "/upkeepList", method = RequestMethod.PUT)
    public BaseResult listUpkeepPage(@RequestBody QueryCondition queryCondition) {

        longConvertToDate(queryCondition);
        Map<String, Object> map = upkeepService.queryKeyWords(queryCondition);
        return BaseResult.getResult(map);

    }

    @RequestMapping(value = "/queryKeyWords", method = RequestMethod.PUT)
    public BaseResult queryKeyWords(@RequestBody QueryCondition queryCondition) {

        longConvertToDate(queryCondition);
        Map<String, Object> map = upkeepService.queryKeyWords(queryCondition);
        return BaseResult.getResult(map);

    }

    @RequestMapping(value = "/upkeep/detail/{upkeepId}", method = RequestMethod.GET)
    public BaseResult detailUpkeep(@PathVariable String upkeepId) {

        UpkeepVO upkeep = upkeepService.getUpKeepDetail(upkeepId);
        return BaseResult.getResult(upkeep);

    }

    /**
     * 更具车牌号或者维修人返回保养信息
     *
     * @param upkeepQueryCondition 查询条件
     * @return List<UpkeepVO>
     */
    @RequestMapping(value = "/remoteQueryUpkeepByCarNumberAndPatentedNumber", method = RequestMethod.POST)
    public List<UpkeepVO> listByCarNumber(@RequestBody UpkeepQueryCondition upkeepQueryCondition) {

        List<UpkeepVO> upkeepVOS = upkeepService.listUpkeepByCarNumberAndPatenteNumber(upkeepQueryCondition);
        return upkeepVOS;

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
