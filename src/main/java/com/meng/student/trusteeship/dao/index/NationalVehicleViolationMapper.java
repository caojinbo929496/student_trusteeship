package com.meng.student.trusteeship.dao.index;

import com.meng.student.trusteeship.entity.index.po.NationalVehicleViolationPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: 吴宸煊
 * @Description: 全国违章查询dao层
 * @Date: Created in 11:20 2018/3/23
 */
@Mapper
public interface NationalVehicleViolationMapper {

    /**
     * 当前未处理违章数
     *
     * @return NationalVehicleViolationPO
     */
    NationalVehicleViolationPO getNationViolation();

    /**
     * 获得当月的违章数
     *
     * @return NationalVehicleViolationPO
     */
    NationalVehicleViolationPO getThisMonthViolation();

    /**
     * 30天的违章数
     *
     * @return NationalVehicleViolationPO
     */
    NationalVehicleViolationPO getThirtyDays();

    /**
     * 60天的违章
     *
     * @return NationalVehicleViolationPO
     */
    NationalVehicleViolationPO getSixtyDays();


}
