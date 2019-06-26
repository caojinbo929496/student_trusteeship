package com.meng.student.trusteeship.entity.vehicle.dto;

import java.util.Date;

public class ManegerCarAcceptDTO {

    /**
     * 车牌号
     */
    private String carNumber;

    /**
     * 年检有效日期截止
     */
    private Long validityDate;

    /**
     * 保险有效日期截止
     */
    private Long insuranceStopDate;

    /**
     * 交易时间
     */
    private Long tradingTime;

    /**
     * 上次里程数
     */
    private Double lastMileage;

    /**
     * 交易金额
     */
    private Double tradingMoney;

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Long getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(Long validityDate) {
        this.validityDate = validityDate;
    }

    public Long getInsuranceStopDate() {
        return insuranceStopDate;
    }

    public void setInsuranceStopDate(Long insuranceStopDate) {
        this.insuranceStopDate = insuranceStopDate;
    }

    public Long getTradingTime() {
        return tradingTime;
    }

    public void setTradingTime(Long tradingTime) {
        this.tradingTime = tradingTime;
    }

    public Double getLastMileage() {
        return lastMileage;
    }

    public void setLastMileage(Double lastMileage) {
        this.lastMileage = lastMileage;
    }

    public Double getTradingMoney() {
        return tradingMoney;
    }

    public void setTradingMoney(Double tradingMoney) {
        this.tradingMoney = tradingMoney;
    }
}
