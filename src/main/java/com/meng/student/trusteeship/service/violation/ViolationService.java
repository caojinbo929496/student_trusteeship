package com.meng.student.trusteeship.service.violation;


import com.meng.student.trusteeship.entity.vehicle.Violation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caojinbo
 * @since 2018.3.14
 * 车辆违章查询
 */
public interface ViolationService {
    /**
     * 通过车牌号查询违章记录
     *
     * @param carNumber 车ID
     * @return 返回一组违章记录
     */
    List<Violation> getViolationByCarNumber(String carNumber);

    /**
     * 通过违章记录id查询具体详细信息
     *
     * @param offenderId 违章人Id
     * @return 返回一组违章记录
     */
    List<Violation> getViolationByOffenderId(String offenderId);

    /**
     * 通过违章记录id查询具体详细信息
     *
     * @param id 违章id
     * @return 返回一条具体的违章记录
     */
    Violation getViolationById(String id);

    /**
     * 通过violationPO保存违章记录
     *
     * @param violation 违章的对象
     */
    void save(Violation violation);

    /**
     * 根据车牌号更改所有违章状态
     *
     * @param carNumber 车牌号
     */
    void updateAllStatus(String carNumber);

    /**
     * 更改车辆违章状态
     *
     * @param violation 违章信息
     */
    void updateStatus(Violation violation);

    /**
     * 通过车牌号和违章时间来获得违章是否处理
     *
     * @param carNumber     车牌号
     * @param violationTime 违章时间
     * @return 违章是否处理  0：未处理 1：处理
     */
    Boolean queryByCarNumberAndViolationTime(String carNumber, Date violationTime);

    /**
     * 根据前端条件查询所需的违章基本信息
     *
     * @param map 前端条件查询的封装类
     * @return 所需的违章基本信息
     */
    Map listViolation(Map map);

    /**
     * 根据车牌号和违章时间查询是否存在本次违章记录
     *
     * @param map 车牌号 和 违章时间
     * @return 违章记录
     */
    Violation getViolationByCarNumberAndTime(HashMap<String, Object> map);
}
