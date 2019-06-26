package com.meng.student.trusteeship.entity;


import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;


/**
 * @author fengqigui
 * @description 查询条件, 保养查询条件。
 * @date 2018/03/18 18:28
 */

public class QueryCondition {


    /**
     * 行驶证有效期范围（开始时间）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    private Long startDateView;

    /**
     * 行驶证有效期范围（结束时间）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private Long endDateView;

    /**
     * 城市名字
     */
    private String cityName;

    /**
     * 仓库名字
     */
    private String repository;

    /**
     * 车牌号码
     */
    private String carNumber;

    /**
     * 送保人
     */
    private String patenteName;

    /**
     * 页面尺寸
     */
    private Integer pageSize;

    /**
     * 当前页数
     */
    private Integer currentPage;

    /**
     * 总条数
     */
    private Integer counts;

    public QueryCondition() {
    }

    public QueryCondition(Integer pageSize, Integer currentPage) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getPatenteName() {
        return patenteName;
    }

    public void setPatenteName(String patenteName) {
        this.patenteName = patenteName;
    }

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

    @Override
    public String toString() {
        return "QueryCondition{" +
                "startDate=" + startDate +
                ", startDateView=" + startDateView +
                ", endDate=" + endDate +
                ", endDateView=" + endDateView +
                ", cityName='" + cityName + '\'' +
                ", repository='" + repository + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", patenteName='" + patenteName + '\'' +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", counts=" + counts +
                '}';
    }
}
