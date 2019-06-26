package com.meng.student.trusteeship.service.index.impl;

import com.meng.student.trusteeship.dao.index.NationalVehicleInspectionMapper;
import com.meng.student.trusteeship.service.index.NationalVehicleInspectionService;
import com.meng.student.trusteeship.entity.index.NationalVehicleInspection;
import com.meng.student.trusteeship.entity.index.po.NationalVehicleInspectionPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author: 吴宸煊
 * @Description: 全国车辆年检查询service实现
 * @Date: Created in 14:39 2018/3/27
 */
@Service
@CacheConfig(cacheNames = "NationalVehicleInspectionServiceImpl")
public class NationalVehicleInspectionServiceImpl implements NationalVehicleInspectionService {

    @Autowired
    private NationalVehicleInspectionMapper nationalVehicleInspectionMapper;

    @Override
    @Cacheable(key = "'getNowExpiredInspection'")
    public NationalVehicleInspection getNowExpiredInspection() {
        NationalVehicleInspectionPO nationalVehicleInspectionPO = nationalVehicleInspectionMapper.getNowExpiredInspection();
        NationalVehicleInspection nationalVehicleInspection = convertToNationalInspectionVO(nationalVehicleInspectionPO);
        return nationalVehicleInspection;
    }

    @Override
    @Cacheable(key = "'getThirtyDaysExpiredInspection'")
    public NationalVehicleInspection getThirtyDaysExpiredInspection() {
        NationalVehicleInspectionPO nationalVehicleInspectionPO = nationalVehicleInspectionMapper.getThirtyDaysExpiredInspection();
        NationalVehicleInspection nationalVehicleInspection = convertToNationalInspectionVO(nationalVehicleInspectionPO);
        return nationalVehicleInspection;
    }

    @Override
    @Cacheable(key = "'getSixtyDaysExpiredInspection'")
    public NationalVehicleInspection getSixtyDaysExpiredInspection() {
        NationalVehicleInspectionPO nationalVehicleInspectionPO = nationalVehicleInspectionMapper.getSixtyDaysExpiredInspection();
        NationalVehicleInspection nationalVehicleInspection = convertToNationalInspectionVO(nationalVehicleInspectionPO);
        return nationalVehicleInspection;
    }

    /**
     * PO 转 VO
     *
     * @param
     * @return
     */
    private NationalVehicleInspection convertToNationalInspectionVO(NationalVehicleInspectionPO nationalVehicleInspectionPO) {

        if (null == nationalVehicleInspectionPO) {
            nationalVehicleInspectionPO = new NationalVehicleInspectionPO();
            nationalVehicleInspectionPO.setNumberInspection(0);
        }
        NationalVehicleInspection nationalVehicleInspection = new NationalVehicleInspection(nationalVehicleInspectionPO);
        return nationalVehicleInspection;
    }
}
