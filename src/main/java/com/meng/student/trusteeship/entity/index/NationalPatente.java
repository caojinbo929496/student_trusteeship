package com.meng.student.trusteeship.entity.index;

import com.meng.student.trusteeship.entity.index.po.NationalPatentePO;

import java.io.Serializable;

/**
 * @Author: 吴宸煊
 * @Description: 全国驾照查询实体类(与页面交互)
 * @Date: Created in 14:28 2018/3/24
 */
public class NationalPatente implements Serializable {
    /**
     * 驾照过期人数
     */
    private Integer numberPatente;

    public Integer getNumberPatente() {
        return numberPatente;
    }

    public NationalPatente() {
    }

    public NationalPatente(NationalPatentePO nationalPatentePO) {
        this.setNumberPatente(nationalPatentePO.getNumberPatente());
    }

    public void setNumberPatente(Integer numberPatente) {
        this.numberPatente = numberPatente;
    }

    @Override
    public String toString() {
        return "NationalPatentePO{" +
                "numberPatente=" + numberPatente +
                '}';
    }
}
