package com.meng.student.trusteeship.service.fuel.impl;

import com.meng.student.trusteeship.dao.fuel.FuelCardManagerMapper;
import com.meng.student.trusteeship.dao.fuel.RefuelingRecordMapper;
import com.meng.student.trusteeship.entity.fuel.*;
import com.meng.student.trusteeship.entity.fuel.po.FuelCardPO;
import com.meng.student.trusteeship.entity.fuel.po.RefuelingRecordPO;
import com.meng.student.trusteeship.service.fuel.RefuelingRecordService;
import com.meng.student.trusteeship.service.fuel.SinopeQueryService;
import com.meng.student.trusteeship.util.UuidUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 加油记录服务类
 *
 * @author weiyangjun
 * @date 2018/03/21 11:13
 */
@Service
public class RefuelingRecordServiceImpl implements RefuelingRecordService {
    private static Logger LOGGER = LoggerFactory.getLogger(RefuelingRecordServiceImpl.class);
    @Autowired
    private SinopeQueryService sinopeQueryService;
    @Autowired
    private RefuelingRecordMapper refuelingRecordMapper;
    @Autowired
    private FuelCardManagerMapper fuelCardManagerMapper;

    @Override
    public String queryRecordByDay(FuelCard card, String startTime, String endTime) {
        return sinopeQueryService.sinopeQueryRecordByDay(card, startTime, endTime);
    }


    @Override
    public void saveARefuelingRecord(RefuelingRecordPO refuelingRecordPO) {
        try {
            refuelingRecordMapper.saveARefuelingRecord(refuelingRecordPO);
        } catch (Exception e) {
            LOGGER.info("保存加油记录时产生异常" + e);
        }
    }


    @Override
    public void saveSeveralRefuelingRecord(RefuelingRecordPO[] refuelingRecordPOArray) {
        if (refuelingRecordPOArray == null || refuelingRecordPOArray.length == 0) {
            return;
        }

        //标识最近一次加油po在refuelingRecordPOArray中下标的变量
        int maxBalanceRecordIndex = 0;

        //存放已知的最近一次加油时间的变量
        long maxBalanceRecordDate = 0;

        //通过循环获得最近一次加油时间,同时每次循环会保存一条交易记录
        for (int i = 0; i < refuelingRecordPOArray.length; i++) {
            saveARefuelingRecord(refuelingRecordPOArray[i]);
            if ("0".equals(refuelingRecordPOArray[i].getTradingType()) && maxBalanceRecordDate < refuelingRecordPOArray[i].
                    getTradingTime().getTime()) {
                maxBalanceRecordIndex = i;
            }
        }

        //更新加油卡信息，主要是将加油卡信息更新到最近一次交易的状态
        FuelCardPO fuelCardPO = new FuelCardPO();
        fuelCardPO.setBalance(refuelingRecordPOArray[maxBalanceRecordIndex].getBalance());
        fuelCardPO.setLastRechargeTime(refuelingRecordPOArray[maxBalanceRecordIndex].getTradingTime());
        fuelCardPO.setMasterCardNumber(refuelingRecordPOArray[maxBalanceRecordIndex].getMasterCardNumber());
        fuelCardPO.setViceCardNumber(refuelingRecordPOArray[maxBalanceRecordIndex].getViceCardNumber());
        fuelCardPO.setCardMerchant(1);
        fuelCardManagerMapper.updateFuelCard(fuelCardPO);
    }

    @Override
    public RefuelingRecordPO[] transformJsonStringToList(String masterCardNumber, String jsonString) {
        //如果json为null，直接返回
        if (jsonString == null) {
            return null;
        }

        //获得副卡号
        String viceCardNumber = null;
        try {
            viceCardNumber = JSONObject.fromObject(jsonString).getString("no");
        } catch (Exception e) {
            LOGGER.info("中石油查询失败，卡号{},错误:{}", masterCardNumber, e);
            return null;
        }
        //获得son字符串中交易记录的部分
        jsonString = jsonString.substring(jsonString.indexOf("["), jsonString.indexOf("]") + 1);

        //将json字符串转换成JSONArray对象
        JSONArray jsonArray = JSONArray.fromObject(jsonString);

        //直接将jsonArray对象转换成SinopeRefuelingRecord对象数组
        SinopeRefuelingRecord[] sinopeRefuelingRecordArray = (SinopeRefuelingRecord[]) JSONArray.toArray(jsonArray,
                SinopeRefuelingRecord.class);

        RefuelingRecordPO[] refuelingRecordPOArray = new RefuelingRecordPO[sinopeRefuelingRecordArray.length];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fuelCardUuid = fuelCardManagerMapper.getFuelCardUuid(masterCardNumber, viceCardNumber, 1);

        //循环转换交易记录对象
        for (int i = 0; i < sinopeRefuelingRecordArray.length; i++) {
            RefuelingRecordPO refuelingRecordPO = new RefuelingRecordPO();
            refuelingRecordPO.setUuid(UuidUtils.getUuid());
            refuelingRecordPO.setMasterCardNumber(masterCardNumber);
            refuelingRecordPO.setViceCardNumber(viceCardNumber);
            try {
                refuelingRecordPO.setTradingTime(simpleDateFormat.parse(sinopeRefuelingRecordArray[i].getOpeTime()));
            } catch (ParseException e) {
                LOGGER.info("主卡号：{},副卡号：{}，时间字符串解析错误,", refuelingRecordPO.getMasterCardNumber(), refuelingRecordPO.getViceCardNumber(), e);
            }
            refuelingRecordPO.setTradingType("加油".equals(sinopeRefuelingRecordArray[i].getTraName()) ? "0" : "1");
            refuelingRecordPO.setTradingMoney(sinopeRefuelingRecordArray[i].getAmount() * 0.01);
            refuelingRecordPO.setOilType(sinopeRefuelingRecordArray[i].getOilName());
            refuelingRecordPO.setOilQuantity(sinopeRefuelingRecordArray[i].getLitre() * 0.01);
            refuelingRecordPO.setOilPrice(sinopeRefuelingRecordArray[i].getPrice() * 0.01);
            refuelingRecordPO.setBalance(sinopeRefuelingRecordArray[i].getBalance() * 0.01);
            refuelingRecordPO.setTradingPosition(sinopeRefuelingRecordArray[i].getNodeTag());
            refuelingRecordPO.setFuelCardUuid(fuelCardUuid);
            Double mileage = refuelingRecordMapper.getAAndroidRefuelingRecord(fuelCardUuid, refuelingRecordPO.getTradingTime());
            if (null == mileage || 0 == mileage) {
                mileage = 0D;
            }
            refuelingRecordPO.setCurrentMileage(mileage);
            refuelingRecordPOArray[i] = refuelingRecordPO;
        }
        return refuelingRecordPOArray;
    }


    @Override
    public RefuelingRecordCount getRefuelingRecordCountByBundlePlateNumber(String bundlePlateNumber) {
        return refuelingRecordMapper.getRefuelingRecordCountByBundlePlateNumber(bundlePlateNumber);
    }


    @Override
    public List<RefuelingRecordForForms> getRefuelingRecordForFormsListByBundlePlateNumber(String bundlePlateNumber) {
        return refuelingRecordMapper.getRefuelingRecordForFormsListByBundlePlateNumber(bundlePlateNumber);
    }


    @Override
    public List<RefuelingRecordForRefuelingRecordPage> getRefuelingRecordForRefuelingRecordPage(GetRefuelRecordsParams getRefuelRecordsParams) {
        //返回结果
        List<RefuelingRecordForRefuelingRecordPage> refuelingRecordForRefuelingRecordPagesList = refuelingRecordMapper.getRefuelingRecordForRefuelingRecordPage(getRefuelRecordsParams);
        for (int i = 0; i < refuelingRecordForRefuelingRecordPagesList.size(); i++) {
            refuelingRecordForRefuelingRecordPagesList.get(i).setLastMileage(refuelingRecordMapper.getLastMileageByFalseTradingTime(refuelingRecordForRefuelingRecordPagesList.get(i).getFalseRefuelingTime(), refuelingRecordForRefuelingRecordPagesList.get(i).getCarNumber()));
        }
        return refuelingRecordForRefuelingRecordPagesList;
    }


    @Override
    public ResponseEntity<byte[]> transformRefuelingRecordForRefuelingRecordPageListToExcel(List<RefuelingRecordForRefuelingRecordPage> refuelingRecordList) {
        HSSFWorkbook wkb = new HSSFWorkbook();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        Calendar currentDate = Calendar.getInstance();
        HSSFSheet cardExcelTemplate = wkb.createSheet("加油记录·" + currentDate.get(Calendar.YEAR) + "-" + currentDate.get(Calendar.MONTH) + "-" + currentDate.get(Calendar.DAY_OF_YEAR));
        cardExcelTemplate.setDefaultColumnWidth(30);

        HSSFRow row1 = cardExcelTemplate.createRow(0);
        row1.createCell(0).setCellValue("城市");
        row1.createCell(1).setCellValue("车牌号码");
        row1.createCell(2).setCellValue("车辆品牌");
        row1.createCell(3).setCellValue("车牌类型");
        row1.createCell(4).setCellValue("加油司机");
        row1.createCell(5).setCellValue("车辆上次里程数");
        row1.createCell(6).setCellValue("录入里程数");
        row1.createCell(7).setCellValue("加油时间");
        row1.createCell(8).setCellValue("加油金额");
        row1.createCell(9).setCellValue("定位地址");
        row1.createCell(10).setCellValue("里程照片url");

        Integer refuelingRecordListSize = refuelingRecordList.size() + 1;
        Double perItemCurrentMileage = null;
        Double perItemLastMileage = null;
        Date perRefuelingDate = null;
        Double perRefuelingMoney = null;
        String perRefuelingPosition = null;
        for (int i = 1; i < refuelingRecordListSize; i++) {
            HSSFRow row = cardExcelTemplate.createRow(i);
            RefuelingRecordForRefuelingRecordPage refuelingRecordForRefuelingRecordPage = refuelingRecordList.get(i - 1);
            row.createCell(0).setCellValue(refuelingRecordForRefuelingRecordPage.getCity());
            row.createCell(1).setCellValue(refuelingRecordForRefuelingRecordPage.getCarNumber());
            row.createCell(2).setCellValue(refuelingRecordForRefuelingRecordPage.getCarBrand());
            row.createCell(3).setCellValue(refuelingRecordForRefuelingRecordPage.getVehicleType());
            row.createCell(4).setCellValue(refuelingRecordForRefuelingRecordPage.getRefuelingDriverName());
            perItemLastMileage = refuelingRecordForRefuelingRecordPage.getLastMileage();

            if (perItemLastMileage == null) {
                row.createCell(5).setCellValue("");
            }
            if (perItemLastMileage != null) {
                row.createCell(5).setCellValue(perItemLastMileage);
            }

            perItemCurrentMileage = refuelingRecordForRefuelingRecordPage.getCurrentMileage();
            if (perItemCurrentMileage == null) {
                row.createCell(6).setCellValue("");
            }
            if (perItemCurrentMileage != null) {
                row.createCell(6).setCellValue(perItemCurrentMileage);
            }

            perRefuelingDate = refuelingRecordForRefuelingRecordPage.getRefuelingTime();

            if (perRefuelingDate == null) {
                row.createCell(7).setCellValue("");
            }

            if (perRefuelingDate != null) {
                row.createCell(7).setCellValue(dateFormat.format(perRefuelingDate));
            }

            perRefuelingMoney = refuelingRecordForRefuelingRecordPage.getRefuelingMoney();
            if (perRefuelingMoney == null) {
                row.createCell(8).setCellValue("");
            }
            if (perRefuelingMoney != null) {
                row.createCell(8).setCellValue(perRefuelingMoney);
            }
            perRefuelingPosition = refuelingRecordForRefuelingRecordPage.getTradingPosition();
            if (perRefuelingPosition == null) {
                row.createCell(9).setCellValue("");
            }
            if (perRefuelingPosition != null) {
                row.createCell(9).setCellValue(perRefuelingPosition);
            }
            row.createCell(10).setCellValue(refuelingRecordForRefuelingRecordPage.getImgUrl());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            wkb.write(out);
        } catch (IOException e) {
            LOGGER.info("加油记录Excel内存文件转换成字节数组时产生错误," + e);
            throw new RuntimeException("加油记录Excel内存文件转换成字节数组时产生错误," + e);
        }

        HttpHeaders headers = new HttpHeaders();
        String fileName = null;
        try {
            fileName = new String(("加油记录" + currentDate.get(Calendar.YEAR) + "-" + currentDate.get(Calendar.MONTH) + "-" + currentDate.get(Calendar.DAY_OF_MONTH) + ".xls").getBytes("utf-8"), "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            LOGGER.info("将加油记录文件名字符串转换成ascii码时产生错误，编码不支持," + e);
            throw new RuntimeException("将加油记录文件名字符串转换成ascii码时产生错误，编码不支持," + e);
        }

        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        byte[] outputStreamByte = out.toByteArray();
        ResponseEntity<byte[]> filebyte = new ResponseEntity<byte[]>(outputStreamByte, headers, HttpStatus.CREATED);
        try {
            out.close();
        } catch (IOException e) {
            LOGGER.info("加油记录Excel内存文件转换成字节数组后，关闭流时产生错误," + e);
            throw new RuntimeException("加油记录Excel内存文件转换成字节数组后，关闭流时产生错误," + e);
        }

        return filebyte;
    }

    @Override
    public Integer getRefuelingRecordNumberForRefuelingRecordPage(GetRefuelRecordsParams getRefuelRecordsParams) {
        return refuelingRecordMapper.getRefuelingRecordNumberForRefuelingRecordPage(getRefuelRecordsParams);
    }


    /**
     * 多条件查询加油记录给加油卡详情页面
     *
     * @param getRefuelRecordsParams
     * @return
     */
    @Override
    public List<RefuelingRecordForRefuelingRecordPage> getRefuelingRecordForRefuelingRecordPageWithFour(GetRefuelRecordsParams getRefuelRecordsParams) {
        return refuelingRecordMapper.getRefuelingRecordForRefuelingRecordPageWithFour(getRefuelRecordsParams);
    }

    /**
     * 多条件查询加油记录数量给加油卡详情页面
     *
     * @param getRefuelRecordsParams
     * @return
     */
    @Override
    public Integer getRefuelingRecordNumberForRefuelingRecordPageWithFour(GetRefuelRecordsParams getRefuelRecordsParams) {
        return refuelingRecordMapper.getRefuelingRecordNumberForRefuelingRecordPageWithFour(getRefuelRecordsParams);
    }
}
