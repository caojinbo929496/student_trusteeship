package com.meng.student.trusteeship.service.index;

import com.meng.student.trusteeship.entity.index.NationalVehicleInspection;

/**
 * @Author: 吴宸煊
 * @Description: 全国车辆年检查询service接口
 * @Date: Created in 14:38 2018/3/27
 */
public interface NationalVehicleInspectionService {

    /**
     * 当前已过期车辆年检数量
     *
     * @param
     * @return NationalVehicleInspection
     */
    NationalVehicleInspection getNowExpiredInspection();

    /**
     * 近30天到期车辆年检数量
     *
     * @param
     * @return NationalVehicleInspection
     */
    NationalVehicleInspection getThirtyDaysExpiredInspection();

    /**
     * 近60天到期车辆年检数量
     *
     * @param
     * @return NationalVehicleInspection
     */
    NationalVehicleInspection getSixtyDaysExpiredInspection();
}
