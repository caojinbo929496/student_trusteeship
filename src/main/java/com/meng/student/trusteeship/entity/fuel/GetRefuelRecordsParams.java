package com.meng.student.trusteeship.entity.fuel;

import java.util.Date;

/**
 * 封装查询加油卡交易记录的参数的类
 *
 * @author weiyangjun
 * @date 2018/03/21 11:13
 */
public class GetRefuelRecordsParams {
    /**
     * 主卡号
     */
    private String masterCardNumber;
    /**
     * 副卡号
     */
    private String viceCardNumber;
    /**
     * 供应商类别，0代表中石油、1代表中石化
     */
    private String cardMerchant;
    /**
     * 记录开始时间
     */
    private Date startTime;
    /**
     * 记录开始时间
     */
    private Long startTimeMilis;
    /**
     * 记录结束时间
     */
    private Long endTimeMilis;
    /**
     * 记录截止时间
     */
    private Date endTime;
    /**
     * 开始下标
     */
    private Integer startIndex;
    /**
     * 要查询的记录数量
     */
    private Integer recordsNumber;
    /**
     * 要查询的交易类型，0代表加油、1代表圈存
     */
    private Integer tradeType;
    /**
     * 开始里程数
     */
    private Double startMileage;
    /**
     * 结束里程数
     */
    private Double endMileage;

    /**
     * 要查询记录的加油卡绑定的车辆所属的城市
     */
    private String city;

    /**
     * 加油记录对应的车牌号
     */
    private String carNumber;

    /**
     * 加油司机名字
     */
    private String driverName;

    /**
     * 加油记录对应的加油卡绑定的车辆所属的仓库
     */
    private String wareHouse;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {

        this.endTime = endTime;


    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getRecordsNumber() {
        return recordsNumber;
    }

    public void setRecordsNumber(Integer recordsNumber) {
        this.recordsNumber = recordsNumber;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public Double getStartMileage() {
        return startMileage;
    }

    public void setStartMileage(Double startMileage) {
        this.startMileage = startMileage;
    }

    public Double getEndMileage() {
        return endMileage;
    }

    public void setEndMileage(Double endMileage) {
        this.endMileage = endMileage;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {


        this.city = city;

    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(String wareHouse) {


        this.wareHouse = wareHouse;

    }

    public Long getStartTimeMilis() {
        return startTimeMilis;
    }

    public void setStartTimeMilis(Long startTimeMilis) {
        this.startTimeMilis = startTimeMilis;
    }

    public Long getEndTimeMilis() {
        return endTimeMilis;
    }

    public void setEndTimeMilis(Long endTimeMilis) {
        this.endTimeMilis = endTimeMilis;
    }

    public GetRefuelRecordsParams() {
    }

    public GetRefuelRecordsParams(String masterCardNumber, String viceCardNumber, String cardMerchant, Date startTime, Date endTime, Integer startIndex, Integer recordsNumber, Integer tradeType, Double startMileage, Double endMileage, String city, String carNumber, String driverName, String wareHouse) {
        this.masterCardNumber = masterCardNumber;
        this.viceCardNumber = viceCardNumber;
        this.cardMerchant = cardMerchant;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startIndex = startIndex;
        this.recordsNumber = recordsNumber;
        this.tradeType = tradeType;
        this.startMileage = startMileage;
        this.endMileage = endMileage;
        this.city = city;
        this.carNumber = carNumber;
        this.driverName = driverName;
        this.wareHouse = wareHouse;
    }

    @Override
    public String toString() {
        return "GetRefuelRecordsParams{" +
                "masterCardNumber='" + masterCardNumber + '\'' +
                ", viceCardNumber='" + viceCardNumber + '\'' +
                ", cardMerchant='" + cardMerchant + '\'' +
                ", startTime=" + startTime +
                ", startTimeMilis=" + startTimeMilis +
                ", endTimeMilis=" + endTimeMilis +
                ", endTime=" + endTime +
                ", startIndex=" + startIndex +
                ", recordsNumber=" + recordsNumber +
                ", tradeType=" + tradeType +
                ", startMileage=" + startMileage +
                ", endMileage=" + endMileage +
                ", city='" + city + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", driverName='" + driverName + '\'' +
                ", wareHouse='" + wareHouse + '\'' +
                '}';
    }
}
