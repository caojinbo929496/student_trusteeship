package com.meng.student.trusteeship.entity.vehicle.po;

import java.util.Date;

/**
 * 车辆信息补全PO类
 *
 * @author weiyangjun
 * @date 2018/03/21 11:13
 */
public class ToBeCompeteledCarPO {
    /**
     * car表中车辆id
     */
    private String id;
    /**
     * 车辆所属城市
     */
    private String city;
    /**
     * 车牌号码
     */
    private String carNumber;
    /**
     * 车辆品牌
     */
    private String brandModelNumber;
    /**
     * 车辆类型
     */
    private String vehicleType;

    /**
     * 所属仓库
     */
    private String warehouse;

    /**
     * 保险开始时间
     */
    private Date startDate;
    /**
     * 保险结束时间
     */
    private Date stopDate;

    /**
     * 保险费用
     */
    private Double insuranceFee;

    /**
     * 车辆购买价格
     */
    private Double vehiclePrice;

    /**
     * 主卡号
     */
    private String masterCardNumber;
    /**
     * 副卡号
     */
    private String viceCardNumber;
    /**
     * 供应商,0代表中石油、1代表中石化
     */
    private Integer cardMerchant;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getBrandModelNumber() {
        return brandModelNumber;
    }

    public void setBrandModelNumber(String brandModelNumber) {
        this.brandModelNumber = brandModelNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public Double getInsuranceFee() {
        return insuranceFee;
    }

    public void setInsuranceFee(Double insuranceFee) {
        this.insuranceFee = insuranceFee;
    }

    public Double getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(Double vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
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

    public ToBeCompeteledCarPO() {
    }

    public ToBeCompeteledCarPO(String id, String city, String carNumber, String brandModelNumber, String vehicleType, String warehouse, Date startDate, Date stopDate, Double insuranceFee, Double vehiclePrice, String masterCardNumber, String viceCardNumber, Integer cardMerchant) {
        this.id = id;
        this.city = city;
        this.carNumber = carNumber;
        this.brandModelNumber = brandModelNumber;
        this.vehicleType = vehicleType;
        this.warehouse = warehouse;
        this.startDate = startDate;
        this.stopDate = stopDate;
        this.insuranceFee = insuranceFee;
        this.vehiclePrice = vehiclePrice;
        this.masterCardNumber = masterCardNumber;
        this.viceCardNumber = viceCardNumber;
        this.cardMerchant = cardMerchant;
    }

    @Override
    public String toString() {
        return "ToBeCompeteledCarPO{" +
                "id='" + id + '\'' +
                ", city='" + city + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", brandModelNumber='" + brandModelNumber + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", warehouse='" + warehouse + '\'' +
                ", startDate=" + startDate +
                ", stopDate=" + stopDate +
                ", insuranceFee=" + insuranceFee +
                ", vehiclePrice=" + vehiclePrice +
                ", masterCardNumber='" + masterCardNumber + '\'' +
                ", viceCardNumber='" + viceCardNumber + '\'' +
                ", cardMerchant=" + cardMerchant +
                '}';
    }
}