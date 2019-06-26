package com.meng.student.trusteeship.dao.index;

import com.meng.student.trusteeship.entity.index.po.NationalInsurancePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: 吴宸煊
 * @Description: 全国车辆保险查询dao接口
 * @Date: Created in 14:26 2018/3/26
 */
@Mapper
public interface NationalInsuranceMapper {

    /**
     * 当前车辆保险过期人数
     *
     * @param
     * @return NationalInsurancePO
     */
    NationalInsurancePO getNowExpiredInsurance();

    /**
     * 近30天内车辆保险过期人数
     *
     * @param
     * @return NationalInsurancePO
     */
    NationalInsurancePO getThirtyDaysExpiredInsurance();

    /**
     * 近60天内保险过期人数
     *
     * @param
     * @return NationalInsurancePO
     */
    NationalInsurancePO getSixthDaysExpiredInsurance();
}
