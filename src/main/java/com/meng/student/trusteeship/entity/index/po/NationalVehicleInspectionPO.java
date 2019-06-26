package com.meng.student.trusteeship.entity.index.po;

import com.meng.student.trusteeship.entity.index.NationalVehicleInspection;

import java.io.Serializable;

/**
 * @Author: 吴宸煊
 * @Description: 全国车辆年检实体（与数据库交互）
 * @Date: Created in 14:18 2018/3/27
 */
public class NationalVehicleInspectionPO implements Serializable {
    /**
     * 全国车辆年检过期统计
     */
    private Integer numberInspection;

    public Integer getNumberInspection() {
        return numberInspection;
    }

    public NationalVehicleInspectionPO() {
    }

    public NationalVehicleInspectionPO(NationalVehicleInspection nationalVehicleInspection) {
        this.setNumberInspection(nationalVehicleInspection.getNumberInspection());
    }

    public void setNumberInspection(Integer numberInspection) {
        this.numberInspection = numberInspection;
    }

    @Override
    public String toString() {
        return "NationalVehicleInspectionPO{" +
                "numberInspection=" + numberInspection +
                '}';
    }

}
