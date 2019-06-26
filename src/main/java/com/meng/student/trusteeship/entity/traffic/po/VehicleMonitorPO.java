package com.meng.student.trusteeship.entity.traffic.po;

import com.meng.student.trusteeship.entity.traffic.VehicleMonitor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description: 车辆违章实体类（与数据库交互）
 * @Author: 吴宸煊
 * @Date: Created in  2018/3/17 0017
 */
public class VehicleMonitorPO implements Serializable {
    /**
     * 车牌号
     */
    private String carNumber;
    /**
     * 所属 仓库
     */
    private String warehouse;
    /**
     * 违章未处理数
     */
    private Integer unTreated;
    /**
     * 扣分
     */
    private Integer deductMark;
    /**
     * 罚款金额
     */
    private BigDecimal penalty;

    public VehicleMonitorPO() {

    }

    public VehicleMonitorPO(VehicleMonitor vehicleMonitor) {
        this.setCarNumber(vehicleMonitor.getCarNumber());
        this.setDeductMark(vehicleMonitor.getDeductMark());
        this.setPenalty(vehicleMonitor.getPenalty());
        this.setUnTreated(vehicleMonitor.getUnTreated());
        this.setWarehouse(vehicleMonitor.getWarehouse());
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

    public Integer getUnTreated() {
        return unTreated;
    }

    public void setUnTreated(Integer unTreated) {
        this.unTreated = unTreated;
    }

    public Integer getDeductMark() {
        return deductMark;
    }

    public void setDeductMark(Integer deductMark) {
        this.deductMark = deductMark;
    }

    public BigDecimal getPenalty() {
        return penalty;
    }

    public void setPenalty(BigDecimal penalty) {
        this.penalty = penalty;
    }

    @Override
    public String toString() {
        return "VehicleMonitorPO{" +
                "carNumber='" + carNumber + '\'' +
                ", warehouse='" + warehouse + '\'' +
                ", unTreated=" + unTreated +
                ", deductMark=" + deductMark +
                ", penalty=" + penalty +
                '}';
    }
}
