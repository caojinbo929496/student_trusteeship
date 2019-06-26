package com.meng.student.trusteeship.entity.vehicle.po;

import java.util.Date;

public class ManegerCarAcceptPO {

    /**
     * 车牌号
     */
    private String carNumber;

    /**
     * 年检有效日期截止
     */
    private Date validityDate;

    /**
     * 最新保险有效日期截止
     */
    private Date insuranceStopDate;

    /**
     * 最新加油记录交易时间
     */
    private Date tradingTime;

    /**
     * 上次里程数
     */
    private Double currentMileage;

    /**
     * 交易金额
     */
    private Double tradingMoney;

    /**
     * 安卓上传的交易时间
     */
    Date falseTradingTime;

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Date getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    public Date getInsuranceStopDate() {
        return insuranceStopDate;
    }

    public void setInsuranceStopDate(Date insuranceStopDate) {
        this.insuranceStopDate = insuranceStopDate;
    }

    public Date getTradingTime() {
        return tradingTime;
    }

    public void setTradingTime(Date tradingTime) {
        this.tradingTime = tradingTime;
    }

    public Double getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(Double currentMileage) {
        this.currentMileage = currentMileage;
    }

    public Double getTradingMoney() {
        return tradingMoney;
    }

    public void setTradingMoney(Double tradingMoney) {
        this.tradingMoney = tradingMoney;
    }
}
