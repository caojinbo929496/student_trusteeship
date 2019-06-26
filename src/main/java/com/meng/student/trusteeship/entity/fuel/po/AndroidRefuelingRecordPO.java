package com.meng.student.trusteeship.entity.fuel.po;

import java.util.Date;

/**
 * 安卓端上传的加油记录PO类
 *
 * @author weiYangJun
 */
public class AndroidRefuelingRecordPO {
    /**
     * 记录uuid
     */
    private String uuid;
    /**
     * 加油时刻
     */
    private Date tradingTime;
    /**
     * 图片远程url
     */
    private String imagePath;
    /**
     * 加油卡绑定的车牌号
     */
    private String carNumber;
    /**
     * 加油时刻车辆里程数
     */
    private Double currentMileage;

    /**
     * 司机驾驶证编号
     */
    private String patenteNumber;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getTradingTime() {
        return tradingTime;
    }

    public void setTradingTime(Date tradingTime) {
        this.tradingTime = tradingTime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Double getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(Double currentMileage) {
        this.currentMileage = currentMileage;
    }

    public String getPatenteNumber() {
        return patenteNumber;
    }

    public void setPatenteNumber(String patenteNumber) {
        this.patenteNumber = patenteNumber;
    }

    public AndroidRefuelingRecordPO() {
    }

    public AndroidRefuelingRecordPO(String uuid, Date tradingTime, String imagePath, String carNumber, Double currentMileage, String patenteNumber) {
        this.uuid = uuid;
        this.tradingTime = tradingTime;
        this.imagePath = imagePath;
        this.carNumber = carNumber;
        this.currentMileage = currentMileage;
        this.patenteNumber = patenteNumber;
    }

    @Override
    public String toString() {
        return "AndroidRefuelingRecordPO{" +
                "uuid='" + uuid + '\'' +
                ", tradingTime=" + tradingTime +
                ", imagePath='" + imagePath + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", currentMileage=" + currentMileage +
                ", patenteNumber='" + patenteNumber + '\'' +
                '}';
    }
}
