package com.meng.student.trusteeship.service.upkeep.impl;

import com.meng.student.trusteeship.dao.upkeep.UpkeepMapper;
import com.meng.student.trusteeship.service.upkeep.UpkeepService;
import com.meng.student.trusteeship.entity.QueryCondition;
import com.meng.student.trusteeship.entity.upkeep.UpkeepPO;
import com.meng.student.trusteeship.entity.upkeep.UpkeepVO;
import com.meng.student.trusteeship.entity.upkeep.query.UpkeepQueryCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fengqigui
 * @description
 * @date 2018/03/16 09:23
 */
@Service
public class UpkeepServiceImpl implements UpkeepService {


    @Autowired
    UpkeepMapper upkeepMapper;


    @Override
    public Map<String, Object> queryKeyWords(QueryCondition queryCondition) {

        if (null == queryCondition) {
            queryCondition = new QueryCondition(10, 0);
        }
        int currentPage = (queryCondition.getCurrentPage() - 1) * queryCondition.getPageSize();
        queryCondition.setCurrentPage(currentPage);
        List<UpkeepPO> list = upkeepMapper.queryKeyWords(queryCondition);
        List<UpkeepVO> collect = list.stream().map(p -> this.convertToUpkeepVO(p)).collect(Collectors.toList());
        int counts = upkeepMapper.getCounts(queryCondition);
        Map<String, Object> map = new HashMap<>();
        map.put("counts", counts);
        map.put("listUpkeeps", collect);
        return map;

    }

    @Override
    public UpkeepVO getUpKeepDetail(String upkeepId) {

        if (null == upkeepId) {
            return null;
        }
        UpkeepPO upkeepPO = upkeepMapper.getByPrimaryKey(upkeepId);
        UpkeepVO upkeepVO = convertToUpkeepVO(upkeepPO);
        return upkeepVO;

    }

    @Override
    public List<UpkeepVO> listUpkeepByCarNumberAndPatenteNumber(UpkeepQueryCondition queryCondition) {

        List<UpkeepVO> list = new ArrayList<>();
        if (queryCondition == null) {
            return list;
        }
        List<UpkeepPO> upkeepPOS = upkeepMapper.listUpkeepByCarNumberAndPatenteNumber(queryCondition);
        List<UpkeepVO> collect = upkeepPOS.stream().map(p -> this.convertToUpkeepVO(p)).collect(Collectors.toList());
        return collect;

    }


    /**
     * PO 转 VO
     *
     * @param upkeepPOS
     * @return
     */
    private UpkeepVO convertToUpkeepVO(UpkeepPO upkeepPOS) {
        if (null == upkeepPOS) {
            return null;
        }
        return new UpkeepVO(upkeepPOS);
    }

}
