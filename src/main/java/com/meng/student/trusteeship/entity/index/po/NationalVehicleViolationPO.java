package com.meng.student.trusteeship.entity.index.po;

import com.meng.student.trusteeship.entity.index.NationalVehicleViolation;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: 吴宸煊
 * @Description: 全国车辆违章实体(与数据库交互)
 * @Date: Created in 10:22 2018/3/23
 */
public class NationalVehicleViolationPO implements Serializable {
    /**
     * 违章未处理数
     */
    private int unTreated;
    /**
     * 扣分
     */
    private Integer deductMark;
    /**
     * 罚款金额
     */
    private BigDecimal penalty;


    public NationalVehicleViolationPO() {
    }

    public NationalVehicleViolationPO(NationalVehicleViolation nationalVehicleViolation) {
        this.setDeductMark(nationalVehicleViolation.getDeductMark());
        this.setPenalty(nationalVehicleViolation.getPenalty());
        this.setUnTreated(nationalVehicleViolation.getUnTreated());
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
        return "NationalVehicleViolationMapper{" +
                "unTreated=" + unTreated +
                ", deductMark=" + deductMark +
                ", penalty=" + penalty +
                '}';
    }
}
