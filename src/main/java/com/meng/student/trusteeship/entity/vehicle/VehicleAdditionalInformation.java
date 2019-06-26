package com.meng.student.trusteeship.entity.vehicle;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 用于界面中录入车辆相关的保险信息采集
 *
 * @author caojinbo
 * @since 2018.3.17
 */
public class VehicleAdditionalInformation {
    /**
     * 绑定车辆的carNumber
     */
    private String carNumber;
    /**
     * 保险有效日期
     */
    private Long registrationTime;
    /**
     * 保险无效日期
     */
    private Long validityDate;
    /**
     * 保险费用
     */
    private BigDecimal insuranceFee;
    /**
     * 保险返还费用（可以为空）
     */
    private BigDecimal cashBackAmount;
    /**
     * 车辆价格
     */
    private BigDecimal vehiclePrice;

    public String getCarNumber() {

        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Long getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Long registrationTime) {
        this.registrationTime = registrationTime;
    }

    public Long getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(Long validityDate) {
        this.validityDate = validityDate;
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

    public BigDecimal getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(BigDecimal vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

}
