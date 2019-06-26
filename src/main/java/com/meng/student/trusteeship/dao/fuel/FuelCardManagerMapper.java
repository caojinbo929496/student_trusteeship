package com.meng.student.trusteeship.dao.fuel;

import com.meng.student.trusteeship.entity.fuel.FuelCard;
import com.meng.student.trusteeship.entity.fuel.GetFuelCardParams;
import com.meng.student.trusteeship.entity.fuel.po.FuelCardPO;
import com.meng.student.trusteeship.entity.fuel.po.UpdateFuelCardParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 加油卡管理的Mapper接口
 *
 * @author weiYangJun
 * @date 2018/03/24 16:22:30
 */
@Mapper
public interface FuelCardManagerMapper {

    /**
     * 多条件查询获得加油卡信息列表
     */
    List<FuelCard> getFuelCardWithMultipleParams(GetFuelCardParams params);

    /**
     * 多条件查询获得加油卡数量
     */
    Integer getFuelCardNumbersWithMultipleParams(GetFuelCardParams params);

    /**
     * 给加油卡绑定车牌号
     *
     * @param masterCardNumber 加油卡主卡号
     * @param viceCardNumber   加油卡副卡号
     * @param carNumber        要绑定的车牌号
     * @return Integer 绑定成功返回非零，绑定成功返回零
     * @Param cardMerchant 加油卡供应商类别
     */
    Integer bindCarForCard(@Param("masterCardNumber") String masterCardNumber,
                           @Param("viceCardNumber") String viceCardNumber, @Param("cardMerchant") Integer cardMerchant, @Param("carNumber") String carNumber);

    /**
     * 保存一张加油卡
     *
     * @param fuelCardPO
     */
    void insertACard(FuelCardPO fuelCardPO);

    /**
     * 任意可选参数更新一张加油卡的信息，主卡号供应商必填，副卡号选填
     *
     * @param fuelCardPO
     */
    void updateFuelCard(FuelCardPO fuelCardPO);

    /**
     * 获得加油卡uuid
     *
     * @param masterCardNumber 加油卡主卡号
     * @param viceCardNumber   加油卡副卡号
     * @param cardMerchant     加油卡供应商类别
     * @return 返回加油卡UUID
     */
    String getFuelCardUuid(@Param("masterCardNumber") String masterCardNumber,
                           @Param("viceCardNumber") String viceCardNumber, @Param("cardMerchant") Integer cardMerchant);


    /**
     * 模糊查询获取所有车牌号
     *
     * @param carNumber 车牌号部分字段
     * @return
     */
    List<String> listCarNumberNotBundled(String carNumber);

    /**
     * 统计输入的车牌号未绑定的记录数
     *
     * @param carNumber 车牌号部分字段
     * @return Integer 输入的车牌号未绑定的记录数,返回值大于0说明该车牌存在且从未被绑定，不大于0说明数据库中无此车牌或者已被绑定
     */
    Integer countCarNumberNotBundled(String carNumber);

    /**
     * 根据加油卡uuid更改车牌号
     *
     * @param uuid
     * @param bundlePlateNumber
     */
    void updateBundlePlateNumber(@Param("uuid") String uuid, @Param("bundlePlateNumber") String bundlePlateNumber);

    /**
     * 根据加油卡绑定的车牌号查询uuid
     *
     * @param bundlePlateNumber
     */
    String getUuidByBundlePlateNumber(@Param("bundlePlateNumber") String bundlePlateNumber);

    /**
     * 查询输入的车牌号是否存在数据库中
     *
     * @param carNumber 车牌号
     * @return Integer 输入的车牌号的记录数,返回值大于0说明该车牌号存在，不大于0说明数据库中无此车牌
     */
    Integer countCarNumber(String carNumber);

    /**
     * 根据uuid修改加油卡信息
     */
    void updateAFuelCard(UpdateFuelCardParams updateFuelCardParams);

}
