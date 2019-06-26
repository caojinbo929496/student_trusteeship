package com.meng.student.trusteeship.service.fuel;

import com.meng.student.trusteeship.entity.fuel.FuelCard;
import com.meng.student.trusteeship.entity.fuel.GetFuelCardParams;
import com.meng.student.trusteeship.entity.fuel.po.FuelCardPO;
import com.meng.student.trusteeship.entity.fuel.po.UpdateFuelCardParams;
import org.springframework.http.ResponseEntity;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 加油卡管理页面服务类接口
 *
 * @author weiyangjun
 */
public interface FuelCardManageService {

    /**
     * 查询加油卡列表
     *
     * @param params 封装查询参数的对象
     * @return 加油卡列表
     */
    List<FuelCard> getFuelCardWithMultipleParams(GetFuelCardParams params);

    /**
     * 获得符合条件的加油卡的数量
     *
     * @param params 封装查询条件的对象
     */
    Integer getFuelCardPONumbersWithMultipleParams(GetFuelCardParams params);

    /**
     * 给加油卡绑定车牌号
     *
     * @param masterCardNumber 加油卡主卡号
     * @param viceCardNumber   加油卡副卡号
     * @param carNumber        车牌号
     * @return 绑定成功返回0，绑定失败1
     */
    Integer bindCarForCard(String masterCardNumber, String viceCardNumber, String carNumber, Integer cardMerchant);

    /**
     * 获得加油卡模板excel表格的方法
     *
     * @return 加油卡模板excel表格
     */
    ResponseEntity<byte[]> responseCardExcelTemplate();

    /**
     * 保存一条加油卡信息
     *
     * @param fuelCardPO
     * @return
     */
    void saveACard(FuelCardPO fuelCardPO);

    /**
     * 批量保存加油卡
     *
     * @param fuelCardPOList
     * @return
     */
    void saveSeveralCard(List fuelCardPOList);

    /**
     * 将excel表格的输入流转换成FuleCardPO的数组
     *
     * @param excelInputStream excel表格的输入流
     * @return Map, 第一个字段是FuelCardPO的List，第二个字段是所有未添加成功的FuelCardPO的List
     */
    Map transformExcelToFuelCardPOList(InputStream excelInputStream);

    /**
     * 模糊匹配参数获取所有未绑定油卡的车牌号
     *
     * @param carNumber 车牌号部分字段
     * @return
     */
    List<String> listCarNumberNotBundled(String carNumber);

    /**
     * 统计输入的车牌号未绑定的记录数
     *
     * @param carNumber 车牌号部分字段
     * @return Integer 输入的车牌号未绑定的记录数,返回值大于0说明该车牌从未被绑定，不大于0说明数据库中无此车牌或者已被绑定
     */
    Integer countCarNumberNotBundled(String carNumber);

    /**
     * 根据加油卡绑定的车牌号查询uuid
     *
     * @param bundlePlateNumber
     */
    String getUuidByBundlePlateNumber(String bundlePlateNumber);

    /**
     * 根据uuid修改加油卡信息
     */
    void updateAFuelCard(UpdateFuelCardParams updateFuelCardParams);

    /**
     * 综合中石油、中石化查询的方法
     * 暂时只支持主卡查询，不支持副卡查询
     *
     * @param fuelCard  需给定4个属性，主卡号、副卡号、供应商、查询密码
     * @param startTime 开始时间，中石油、中石化均按日 时间格式:'2018-02-01'
     * @param endTime   结束时间，中石油、中石化均按日 时间格式格式:'2018-04-30'
     * @return 改卡在指定时间内的加油记录
     */
    void fuelCardQueryFromCardMerchant(FuelCard fuelCard, String startTime, String endTime);

    /**
     * 根据加油卡uuid更改车牌号
     *
     * @param uuid
     * @param bundlePlateNumber
     */
    void updateBundlePlateNumber(String uuid, String bundlePlateNumber);
}

