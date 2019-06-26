package com.meng.student.trusteeship.entity.student.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 缴费信息
 */
public class Payment {

    private String id;

    private String studentId;

    private BigDecimal paymentAmount;

    private Boolean isOffer;

    private String referrer;

    private BigDecimal offerAmount;

    private Date paymentTime;

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

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Boolean getOffer() {
        return isOffer;
    }

    public void setOffer(Boolean offer) {
        isOffer = offer;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public BigDecimal getOfferAmount() {
        return offerAmount;
    }

    public void setOfferAmount(BigDecimal offerAmount) {
        this.offerAmount = offerAmount;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }
}
