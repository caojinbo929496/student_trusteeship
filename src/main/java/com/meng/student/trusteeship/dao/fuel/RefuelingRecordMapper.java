package com.meng.student.trusteeship.dao.fuel;

import com.meng.student.trusteeship.entity.fuel.*;
import com.meng.student.trusteeship.entity.fuel.po.AndroidRefuelingRecordPO;
import com.meng.student.trusteeship.entity.fuel.po.RefuelingRecordPO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author weiyangjun
 * @date 2018/03/21 11:13
 */
public interface RefuelingRecordMapper {
    /**
     * 保存一条加油卡交易记录
     *
     * @param refuelingRecordPO
     */
    void saveARefuelingRecord(RefuelingRecordPO refuelingRecordPO);

    /**
     * 通过绑定的车牌号获得最新的一条加油记录
     *
     * @param bundlePlateNumber 加油卡绑定的车牌号
     * @return
     */
    RefuelingRecord getARefuelingRecordByBundlePlateNumber(@Param("bundlePlateNumber") String bundlePlateNumber);

    /**
     * 保存一条安卓端上传的加油记录到‘refueling_image_record’表中
     *
     * @param androidRefuelingRecordPO 封装安卓端上传的加油记录的对象
     */
    void saveAndroidRefuelingRecord(AndroidRefuelingRecordPO androidRefuelingRecordPO);

    /**
     * 通过绑定的车牌号获得车辆总行驶里程、总加油金额、加油次数
     *
     * @param bundlePlateNumber 加油卡绑定的车牌号
     * @return
     */
    RefuelingRecordCount getRefuelingRecordCountByBundlePlateNumber(@Param("bundlePlateNumber") String bundlePlateNumber);

    /**
     * 通过绑定的车牌号查询加油记录给车辆监控模块
     *
     * @param bundlePlateNumber 加油卡绑定的车牌号
     * @return 符合条件的加油记录的列表
     */
    List<RefuelingRecordForForms> getRefuelingRecordForFormsListByBundlePlateNumber(@Param("bundlePlateNumber") String bundlePlateNumber);

    /**
     * 多条件查询符合条件的交易记录数量给加油记录页面
     *
     * @param getRefuelRecordsParams 封装查询参数的对象
     * @return 符合条件的交易记录的数量
     */
    Integer getRefuelingRecordNumberForRefuelingRecordPage(GetRefuelRecordsParams getRefuelRecordsParams);

    /**
     * 多条件查询交易记录给加油记录页面,不包括上次里程数
     *
     * @param getRefuelRecordsParams 封装查询参数的对象
     * @return 指定数量的符合条件的交易记录的列表
     */
    List<RefuelingRecordForRefuelingRecordPage> getRefuelingRecordForRefuelingRecordPage(GetRefuelRecordsParams getRefuelRecordsParams);

    /**
     * 根据从供应商服务器查询处的加油卡号和交易时间，从安卓端上传的加油记录中找出符合本记录的里程数
     *
     * @param cardNumber  加油卡号
     * @param tradingTime 交易时间
     * @return 与加油记录对应的里程数
     */
    Double getAAndroidRefuelingRecord(@Param("fuelCardUuid") String cardNumber, @Param("tradingTime") Date tradingTime);


    /**
     * 通过城市和仓库为参数查询加油记录给安卓端
     *
     * @param city      城市
     * @param warehouse 仓库
     * @return 加油记录列表
     */
    List<RefuelingRecordForRefuelingRecordPage> getRefuelingRecordForAndroidWithCityAndWarehouse(@Param("city") String city, @Param("warehouse") String warehouse);

    /**
     * 获得指定卡最近的加油时间
     *
     * @param masterCardNumber 加油卡主卡号
     * @param viceCardNumber   加油卡副卡号
     * @param cardMerchant     供应商类别
     * @return 指定加油卡最近的交易时间
     */
    Date getLastRechargeTime(@Param("masterCardNumber") String masterCardNumber, @Param("viceCardNumber") String viceCardNumber, @Param("cardMerchant") Integer cardMerchant);

    /**
     * 以加油记录图片表的加油时间和车牌号为参数查询上次里程数
     *
     * @return
     */
    Double getLastMileageByFalseTradingTime(@Param("falseTradingTime") Date falseTradingTime, @Param("carNumber") String carNumber);


    /**
     * 多条件查询加油记录给加油卡详情页面
     *
     * @param getRefuelRecordsParams
     * @return
     */
    List<RefuelingRecordForRefuelingRecordPage> getRefuelingRecordForRefuelingRecordPageWithFour(GetRefuelRecordsParams getRefuelRecordsParams);

    /**
     * 多条件查询加油记录数量给加油卡详情页面
     *
     * @param getRefuelRecordsParams
     * @return
     */
    Integer getRefuelingRecordNumberForRefuelingRecordPageWithFour(GetRefuelRecordsParams getRefuelRecordsParams);

    /**
     * 查询指定加油记录是否存在
     *
     * @param masterCardNumber
     * @param viceCardNumber
     * @param tradingTime
     * @return 返回加油卡uuid，不为空代表加油记录已存在、无需插入
     */
    String countRefuelingRecord(@Param("masterCardNumber") String masterCardNumber, @Param("viceCardNumber") String viceCardNumber, @Param("tradingTime") Date tradingTime, @Param("cardMerchant") Integer cardMerchant);

    /**
     * 根据加油卡uuid和加油时间配对当前里程数
     *
     * @param fuelCardUuid
     * @param tradingTime
     * @return
     */
    Double getCurrentMileageFromImageRecord(@Param("fuelCardUuid") String fuelCardUuid, @Param("tradingTime") Date tradingTime);
}