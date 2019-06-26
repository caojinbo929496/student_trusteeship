package com.meng.student.trusteeship.entity.student.vo;

import java.util.Date;

public class StudentQueryVo {

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
     * 学生名称
     */
    private String studentName;
    /**
     * 注册时间 起始时间
     */
    private Date registrationStartTime;

    /**
     * 注册时间 结束时间
     */
    private Date registrationEndTime;
    /**
     * 意向
     */
    private Integer intention;
    /**
     * 家长名称
     */
    private String parentName;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getRegistrationStartTime() {
        return registrationStartTime;
    }

    public void setRegistrationStartTime(Date registrationStartTime) {
        this.registrationStartTime = registrationStartTime;
    }

    public Date getRegistrationEndTime() {
        return registrationEndTime;
    }

    public void setRegistrationEndTime(Date registrationEndTime) {
        this.registrationEndTime = registrationEndTime;
    }

    public Integer getIntention() {
        return intention;
    }

    public void setIntention(Integer intention) {
        this.intention = intention;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
