package com.meng.student.trusteeship.service.index.impl;

import com.meng.student.trusteeship.dao.index.NationalPatenteMapper;
import com.meng.student.trusteeship.entity.index.NationalPatente;
import com.meng.student.trusteeship.entity.index.po.NationalPatentePO;
import com.meng.student.trusteeship.service.index.NationalPatenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**
 * @Author: 吴宸煊
 * @Description: 全国驾照查询service实现
 * @Date: Created in 15:39 2018/3/24
 */
@Service
@CacheConfig(cacheNames = "NationalPatenteServiceImpl")
public class NationalPatenteServiceImpl implements NationalPatenteService {

    @Autowired
    private NationalPatenteMapper nationalPatenteMapper;

    @Override
    @Cacheable(key = "'getNowExpiredPatente'")
    public NationalPatente getNowExpiredPatente() {
        NationalPatentePO nationalPatentePO = nationalPatenteMapper.getNowExpiredPatente();
        NationalPatente nationalPatente = convertToNationalPatenteVO(nationalPatentePO);
        return nationalPatente;
    }

    @Override
    @Cacheable(key = "'getThirtyDaysExpiredPatente'")
    public NationalPatente getThirtyDaysExpiredPatente() {
        NationalPatentePO nationalPatentePO = nationalPatenteMapper.getThirtyDaysExpiredPatente();
        NationalPatente nationalPatente = convertToNationalPatenteVO(nationalPatentePO);
        return nationalPatente;
    }

    @Override
    @Cacheable(key = "'getSixtyDaysExpiredPatente'")
    public NationalPatente getSixtyDaysExpiredPatente() {
        NationalPatentePO nationalPatentePO = nationalPatenteMapper.getSixtyDaysExpiredPatente();
        NationalPatente nationalPatente = convertToNationalPatenteVO(nationalPatentePO);
        return nationalPatente;
    }

    /**
     * PO 转 VO
     *
     * @param
     * @return
     */
    private NationalPatente convertToNationalPatenteVO(NationalPatentePO nationalPatentePO) {

        if (null == nationalPatentePO) {
            nationalPatentePO = new NationalPatentePO();
            nationalPatentePO.setNumberPatente(0);
        }
        NationalPatente nationalPatente = new NationalPatente(nationalPatentePO);
        return nationalPatente;
    }

}
