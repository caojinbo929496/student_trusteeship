package com.meng.student.trusteeship.entity.index;

import com.meng.student.trusteeship.entity.index.po.NationalVehicleInspectionPO;

import java.io.Serializable;

/**
 * @Author: 吴宸煊
 * @Description: 全国车辆年检实体（与页面交互）
 * @Date: Created in 14:21 2018/3/27
 */
public class NationalVehicleInspection implements Serializable {
    /**
     * 全国车辆年检过期统计
     */
    private Integer numberInspection;

    public Integer getNumberInspection() {
        return numberInspection;
    }

    public void setNumberInspection(Integer numberInspection) {
        this.numberInspection = numberInspection;
    }

    public NationalVehicleInspection() {
    }

    public NationalVehicleInspection(NationalVehicleInspectionPO nationalVehicleInspectionPO) {
        this.setNumberInspection(nationalVehicleInspectionPO.getNumberInspection());
    }

    @Override
    public String toString() {
        return "NationalVehicleInspectionPO{" +
                "numberInspection=" + numberInspection +
                '}';
    }
}
