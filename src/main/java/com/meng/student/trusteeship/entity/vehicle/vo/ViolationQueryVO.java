package com.meng.student.trusteeship.entity.vehicle.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * 前端违章查询 附加属性
 *
 * @author caojinbo
 * @since 2017.3.21
 */
public class ViolationQueryVO {

    /**
     * 车牌号
     */
    private String carNumber;

    /**
     * 城市
     */
    private String city;

    /**
     * 仓库
     */
    private String warehouse;

    /**
     * 违章时间（精确到秒）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    private Long startDateView;
    /**
     * 违章时间（精确到秒）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    private Long endDateView;
    /**
     * 违章人
     */
    private String offender;

    /**
     * 驾驶证号
     */
    private String offenderNumber;

    /**
     * 违章处理状态 （0：未处理； 1：处理）
     */
    private Boolean status;
    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 页面大小
     */
    private Integer pageSize;

    private Integer queryStatus;

    public Long getStartDateView() {
        return startDateView;
    }

    public void setStartDateView(Long startDateView) {
        this.startDateView = startDateView;
    }

    public Long getEndDateView() {
        return endDateView;
    }

    public void setEndDateView(Long endDateView) {
        this.endDateView = endDateView;
    }

    public Integer getQueryStatus() {
        return queryStatus;
    }

    public void setQueryStatus(Integer queryStatus) {
        this.queryStatus = queryStatus;
    }

    public String getOffenderNumber() {
        return offenderNumber;
    }

    public void setOffenderNumber(String offenderNumber) {
        this.offenderNumber = offenderNumber;
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

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getOffender() {
        return offender;
    }

    public void setOffender(String offender) {
        this.offender = offender;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
