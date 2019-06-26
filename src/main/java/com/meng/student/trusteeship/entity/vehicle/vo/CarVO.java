package com.meng.student.trusteeship.entity.vehicle.vo;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author caojinbo
 * @since 2018.3.13
 * 车辆基本信息
 */
public class CarVO implements Serializable {
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
     * 车牌号
     */
    private String carNumber;

    /**
     * 地址
     */
    private String address;

    /**
     * 使用性质
     */
    private String natureOfUse;

    /**
     * 发证日期
     */
    private Long dareOfIssue;

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
     * 驾驶证注册日期
     */
    private Long registrationTime;
    /**
     * 驾驶证失效日期
     */
    private Long validityDate;

    /**
     * 车辆价格
     */
    private BigDecimal vehiclePrice;

    /**
     * 车辆当前状态
     */
    private Boolean status;

    /**
     * 年检状态
     */
    private String annualInspectionStatus;

    /**
     * 上传时间
     */
    private Long uploadTime;

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

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
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

    public Long getDareOfIssue() {
        return dareOfIssue;
    }

    public void setDareOfIssue(Long dareOfIssue) {
        this.dareOfIssue = dareOfIssue;
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

    public Long getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Long registrationTime) {
        this.registrationTime = registrationTime;
    }

    public Long getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(Long validityDate) {
        this.validityDate = validityDate;
    }

    public BigDecimal getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(BigDecimal vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getAnnualInspectionStatus() {
        return annualInspectionStatus;
    }

    public void setAnnualInspectionStatus(String annualInspectionStatus) {
        this.annualInspectionStatus = annualInspectionStatus;
    }

    public Long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Long uploadTime) {
        this.uploadTime = uploadTime;
    }
}
