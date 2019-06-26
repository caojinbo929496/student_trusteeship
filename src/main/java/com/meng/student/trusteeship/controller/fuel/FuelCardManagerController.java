package com.meng.student.trusteeship.controller.fuel;

import com.meng.student.trusteeship.dao.car.CarMapper;
import com.meng.student.trusteeship.entity.fuel.*;
import com.meng.student.trusteeship.entity.fuel.dto.RefuelingRecordForFormsDTO;
import com.meng.student.trusteeship.entity.fuel.po.FuelCardPO;
import com.meng.student.trusteeship.entity.fuel.po.UpdateFuelCardParams;
import com.meng.student.trusteeship.entity.vehicle.po.ManegerCarAcceptPO;
import com.meng.student.trusteeship.service.fuel.FuelCardManageService;
import com.meng.student.trusteeship.service.fuel.RefuelingRecordService;
import com.meng.student.trusteeship.util.ConvertUtils;
import com.meng.student.trusteeship.util.UuidUtils;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 加油卡管理及加油记录相关的controller
 *
 * @author weiyangjun
 * @date 2018/03/21 14:48
 */
@RestController
public class FuelCardManagerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FuelCardManagerController.class);

    @Autowired
    private FuelCardManageService fuelCardManageService;

    @Autowired
    private RefuelingRecordService refuelingRecordService;

    @Autowired
    CarMapper carMapper;

    /**
     * 根据给定的对象参数获得加油卡信息列表
     *
     * @param params 封装查询参数的对象
     * @return 返回指定数量的加油卡列表和符合条件的加油卡总数量
     */
    @RequestMapping(value = "fuelCardManager/getFuelCardPOWithMultipleParams", method = RequestMethod.POST)
    public Map<String, Object> getFuelCardPOWithMultipleParams(@RequestBody GetFuelCardParams params) {


        //获得加油卡数量
        Integer FuelCardNumber = fuelCardManageService.getFuelCardPONumbersWithMultipleParams(params);

        //获得加油卡列表
        List<FuelCard> fuelCardList = fuelCardManageService.getFuelCardWithMultipleParams(params);
        if (fuelCardList != null) {
            for (int i = 0; i < fuelCardList.size(); i++) {
                if (fuelCardList.get(i).getAddingTime() != null) {
                    fuelCardList.get(i).setAddingTimeMilis(fuelCardList.get(i).getAddingTime().getTime());
                }
                if (fuelCardList.get(i).getLastRechargeTime() != null) {
                    fuelCardList.get(i).setLastRechargeTimeMilis(fuelCardList.get(i).getLastRechargeTime().getTime());
                }
            }
        }
        //封装成map返回给页面
        Map<String, Object> fuelCardMap = new HashMap<>(2);
        fuelCardMap.put("fuelCardNumber", FuelCardNumber);
        fuelCardMap.put("fuelCardList", fuelCardList);

        return fuelCardMap;
    }

    /**
     * 绑定车牌号
     *
     * @param params 包括masterCardNumber 主卡号 ，viceCardNumber   副卡号 ，carNumber  车牌号
     * @return
     */
    @RequestMapping(value = "fuelCardManager/bindCarNumber", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> bindCarNumber(@RequestBody String params) {
        Map<String, String> resultJson = new HashMap<>(1);
        JSONObject paramsJson = JSONObject.fromObject(params);
        String carNumber = paramsJson.getString("carNumber");
        String masterCardNumber = paramsJson.getString("masterCardNumber");
        String viceCardNumber = paramsJson.getString("viceCardNumber");
        Integer cardMerchant = paramsJson.getInt("cardMerchant");

        //绑定是否成功的标志变量
        Integer ifUpdateSuccess = fuelCardManageService.bindCarForCard(masterCardNumber, viceCardNumber, carNumber, cardMerchant);


        //根据绑定是否成功返回对应的信息给页面
        if (ifUpdateSuccess == 0) {
            resultJson.put("ifUpdateSuccess", "success");
        }
        if (ifUpdateSuccess == 1) {
            resultJson.put("ifUpdateSuccess", "false");
        }
        return resultJson;

    }

    /**
     * 返回加油卡添加模板
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "fuelCardManager/getCardExcelTemplate", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getCardExcelTemplate() {
        return fuelCardManageService.responseCardExcelTemplate();
    }

    /**
     * 以excel批量导入加油卡信息
     *
     * @param cardExcel 加油卡信息excel文件
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "fuelCardManager/uploadServeralCards", method = RequestMethod.POST)
    @ResponseBody
    public Map uploadServeralCards(MultipartFile cardExcel) {
        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("result", "0");
        Map fuelCardPOListMap = null;
        try {
            fuelCardPOListMap = fuelCardManageService.transformExcelToFuelCardPOList(cardExcel.getInputStream());
        } catch (Exception e) {
            LOGGER.info("服务器内部错误，请稍后再试," + e);
            resultMap.put("result", "1");
            return resultMap;
        }
        fuelCardManageService.saveSeveralCard((ArrayList<FuelCardPO>) fuelCardPOListMap.get("fuelCardPOAddedList"));
        resultMap.put("fuelCardPONotAddList", fuelCardPOListMap.get("fuelCardPONotAddList"));
        return resultMap;
    }

    /**
     * 通过绑定的车牌号获得车辆总行驶里程、总加油金额、加油次数
     *
     * @param bundlePlateNumber 加油卡绑定的车牌号
     * @return
     */
    @RequestMapping(value = "fuelCardManager/getRefuelingRecordCount/{bundlePlateNumber}", method = RequestMethod.GET)
    public RefuelingRecordCount getRefuelingRecordCountByBundlePlateNumber(@PathVariable("bundlePlateNumber") String bundlePlateNumber) {
        return refuelingRecordService.getRefuelingRecordCountByBundlePlateNumber(bundlePlateNumber);
    }

    /**
     * 通过绑定的车牌号查询加油记录给车辆监控模块
     *
     * @param bundlePlateNumber 车牌号
     * @return 返回该车牌号的所有加油记录列表
     */
    @RequestMapping(value = "fuelCardManager/getRefuelingRecordForFormsList/{bundlePlateNumber}", method = RequestMethod.GET)
    public List<RefuelingRecordForFormsDTO> getRefuelingRecordForFormsListByBundlePlateNumber(@PathVariable("bundlePlateNumber") String bundlePlateNumber) {
        List<RefuelingRecordForForms> refuelingRecordForFormsListByBundlePlateNumber = refuelingRecordService.getRefuelingRecordForFormsListByBundlePlateNumber(bundlePlateNumber);
        return refuelingRecordForFormsListByBundlePlateNumber.stream().map(ConvertUtils::convert).collect(Collectors.toList());
    }

    /**
     * 多条件查询加油卡交易记录给加油记录页面
     *
     * @param getRefuelRecordsParams 封装查询条件的对象
     * @return 指定数量的符合条件的交易记录和符合条件的交易记录的总数量
     */
    @RequestMapping(value = "fuelCardManager/getRefuelingRecordForRefuelingRecordPage", method = RequestMethod.POST)
    public Map<String, Object> getRefuelingRecordForRefuelingRecordPage(@RequestBody GetRefuelRecordsParams getRefuelRecordsParams) {

        if (getRefuelRecordsParams.getStartTimeMilis() != null) {
            getRefuelRecordsParams.setStartTime(new Date(getRefuelRecordsParams.getStartTimeMilis()));
        }
        if (getRefuelRecordsParams.getEndTimeMilis() != null) {
            getRefuelRecordsParams.setEndTime(new Date(getRefuelRecordsParams.getEndTimeMilis()));
        }
        Map<String, Object> resultMap = new HashMap();

        //符合条件的交易记录的总数量
        resultMap.put("refuelingNumber", refuelingRecordService.getRefuelingRecordNumberForRefuelingRecordPage(getRefuelRecordsParams));
        List<RefuelingRecordForRefuelingRecordPage> refuelingRecordForRefuelingRecordPageList = refuelingRecordService.getRefuelingRecordForRefuelingRecordPage(getRefuelRecordsParams);
        if (refuelingRecordForRefuelingRecordPageList != null) {
            for (int i = 0; i < refuelingRecordForRefuelingRecordPageList.size(); i++) {
                if (refuelingRecordForRefuelingRecordPageList.get(i).getRefuelingTime() != null) {
                    refuelingRecordForRefuelingRecordPageList.get(i).setRefuelingTimeMilis(refuelingRecordForRefuelingRecordPageList.get(i).getRefuelingTime().getTime());
                }
            }
        }
        //指定数量的交易记录列表
        resultMap.put("refuelingList", refuelingRecordForRefuelingRecordPageList);

        return resultMap;
    }

    /**
     * 根据条件查询加油卡交易记录并转换成excel文件返回给用户
     *
     * @param getRefuelRecordsParams 封装加油卡交易记录查询参数的对象
     * @return excel文件
     */
    @RequestMapping(value = "fuelCardManager/downRecordForRefuelingRecordPageExcel", method = RequestMethod.POST)
    public ResponseEntity<byte[]> downRecordForRefuelingRecordPageExcel(GetRefuelRecordsParams getRefuelRecordsParams) {
        return refuelingRecordService.transformRefuelingRecordForRefuelingRecordPageListToExcel(refuelingRecordService.getRefuelingRecordForRefuelingRecordPage(getRefuelRecordsParams));
    }

    /**
     * 返回加油记录给吴宸煊
     *
     * @param carNumber 车牌号
     * @return 加油卡交易记录列表
     */
    @RequestMapping(value = "fuelCardManager/getRefuelingRecordForWuChenXuan/{carNumber}", method = RequestMethod.GET)
    public List<RefuelingRecordForRefuelingRecordPage> getRefuelingRecordForWuChenXuan(@PathVariable("carNumber") String carNumber) {
        //将车牌号封装到对象中，复用前面的查询方法
        GetRefuelRecordsParams getRefuelRecordsParams = new GetRefuelRecordsParams();
        getRefuelRecordsParams.setCarNumber(carNumber);
        return refuelingRecordService.getRefuelingRecordForRefuelingRecordPage(getRefuelRecordsParams);

    }

    /**
     * 返回加油记录给曹金波
     *
     * @param city          城市
     * @param warehouse     仓库
     * @param carNumber     车牌号
     * @param patenteNumber 驾驶证编号
     * @return
     */
    @RequestMapping(value = "fuelCardManager/listWarehouseCarInfo/{city}/{warehouse}/{carNumber}/{patenteNumber}", method = RequestMethod.GET)
    public List<ManegerCarAcceptPO> listWarehouseCarInfo(@PathVariable("city") String city, @PathVariable("warehouse") String warehouse, @PathVariable("carNumber") String carNumber, @PathVariable("patenteNumber") String patenteNumber) {
        LOGGER.info("listWarehouseCarInfo(@PathVariable(\"city\")===>" + city + "," + warehouse + "," + carNumber + "," + patenteNumber);
        Map<String, String> map = new HashMap<>();
        map.put("city", city);
        map.put("warehouse", warehouse);
        map.put("carNumber", carNumber);
        map.put("patenteNumber", patenteNumber);
        return carMapper.listWarehouseCarInfo(map);
    }

    /**
     * 获取所有未绑定油卡的车牌号
     *
     * @param carNumber 车牌号部分字段
     * @return
     */
    @RequestMapping(value = "fuelCardManager/listCarNumberNotBundled", method = RequestMethod.POST)
    public List<String> listCarNumberNotBundled(@RequestBody String carNumber) {
        return fuelCardManageService.listCarNumberNotBundled(carNumber);
    }

    /**
     * 添加油卡
     *
     * @param fuelCardPO
     * @return
     */
    @RequestMapping(value = "fuelCardManager/addAFuelCard", method = RequestMethod.POST)
    public Map addAFuelCard(@RequestBody FuelCardPO fuelCardPO) {
        Map<String, String> resultMessage = new HashMap<>();

        resultMessage.put("result", "添加成功");

        GetFuelCardParams getFuelCardParams = new GetFuelCardParams();
        getFuelCardParams.setMasterCardNumber(fuelCardPO.getMasterCardNumber());
        getFuelCardParams.setViceCardNumber(fuelCardPO.getViceCardNumber());
        getFuelCardParams.setCardMerchant(String.valueOf(fuelCardPO.getCardMerchant()));
        Integer ifFuelCardExists = fuelCardManageService.getFuelCardPONumbersWithMultipleParams(getFuelCardParams);
        if (ifFuelCardExists > 0) {
            resultMessage.put("result", "加油卡号已存在,无法添加");
            return resultMessage;
        }

        String bundledUuid = fuelCardManageService.getUuidByBundlePlateNumber(fuelCardPO.getBundlePlateNumber());

        if (bundledUuid != null) {
            fuelCardManageService.updateBundlePlateNumber(bundledUuid, null);
        }
        fuelCardPO.setUuid(UuidUtils.getUuid());
        fuelCardPO.setAddingTime(new Date());
        fuelCardPO.setBalance(0D);

        Calendar lastRechargeTimeCalendar = Calendar.getInstance();
        lastRechargeTimeCalendar.set(Calendar.YEAR, 2000);
        lastRechargeTimeCalendar.set(Calendar.MONTH, 0);
        lastRechargeTimeCalendar.set(Calendar.DAY_OF_MONTH, 1);
        //2000-01-01说明该加油卡还未从对应服务商更新信息
        fuelCardPO.setLastRechargeTime(lastRechargeTimeCalendar.getTime());
        fuelCardManageService.saveACard(fuelCardPO);

        return resultMessage;
    }

    /**
     * @param carNumber
     * @return 非null代表该车牌号已被绑定
     */
    @RequestMapping(value = "fuelCardManager/ifCarNumberBundled", method = RequestMethod.POST)
    public String ifCarNumberBundled(String carNumber) {
        return fuelCardManageService.getUuidByBundlePlateNumber(carNumber);
    }

    @RequestMapping(value = "/fuelCardManager/fuelCardDetailRecord", method = RequestMethod.POST)
    public Map<String, Object> getRefuelingRecordForFuelCardDetail(@RequestBody GetRefuelRecordsParams getRefuelRecordsParams) {
        Map<String, Object> resultMap = new HashMap();
        //符合条件的交易记录的总数量
        resultMap.put("refuelingNumber", refuelingRecordService.getRefuelingRecordNumberForRefuelingRecordPageWithFour(getRefuelRecordsParams));

        //指定数量的交易记录列表
        resultMap.put("refuelingList", refuelingRecordService.getRefuelingRecordForRefuelingRecordPageWithFour(getRefuelRecordsParams));

        return resultMap;
    }

    @RequestMapping(value = "/fuelCardManager/modifyAFuelCard", method = RequestMethod.POST)
    public void modifyAFuelCard(@RequestBody UpdateFuelCardParams updateFuelCardParams) {
        fuelCardManageService.updateAFuelCard(updateFuelCardParams);
    }
}
