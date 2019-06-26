package com.meng.student.trusteeship.entity.traffic;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author fengqigui
 * @description 车辆油耗统计
 * @date 2018/03/24 9:20
 */
public class OilWearMonitor {

    /**
     * 车辆的ID
     */
    private String carId;

    /**
     * 油耗记录ID
     */
    private String refuelingId;

    /**
     * 车牌号码
     */
    private String carNumber;

    /**
     * 车辆所属仓库
     */
    private String warehouse;

    /**
     * 此次里程数
     */
    private BigDecimal mileage;

    /**
     * 记录生成时间
     */
    private Date date;

    /**
     * 加油的费用
     */
    private BigDecimal free;

    /**
     * 此段时间加油总费用
     */
    private BigDecimal totalFree;

    /**
     * 此段时间行驶的里程数
     */
    private BigDecimal totalMileage;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getRefuelingId() {
        return refuelingId;
    }

    public void setRefuelingId(String refuelingId) {
        this.refuelingId = refuelingId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getFree() {
        return free;
    }

    public void setFree(BigDecimal free) {
        this.free = free;
    }

    public BigDecimal getTotalFree() {
        return totalFree;
    }

    public void setTotalFree(BigDecimal totalFree) {
        this.totalFree = totalFree;
    }

    public BigDecimal getTotalMileage() {
        return totalMileage;
    }

    public void setTotalMileage(BigDecimal totalMileage) {
        this.totalMileage = totalMileage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
