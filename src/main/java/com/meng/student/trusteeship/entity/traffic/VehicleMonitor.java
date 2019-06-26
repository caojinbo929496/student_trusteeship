package com.meng.student.trusteeship.entity.traffic;

import com.meng.student.trusteeship.entity.traffic.po.VehicleMonitorPO;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: 吴宸煊
 * @Description: 车辆违法统计实体类（用于界面交互）
 * @Date: Created in 13:53 2018/3/19
 */
public class VehicleMonitor implements Serializable {
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

    public VehicleMonitor() {

    }

    public VehicleMonitor(VehicleMonitorPO vehicleMonitorPO) {
        this.setCarNumber(vehicleMonitorPO.getCarNumber());
        this.setDeductMark(vehicleMonitorPO.getDeductMark());
        this.setPenalty(vehicleMonitorPO.getPenalty());
        this.setUnTreated(vehicleMonitorPO.getUnTreated());
        this.setWarehouse(vehicleMonitorPO.getWarehouse());
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
