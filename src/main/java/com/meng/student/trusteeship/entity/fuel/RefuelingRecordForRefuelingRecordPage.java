package com.meng.student.trusteeship.entity.fuel;

import java.util.Date;

/**
 * 传给加油记录页面的交易记录实体类
 *
 * @author weiyangjun
 * @date 2018/03/21 11:13
 */
public class RefuelingRecordForRefuelingRecordPage {
    /**
     * 该加油卡所属城市
     */
    private String city;
    /**
     * 车牌号
     */
    private String carNumber;
    /**
     * 车辆品牌
     */
    private String carBrand;

    /**
     * 车辆类别
     */
    private String vehicleType;
    /**
     * 加油司机驾照编号
     */
    private String refuelingDriverNumber;
    /**
     * 加油司机名字
     */
    private String refuelingDriverName;
    /**
     * 上次录入里程数
     */
    private Double lastMileage;
    /**
     * 该加油记录的里程数
     */
    private Double currentMileage;
    /**
     * 加油发生时刻
     */
    private Date refuelingTime;

    /**
     * 加油发生时刻
     */
    private Long refuelingTimeMilis;
    /**
     * 加油金额
     */
    private Double refuelingMoney;
    /**
     * 加油地点
     */
    private String tradingPosition;
    /**
     * 加油拍照图片url
     */
    private String imgUrl;


    /**
     * 司机上传的加油发生时刻
     */
    private Date falseRefuelingTime;

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

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getRefuelingDriverNumber() {
        return refuelingDriverNumber;
    }

    public void setRefuelingDriverNumber(String refuelingDriverNumber) {
        this.refuelingDriverNumber = refuelingDriverNumber;
    }

    public String getRefuelingDriverName() {
        return refuelingDriverName;
    }

    public void setRefuelingDriverName(String refuelingDriverName) {
        this.refuelingDriverName = refuelingDriverName;
    }

    public Double getLastMileage() {
        return lastMileage;
    }

    public void setLastMileage(Double lastMileage) {
        this.lastMileage = lastMileage;
    }

    public Double getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(Double currentMileage) {
        this.currentMileage = currentMileage;
    }

    public Date getRefuelingTime() {
        return refuelingTime;
    }

    public void setRefuelingTime(Date refuelingTime) {
        this.refuelingTime = refuelingTime;
    }

    public Double getRefuelingMoney() {
        return refuelingMoney;
    }

    public void setRefuelingMoney(Double refuelingMoney) {
        this.refuelingMoney = refuelingMoney;
    }

    public String getTradingPosition() {
        return tradingPosition;
    }

    public void setTradingPosition(String tradingPosition) {
        this.tradingPosition = tradingPosition;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Date getFalseRefuelingTime() {
        return falseRefuelingTime;
    }

    public void setFalseRefuelingTime(Date falseRefuelingTime) {
        this.falseRefuelingTime = falseRefuelingTime;
    }

    public RefuelingRecordForRefuelingRecordPage() {
    }

    public RefuelingRecordForRefuelingRecordPage(String city, String carNumber, String carBrand, String vehicleType, String refuelingDriverNumber, String refuelingDriverName, Double lastMileage, Double currentMileage, Date refuelingTime, Double refuelingMoney, String tradingPosition, String imgUrl, Date falseRefuelingTime) {
        this.city = city;
        this.carNumber = carNumber;
        this.carBrand = carBrand;
        this.vehicleType = vehicleType;
        this.refuelingDriverNumber = refuelingDriverNumber;
        this.refuelingDriverName = refuelingDriverName;
        this.lastMileage = lastMileage;
        this.currentMileage = currentMileage;
        this.refuelingTime = refuelingTime;
        this.refuelingMoney = refuelingMoney;
        this.tradingPosition = tradingPosition;
        this.imgUrl = imgUrl;
        this.falseRefuelingTime = falseRefuelingTime;
    }

    public Long getRefuelingTimeMilis() {
        return refuelingTimeMilis;
    }

    public void setRefuelingTimeMilis(Long refuelingTimeMilis) {
        this.refuelingTimeMilis = refuelingTimeMilis;
    }

    @Override
    public String toString() {
        return "RefuelingRecordForRefuelingRecordPage{" +
                "city='" + city + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", carBrand='" + carBrand + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", refuelingDriverNumber='" + refuelingDriverNumber + '\'' +
                ", refuelingDriverName='" + refuelingDriverName + '\'' +
                ", lastMileage=" + lastMileage +
                ", currentMileage=" + currentMileage +
                ", refuelingTime=" + refuelingTime +
                ", refuelingMoney=" + refuelingMoney +
                ", tradingPosition='" + tradingPosition + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", falseRefuelingTime=" + falseRefuelingTime +
                '}';
    }
}
