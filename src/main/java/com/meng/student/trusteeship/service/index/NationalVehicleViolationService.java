package com.meng.student.trusteeship.service.index;

import com.meng.student.trusteeship.entity.index.NationalVehicleViolation;

/**
 * @Author: 吴宸煊
 * @Description: 全国违章查询service层
 * @Date: Created in 11:25 2018/3/23
 */
public interface NationalVehicleViolationService {

    /**
     * 获得当前未处理的违章数
     *
     * @param
     * @return NationalVehicleViolation
     */
    NationalVehicleViolation getNationViolation();

    /**
     * 获得当月的违章数
     *
     * @param
     * @return NationalVehicleViolation
     */
    NationalVehicleViolation getThisMonthViolation();

    /**
     * 获得30天的违章数
     *
     * @param
     * @return NationalVehicleViolation
     */
    NationalVehicleViolation getThirtyDays();

    /**
     * 获得60天的违章数
     *
     * @param
     * @return NationalVehicleViolation
     */
    NationalVehicleViolation getSixtyDays();
}
