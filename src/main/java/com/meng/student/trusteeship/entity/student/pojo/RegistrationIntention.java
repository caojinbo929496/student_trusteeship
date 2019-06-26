package com.meng.student.trusteeship.entity.student.pojo;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 学生报名意向
 */
public class RegistrationIntention {

    private String id;

    private String studentId;

    private Integer intention;

    private BigDecimal deposit;

    private Boolean isRenew;

    private String contactResult;

    private Date contactTime;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getIntention() {
        return intention;
    }

    public void setIntention(Integer intention) {
        this.intention = intention;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public Boolean getRenew() {
        return isRenew;
    }

    public void setRenew(Boolean renew) {
        isRenew = renew;
    }

    public String getContactResult() {
        return contactResult;
    }

    public void setContactResult(String contactResult) {
        this.contactResult = contactResult;
    }

    public Date getContactTime() {
        return contactTime;
    }

    public void setContactTime(Date contactTime) {
        this.contactTime = contactTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
