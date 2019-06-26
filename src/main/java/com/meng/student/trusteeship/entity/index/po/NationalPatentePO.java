package com.meng.student.trusteeship.entity.index.po;

import com.meng.student.trusteeship.entity.index.NationalPatente;

import java.io.Serializable;

/**
 * @Author: 吴宸煊
 * @Description: 全国驾照查询实体类（与数据库交互）
 * @Date: Created in 14:02 2018/3/24
 */
public class NationalPatentePO implements Serializable {
    /**
     * 驾照过期人数
     */
    private Integer numberPatente;

    public Integer getNumberPatente() {
        return numberPatente;
    }

    public NationalPatentePO() {
    }

    public NationalPatentePO(NationalPatente nationalPatente) {
        this.setNumberPatente(nationalPatente.getNumberPatente());

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
