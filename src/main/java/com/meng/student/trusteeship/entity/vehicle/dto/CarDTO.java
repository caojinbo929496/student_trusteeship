package com.meng.student.trusteeship.entity.vehicle.dto;

import java.util.Date;

public class CarDTO {
    /**
     * 注册日期
     */
    private Date registrationTime;
    /**
     * 有效日期
     */
    private Date validityDate;
    /**
     * 车辆识别号
     */
    private String vin;
    /**
     * 发动机号
     */
    private String engineNo;
    /**
     * 车辆id
     */
    private Long id;
    /**
     * 车辆名称
     */
    private String name;
    /**
     * 车牌号
     */
    private String licensePlate;
    /**
     * 车辆装载件数
     */
    private Float capacity;
    /**
     * 车辆状态（1：启动；2：停用）
     */
    private Integer state;
    /**
     * 所在省份
     */
    private String province;
    /**
     * 所在城市
     */
    private String city;
    /**
     * 所在区县
     */
    private String country;
    /**
     * 城市id
     */
    private Integer cityId;
    /**
     * 1.0的id
     */
    private String oldId;
    /**
     * 车型（0：货车；1：面包车）
     */
    private Integer carMode;
    /**
     * 车辆分类（0：自有包邮；1：自有不包邮；2：外包）
     */
    private Integer carClass;
    /**
     * 最大载重
     */
    private Float maximumPayload;
    /**
     * 最大容积（升）
     */
    private Float maximumVolume;
    /**
     * 车主
     */
    private String carOwner;
    /**
     * 车主电话
     */
    private String carOwnerMobileNo;
    /**
     * 所属仓库id
     */
    private Integer warehouseId;

    /**
     * 所属仓库
     */
    private String warehouse;
    /**
     * 用户id
     */
    private Integer createUserId;
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
    private Date dareOfIssue;
    /**
     * 车标
     */
    private String brandModelNumber;

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
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

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Float getCapacity() {
        return capacity;
    }

    public void setCapacity(Float capacity) {
        this.capacity = capacity;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getOldId() {
        return oldId;
    }

    public void setOldId(String oldId) {
        this.oldId = oldId;
    }

    public Integer getCarMode() {
        return carMode;
    }

    public void setCarMode(Integer carMode) {
        this.carMode = carMode;
    }

    public Integer getCarClass() {
        return carClass;
    }

    public void setCarClass(Integer carClass) {
        this.carClass = carClass;
    }

    public Float getMaximumPayload() {
        return maximumPayload;
    }

    public void setMaximumPayload(Float maximumPayload) {
        this.maximumPayload = maximumPayload;
    }

    public Float getMaximumVolume() {
        return maximumVolume;
    }

    public void setMaximumVolume(Float maximumVolume) {
        this.maximumVolume = maximumVolume;
    }

    public String getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(String carOwner) {
        this.carOwner = carOwner;
    }

    public String getCarOwnerMobileNo() {
        return carOwnerMobileNo;
    }

    public void setCarOwnerMobileNo(String carOwnerMobileNo) {
        this.carOwnerMobileNo = carOwnerMobileNo;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
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

    public Date getDareOfIssue() {
        return dareOfIssue;
    }

    public void setDareOfIssue(Date dareOfIssue) {
        this.dareOfIssue = dareOfIssue;
    }

    public String getBrandModelNumber() {
        return brandModelNumber;
    }

    public void setBrandModelNumber(String brandModelNumber) {
        this.brandModelNumber = brandModelNumber;
    }
}
