package com.meng.student.trusteeship.entity.traffic.po;

import com.meng.student.trusteeship.entity.traffic.DriverMonitor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: 吴宸煊
 * @Description: 司机违章实体类(与数据库交互)
 * @Date: Created in 14:55 2018/3/19
 */
public class DriverMonitorPO implements Serializable {
    /**
     * 司机名字
     */
    private String name;
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

    public DriverMonitorPO() {

    }

    public DriverMonitorPO(DriverMonitor driverMonitor) {
        this.setName(driverMonitor.getName());
        this.setDeductMark(driverMonitor.getDeductMark());
        this.setPenalty(driverMonitor.getPenalty());
        this.setUnTreated(driverMonitor.getUnTreated());
        this.setWarehouse(driverMonitor.getWarehouse());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "DriverMonitorPO{" +
                "name='" + name + '\'' +
                ", warehouse='" + warehouse + '\'' +
                ", unTreated=" + unTreated +
                ", deductMark=" + deductMark +
                ", penalty=" + penalty +
                '}';
    }
}
