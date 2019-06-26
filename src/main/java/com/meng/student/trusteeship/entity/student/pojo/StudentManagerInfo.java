package com.meng.student.trusteeship.entity.student.pojo;

import java.math.BigDecimal;

public class StudentManagerInfo {

    private String studentId;

    private String studentName;

    private Integer studentPhone;

    private String schoolClass;

    private String parentName1;

    private Integer parentPhone;

    private Integer intention;

    private BigDecimal deposit;

    private BigDecimal paymentAmount;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(Integer studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(String schoolClass) {
        this.schoolClass = schoolClass;
    }

    public String getParentName1() {
        return parentName1;
    }

    public void setParentName1(String parentName1) {
        this.parentName1 = parentName1;
    }

    public Integer getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(Integer parentPhone) {
        this.parentPhone = parentPhone;
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

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
