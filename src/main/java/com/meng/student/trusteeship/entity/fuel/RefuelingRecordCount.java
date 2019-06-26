package com.meng.student.trusteeship.entity.fuel;

import java.io.Serializable;

/**
 * 车牌号对应的加油记录统计信息的实体类
 *
 * @author weiyangjun
 * @date 2018/03/21 11:13
 */
public class RefuelingRecordCount implements Serializable {
    /**
     * 总行驶里程
     */
    private Double totalMileage;
    /**
     * 总加油次数
     */
    private Integer refuelingTimes;
    /**
     * 总加油金额
     */
    private Double totalRefuelingMoney;

    public Double getTotalMileage() {
        return totalMileage;
    }

    public void setTotalMileage(Double totalMileage) {
        this.totalMileage = totalMileage;
    }

    public Integer getRefuelingTimes() {
        return refuelingTimes;
    }

    public void setRefuelingTimes(Integer refuelingTimes) {
        this.refuelingTimes = refuelingTimes;
    }

    public Double getTotalRefuelingMoney() {
        return totalRefuelingMoney;
    }

    public void setTotalRefuelingMoney(Double totalRefuelingMoney) {
        this.totalRefuelingMoney = totalRefuelingMoney;
    }

    public RefuelingRecordCount() {
    }

    public RefuelingRecordCount(Double totalMileage, Integer refuelingTimes, Double totalRefuelingMoney) {

        this.totalMileage = totalMileage;
        this.refuelingTimes = refuelingTimes;
        this.totalRefuelingMoney = totalRefuelingMoney;
    }

    @Override
    public String toString() {
        return "RefuelingRecordCount{" +

                ", totalMileage=" + totalMileage +
                ", refuelingTimes=" + refuelingTimes +
                ", totalRefuelingMoney=" + totalRefuelingMoney +
                '}';
    }
}
