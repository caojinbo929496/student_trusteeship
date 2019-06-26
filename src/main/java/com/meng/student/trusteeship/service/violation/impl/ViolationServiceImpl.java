package com.meng.student.trusteeship.service.violation.impl;

import com.meng.student.trusteeship.dao.patente.PatenteMapper;
import com.meng.student.trusteeship.dao.violation.ViolationMapper;
import com.meng.student.trusteeship.entity.vehicle.Violation;
import com.meng.student.trusteeship.entity.vehicle.ViolationUnity;
import com.meng.student.trusteeship.entity.vehicle.po.ViolationPO;
import com.meng.student.trusteeship.entity.vehicle.po.ViolationUnityPo;
import com.meng.student.trusteeship.service.violation.ViolationService;
import com.meng.student.trusteeship.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ViolationServiceImpl implements ViolationService {

    @Autowired
    private ViolationMapper violationMapper;

    @Autowired
    private PatenteMapper patenteMapper;

    @Override
    public List<Violation> getViolationByCarNumber(String carNumber) {
        List<ViolationPO> violationPOList = violationMapper.listViolationPOByCarNumber(carNumber);
        return violationPOList.stream().map(ConvertUtils::convert).collect(Collectors.toList());
    }

    @Override
    public List<Violation> getViolationByOffenderId(String offenderId) {
        List<ViolationPO> violationPOList = violationMapper.getViolationPOByOffenderId(offenderId);
        return violationPOList.stream().map(ConvertUtils::convert).collect(Collectors.toList());
    }

    @Override
    public Violation getViolationById(String id) {
        ViolationPO violationPO = violationMapper.getViolationPOById(id);
        return ConvertUtils.convert(violationPO);
    }

    @Override
    public void save(Violation violation) {
        ViolationPO violationPO = ConvertUtils.convert(violation);
        violationMapper.save(violationPO);
    }

    @Override
    public void updateAllStatus(String carNumber) {
        violationMapper.updateAllStatus(carNumber);
    }

    @Override
    public void updateStatus(Violation violation) {
        Map updateStatusInfo = getUpdateStatusInfo(violation);
        violationMapper.updateStatus(updateStatusInfo);
    }

    private Map getUpdateStatusInfo(Violation violation) {
        Assert.notNull(violation, "违章信息不能为空");
        Assert.hasLength(violation.getCarNumber(), "违章信息中车牌号不能为空");
        Assert.notNull(violation.getViolationTime(), "违章信息中违章时间不能为空");

        HashMap<String, Object> map = new HashMap<>();
        map.put("carNumber", violation.getCarNumber());
        map.put("violationTime", violation.getViolationTime());
        map.put("status", false);

        return map;
    }


    @Override
    public Boolean queryByCarNumberAndViolationTime(String carNumber, Date violationTime) {
        return null;
    }

    @Override
    public Map<String, Object> listViolation(Map queryMap) {
        List<ViolationUnityPo> violationUnityPos = violationMapper.listViolation(queryMap);
        List<ViolationUnity> violationUnities = violationUnityPos.stream().map(ConvertUtils::convert).collect(Collectors.toList());

        queryMap.put("queryStatus", "1");
        Integer count = violationMapper.listViolation(queryMap).size();
        HashMap<String, Object> map = new HashMap<>();
        map.put("count", count);
        map.put("data", violationUnities);

        return map;
    }

    @Override
    public Violation getViolationByCarNumberAndTime(HashMap<String, Object> map) {
        ViolationPO violationPO = violationMapper.getViolationByCarNumberAndTime(map);
        if (violationPO == null) {
            return null;
        }
        return ConvertUtils.convert(violationPO);
    }


}
