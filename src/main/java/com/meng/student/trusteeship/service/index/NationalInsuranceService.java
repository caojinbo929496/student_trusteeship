package com.meng.student.trusteeship.service.index;

import com.meng.student.trusteeship.entity.index.NationalInsurance;

/**
 * @Author: 吴宸煊
 * @Description: 全国保险查询service接口
 * @Date: Created in 15:07 2018/3/26
 */
public interface NationalInsuranceService {

    /**
     * 当前车辆保险过期数量
     *
     * @param
     * @return NationalInsurance
     */
    NationalInsurance getNowExpiredInsurance();

    /**
     * 近30天车辆保险到期数量
     *
     * @param
     * @return NationalInsurance
     */
    NationalInsurance getThirtyDaysExpiredInsurance();

    /**
     * 近60天内车辆保险到期数量
     *
     * @param
     * @return NationalInsurance
     */
    NationalInsurance getSixthDaysExpiredInsurance();
}
