package com.meng.student.trusteeship.entity.fuel;

import java.util.Date;

/**
 * 加油卡实体类
 *
 * @author weiyangjun
 */
public class FuelCard {
    /**
     * 主卡号
     */
    private String masterCardNumber;
    /**
     * 副卡号
     */
    private String viceCardNumber;
    /**
     * 加油卡查询密码
     */
    private String password;
    /**
     * 油卡类型，0表示多用户卡主卡，1表示多用户卡副卡
     */
    private Byte cardType;
    /**
     * 加油卡余额
     */
    private Double balance;
    /**
     * 加油卡添加时间
     */
    private Date addingTime;

    /**
     * 加油卡添加时间
     */
    private Long addingTimeMilis;
    /**
     * 上次充值日期
     */
    private Date lastRechargeTime;


    /**
     * 上次充值日期
     */
    private Long lastRechargeTimeMilis;
    /**
     * 绑定的车牌号码
     */
    private String bundlePlateNumber;

    /**
     * 车辆所属城市
     */
    private String city;

    /**
     * 油卡商家,0标识中石油，1表示中石化
     */
    private Integer cardMerchant;

    /**
     * 加油卡uuid
     */
    private String uuid;

    public Long getAddingTimeMilis() {
        return addingTimeMilis;
    }

    public void setAddingTimeMilis(Long addingTimeMilis) {
        this.addingTimeMilis = addingTimeMilis;
    }

    public Long getLastRechargeTimeMilis() {
        return lastRechargeTimeMilis;
    }

    public void setLastRechargeTimeMilis(Long lastRechargeTimeMilis) {
        this.lastRechargeTimeMilis = lastRechargeTimeMilis;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getCardType() {
        return cardType;
    }

    public void setCardType(Byte cardType) {
        this.cardType = cardType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getAddingTime() {
        return addingTime;
    }

    public void setAddingTime(Date addingTime) {
        this.addingTime = addingTime;
    }

    public Date getLastRechargeTime() {
        return lastRechargeTime;
    }

    public void setLastRechargeTime(Date lastRechargeTime) {
        this.lastRechargeTime = lastRechargeTime;
    }

    public String getBundlePlateNumber() {
        return bundlePlateNumber;
    }

    public void setBundlePlateNumber(String bundlePlateNumber) {
        this.bundlePlateNumber = bundlePlateNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCardMerchant() {
        return cardMerchant;
    }

    public void setCardMerchant(Integer cardMerchant) {
        this.cardMerchant = cardMerchant;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public FuelCard(String masterCardNumber) {
        this.masterCardNumber = masterCardNumber;
    }

    public FuelCard() {
    }
}
