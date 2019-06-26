package com.meng.student.trusteeship.service.index;

import com.meng.student.trusteeship.entity.index.NationalPatente;

/**
 * @Author: 吴宸煊
 * @Description: 全国驾照查询service接口
 * @Date: Created in 15:38 2018/3/24
 */
public interface NationalPatenteService {

    /**
     * 查询当前驾照已经过期人数
     *
     * @param
     * @return NationalPatente
     */
    NationalPatente getNowExpiredPatente();

    /**
     * 查询30天内驾照到期人数
     *
     * @param
     * @return NationalPatente
     */
    NationalPatente getThirtyDaysExpiredPatente();

    /**
     * 查询60天内驾照到期人数
     *
     * @param
     * @return NationalPatente
     */
    NationalPatente getSixtyDaysExpiredPatente();
}
