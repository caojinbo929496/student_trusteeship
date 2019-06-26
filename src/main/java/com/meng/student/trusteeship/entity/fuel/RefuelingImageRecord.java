package com.meng.student.trusteeship.entity.fuel;

import java.util.Date;

/**
 * 安卓端上传的的加油记录的实体类
 *
 * @author Administrator
 */
public class RefuelingImageRecord {
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

    public RefuelingImageRecord(String uuid, Date tradingTime, String imagePath, String carNumber,
                                Double currentMileage) {
        super();
        this.uuid = uuid;
        this.tradingTime = tradingTime;
        this.imagePath = imagePath;
        this.carNumber = carNumber;
        this.currentMileage = currentMileage;
    }

    public RefuelingImageRecord() {
        super();
    }

    @Override
    public String toString() {
        return "AndroidRefuelingRecordPO [uuid=" + uuid + ", tradingTime=" + tradingTime + ", imagePath=" + imagePath
                + ", carNumber=" + carNumber + ", currentMileage=" + currentMileage + "]";
    }

}
