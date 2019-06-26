package com.meng.student.trusteeship.entity.upkeep.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author fengqigui
 * @description
 * @date 2018/04/02 10:26
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpkeepQueryCondition {

    /**
     * 车辆牌照
     */
    private String carNumber;

    /**
     * 保养的驾照编号
     */
    private String patenteNumber;

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getPatenteNumber() {
        return patenteNumber;
    }

    public void setPatenteNumber(String patenteNumber) {
        this.patenteNumber = patenteNumber;
    }
}
