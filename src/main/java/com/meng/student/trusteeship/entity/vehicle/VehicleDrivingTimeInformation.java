package com.meng.student.trusteeship.entity.vehicle;


import java.util.Date;

/**
 * @author caojinbo
 * @since 2018.3.13
 * 车辆行驶时刻信息
 */
public class VehicleDrivingTimeInformation {

    /**
     * 唯一主键id
     */
    private String id;
    /**
     * 驾驶证id
     */
    private String patenteId;
    /**
     * 驾驶证id
     */
    private String patenteNumber;
    /**
     * 车辆启动时间
     */
    private Date startTime;
    /**
     * 车辆归还时间
     */
    private Date stopTime;
    /**
     * 车牌号
     */
    private String carNumber;
    /**
     * 车辆对应的唯一主键id
     */
    private String carId;

    public String getPatenteNumber() {
        return patenteNumber;
    }

    public void setPatenteNumber(String patenteNumber) {
        this.patenteNumber = patenteNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatenteId() {
        return patenteId;
    }

    public void setPatenteId(String patenteId) {
        this.patenteId = patenteId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

}
