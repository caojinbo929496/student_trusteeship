package com.meng.student.trusteeship.entity.vehicle;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 前端违章查询列表显示基本信息
 *
 * @author caojinbo
 * @since 2017.3.21
 */
public class ViolationUnity {
    /**
     * uuid唯一标识
     */
    private String id;
    /**
     * 车牌号
     */
    private String carNumber;
    /**
     * 违章时间（精确到秒）
     */
    private Long violationTime;
    /**
     * 违章地点
     */
    private String violationPlace;
    /**
     * 违章信息
     */
    private String violationInformation;
    /**
     * 扣分
     */
    private Integer deductMark;
    /**
     * 违章单位
     */
    private String processingUnit;
    /**
     * 罚款金额（可为空）
     */
    private BigDecimal penalty;
    /**
     * 对应车辆的id
     */
    private String carId;

    /**
     * 违法记录处理状态（默认为0未处理；1处理）
     */
    private Boolean status;
    /**
     * 违法人id (当传入对象的时候该字段不用理会)
     */
    private String offenderId;

    /**
     * 违章人
     */
    private String offender;
    /**
     * 城市
     */
    private String city;

    /**
     * 仓库
     */
    private String warehouse;

    /**
     * 驾驶证号
     */
    private String patenteNumber;

    public String getPatenteNumber() {
        return patenteNumber;
    }

    public void setPatenteNumber(String patenteNumber) {
        this.patenteNumber = patenteNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Long getViolationTime() {
        return violationTime;
    }

    public void setViolationTime(Long violationTime) {
        this.violationTime = violationTime;
    }

    public String getViolationPlace() {
        return violationPlace;
    }

    public void setViolationPlace(String violationPlace) {
        this.violationPlace = violationPlace;
    }

    public String getViolationInformation() {
        return violationInformation;
    }

    public void setViolationInformation(String violationInformation) {
        this.violationInformation = violationInformation;
    }

    public Integer getDeductMark() {
        return deductMark;
    }

    public void setDeductMark(Integer deductMark) {
        this.deductMark = deductMark;
    }

    public String getProcessingUnit() {
        return processingUnit;
    }

    public void setProcessingUnit(String processingUnit) {
        this.processingUnit = processingUnit;
    }

    public BigDecimal getPenalty() {
        return penalty;
    }

    public void setPenalty(BigDecimal penalty) {
        this.penalty = penalty;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getOffenderId() {
        return offenderId;
    }

    public void setOffenderId(String offenderId) {
        this.offenderId = offenderId;
    }

    public String getOffender() {
        return offender;
    }

    public void setOffender(String offender) {
        this.offender = offender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }
}
