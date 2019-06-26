package com.meng.student.trusteeship.entity.vehicle.po;

import java.math.BigDecimal;
import java.util.Date;

public class CarAllInfoPO {
    /**
     * uuid 唯一主键
     */
    private String id;
    /**
     * 车辆所属城市
     */
    private String city;
    /**
     * 车标
     */
    private String brandModelNumber;
    /**
     * 车主
     */
    private String owners;
    /**
     * 车辆所属仓库
     */
    private String warehouse;
    /**
     * 车辆当前状态
     */
    private Boolean status;
    /**
     * 车牌号
     */
    private String carNumber;
    /**
     * 车辆类型
     */
    private String vehicleType;
    /**
     * 引擎号
     */
    private String engineNumber;
    /**
     * 车架号
     */
    private String frameNumber;
    /**
     * 车辆价格
     */
    private BigDecimal vehiclePrice;
    /**
     * 驾驶证注册日期
     */
    private Date registrationTime;
    /**
     * 驾驶证失效日期
     */
    private Date validityDate;

    /**
     * 发证日期
     */
    private Date dareOfIssue;

    /**
     * 地址
     */
    private String address;

    /**
     * 使用性质
     */
    private String natureOfUse;
    /**
     * 当前车辆的行驶里程数
     */
    private BigDecimal currentMileage;
    /**
     * 当前车辆的副卡号
     */
    private String viceCardNumber;
    /**
     * 未处理违章记录数
     */
    private String violationNumber;
    /**
     * 保险到期时间
     */
    private Date stopDate;

    /**
     * 年检状态
     */
    private String annualInspectionStatus;

    /**
     * 上传时间
     */
    private Date uploadTime;

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getAnnualInspectionStatus() {
        return annualInspectionStatus;
    }

    public void setAnnualInspectionStatus(String annualInspectionStatus) {
        this.annualInspectionStatus = annualInspectionStatus;
    }

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

    public String getBrandModelNumber() {
        return brandModelNumber;
    }

    public void setBrandModelNumber(String brandModelNumber) {
        this.brandModelNumber = brandModelNumber;
    }

    public String getOwners() {
        return owners;
    }

    public void setOwners(String owners) {
        this.owners = owners;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(String frameNumber) {
        this.frameNumber = frameNumber;
    }

    public BigDecimal getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(BigDecimal vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public Date getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    public Date getDareOfIssue() {
        return dareOfIssue;
    }

    public void setDareOfIssue(Date dareOfIssue) {
        this.dareOfIssue = dareOfIssue;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNatureOfUse() {
        return natureOfUse;
    }

    public void setNatureOfUse(String natureOfUse) {
        this.natureOfUse = natureOfUse;
    }

    public BigDecimal getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(BigDecimal currentMileage) {
        this.currentMileage = currentMileage;
    }

    public String getViceCardNumber() {
        return viceCardNumber;
    }

    public void setViceCardNumber(String viceCardNumber) {
        this.viceCardNumber = viceCardNumber;
    }

    public String getViolationNumber() {
        return violationNumber;
    }

    public void setViolationNumber(String violationNumber) {
        this.violationNumber = violationNumber;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }
}
