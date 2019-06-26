package com.meng.student.trusteeship.entity.index;

import com.meng.student.trusteeship.entity.index.po.NationalInsurancePO;

import java.io.Serializable;

/**
 * @Author: 吴宸煊
 * @Description: 保险统计实现(与页面交互)
 * @Date: Created in 14:19 2018/3/26
 */
public class NationalInsurance implements Serializable {

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

    public NationalInsurance() {
    }

    public NationalInsurance(NationalInsurancePO nationalInsurancePO) {
        this.setNumberInsurance(nationalInsurancePO.getNumberInsurance());
    }

    @Override
    public String toString() {
        return "NationalInsurancePO{" +
                "numberInsurance=" + numberInsurance +
                '}';
    }
}
