package com.meng.student.trusteeship.entity.vehicle.vo;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 车辆查询界面的条件
 *
 * @author caojinbo
 * @since 2018.3.27
 */
public class VehicleConditionQueryVO {
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
     * 车辆状态
     */
    private Boolean vehicleStatus;
    /**
     * 保险开始时间，时间包装
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date insuranceStartDate;
    private Long insuranceStartDateView;

    /**
     * 保险结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date insuranceStopDate;
    private Long insuranceStopDateView;

    /**
     * 违章状态
     */
    private Boolean violationStatus;
    /**
     * 年检状态
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date carCheckStartDate;
    private Long carCheckStartDateView;

    /**
     * 年检日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date carCheckStopDate;
    private Long carCheckStopDateView;
    /**
     * 保险是否为空
     */
    private Integer insuranceIsNull;

    /**
     * 年检是否为空
     */
    private Integer carCheckIsNull;

    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 页面大小
     */
    private Integer pageSize;

    public Integer getInsuranceIsNull() {
        return insuranceIsNull;
    }

    public void setInsuranceIsNull(Integer insuranceIsNull) {
        this.insuranceIsNull = insuranceIsNull;
    }

    public Integer getCarCheckIsNull() {
        return carCheckIsNull;
    }

    public void setCarCheckIsNull(Integer carCheckIsNull) {
        this.carCheckIsNull = carCheckIsNull;
    }

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

    public Boolean getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(Boolean vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public Date getInsuranceStartDate() {
        return insuranceStartDate;
    }

    public void setInsuranceStartDate(Date insuranceStartDate) {
        this.insuranceStartDate = insuranceStartDate;
    }

    public Date getInsuranceStopDate() {
        return insuranceStopDate;
    }

    public void setInsuranceStopDate(Date insuranceStopDate) {
        this.insuranceStopDate = insuranceStopDate;
    }

    public Boolean getViolationStatus() {
        return violationStatus;
    }

    public void setViolationStatus(Boolean violationStatus) {
        this.violationStatus = violationStatus;
    }

    public Date getCarCheckStartDate() {
        return carCheckStartDate;
    }

    public void setCarCheckStartDate(Date carCheckStartDate) {
        this.carCheckStartDate = carCheckStartDate;
    }

    public Date getCarCheckStopDate() {
        return carCheckStopDate;
    }

    public void setCarCheckStopDate(Date carCheckStopDate) {
        this.carCheckStopDate = carCheckStopDate;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getInsuranceStartDateView() {
        return insuranceStartDateView;
    }

    public void setInsuranceStartDateView(Long insuranceStartDateView) {
        this.insuranceStartDateView = insuranceStartDateView;
    }

    public Long getInsuranceStopDateView() {
        return insuranceStopDateView;
    }

    public void setInsuranceStopDateView(Long insuranceStopDateView) {
        this.insuranceStopDateView = insuranceStopDateView;
    }

    public Long getCarCheckStartDateView() {
        return carCheckStartDateView;
    }

    public void setCarCheckStartDateView(Long carCheckStartDateView) {
        this.carCheckStartDateView = carCheckStartDateView;
    }

    public Long getCarCheckStopDateView() {
        return carCheckStopDateView;
    }

    public void setCarCheckStopDateView(Long carCheckStopDateView) {
        this.carCheckStopDateView = carCheckStopDateView;
    }
}
