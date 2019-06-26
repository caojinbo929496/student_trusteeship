package com.meng.student.trusteeship.entity.fuel.po;

import java.util.Date;

/**
 * 加油卡PO类
 *
 * @author weiyangjun
 */
public class FuelCardPO {
    private String uuid;
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
     * 油卡类型，0表示记名单用户卡，1表示记名多用户卡，2表示不记名单用户卡
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
     * 上次充值日期
     */
    private Date lastRechargeTime;

    /**
     * 绑定的车牌号码
     */
    private String bundlePlateNumber;

    /**
     * 油卡商家,0标识中石油，1表示中石化
     */
    private Integer cardMerchant;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public Integer getCardMerchant() {
        return cardMerchant;
    }

    public void setCardMerchant(Integer cardMerchant) {
        this.cardMerchant = cardMerchant;
    }

    public FuelCardPO(String uuid) {
        this.uuid = uuid;
    }

    public FuelCardPO() {
    }

    public FuelCardPO(String uuid, String masterCardNumber, String viceCardNumber, String password, Byte cardType, Double balance, Date addingTime, Date lastRechargeTime, String bundlePlateNumber, Integer cardMerchant) {
        this.uuid = uuid;
        this.masterCardNumber = masterCardNumber;
        this.viceCardNumber = viceCardNumber;
        this.password = password;
        this.cardType = cardType;
        this.balance = balance;
        this.addingTime = addingTime;
        this.lastRechargeTime = lastRechargeTime;
        this.bundlePlateNumber = bundlePlateNumber;
        this.cardMerchant = cardMerchant;
    }

    @Override
    public String toString() {
        return "FuelCardPO{" +
                "uuid='" + uuid + '\'' +
                ", masterCardNumber='" + masterCardNumber + '\'' +
                ", viceCardNumber='" + viceCardNumber + '\'' +
                ", password='" + password + '\'' +
                ", cardType=" + cardType +
                ", balance=" + balance +
                ", addingTime=" + addingTime +
                ", lastRechargeTime=" + lastRechargeTime +
                ", bundlePlateNumber='" + bundlePlateNumber + '\'' +
                ", cardMerchant=" + cardMerchant +
                '}';
    }
}
