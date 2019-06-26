package com.meng.student.trusteeship.entity.fuel.dto;

import java.io.Serializable;

public class RefuelingRecordForFormsDTO implements Serializable {
    /**
     * 加油时间
     */
    private Long tradingTime;
    /**
     * 主卡号
     */
    private String masterCardNumber;
    /**
     * 副卡号
     */
    private String viceCardNumber;
    /**
     * 供应商，0代表中石油，1代表中石化
     */
    private Integer cardMerchant;
    /**
     * 录入里程数
     */
    private Double currentMileage;
    /**
     * 加油金额
     */
    private Double tradingMoney;
    /**
     * 加油司机id,在行驶时刻表里面查
     */
    private String driverId;
    /**
     * 加油位置
     */
    private String tradingPosition;
    /**
     * 里程数图片url
     */
    private String mileageImgUrl;

    /**
     * 司机名字
     */
    private String driverName;
    /**
     * 司机驾驶证编号
     */
    private String patenteNumber;

    public Long getTradingTime() {
        return tradingTime;
    }

    public void setTradingTime(Long tradingTime) {
        this.tradingTime = tradingTime;
    }

    public String getMasterCardNumber() {
        return masterCardNumber;
    }

    public void setMasterCardNumber(String masterCardNumber) {
        this.masterCardNumber = masterCardNumber;
    }

    public String getViceCardNumber() {
        return viceCardNumber;
    }

    public void setViceCardNumber(String viceCardNumber) {
        this.viceCardNumber = viceCardNumber;
    }

    public Integer getCardMerchant() {
        return cardMerchant;
    }

    public void setCardMerchant(Integer cardMerchant) {
        this.cardMerchant = cardMerchant;
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

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getTradingPosition() {
        return tradingPosition;
    }

    public void setTradingPosition(String tradingPosition) {
        this.tradingPosition = tradingPosition;
    }

    public String getMileageImgUrl() {
        return mileageImgUrl;
    }

    public void setMileageImgUrl(String mileageImgUrl) {
        this.mileageImgUrl = mileageImgUrl;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getPatenteNumber() {
        return patenteNumber;
    }

    public void setPatenteNumber(String patenteNumber) {
        this.patenteNumber = patenteNumber;
    }
}
