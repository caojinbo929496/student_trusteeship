package com.meng.student.trusteeship.entity.fuel;

/**
 * 多条件查询加油卡参数包装类
 *
 * @author weiyangjun
 */
public class GetFuelCardPOParams {
    /**
     * 主卡号
     */
    private String masterCardNumber;
    /**
     * 副卡号
     */
    private String viceCardNumber;
    /**
     * 0表示中石油的卡，1表示中石化的卡
     */
    private String cardMerchant;

    /**
     * 绑定的车牌号码
     */
    private String bundlePlateNumber;
    /**
     * 加油卡绑定的车辆所属城市
     */
    private String city;

    /**
     * 加油卡是否已绑定车辆
     */
    private String ifBundle;
    /**
     * 要查询记录的起始下标
     */
    private Integer start;
    /**
     * 要查询的记录条数
     */
    private Integer length;

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

    public String getCardMerchant() {
        return cardMerchant;
    }

    public void setCardMerchant(String cardMerchant) {
        this.cardMerchant = cardMerchant;
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

    public String getIfBundle() {
        return ifBundle;
    }

    public void setIfBundle(String ifBundle) {
        this.ifBundle = ifBundle;
    }

    public GetFuelCardPOParams() {
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public GetFuelCardPOParams(String masterCardNumber, String viceCardNumber, String cardMerchant, String bundlePlateNumber, String city, String ifBundle, Integer start, Integer length) {
        this.masterCardNumber = masterCardNumber;
        this.viceCardNumber = viceCardNumber;
        this.cardMerchant = cardMerchant;
        this.bundlePlateNumber = bundlePlateNumber;
        this.city = city;
        this.ifBundle = ifBundle;
        this.start = start;
        this.length = length;
    }

    @Override
    public String toString() {
        return "GetFuelCardPOParams{" +
                "masterCardNumber='" + masterCardNumber + '\'' +
                ", viceCardNumber='" + viceCardNumber + '\'' +
                ", cardMerchant=" + cardMerchant +
                ", bundlePlateNumber='" + bundlePlateNumber + '\'' +
                ", city='" + city + '\'' +
                ", ifBundle='" + ifBundle + '\'' +
                ", start=" + start +
                ", length=" + length +
                '}';
    }
}
