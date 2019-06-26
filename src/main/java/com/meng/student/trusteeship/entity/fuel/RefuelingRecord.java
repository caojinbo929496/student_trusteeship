package com.meng.student.trusteeship.entity.fuel;

import java.util.Date;

/**
 * 加油记录实体类
 *
 * @author weiYangJun
 */
public class RefuelingRecord {
    /**
     * 该加油记录所属加油卡的主卡号.
     */
    private String masterCardNumber;
    /**
     * 该加油记录所属加油卡的副卡号.
     */
    private String viceCardNumber;
    /**
     * 交易发生时间
     */
    private Date tradingTime;
    /**
     * 交易类型，0代表加油，1代表圈存
     */
    private String tradingType;
    /**
     * 交易金额,中石化json数值型全部取两位小数点
     */
    private Double tradingMoney;
    /**
     * 油品,中石化圈存与加油数据格式是统一的，数值型没有为0，字符串没有为*
     */
    private String oilType;
    /**
     * 加油数量
     */
    private Double oilQuantity;
    /**
     * 每升油价
     */
    private Double oilPrice;
    /**
     * 余额
     */
    private Double balance;
    /**
     * 交易地点
     */
    private String tradingPosition;
    /**
     * 当前行驶公里数
     */
    private Double currentMileage;
    /**
     * 司机上传的与该条记录相对应的里程数拍照的id
     */
    private String refuelingImageRecordId;

    public RefuelingRecord() {
        super();
    }

    public RefuelingRecord(String masterCardNumber, String viceCardNumber, Date tradingTime, String tradingType,
                           Double tradingMoney, String oilType, Double oilQuantity, Double oilPrice, Double balance,
                           String tradingPosition, Double currentMileage, String refuelingImageRecordId) {
        super();
        this.masterCardNumber = masterCardNumber;
        this.viceCardNumber = viceCardNumber;
        this.tradingTime = tradingTime;
        this.tradingType = tradingType;
        this.tradingMoney = tradingMoney;
        this.oilType = oilType;
        this.oilQuantity = oilQuantity;
        this.oilPrice = oilPrice;
        this.balance = balance;
        this.tradingPosition = tradingPosition;
        this.currentMileage = currentMileage;
        this.refuelingImageRecordId = refuelingImageRecordId;
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

    public Date getTradingTime() {
        return tradingTime;
    }

    public void setTradingTime(Date tradingTime) {
        this.tradingTime = tradingTime;
    }

    public String getTradingType() {
        return tradingType;
    }

    public void setTradingType(String tradingType) {
        this.tradingType = tradingType;
    }

    public Double getTradingMoney() {
        return tradingMoney;
    }

    public void setTradingMoney(Double tradingMoney) {
        this.tradingMoney = tradingMoney;
    }

    public String getOilType() {
        return oilType;
    }

    public void setOilType(String oilType) {
        this.oilType = oilType;
    }

    public Double getOilQuantity() {
        return oilQuantity;
    }

    public void setOilQuantity(Double oilQuantity) {
        this.oilQuantity = oilQuantity;
    }

    public Double getOilPrice() {
        return oilPrice;
    }

    public void setOilPrice(Double oilPrice) {
        this.oilPrice = oilPrice;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getTradingPosition() {
        return tradingPosition;
    }

    public void setTradingPosition(String tradingPosition) {
        this.tradingPosition = tradingPosition;
    }

    public Double getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(Double currentMileage) {
        this.currentMileage = currentMileage;
    }

    public String getRefuelingImageRecordId() {
        return refuelingImageRecordId;
    }

    public void setRefuelingImageRecordId(String refuelingImageRecordId) {
        this.refuelingImageRecordId = refuelingImageRecordId;
    }

    @Override
    public String toString() {
        return "RefuelingRecordPO [masterCardNumber=" + masterCardNumber + ", viceCardNumber=" + viceCardNumber
                + ", tradingTime=" + tradingTime + ", tradingType=" + tradingType + ", tradingMoney=" + tradingMoney
                + ", oilType=" + oilType + ", oilQuantity=" + oilQuantity + ", oilPrice=" + oilPrice + ", balance="
                + balance + ", tradingPosition=" + tradingPosition + ", currentMileage=" + currentMileage
                + ", refuelingImageRecordId=" + refuelingImageRecordId + "]";
    }

}
