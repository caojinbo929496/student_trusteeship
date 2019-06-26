package com.meng.student.trusteeship.dao.violation;

import com.meng.student.trusteeship.entity.vehicle.po.ViolationCountInfoPO;
import com.meng.student.trusteeship.entity.vehicle.po.ViolationPO;
import com.meng.student.trusteeship.entity.vehicle.po.ViolationUnityPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 车辆违章查询
 */
@Mapper
public interface ViolationMapper {
    /**
     * 通过车牌号查询违章记录
     *
     * @param carNumber 车ID
     * @return 返回一组违章记录
     */
    List<ViolationPO> listViolationPOByCarNumber(String carNumber);

    /**
     * 通过违章记录id查询具体详细信息
     *
     * @param offenderId 违章人Id
     * @return 返回一组违章记录
     */
    List<ViolationPO> getViolationPOByOffenderId(String offenderId);

    /**
     * 通过违章记录id查询具体详细信息
     *
     * @param id 违章id
     * @return 返回一条具体的违章记录
     */
    ViolationPO getViolationPOById(String id);

    /**
     * 通过violationPO保存违章记录
     *
     * @param violationPO 违章的对象
     */
    void save(ViolationPO violationPO);

    /**
     * 根据车牌号更改违章状态
     *
     * @param carNumber 车牌号
     */
    void updateAllStatus(String carNumber);


    /**
     * 根据车牌号违章时间和状态状态更改违章记录
     *
     * @param updateStatusInfo 更改车辆违章状态的条件
     */
    void updateStatus(Map updateStatusInfo);

    /**
     * 根据车牌号更改违章状态
     *
     * @param carNumber 车牌号
     */
    void updateStatus(String carNumber);

    /**
     * 根据前端查询条件进行分页查询
     *
     * @param queryInfo 前端界面的查询条件
     */
    List<ViolationUnityPo> listViolation(Map queryInfo);

    /**
     * 根据前端查询条件进行查询所需违章数量
     *
     * @param queryInfo 前端界面的查询条件
     * @return 返回所需的数量
     */
    int countViolation(Map queryInfo);

    /**
     * 获得所有的违章记录数
     *
     * @return 违章记录数
     */
    Integer conutViolation();

    /**
     * 根据车牌号和违章时间查询是否存在本次违章记录
     *
     * @param map 车牌号 和 违章时间
     * @return 违章记录
     */
    ViolationPO getViolationByCarNumberAndTime(Map<String, Object> map);

    /**
     * 根据车牌号查询车辆的总未处理的违章数，总的罚款金额，总的扣分数
     *
     * @param carNumber 车牌号
     * @return 车辆的总未处理的违章数，总的罚款金额，总的扣分数对象
     */
    ViolationCountInfoPO getViolationCountInfo(String carNumber);

}
