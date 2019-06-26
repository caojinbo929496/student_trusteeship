package com.meng.student.trusteeship.dao.index;

import com.meng.student.trusteeship.entity.index.po.NationalPatentePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: 吴宸煊
 * @Description: 全国驾照过期查询dao层
 * @Date: Created in 14:30 2018/3/24
 */
@Mapper
public interface NationalPatenteMapper {

    /**
     * 查询当前驾照过期人数
     *
     * @param
     * @return NationalPatentePO
     */
    NationalPatentePO getNowExpiredPatente();

    /**
     * 查询30天内驾照到期人数
     *
     * @param
     * @return NationalPatentePO
     */
    NationalPatentePO getThirtyDaysExpiredPatente();

    /**
     * 查询60天内驾照到期人数
     *
     * @param
     * @return NationalPatentePO
     */
    NationalPatentePO getSixtyDaysExpiredPatente();
}
