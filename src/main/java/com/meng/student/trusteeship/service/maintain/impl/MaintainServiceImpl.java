package com.meng.student.trusteeship.service.maintain.impl;

import com.meng.student.trusteeship.dao.maintain.MaintainMapper;
import com.meng.student.trusteeship.service.maintain.MaintainService;
import com.meng.student.trusteeship.entity.QueryCondition;
import com.meng.student.trusteeship.entity.maintain.MaintainPO;
import com.meng.student.trusteeship.entity.maintain.MaintainVO;
import com.meng.student.trusteeship.entity.maintain.query.MaintainQueryCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fengqigui
 * @description MaintainService 的实现类
 * @date 2018/03/19 14:15
 */
@Service
public class MaintainServiceImpl implements MaintainService {

    @Autowired
    private MaintainMapper maintainMapper;


    @Override
    public Map<String, Object> listMaintain(QueryCondition queryCondition) {

        if (null == queryCondition) {
            queryCondition = new QueryCondition(10, 0);
        }
        int currentPage = (queryCondition.getCurrentPage() - 1) * queryCondition.getPageSize();
        queryCondition.setCurrentPage(currentPage);
        List<MaintainPO> list = maintainMapper.queryKeyWords(queryCondition);
        List<MaintainVO> collect = list.stream().map(p -> this.convertToMaintainVO(p)).collect(Collectors.toList());
        int counts = maintainMapper.getCounts(queryCondition);
        Map<String, Object> map = new HashMap<>();
        map.put("maintainLists", collect);
        map.put("counts", counts);
        return map;
    }

    @Override
    public MaintainVO getMaintainById(String maintainId) {
        if (null == maintainId) {
            return null;
        }
        MaintainPO maintainPO = maintainMapper.selectByPrimaryKey(maintainId);
        MaintainVO maintainVO = convertToMaintainVO(maintainPO);
        return maintainVO;
    }

    @Override
    public Map<String, Object> listQueryKeyWords(QueryCondition queryCondition) {

        if (null == queryCondition) {
            queryCondition = new QueryCondition(10, 1);
        }
        int i = (queryCondition.getCurrentPage() - 1) * queryCondition.getPageSize();
        queryCondition.setCurrentPage(i);
        List<MaintainPO> list = maintainMapper.queryKeyWords(queryCondition);
        List<MaintainVO> collect = list.stream().map(p -> this.convertToMaintainVO(p)).collect(Collectors.toList());
        int counts = maintainMapper.getCounts(queryCondition);
        Map<String, Object> map = new HashMap<>();
        map.put("maintainLists", collect);
        map.put("counts", counts);
        return map;

    }

    @Override
    public List<MaintainVO> listMaintainByCarNumberAndPatenteNumber(MaintainQueryCondition maintainQueryCondition) {

        List<MaintainVO> list = new ArrayList<>();
        if (maintainQueryCondition == null) {
            return list;
        }

        List<MaintainPO> listCollect = maintainMapper.listMaintainByCarNumberAndPatenteNumber(maintainQueryCondition);
        List<MaintainVO> collect = listCollect.stream().map(p -> this.convertToMaintainVO(p)).collect(Collectors.toList());
        return collect;

    }

    /**
     * PO 转 VO
     *
     * @param maintainPO
     * @return
     */
    private MaintainVO convertToMaintainVO(MaintainPO maintainPO) {
        if (null == maintainPO) {
            return null;
        }
        return new MaintainVO(maintainPO);
    }

}
