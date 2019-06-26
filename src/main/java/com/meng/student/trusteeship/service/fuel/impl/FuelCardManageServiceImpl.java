package com.meng.student.trusteeship.service.fuel.impl;

import com.meng.student.trusteeship.dao.fuel.FuelCardManagerMapper;
import com.meng.student.trusteeship.dao.fuel.RefuelingRecordMapper;
import com.meng.student.trusteeship.entity.fuel.FuelCard;
import com.meng.student.trusteeship.entity.fuel.GetFuelCardParams;
import com.meng.student.trusteeship.entity.fuel.po.FuelCardPO;
import com.meng.student.trusteeship.entity.fuel.po.RefuelingRecordPO;
import com.meng.student.trusteeship.entity.fuel.po.UpdateFuelCardParams;
import com.meng.student.trusteeship.service.fuel.FuelCardManageService;
import com.meng.student.trusteeship.service.fuel.PetroQueryService;
import com.meng.student.trusteeship.service.fuel.SinopeQueryService;
import com.meng.student.trusteeship.util.UuidUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
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
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 加油卡管理服务类
 *
 * @author weiyangjun
 * @date 2018/03/21 11:13
 */
@Service
public class FuelCardManageServiceImpl implements FuelCardManageService {

    @Autowired
    private FuelCardManagerMapper fuelCardManagerMapper;

    @Autowired
    private RefuelingRecordMapper refuelingRecordMapper;
    @Autowired
    private SinopeQueryService sinopeQueryService;

    @Autowired
    private PetroQueryService petroQueryService;
    private final static Logger LOGGER = LoggerFactory.getLogger(FuelCardManageServiceImpl.class);

    @Override
    public List<FuelCard> getFuelCardWithMultipleParams(GetFuelCardParams params) {
        return fuelCardManagerMapper.getFuelCardWithMultipleParams(params);
    }

    @Override
    public Integer getFuelCardPONumbersWithMultipleParams(GetFuelCardParams params) {
        return fuelCardManagerMapper.getFuelCardNumbersWithMultipleParams(params);
    }

    @Override
    public Integer bindCarForCard(String masterCardNumber, String viceCardNumber, String carNumber, Integer cardMerchant) {
        Integer ifCarNumberExists = fuelCardManagerMapper.countCarNumber(carNumber);

        //数据库中不存在此车牌号
        if (ifCarNumberExists <= 0) {
            return 1;
        }


        //车牌号存在且未被绑定,绑定成功
        Integer ifCarNumberCanUse = fuelCardManagerMapper.countCarNumberNotBundled(carNumber);
        if (ifCarNumberCanUse > 0) {
            fuelCardManagerMapper.bindCarForCard(masterCardNumber, viceCardNumber, cardMerchant, carNumber);
        }

        //数据库中存在此车牌号且已被绑定，则解绑再绑定
        String ifCarNumberNotBundled = fuelCardManagerMapper.getUuidByBundlePlateNumber(carNumber);
        if (ifCarNumberNotBundled != null) {
            fuelCardManagerMapper.updateBundlePlateNumber(ifCarNumberNotBundled, null);
            fuelCardManagerMapper.bindCarForCard(masterCardNumber, viceCardNumber, cardMerchant, carNumber);
        }
        return 0;
    }

    @Override
    public ResponseEntity<byte[]> responseCardExcelTemplate() {
        //创建excel工厂
        HSSFWorkbook wkb = new HSSFWorkbook();

        //excel工厂产生一个新的excel文件
        HSSFSheet cardExcelTemplate = wkb.createSheet("油卡模板");

        //设置excel文件列的默认宽度
        cardExcelTemplate.setDefaultColumnWidth(30);

        //创建excel文件第一行并设置列和内容
        HSSFRow row1 = cardExcelTemplate.createRow(0);
        HSSFCell row1Cell = row1.createCell(0);

        row1Cell.setCellValue("1.卡查询密码是登录中石油或中石化网站的密码;2.供应商请填0或1，0表示中石油的卡，1表示中石化的卡;" +
                "3.卡类型：请填0或1，0表示主卡，1表示副卡。第1行和第2行请不要改动;4.第3行是示范数据、无需删掉，从第4行开始填写数据;");

        cardExcelTemplate.addMergedRegion(new CellRangeAddress(0, 0, 0, 25));

        HSSFCellStyle style = wkb.createCellStyle();
        HSSFFont font = wkb.createFont();
        font.setColor(HSSFColor.RED.index);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        row1.getCell(0).setCellStyle(style);

        //创建excel文件第二行并设置列和内容
        HSSFRow row2 = cardExcelTemplate.createRow(1);
        row2.createCell(0).setCellValue("主卡号(必填)");
        row2.createCell(1).setCellValue("副卡号（多用户卡必填，单用户卡不填）");
        row2.createCell(2).setCellValue("供应商（必填）");
        row2.createCell(3).setCellValue("卡查询密码（必填）");
        row2.createCell(4).setCellValue("绑定车牌号（选填）");
        row2.createCell(5).setCellValue("卡类型（必填）");
        row2.createCell(6).setCellValue("余额（选填）");

        //查询所有加油卡的信息
        GetFuelCardParams getFuelCardParams = new GetFuelCardParams();
        getFuelCardParams.setStart(0);
        getFuelCardParams.setLength(1);
        List<FuelCard> fuelCardList = fuelCardManagerMapper.getFuelCardWithMultipleParams(getFuelCardParams);
        HSSFRow otherRow = null;

        //将加油卡信息逐行添加到excel文件中
        for (int i = 0; i < fuelCardList.size(); i++) {
            otherRow = cardExcelTemplate.createRow(i + 2);
            otherRow.createCell(0).setCellValue(fuelCardList.get(i).getMasterCardNumber());
            otherRow.createCell(1).setCellValue(fuelCardList.get(i).getViceCardNumber());
            otherRow.createCell(2).setCellValue(fuelCardList.get(i).getCardMerchant());
            otherRow.createCell(3).setCellValue(fuelCardList.get(i).getPassword());
            otherRow.createCell(4).setCellValue(fuelCardList.get(i).getBundlePlateNumber());
            otherRow.createCell(5).setCellValue(fuelCardList.get(i).getCardType());
            otherRow.createCell(6).setCellValue(fuelCardList.get(i).getBalance());
        }

        //将创建的excel文件写到字节输出流中
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            wkb.write(out);
        } catch (IOException e) {
            LOGGER.info("excel内存文件转换为的字节数组写到输出流中时产生错误，" + e);
            throw new RuntimeException("服务器内部错误");
        }
        HttpHeaders headers = new HttpHeaders();

        //在响应头中设置excel文件名
        String fileName = null;
        try {
            fileName = new String("加油卡模板.xls".getBytes("utf-8"), "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            LOGGER.info("字符编码格式不支持，" + e);
            throw new RuntimeException("服务器内部错误");
        }
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        //将excel文件的字节输出流转换成字节数组
        byte[] outputStreamByte = out.toByteArray();
        ResponseEntity<byte[]> filebyte = new ResponseEntity<byte[]>(outputStreamByte, headers, HttpStatus.CREATED);
        try {
            out.close();
        } catch (IOException e) {
            LOGGER.info("excel文件转换为的字节数组写到输出流中后关闭输出流时产生错误，" + e);
            throw new RuntimeException("服务器内部错误");
        }

        //返回字节数组的ResponseEntity
        return filebyte;
    }

    @Override
    public void saveACard(FuelCardPO fuelCardPO) {
        String fuelCardUuid = fuelCardManagerMapper.getFuelCardUuid(fuelCardPO.getMasterCardNumber(), fuelCardPO.getViceCardNumber(), fuelCardPO.getCardMerchant());
        if (fuelCardUuid == null) {
            fuelCardManagerMapper.insertACard(fuelCardPO);
        }
    }

    @Override
    public void saveSeveralCard(List fuelCardPOList) {
        for (int i = 0; i < fuelCardPOList.size(); i++) {
            saveACard((FuelCardPO) fuelCardPOList.get(i));
        }
    }

    @Override
    public Map transformExcelToFuelCardPOList(InputStream excelInputStream) {
        Map<String, List> result = new HashMap<>(5);
        //根据输入流创建excel文件工厂
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(excelInputStream);
        } catch (Exception e) {
            throw new RuntimeException("创建excel文件工厂时产生io错误，" + e);
        }
        //获取工厂下挂的第一个表格
        Sheet excelSheet = workbook.getSheetAt(0);

        //从第四行开始读数据
        Integer startRowNumber = excelSheet.getFirstRowNum() + 3;
        //直到最后一行
        Integer endRowNumber = excelSheet.getLastRowNum();

        ArrayList<FuelCardPO> fuelCardPOAddedList = new ArrayList<>();
        ArrayList<FuelCardPO> fuelCardPONotAddList = new ArrayList<>();

        String perMasterCardNumber = null;
        String perViceCardNumber = null;
        Integer perCardMerchant = null;
        String perPassword = null;
        String perCardType = null;
        String perIfCardExists = null;
        String perBalance = null;
        //存放每一个加油卡上次加油时间的变量
        Date perItemLastRechargeTime = null;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2000);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        for (int i = startRowNumber; i <= endRowNumber; i++) {

            //初始化所有值
            perMasterCardNumber = null;
            perViceCardNumber = null;
            perCardMerchant = null;
            perPassword = null;
            perCardType = null;
            perBalance = null;

            FuelCardPO fuelCardPO = new FuelCardPO();
            Row cardRow = excelSheet.getRow(i);
            //先假定所有列的值都是String类型
            for (Cell cell : cardRow) {
                cell.setCellType(Cell.CELL_TYPE_STRING);
            }

            //在每行逐列读取数据并封装进FuelCardPO对象中,先获取必填字段
            try {
                perMasterCardNumber = cardRow.getCell(0).getStringCellValue();
            } catch (Exception e) {
            }

            try {
                perViceCardNumber = cardRow.getCell(1).getStringCellValue();
            } catch (Exception e) {

            }

            try {
                perCardMerchant = Integer.parseInt(cardRow.getCell(2).getStringCellValue());
            } catch (Exception e) {
            }

            try {
                perPassword = cardRow.getCell(3).getStringCellValue();
            } catch (Exception e) {

            }

            try {
                perCardType = cardRow.getCell(5).getStringCellValue();
            } catch (Exception e) {

            }
            //不管必填字段是否为空，填上返回需要的信息

            if (perMasterCardNumber == null) {
                fuelCardPO.setMasterCardNumber("");
            }
            if (perMasterCardNumber != null) {
                fuelCardPO.setMasterCardNumber(perMasterCardNumber);
            }
            if (perViceCardNumber == null) {
                fuelCardPO.setViceCardNumber("");
            }
            if (perViceCardNumber != null) {
                fuelCardPO.setViceCardNumber(perViceCardNumber);
            }
            if (perCardMerchant == null) {
                fuelCardPO.setCardMerchant(-1);
            }
            if (perCardMerchant != null) {
                fuelCardPO.setCardMerchant(perCardMerchant);
            }
            //必填字段是否为空
            boolean ifCheckCharacterTrue = (null == perMasterCardNumber || "".equals(perMasterCardNumber) || null == perCardMerchant || null == perCardType || "".equals(perCardType) || null == perPassword || "".equals(perPassword));
            if (ifCheckCharacterTrue) {
                fuelCardPONotAddList.add(fuelCardPO);
                continue;
            }

            //校验密码格式
            if (!perPassword.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,14}$")) {
                fuelCardPONotAddList.add(fuelCardPO);
                continue;
            }

            //验证主卡号是否是数值
            boolean ifMasterCardNumberNumber = perMasterCardNumber.matches("[0-9]+");

            if (!ifMasterCardNumberNumber) {
                fuelCardPONotAddList.add(fuelCardPO);
                continue;
            }


            fuelCardPO.setPassword(perPassword);


            //卡类型填写是否正确
            try {
                fuelCardPO.setCardType(Byte.parseByte(perCardType));
            } catch (Exception e) {
                fuelCardPONotAddList.add(fuelCardPO);
                continue;
            }

            //供应商填写的是否在允许的范围内
            if (fuelCardPO.getCardMerchant() != 0 && fuelCardPO.getCardMerchant() != 1) {
                fuelCardPONotAddList.add(fuelCardPO);
                continue;
            }

            //卡类型填写的是否在允许的范围内
            if (fuelCardPO.getCardType() != 0 && fuelCardPO.getCardType() != 1) {
                fuelCardPONotAddList.add(fuelCardPO);
                continue;
            }
            //如果副卡填写了，自动更正卡类型
            if ("0".equals(perCardType)) {
                if (perViceCardNumber != null) {
                    fuelCardPONotAddList.add(fuelCardPO);
                    continue;
                }
            }

            if ("1".equals(perCardType)) {
                if (perViceCardNumber == null || (!perViceCardNumber.matches("[0-9]+"))) {
                    fuelCardPONotAddList.add(fuelCardPO);
                    continue;
                }
            }

            //解析车牌号列
            String bundlePlateNumber = null;
            try {
                bundlePlateNumber = cardRow.getCell(4).getStringCellValue();
            } catch (Exception e) {
            }

            if (null == bundlePlateNumber || "".equals(bundlePlateNumber)) {
                fuelCardPO.setBundlePlateNumber(null);
            }

            if (null != bundlePlateNumber && !"".equals(bundlePlateNumber)) {
                //数据库中无此车牌号
                Integer ifCarNumberExists = fuelCardManagerMapper.countCarNumber(bundlePlateNumber);
                if (ifCarNumberExists <= 0) {
                    fuelCardPONotAddList.add(fuelCardPO);
                    continue;
                }

                //车牌号是否已被绑定
                String bundledUuid = fuelCardManagerMapper.getUuidByBundlePlateNumber(bundlePlateNumber);

                if (null != bundledUuid) {
                    fuelCardManagerMapper.updateBundlePlateNumber(bundledUuid, null);
                }

                fuelCardPO.setBundlePlateNumber(bundlePlateNumber);
            }

            try {
                perBalance = cardRow.getCell(6).getStringCellValue();
            } catch (Exception e) {
            }

            //余额填写了的情况下验证格式
            if (perBalance != null) {
                Double numberBalance = null;

                //解析余额列
                try {
                    numberBalance = Double.parseDouble(perBalance);
                } catch (Exception e) {
                    fuelCardPONotAddList.add(fuelCardPO);
                    continue;
                }

                //余额非负
                if (numberBalance < 0) {
                    fuelCardPONotAddList.add(fuelCardPO);
                    continue;
                }

            }
            if (perBalance == null) {
                fuelCardPO.setBalance(0d);
            }
            fuelCardPO.setUuid(UuidUtils.getUuid());
            fuelCardPO.setAddingTime(new Date());
            //该加油卡上次加油时间需要查询获得
            perItemLastRechargeTime = refuelingRecordMapper.getLastRechargeTime(fuelCardPO.getMasterCardNumber(), fuelCardPO.getViceCardNumber(), fuelCardPO.getCardMerchant());

            if (perItemLastRechargeTime == null) {
                fuelCardPO.setLastRechargeTime(calendar.getTime());
            }
            if (perItemLastRechargeTime != null) {
                fuelCardPO.setLastRechargeTime(perItemLastRechargeTime);
            }

            //判断要添加的卡是否存在于数据库中，如果存在则不添加
            perIfCardExists = fuelCardManagerMapper.getFuelCardUuid(perMasterCardNumber, perViceCardNumber, fuelCardPO.getCardMerchant());
            if (perIfCardExists == null) {
                fuelCardPOAddedList.add(fuelCardPO);
            }
            if (perIfCardExists != null) {
                fuelCardPONotAddList.add(fuelCardPO);
            }

        }
        result.put("fuelCardPONotAddList", fuelCardPONotAddList);
        result.put("fuelCardPOAddedList", fuelCardPOAddedList);
        return result;
    }

    /**
     * 模糊匹配参数获取所有未绑定油卡的车牌号
     *
     * @param carNumber 车牌号部分字段
     * @return List<String>
     */
    @Override
    public List<String> listCarNumberNotBundled(String carNumber) {
        return fuelCardManagerMapper.listCarNumberNotBundled(carNumber);
    }

    /**
     * 统计输入的车牌号未绑定的记录数
     *
     * @param carNumber 车牌号部分字段
     * @return Integer 输入的车牌号未绑定的记录数,返回值大于0说明该车牌从未被绑定，不大于0说明数据库中无此车牌或者已被绑定
     */
    @Override
    public Integer countCarNumberNotBundled(String carNumber) {
        return fuelCardManagerMapper.countCarNumberNotBundled(carNumber);
    }

    /**
     * 根据加油卡绑定的车牌号查询uuid
     *
     * @param bundlePlateNumber
     */
    @Override
    public String getUuidByBundlePlateNumber(String bundlePlateNumber) {
        return fuelCardManagerMapper.getUuidByBundlePlateNumber(bundlePlateNumber);
    }


    /**
     * 根据uuid修改加油卡信息
     *
     * @param updateFuelCardParams
     */
    @Override
    public void updateAFuelCard(UpdateFuelCardParams updateFuelCardParams) {
        fuelCardManagerMapper.updateAFuelCard(updateFuelCardParams);
    }

    /**
     * 综合中石油、中石化查询并持久化到数据库的方法，
     * 暂时只支持主卡查询，不支持副卡查询
     *
     * @param fuelCard  需给定4个属性，主卡号、副卡号、供应商、查询密码
     * @param startTime 开始时间，中石油、中石化均按日 时间格式:'2018-02-01'
     * @param endTime   结束时间，中石油、中石化均按日 时间格式格式:'2018-04-30'
     * @return 改卡在指定时间内的加油记录
     */
    @Override
    public void fuelCardQueryFromCardMerchant(FuelCard fuelCard, String startTime, String endTime) {
        //如果出现不合法的参数，直接返回
        if (fuelCard == null || startTime == null || endTime == null) {
            return;
        }

        String[] refuelingRecordQueryResult = null;
        JSONArray recordResultJsonArray = null;
        String fuelCardUuid = fuelCardManagerMapper.getFuelCardUuid(fuelCard.getMasterCardNumber(), fuelCard.getViceCardNumber(), fuelCard.getCardMerchant());
        //用于更新加油卡信息
        FuelCardPO fuelCardPO = new FuelCardPO();
        fuelCardPO.setMasterCardNumber(fuelCard.getMasterCardNumber());
        fuelCardPO.setViceCardNumber(fuelCard.getViceCardNumber());
        fuelCardPO.setCardMerchant(fuelCard.getCardMerchant());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date lastRechargeTime = null;
        Date lastRefuelingTime = null;
        //上次充值时间
        try {
            lastRechargeTime = simpleDateFormat.parse("2000-01-01 00:00:00");
            lastRefuelingTime = simpleDateFormat.parse("2000-01-01 00:00:00");
        } catch (ParseException e) {
        }
        Integer loopCount = 0;

        //中石油的用这个查
        if (fuelCard.getCardMerchant() == 0) {
            endTime = endTime + " 02:00:00";
            startTime = startTime + " 02:00:00";
            JSONArray rechargeRecordJson = null;
            //先查询并保证查询的结果正确
            do {
                LOGGER.info("中石油加油卡主卡号：{}、副卡号：{}、供应商：{} 第{}次查询开始,", fuelCard.getMasterCardNumber(), fuelCard.getViceCardNumber(), fuelCard.getCardMerchant(), loopCount);
                refuelingRecordQueryResult = petroQueryService.petroQueryWithDay(fuelCard, startTime, endTime);
                try {
                    recordResultJsonArray = JSONObject.fromObject(refuelingRecordQueryResult[0]).getJSONArray("data");
                    rechargeRecordJson = JSONObject.fromObject(refuelingRecordQueryResult[1]).getJSONArray("data");
                } catch (Exception e) {
                    LOGGER.info("加油卡主卡号：{}、副卡号：{}、供应商：{}, 异常:{}", fuelCard.getMasterCardNumber(), fuelCard.getViceCardNumber(), fuelCard.getCardMerchant(), e);
                    refuelingRecordQueryResult[0] = "-1";
                }
                loopCount++;
            } while (loopCount < 2 && "-1".equals(refuelingRecordQueryResult[0]));

            if (loopCount == 2 && "-1".equals(refuelingRecordQueryResult[0])) {
                LOGGER.info("出现两次无法查询的加油卡，加油卡主卡号：{}、副卡号：{}、供应商：{}", fuelCard.getMasterCardNumber(), fuelCard.getViceCardNumber(), fuelCard.getCardMerchant());
                return;
            }
            //然后将查询结果转换成ArrayList
            for (int i = 0; i < recordResultJsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) recordResultJsonArray.get(i);
                RefuelingRecordPO refuelingRecordPO = new RefuelingRecordPO();
                refuelingRecordPO.setMasterCardNumber(fuelCard.getMasterCardNumber());
                refuelingRecordPO.setViceCardNumber(fuelCard.getViceCardNumber());
                refuelingRecordPO.setTradingMoney(jsonObject.getDouble("amount"));
                refuelingRecordPO.setBalance(jsonObject.getDouble("balance"));
                refuelingRecordPO.setTradingType("0");
                refuelingRecordPO.setOilType(jsonObject.getString("giftName"));
                refuelingRecordPO.setOilQuantity(jsonObject.getDouble("volumn"));
                refuelingRecordPO.setTradingPosition(jsonObject.getString("orgName"));
                refuelingRecordPO.setOilPrice(refuelingRecordPO.getTradingMoney() / refuelingRecordPO.getOilQuantity());
                try {
                    refuelingRecordPO.setTradingTime(simpleDateFormat.parse(jsonObject.getString("occurTime")));
                } catch (ParseException e) {
                    LOGGER.info("该加油卡的某条加油记录时间解析错误，加油卡主卡号：{}、副卡号：{}、供应商：{}，异常{}", fuelCard.getMasterCardNumber(), fuelCard.getViceCardNumber(), fuelCard.getCardMerchant(), e);
                }

                String perFuelCardUuid = refuelingRecordMapper.countRefuelingRecord(refuelingRecordPO.getMasterCardNumber(), refuelingRecordPO.getViceCardNumber(), refuelingRecordPO.getTradingTime(), fuelCard.getCardMerchant());
                if (perFuelCardUuid == null) {
                    refuelingRecordPO.setUuid(UuidUtils.getUuid());
                    refuelingRecordPO.setFuelCardUuid(fuelCardUuid);
                    Double thisCurrentMileage = refuelingRecordMapper.getCurrentMileageFromImageRecord(fuelCardUuid, refuelingRecordPO.getTradingTime());
                    if (thisCurrentMileage == null) {
                        refuelingRecordPO.setCurrentMileage(0d);
                    }

                    if (thisCurrentMileage != null) {
                        refuelingRecordPO.setCurrentMileage(thisCurrentMileage);
                    }

                    refuelingRecordMapper.saveARefuelingRecord(refuelingRecordPO);
                }


                if (refuelingRecordPO.getTradingTime().getTime() > lastRefuelingTime.getTime()) {
                    fuelCardPO.setBalance(refuelingRecordPO.getBalance());
                    lastRefuelingTime = refuelingRecordPO.getTradingTime();
                }
            }
            for (int i = 0; i < rechargeRecordJson.size(); i++) {
                JSONObject jsonObject1 = (JSONObject) rechargeRecordJson.get(i);
                Date thisRechargeTime = null;
                try {
                    thisRechargeTime = simpleDateFormat.parse(jsonObject1.getString("time"));
                } catch (ParseException e) {
                    LOGGER.info("该加油卡的某条加油记录时间解析错误，加油卡主卡号：{}、副卡号：{}、供应商：{},异常{}", fuelCard.getMasterCardNumber(), fuelCard.getViceCardNumber(), fuelCard.getCardMerchant(), e);
                }

                if (thisRechargeTime.getTime() > lastRechargeTime.getTime()) {
                    lastRechargeTime = thisRechargeTime;
                    if (lastRechargeTime.getTime() > lastRefuelingTime.getTime()) {
                        fuelCardPO.setBalance(jsonObject1.getDouble("LoyaltyAndBalance"));
                    }
                }
            }

            fuelCardPO.setLastRechargeTime(lastRechargeTime);
            fuelCardManagerMapper.updateFuelCard(fuelCardPO);

        }

        String sinopeRefuelingRecordQueryResult = null;
        //中石化的用这个查
        if (fuelCard.getCardMerchant() == 1) {
            do {
                LOGGER.info("中石化加油卡主卡号：{}、副卡号：{}、供应商：{} 第{}次查询开始,", fuelCard.getMasterCardNumber(), fuelCard.getViceCardNumber(), fuelCard.getCardMerchant(), loopCount);
                sinopeRefuelingRecordQueryResult = sinopeQueryService.sinopeQueryRecordByDay(fuelCard, startTime, endTime);

                if (sinopeRefuelingRecordQueryResult == null) {
                    sinopeRefuelingRecordQueryResult = "-1";
                    loopCount++;
                    continue;
                }
                try {
                    recordResultJsonArray = JSONObject.fromObject(sinopeRefuelingRecordQueryResult).getJSONArray("list");
                } catch (Exception e) {
                    sinopeRefuelingRecordQueryResult = "-1";
                }
                loopCount++;
            } while (loopCount < 2 && "-1".equals(sinopeRefuelingRecordQueryResult));

            if (loopCount == 2 && "-1".equals(sinopeRefuelingRecordQueryResult)) {
                LOGGER.info("中石化出现一个两次无法查询成功的加油卡。加油卡主卡号：{}、副卡号：{}、供应商：{}", fuelCard.getMasterCardNumber(), fuelCard.getViceCardNumber(), fuelCard.getCardMerchant());
                return;
            }
            //然后将查询结果转换成ArrayList
            for (int i = 0; i < recordResultJsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) recordResultJsonArray.get(i);
                if ("圈存".equals(jsonObject.getString("traName"))) {
                    Date thisLastRechargeTime = null;
                    try {
                        thisLastRechargeTime = simpleDateFormat.parse(jsonObject.getString("opeTime"));
                    } catch (ParseException e) {
                        LOGGER.info("该加油卡的某条加油记录时间解析错误，加油卡主卡号：{}、副卡号：{}、供应商：{},异常{}", fuelCard.getMasterCardNumber(), fuelCard.getViceCardNumber(), fuelCard.getCardMerchant(), e);
                    }
                    if (thisLastRechargeTime.getTime() > lastRechargeTime.getTime()) {
                        lastRechargeTime = thisLastRechargeTime;
                        fuelCardPO.setBalance(jsonObject.getDouble("balance") * 0.01);
                    }
                }
                fuelCardPO.setLastRechargeTime(lastRechargeTime);

                RefuelingRecordPO refuelingRecordPO = new RefuelingRecordPO();
                refuelingRecordPO.setMasterCardNumber(fuelCard.getMasterCardNumber());
                refuelingRecordPO.setViceCardNumber(fuelCard.getViceCardNumber());
                refuelingRecordPO.setTradingMoney(jsonObject.getDouble("amount") * 0.01);
                refuelingRecordPO.setBalance(jsonObject.getDouble("balance") * 0.01);
                refuelingRecordPO.setTradingType("0");
                refuelingRecordPO.setTradingPosition(jsonObject.getString("nodeTag"));
                try {
                    refuelingRecordPO.setTradingTime(simpleDateFormat.parse(jsonObject.getString("opeTime")));
                } catch (ParseException e) {
                    LOGGER.info("该加油卡的某条加油记录时间解析错误，加油卡主卡号：{}、副卡号：{}、供应商：{},异常{}", fuelCard.getMasterCardNumber(), fuelCard.getViceCardNumber(), fuelCard.getCardMerchant(), e);
                }
                refuelingRecordPO.setOilType(jsonObject.getString("price"));
                refuelingRecordPO.setOilQuantity(jsonObject.getDouble("litre"));
                refuelingRecordPO.setOilPrice(jsonObject.getDouble("price") * 0.01);
                String ifRefuelingRecordExists = refuelingRecordMapper.countRefuelingRecord(refuelingRecordPO.getMasterCardNumber(), refuelingRecordPO.getViceCardNumber(), refuelingRecordPO.getTradingTime(), fuelCard.getCardMerchant());
                if (ifRefuelingRecordExists == null) {
                    refuelingRecordPO.setFuelCardUuid(fuelCardUuid);
                    refuelingRecordPO.setUuid(UuidUtils.getUuid());
                    Double thisCurrentMileage = refuelingRecordMapper.getCurrentMileageFromImageRecord(fuelCardUuid, refuelingRecordPO.getTradingTime());
                    refuelingRecordPO.setCurrentMileage(thisCurrentMileage == null ? 0 : thisCurrentMileage);
                    refuelingRecordMapper.saveARefuelingRecord(refuelingRecordPO);
                }
                if (refuelingRecordPO.getTradingTime().getTime() > lastRefuelingTime.getTime()) {
                    fuelCardPO.setBalance(refuelingRecordPO.getBalance());
                    lastRefuelingTime = refuelingRecordPO.getTradingTime();
                }
                //更新加油卡信息
                fuelCardManagerMapper.updateFuelCard(fuelCardPO);
            }
        }
        LOGGER.info("加油卡查询:主卡号：{}、副卡号：{}、供应商：{}查询成功", fuelCard.getMasterCardNumber(), fuelCard.getViceCardNumber(), fuelCard.getCardMerchant());
    }

    /**
     * 根据加油卡uuid更改车牌号
     *
     * @param uuid
     * @param bundlePlateNumber
     */
    @Override
    public void updateBundlePlateNumber(String uuid, String bundlePlateNumber) {
        fuelCardManagerMapper.updateBundlePlateNumber(uuid, bundlePlateNumber);
    }
}
