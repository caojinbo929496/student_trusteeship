package com.meng.student.trusteeship.entity.vehicle.po;

import java.math.BigDecimal;

public class ViolationCountInfoPO {
    /**
     * 未处理的违章数
     */
    private Integer unhandledIllegalRecords;

    /**
     * 罚款总分数
     */
    private Integer totalDeductMark;

    /**
     * 罚款总金额
     */
    private BigDecimal totalFine;

    /**
     * 车牌号
     */
    private String carNumber;

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Integer getUnhandledIllegalRecords() {
        return unhandledIllegalRecords;
    }

    public void setUnhandledIllegalRecords(Integer unhandledIllegalRecords) {
        this.unhandledIllegalRecords = unhandledIllegalRecords;
    }

    public Integer getTotalDeductMark() {
        return totalDeductMark;
    }

    public void setTotalDeductMark(Integer totalDeductMark) {
        this.totalDeductMark = totalDeductMark;
    }

    public BigDecimal getTotalFine() {
        return totalFine;
    }

    public void setTotalFine(BigDecimal totalFine) {
        this.totalFine = totalFine;
    }
}
