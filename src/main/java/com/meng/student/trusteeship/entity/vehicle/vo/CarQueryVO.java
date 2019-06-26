package com.meng.student.trusteeship.entity.vehicle.vo;

import java.util.Date;


/**
 * @author caojinbo
 * @since 2018.3.18
 * 车辆分页查询
 */
public class CarQueryVO {

    private Integer page;

    private Integer limit;
    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 页面大小
     */
    private Integer pageSize;
    /**
     * 城市
     */
    private String city;
    /**
     * 车牌号
     */
    private String carNumber;
    /**
     * 仓库
     */
    private String warehouse;
    /**
     * 车辆状态
     */
    private Boolean status;
    /**
     * 上传时间（相当于注册时间）
     */
    private Date registrationTime;
    /**
     * 查询年检有效期  起始时间
     */
    private Date startDate;
    /**
     * 查询年检有效期  结束时间
     */
    private Date endDate;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
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

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
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

    /**
     * 获取
     *
     * @return page
     */
    public Integer getPage() {
        return this.page;
    }

    /**
     * 设置
     *
     * @param page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * 获取
     *
     * @return limit
     */
    public Integer getLimit() {
        return this.limit;
    }

    /**
     * 设置
     *
     * @param limit
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "CarQueryVO{" +
                "page=" + page +
                ", limit=" + limit +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", city='" + city + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", warehouse='" + warehouse + '\'' +
                ", status=" + status +
                ", registrationTime=" + registrationTime +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
