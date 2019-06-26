package com.meng.student.trusteeship.entity.vehicle.dto;

public class ManagerCarSendDTO {
    /**
     * 城市
     */
    private String city;
    /**
     * 仓库
     */
    private String warehouse;
    /**
     * 车牌号
     */
    private String carNumber;
    /**
     * 驾驶证编号
     */
    private String patenteNumber;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

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
}
