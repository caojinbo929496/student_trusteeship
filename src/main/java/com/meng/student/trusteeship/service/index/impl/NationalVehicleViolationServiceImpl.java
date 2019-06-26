package com.meng.student.trusteeship.service.index.impl;

import com.meng.student.trusteeship.dao.index.NationalVehicleViolationMapper;
import com.meng.student.trusteeship.entity.index.NationalVehicleViolation;
import com.meng.student.trusteeship.entity.index.po.NationalVehicleViolationPO;
import com.meng.student.trusteeship.service.index.NationalVehicleViolationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Author: 吴宸煊
 * @Description: 全国违章查询service实现
 * @Date: Created in 11:27 2018/3/23
 */
@Service
@CacheConfig(cacheNames = "index")
public class NationalVehicleViolationServiceImpl implements NationalVehicleViolationService {

    @Autowired
    private NationalVehicleViolationMapper nationalVehicleViolationMapper;

    @Override
    @Cacheable(key = "'getNationViolation'")
    public NationalVehicleViolation getNationViolation() {
        NationalVehicleViolationPO nationalVehicleViolationPO = nationalVehicleViolationMapper.getNationViolation();
        NationalVehicleViolation nationalVehicleViolation = convertToNationalViolationVO(nationalVehicleViolationPO);
        return nationalVehicleViolation;

    }

    @Override
    @Cacheable(key = "'getThisMonthViolation'")
    public NationalVehicleViolation getThisMonthViolation() {
        NationalVehicleViolationPO nationalVehicleViolationPO = nationalVehicleViolationMapper.getThisMonthViolation();
        NationalVehicleViolation nationalVehicleViolation = convertToNationalViolationVO(nationalVehicleViolationPO);
        return nationalVehicleViolation;
    }

    @Override
    @Cacheable(key = "'getThirtyDays'")
    public NationalVehicleViolation getThirtyDays() {
        NationalVehicleViolationPO nationalVehicleViolationPO = nationalVehicleViolationMapper.getThirtyDays();
        NationalVehicleViolation nationalVehicleViolation = convertToNationalViolationVO(nationalVehicleViolationPO);
        return nationalVehicleViolation;
    }

    @Override
    @Cacheable(key = "'getSixtyDays'")
    public NationalVehicleViolation getSixtyDays() {
        NationalVehicleViolationPO nationalVehicleViolationPO = nationalVehicleViolationMapper.getSixtyDays();
        NationalVehicleViolation nationalVehicleViolation = convertToNationalViolationVO(nationalVehicleViolationPO);
        return nationalVehicleViolation;
    }

    /**
     * PO 转 VO
     *
     * @param
     * @return
     */
    private NationalVehicleViolation convertToNationalViolationVO(NationalVehicleViolationPO nationalVehicleViolationPO) {
        if (null == nationalVehicleViolationPO) {
            nationalVehicleViolationPO = new NationalVehicleViolationPO();
            nationalVehicleViolationPO.setUnTreated(0);
            nationalVehicleViolationPO.setDeductMark(0);
            nationalVehicleViolationPO.setPenalty(new BigDecimal("0.00"));
        } else {
            if (null == nationalVehicleViolationPO.getDeductMark()) {
                nationalVehicleViolationPO.setDeductMark(0);
            }
            if (null == nationalVehicleViolationPO.getPenalty()) {
                nationalVehicleViolationPO.setPenalty(new BigDecimal("0.00"));
            }
        }
        NationalVehicleViolation nationalVehicleViolation = new NationalVehicleViolation(nationalVehicleViolationPO);
        return nationalVehicleViolation;
    }
}
