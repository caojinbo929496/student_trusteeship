package com.meng.student.trusteeship.dao.insurance;


import com.meng.student.trusteeship.entity.vehicle.Insurance;
import com.meng.student.trusteeship.entity.vehicle.po.InsuranceAndImagesPO;
import com.meng.student.trusteeship.entity.vehicle.po.InsurancePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据库层保存车辆保险基本信息
 *
 * @author caojinbo
 * @since 2018.3.20
 */
@Mapper
public interface InsuranceMapper {
    /**
     * 保存车辆保险基本信息
     */
    void save(InsurancePO insurancePO);


    /**
     * 根据车牌号获取对应的最新的保险
     *
     * @param carNumber 车牌号
     * @return 对应车辆保险的基本信息
     */
    List<InsurancePO> getByCarNumber(String carNumber);

    /**
     * 根据车牌号获取对应的所有保险
     *
     * @param carNumber 车牌号
     * @return 对应车辆保险的基本信息
     */
    List<InsurancePO> getAllByCarNumber(String carNumber);

    /**
     * 根据车牌号获取所有的保险
     *
     * @param carNumber 车牌号
     * @return 对应车辆保险的基本信息
     */
    List<InsurancePO> listByCarNumber(String carNumber);

    /**
     * 根据车牌号获取对应的最新的保险结束时间
     *
     * @param carNumber 车牌号
     * @return 对应车辆保险的基本信息
     */
    List<InsurancePO> getFreshInsuranceByCarNumber(String carNumber);

    /**
     * 根据车牌号查询保险及保险图片
     *
     * @param carNumber 车牌号
     * @return List<InsuranceAndImagesPO>
     */
    List<InsuranceAndImagesPO> getInsuranceAndImages(@Param("carNumber") String carNumber);

    /**
     * 更新操作
     *
     * @param insurance
     * @return
     */
    int updateInsuranceByCarNumberOfDate(Insurance insurance);

    /**
     * 根据牌照和最新的一次时间进行查询 ID
     *
     * @param insurance 保险实体 无ID
     * @return 保险的实体 有ID
     */
    InsurancePO getInsuranceByCarNumberOfDate(Insurance insurance);


    void deleteInsuranceById(String insuranceId);
}
