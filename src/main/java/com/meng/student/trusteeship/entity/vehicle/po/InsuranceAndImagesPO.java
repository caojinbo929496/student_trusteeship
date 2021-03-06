package com.meng.student.trusteeship.entity.vehicle.po;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class InsuranceAndImagesPO {
    /**
     * 对应的唯一主键id
     */
    private String id;
    /**
     * 保险有效开始日期
     */
    private Date startDate;
    /**
     * 保险的停止日期
     */
    private Date stopDate;
    /**
     * 保险总共费用
     */
    private BigDecimal insuranceFee;
    /**
     * 返现金额
     */
    private BigDecimal cashBackAmount;
    /**
     * 车牌号
     */
    private String carNumber;
    /**
     * 操作人id
     */
    private String operatorId;

    /**
     *
     */
    private List<InsuranceImagePO> insuranceImagePOS;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public BigDecimal getInsuranceFee() {
        return insuranceFee;
    }

    public void setInsuranceFee(BigDecimal insuranceFee) {
        this.insuranceFee = insuranceFee;
    }

    public BigDecimal getCashBackAmount() {
        return cashBackAmount;
    }

    public void setCashBackAmount(BigDecimal cashBackAmount) {
        this.cashBackAmount = cashBackAmount;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public List<InsuranceImagePO> getInsuranceImagePOS() {
        return insuranceImagePOS;
    }

    public void setInsuranceImagePOS(List<InsuranceImagePO> insuranceImagePOS) {
        this.insuranceImagePOS = insuranceImagePOS;
    }
}
