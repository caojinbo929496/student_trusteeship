package com.meng.student.trusteeship.entity.fuel.po;

/**
 * @author weiyangjun
 * @date 2018/03/21 11:13
 */
public class UpdateFuelCardParams {
    private String uuid;
    private String masterCardNumber;
    private String viceCardNumber;
    private Byte cardMerchant;
    private Byte cardType;
    private String password;

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

    public Byte getCardMerchant() {
        return cardMerchant;
    }

    public void setCardMerchant(Byte cardMerchant) {
        this.cardMerchant = cardMerchant;
    }

    public Byte getCardType() {
        return cardType;
    }

    public void setCardType(Byte cardType) {
        this.cardType = cardType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
