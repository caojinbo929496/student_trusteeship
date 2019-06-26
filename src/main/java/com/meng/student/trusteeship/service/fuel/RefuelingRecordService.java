package com.meng.student.trusteeship.service.fuel;

import com.meng.student.trusteeship.entity.fuel.*;
import com.meng.student.trusteeship.entity.fuel.po.RefuelingRecordPO;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.List;


/**
 * 加油记录服务类接口
 *
 * @author weiyangjun
 * @date 2018/03/21 11:13
 */
public interface RefuelingRecordService {
    /**
     * 按日查询加油卡交易记录
     *
     * @param card      封装加油卡信息的实体类
     * @param startTime 要查询记录的起始时刻
     * @param endTime   要查询记录的终止时刻
     * @return 查询获得的记录的json字符串
     */
    String queryRecordByDay(FuelCard card, String startTime, String endTime);

    /**
     * 保存一条从服务商查询获得的交易记录
     *
     * @param refuelingRecordPO 加油记录PO类
     */
    void saveARefuelingRecord(RefuelingRecordPO refuelingRecordPO);

    /**
     * 保存多条从服务商获得的交易记录
     *
     * @param refuelingRecordPOArray 交易记录数组
     */
    void saveSeveralRefuelingRecord(RefuelingRecordPO[] refuelingRecordPOArray);

    /**
     * 将交易记录json字符串转换成实体类数组
     *
     * @param masterCardNumber 加油卡主卡号
     * @param jsonString       交易记录json字符串
     * @return
     * @throws ParseException json解析异常
     */
    RefuelingRecordPO[] transformJsonStringToList(String masterCardNumber, String jsonString);

    /**
     * 通过绑定的车牌号获得车辆总行驶里程、总加油金额、加油次数
     *
     * @param bundlePlateNumber 加油卡绑定的车牌号
     * @return
     */
    RefuelingRecordCount getRefuelingRecordCountByBundlePlateNumber(String bundlePlateNumber);

    /**
     * 通过绑定的车牌号查询加油记录给车辆监控模块
     *
     * @param bundlePlateNumber 加油卡绑定的车牌号
     * @return 加油记录列表
     */
    List<RefuelingRecordForForms> getRefuelingRecordForFormsListByBundlePlateNumber(String bundlePlateNumber);

    /**
     * 多条件查询指定数量的加油记录给加油记录页面
     *
     * @param getRefuelRecordsParams 封装查询参数的对象
     * @return 加油记录列表
     */
    List<RefuelingRecordForRefuelingRecordPage> getRefuelingRecordForRefuelingRecordPage(GetRefuelRecordsParams getRefuelRecordsParams);

    /**
     * 将加油记录列表转换成excel表格
     *
     * @param refuelingRecordList 加油记录列表
     * @return excel表格
     */
    ResponseEntity<byte[]> transformRefuelingRecordForRefuelingRecordPageListToExcel(List<RefuelingRecordForRefuelingRecordPage> refuelingRecordList);

    /**
     * 多条件查询符合条件的加油记录数量给加油记录页面
     *
     * @param getRefuelRecordsParams 封装查询条件的对象
     * @return 符合条件的加油记录的数量
     */
    Integer getRefuelingRecordNumberForRefuelingRecordPage(GetRefuelRecordsParams getRefuelRecordsParams);

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

}
