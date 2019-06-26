package com.meng.student.trusteeship.entity.vehicle.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class InsuranceAndImagesVO implements Serializable {
    /**
     * 对应的唯一主键id
     */
    private String id;
    /**
     * 保险有效开始日期
     */
    private Long startDate;
    /**
     * 保险的停止日期
     */
    private Long stopDate;
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
     * 操作人姓名
     */
    private String operator;

    /**
     *
     */
    private List insuranceImagePOS;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getStopDate() {
        return stopDate;
    }

    public void setStopDate(Long stopDate) {
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public List getInsuranceImagePOS() {
        return insuranceImagePOS;
    }

    public void setInsuranceImagePOS(List insuranceImagePOS) {
        this.insuranceImagePOS = insuranceImagePOS;
    }
}
