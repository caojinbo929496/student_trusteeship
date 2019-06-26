package com.meng.student.trusteeship.entity.vehicle;

public class SimpleDriverTimeInfo {
    /**
     * 车牌号
     */
    private String carNumber;
    /**
     * 司机驾驶证
     */
    private String patenteNumber;
    /**
     * 状态 （0:上车， 1:下车两个字段）
     */
    private Byte status;

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getPatenteNumber() {
        return patenteNumber;
    }

    public void setPatenteNumber(String patenteNumber) {
        this.patenteNumber = patenteNumber;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
