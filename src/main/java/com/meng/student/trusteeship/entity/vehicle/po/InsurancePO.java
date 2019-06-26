package com.meng.student.trusteeship.entity.vehicle.po;


import java.math.BigDecimal;
import java.util.Date;

/**
 * @author caojinbo
 * @since 2018.3.13
 * 车辆保险
 */
public class InsurancePO {

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
     * 保险对应的车辆所属的城市
     */
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

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
}
