package com.meng.student.trusteeship.entity.maintain.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author fengqigui
 * @description 车辆维修的查询的条件
 * @date 2018/04/02 10:24
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MaintainQueryCondition {

    /**
     * 维修车辆的驾照编号
     */
    private String patenteNumber;

    /**
     * 车辆的牌照
     */
    private String carNumber;

    public String getPatenteNumber() {
        return patenteNumber;
    }

    public void setPatenteNumber(String patenteNumber) {
        this.patenteNumber = patenteNumber;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
}
