package com.meng.student.trusteeship.service.index.impl;

import com.meng.student.trusteeship.dao.index.NationalInsuranceMapper;
import com.meng.student.trusteeship.entity.index.NationalInsurance;
import com.meng.student.trusteeship.entity.index.po.NationalInsurancePO;
import com.meng.student.trusteeship.service.index.NationalInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author: 吴宸煊
 * @Description: 全国保险查询service实现
 * @Date: Created in 15:08 2018/3/26
 */
@Service
@CacheConfig(cacheNames = "NationalInsuranceServiceImpl")
public class NationalInsuranceServiceImpl implements NationalInsuranceService {

    @Autowired
    private NationalInsuranceMapper nationalInsuranceMapper;

    @Override
    @Cacheable(key = "'getNowExpiredInsurance'")
    public NationalInsurance getNowExpiredInsurance() {
        NationalInsurancePO nationalInsurancePO = nationalInsuranceMapper.getNowExpiredInsurance();
        NationalInsurance nationalInsurance = convertToNationalInsuranceVO(nationalInsurancePO);
        return nationalInsurance;
    }

    @Override
    @Cacheable(key = "'getThirtyDaysExpiredInsurance'")
    public NationalInsurance getThirtyDaysExpiredInsurance() {
        NationalInsurancePO nationalInsurancePO = nationalInsuranceMapper.getThirtyDaysExpiredInsurance();
        NationalInsurance nationalInsurance = convertToNationalInsuranceVO(nationalInsurancePO);
        return nationalInsurance;
    }

    @Override
    @Cacheable(key = "'getSixthDaysExpiredInsurance'")
    public NationalInsurance getSixthDaysExpiredInsurance() {
        NationalInsurancePO nationalInsurancePO = nationalInsuranceMapper.getSixthDaysExpiredInsurance();
        NationalInsurance nationalInsurance = convertToNationalInsuranceVO(nationalInsurancePO);
        return nationalInsurance;
    }


    /**
     * PO 转 VO
     *
     * @param
     * @return
     */
    private NationalInsurance convertToNationalInsuranceVO(NationalInsurancePO nationalInsurancePO) {

        if (null == nationalInsurancePO) {
            nationalInsurancePO = new NationalInsurancePO();
            nationalInsurancePO.setNumberInsurance(0);
        }
        NationalInsurance nationalInsurance = new NationalInsurance(nationalInsurancePO);
        return nationalInsurance;
    }


}
