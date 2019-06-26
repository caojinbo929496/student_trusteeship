package com.meng.student.trusteeship.entity.index.po;

import com.meng.student.trusteeship.entity.index.NationalInsurance;

import java.io.Serializable;

/**
 * @Author: 吴宸煊
 * @Description: 全国保险过期查询（与数据库交互）
 * @Date: Created in 14:09 2018/3/26
 */
public class NationalInsurancePO implements Serializable {
    /**
     * 保险过期数量统计
     */
    private Integer numberInsurance;

    public Integer getNumberInsurance() {
        return numberInsurance;
    }

    public void setNumberInsurance(Integer numberInsurance) {
        this.numberInsurance = numberInsurance;
    }

    public NationalInsurancePO() {
    }

    public NationalInsurancePO(NationalInsurance nationalInsurance) {
        this.setNumberInsurance(nationalInsurance.getNumberInsurance());
    }

    @Override
    public String toString() {
        return "NationalInsurancePO{" +
                "numberInsurance=" + numberInsurance +
                '}';
    }
}
