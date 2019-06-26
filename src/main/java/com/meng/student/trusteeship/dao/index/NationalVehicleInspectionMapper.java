package com.meng.student.trusteeship.dao.index;

import com.meng.student.trusteeship.entity.index.po.NationalVehicleInspectionPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: 吴宸煊
 * @Description: 全国车辆年检dao接口
 * @Date: Created in 14:34 2018/3/27
 */
@Mapper
public interface NationalVehicleInspectionMapper {

    /**
     * 当前已过期车辆年检
     *
     * @param
     * @return NationalVehicleInspectionPO
     */
    NationalVehicleInspectionPO getNowExpiredInspection();

    /**
     * 近30天内到期车辆年检
     *
     * @param
     * @return NationalVehicleInspectionPO
     */
    NationalVehicleInspectionPO getThirtyDaysExpiredInspection();

    /**
     * 近60天内到期车辆年检
     *
     * @param
     * @return NationalVehicleInspectionPO
     */
    NationalVehicleInspectionPO getSixtyDaysExpiredInspection();
}
