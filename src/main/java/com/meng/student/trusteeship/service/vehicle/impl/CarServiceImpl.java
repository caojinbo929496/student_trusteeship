package com.meng.student.trusteeship.service.vehicle.impl;

import com.meng.student.trusteeship.dao.car.CarMapper;
import com.meng.student.trusteeship.dao.fuel.RefuelingRecordMapper;
import com.meng.student.trusteeship.dao.insurance.InsuranceMapper;
import com.meng.student.trusteeship.dao.maintain.MaintainMapper;
import com.meng.student.trusteeship.dao.mongo.InsuranceImageOperation;
import com.meng.student.trusteeship.dao.upkeep.UpkeepMapper;
import com.meng.student.trusteeship.dao.violation.ViolationMapper;
import com.meng.student.trusteeship.entity.administrator.Administrator;
import com.meng.student.trusteeship.entity.fuel.RefuelingRecordCount;
import com.meng.student.trusteeship.entity.fuel.RefuelingRecordForForms;
import com.meng.student.trusteeship.entity.fuel.dto.RefuelingRecordForFormsDTO;
import com.meng.student.trusteeship.entity.maintain.MaintainPO;
import com.meng.student.trusteeship.entity.maintain.MaintainVO;
import com.meng.student.trusteeship.entity.mongo.InsuranceResources;
import com.meng.student.trusteeship.entity.upkeep.UpkeepPO;
import com.meng.student.trusteeship.entity.upkeep.UpkeepVO;
import com.meng.student.trusteeship.entity.vehicle.Car;
import com.meng.student.trusteeship.entity.vehicle.CarAllInfo;
import com.meng.student.trusteeship.entity.vehicle.dto.ManagerCarSendDTO;
import com.meng.student.trusteeship.entity.vehicle.dto.ManegerCarAcceptDTO;
import com.meng.student.trusteeship.entity.vehicle.po.*;
import com.meng.student.trusteeship.entity.vehicle.vo.CarVO;
import com.meng.student.trusteeship.entity.vehicle.vo.InsuranceAndImagesVO;
import com.meng.student.trusteeship.entity.vehicle.vo.ViolationVO;
import com.meng.student.trusteeship.service.administrator.AdministratorService;
import com.meng.student.trusteeship.service.fuel.RefuelingRecordService;
import com.meng.student.trusteeship.service.vehicle.CarService;
import com.meng.student.trusteeship.util.ConvertUtils;
import com.meng.student.trusteeship.util.UuidUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@CacheConfig(cacheNames = "CarServiceImpl")
public class CarServiceImpl implements CarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    private RefuelingRecordService refuelingRecordService;

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private MaintainMapper maintainMapper;

    @Autowired
    private UpkeepMapper upkeepMapper;

    @Autowired
    private InsuranceMapper insuranceMapper;

    @Autowired
    private ViolationMapper violationMapper;

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private InsuranceImageOperation insuranceImageOperation;


    @Autowired
    RefuelingRecordMapper refuelingRecordMapper;

    @Override
    public void saveCar(Car car) {
        CarPO carPO = ConvertUtils.convert(car);
        carMapper.insertCar(carPO);
    }

    @Override
    public Car getCarById(String id) {
        CarPO carPO = carMapper.getCarById(id);
        return ConvertUtils.convert(carPO);
    }

    @Override
    public Car getCarByCarNumber(String carNumber) {
        CarPO carPO = carMapper.getCarByCarNumber(carNumber);
        return ConvertUtils.convert(carPO);
    }

    @Override
    public void updateVehiclePrice(Car car) {
        carMapper.updateVehiclePrice(car);
    }

    @Override
    public List<Car> listAll() {
        List<CarPO> carPOS = carMapper.listCar();
        return carPOS.stream().map(ConvertUtils::convert).collect(Collectors.toList());
    }

    @Override
    @Cacheable(key = "'listCarAllInfo'")
    public Map<String, Object> listCarAllInfo(Map map) {
        LOGGER.info("開始查詢車輛信息");
        List<CarAllInfoPO> carAllInfoPOS = carMapper.listCarAllInfo(map);
        List<CarAllInfo> carAllInfos = carAllInfoPOS.stream().map(ConvertUtils::convert).collect(Collectors.toList());

        map.put("queryStatus", "1");
        Integer count = carMapper.listCarAllInfo(map).size();
        HashMap<String, Object> returnMap = new HashMap<>();
        String message = "success";
        returnMap.put("code", 0);
        returnMap.put("message", message);
        returnMap.put("count", count);
        returnMap.put("carAllInfos", carAllInfos);

        return returnMap;
    }

    @Override
    @Cacheable(key = "'getCarAllInfo'+#carNumber")
    public Map getCarAllInfo(String carNumber) {
        RefuelingRecordCount refuelingRecordCount = refuelingRecordService.getRefuelingRecordCountByBundlePlateNumber(carNumber);
        List<RefuelingRecordForForms> refuelingRecordForForms = refuelingRecordService.getRefuelingRecordForFormsListByBundlePlateNumber(carNumber);
        List<UpkeepPO> upkeepPOS = upkeepMapper.getByCarNumber(carNumber);
        List<MaintainPO> maintainPOS = maintainMapper.getByCarNumber(carNumber);
        CarPO carPO = carMapper.getCarByCarNumber(carNumber);
        ViolationCountInfoPO violationCountInfoPO = violationMapper.getViolationCountInfo(carNumber);
        List<ViolationPO> violationPOS = violationMapper.listViolationPOByCarNumber(carNumber);
        List<InsurancePO> insurancePOS = insuranceMapper.getAllByCarNumber(carNumber);
        List<CarPO> carPOYearChecks = carMapper.listCarAllInsurance(carNumber);

        InsurancePO freashInsurancePO = null;
        if (insuranceMapper.getFreshInsuranceByCarNumber(carNumber).size() > 0) {
            freashInsurancePO = insuranceMapper.getFreshInsuranceByCarNumber(carNumber).get(0);
        }

        List<InsuranceAndImagesVO> insuranceAndImagesVOS = insurancePOS.stream().map(this::convert).collect(Collectors.toList());
        List<RefuelingRecordForFormsDTO> refuelingRecordForFormsVO = refuelingRecordForForms.stream().map(ConvertUtils::convert).collect(Collectors.toList());
        List<MaintainVO> maintainVOList = maintainPOS.stream().map(ConvertUtils::convertToMaintainVO).collect(Collectors.toList());
        List<UpkeepVO> upkeepVOList = upkeepPOS.stream().map(ConvertUtils::convertToUpkeepVO).collect(Collectors.toList());
        List<CarVO> carVOList = carPOYearChecks.stream().map(ConvertUtils::convertToCarVO).collect(Collectors.toList());
        List<ViolationVO> violationVOS = violationPOS.stream().map(ConvertUtils::convertToViolationVO).collect(Collectors.toList());

        HashMap<String, Object> returnMap = new HashMap<>();
        returnMap.put("car", ConvertUtils.convertToCarVO(carPO));
        returnMap.put("refuelingRecordCount", refuelingRecordCount);
        returnMap.put("refuelingRecordForForms", refuelingRecordForFormsVO);
        returnMap.put("upkeeps", upkeepVOList);
        returnMap.put("maintains", maintainVOList);
        returnMap.put("violationCountInfo", violationCountInfoPO);
        returnMap.put("violations", violationVOS);
        returnMap.put("freashInsurance", ConvertUtils.convertToInsuranceVO(freashInsurancePO));
        returnMap.put("vehicleYearChecks", carVOList);
        returnMap.put("insuranceAndImagesPOS", insuranceAndImagesVOS);


        return returnMap;
    }

    @Override
    public List<ManegerCarAcceptDTO> listWarehouseInfo(ManagerCarSendDTO managerCarSendDTO) {
        Assert.notNull(managerCarSendDTO, "APP端传过来的仓库管理员查询信息不能为null");
        Assert.hasLength(managerCarSendDTO.getCity(), "该城市信息不能为空");

        Map map = getMap(managerCarSendDTO);
        List<ManegerCarAcceptPO> manegerCarAcceptPOS = carMapper.listWarehouseCarInfo(map);

        //上次里程数分开查询
        for (int i = 0; i < manegerCarAcceptPOS.size(); i++) {
            manegerCarAcceptPOS.get(i).setCurrentMileage(refuelingRecordMapper.getLastMileageByFalseTradingTime(manegerCarAcceptPOS.get(i).getTradingTime(), manegerCarAcceptPOS.get(i).getCarNumber()));
        }
        return manegerCarAcceptPOS.stream().map(ConvertUtils::convert).collect(Collectors.toList());
    }


    @Override
    public ResponseEntity<byte[]> responseVehicleTemplate() {
        HSSFWorkbook wkb = new HSSFWorkbook();

        HSSFSheet cardExcelTemplate = wkb.createSheet("车辆信息补全模板");
        cardExcelTemplate.setDefaultColumnWidth(30);

        HSSFCellStyle style = wkb.createCellStyle();
        HSSFFont font = wkb.createFont();
        font.setColor(HSSFColor.RED.index);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        HSSFRow row1 = cardExcelTemplate.createRow(0);
        row1.createCell(0).setCellValue("前6列不可更改");
        cardExcelTemplate.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
        row1.getCell(0).setCellStyle(style);
        row1.createCell(6).setCellValue("后5列为必填字段，如果已经有数据则不填，没有请填写;时间请按格式\"2018-01-01\"填写,Excel可能会自动变换时间形式、您只需按格式填写即可");
        cardExcelTemplate.addMergedRegion(new CellRangeAddress(0, 0, 6, 12));
        row1.getCell(6).setCellStyle(style);

        HSSFRow row2 = cardExcelTemplate.createRow(1);
        row2.createCell(0).setCellValue("车辆ID");
        row2.createCell(1).setCellValue("城市");
        row2.createCell(2).setCellValue("车牌号码");
        row2.createCell(3).setCellValue("车辆品牌");
        row2.createCell(4).setCellValue("车辆类型");
        row2.createCell(5).setCellValue("所属仓库");
        row2.createCell(6).setCellValue("保险开始时间");
        row2.createCell(7).setCellValue("保险结束时间");
        row2.createCell(8).setCellValue("保险费用");
        row2.createCell(9).setCellValue("返现金额");
        row2.createCell(10).setCellValue("车辆购买价格");

        List<ToBeCompeteledCarPO> toBeCompeteledCarPOList = carMapper.listPartCar();
        HSSFRow otherRow = null;

        for (int i = 0; i < toBeCompeteledCarPOList.size(); i++) {
            otherRow = cardExcelTemplate.createRow(i + 2);
            otherRow.createCell(0).setCellValue(toBeCompeteledCarPOList.get(i).getId());
            otherRow.createCell(1).setCellValue(toBeCompeteledCarPOList.get(i).getCity());
            otherRow.createCell(2).setCellValue(toBeCompeteledCarPOList.get(i).getCarNumber());
            otherRow.createCell(3).setCellValue(toBeCompeteledCarPOList.get(i).getBrandModelNumber());
            otherRow.createCell(4).setCellValue(toBeCompeteledCarPOList.get(i).getVehicleType());
            otherRow.createCell(5).setCellValue(toBeCompeteledCarPOList.get(i).getWarehouse());
            otherRow.createCell(6).setCellValue("");
            otherRow.createCell(7).setCellValue("");
            otherRow.createCell(8).setCellValue("");
            otherRow.createCell(9).setCellValue("");
            otherRow.createCell(10).setCellValue("");
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            wkb.write(out);
        } catch (IOException e) {
            LOGGER.info("excel内存文件转换为的字节数组写到输出流中时产生错误，" + e);
            throw new RuntimeException("excel内存文件转换为的字节数组写到输出流中时产生错误，" + e);
        }

        HttpHeaders headers = new HttpHeaders();
        String fileName = null;
        try {
            fileName = new String("车辆信息补全模板.xls".getBytes("utf-8"), "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("字符编码格式不支持，" + e);
        }
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        byte[] outputStreamByte = out.toByteArray();
        ResponseEntity<byte[]> filebyte = new ResponseEntity<byte[]>(outputStreamByte, headers, HttpStatus.CREATED);
        try {
            out.close();
        } catch (IOException e) {
            throw new RuntimeException("excel内存文件转换为的字节数组写到输出流中关闭输出流时产生错误，" + e);
        }

        return filebyte;
    }


    @Override
    public List<Object> uploadSeveralVehicleInformation(InputStream excelInputStream, HttpSession session) {
        List<Object> carPOInsertFailList = new ArrayList();
        carPOInsertFailList.add(0);
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(excelInputStream);
        } catch (IOException e) {
            LOGGER.info("创建excel工厂时产生错误," + e);
            carPOInsertFailList.set(0, 1);
            return carPOInsertFailList;
        } catch (InvalidFormatException e) {
            LOGGER.info("传入的文件数据格式错误，" + e);
            carPOInsertFailList.set(0, 2);
            return carPOInsertFailList;
        }

        Sheet excelSheet = workbook.getSheetAt(0);

        Integer startRowNumber = excelSheet.getFirstRowNum() + 2;
        Integer endRowNumber = excelSheet.getLastRowNum();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        BigDecimal perInputVehiclePrice = null;
        for (int i = startRowNumber; i <= endRowNumber; i++) {
            Row carRow = excelSheet.getRow(i);
            InsurancePO insurancePO = new InsurancePO();
            //除了时间提前读取之外，其它字段都已字符串读取
            int perRowCellIndex = 0;

            for (; perRowCellIndex <= carRow.getLastCellNum(); perRowCellIndex++) {

                if (perRowCellIndex != 6 && perRowCellIndex != 7 && (carRow.getCell(perRowCellIndex) != null)) {
                    carRow.getCell(perRowCellIndex).setCellType(Cell.CELL_TYPE_STRING);
                }
            }

            try {


                insurancePO.setCarNumber(carRow.getCell(2).getStringCellValue());

                carRow.getCell(6).setCellType(Cell.CELL_TYPE_NUMERIC);
                carRow.getCell(7).setCellType(Cell.CELL_TYPE_NUMERIC);

                insurancePO.setStartDate(HSSFDateUtil.getJavaDate(carRow.getCell(6).getNumericCellValue()));
                insurancePO.setStopDate(HSSFDateUtil.getJavaDate(carRow.getCell(7).getNumericCellValue()));


                BigDecimal insuranceFee = new BigDecimal(carRow.getCell(8).getStringCellValue());
                insuranceFee = insuranceFee.setScale(2, BigDecimal.ROUND_HALF_UP);
                insurancePO.setInsuranceFee(insuranceFee);

                BigDecimal cashBackAmount = new BigDecimal(carRow.getCell(9).getStringCellValue());
                cashBackAmount = cashBackAmount.setScale(BigDecimal.ROUND_CEILING, BigDecimal.ROUND_HALF_UP);
                insurancePO.setCashBackAmount(cashBackAmount);
                Administrator administrator = (Administrator) session.getAttribute("administrator");
                //该系统网页端只会有一位用户，暂时这么写
                insurancePO.setOperatorId(administrator.getUuid());

                //设置保险的uuid
                insurancePO.setId(UuidUtils.getUuid());

            } catch (Exception e) {
                CarPO carPO = new CarPO();
                Cell carPOIdCell = carRow.getCell(0);
                Cell carPOCityCell = carRow.getCell(1);
                if (null == carPOIdCell || carPOCityCell == null) {
                    carPOInsertFailList.set(0, 1);
                    continue;
                }
                carPO.setId(carPOIdCell.getStringCellValue());
                carPO.setCity(carPOCityCell.getStringCellValue());
                carPO.setCarNumber(insurancePO.getCarNumber());
                carPOInsertFailList.add(carPO);
                carPOInsertFailList.set(0, 1);
                continue;
            }

            if (insurancePO.getCashBackAmount().compareTo(insurancePO.getInsuranceFee()) > 0) {
                CarPO carPO = new CarPO();
                carPO.setId(carRow.getCell(0).getStringCellValue());
                carPO.setCity(carRow.getCell(1).getStringCellValue());
                carPO.setCarNumber(insurancePO.getCarNumber());
                carPOInsertFailList.add(carPO);
                carPOInsertFailList.set(0, 1);
                continue;
            }


            if (insurancePO.getStartDate().getTime() >= insurancePO.getStopDate().getTime()) {
                CarPO carPO = new CarPO();
                carPO.setId(carRow.getCell(0).getStringCellValue());
                carPO.setCity(carRow.getCell(1).getStringCellValue());
                carPO.setCarNumber(insurancePO.getCarNumber());
                carPOInsertFailList.add(carPO);
                carPOInsertFailList.set(0, 1);
                continue;
            }


            try {
                perInputVehiclePrice = new BigDecimal(carRow.getCell(10).getStringCellValue());
                perInputVehiclePrice = perInputVehiclePrice.setScale(2, BigDecimal.ROUND_HALF_UP);

            } catch (Exception e) {
                CarPO carPO = new CarPO();
                carPO.setId(carRow.getCell(0).getStringCellValue());
                carPO.setCity(carRow.getCell(1).getStringCellValue());
                carPO.setCarNumber(insurancePO.getCarNumber());
                carPOInsertFailList.add(carPO);
                carPOInsertFailList.set(0, 1);
                continue;
            }

            if (insurancePO.getInsuranceFee().signum() < 0 || insurancePO.getCashBackAmount().signum() < 0 || perInputVehiclePrice.signum() < 0) {
                CarPO carPO = new CarPO();
                carPO.setId(carRow.getCell(0).getStringCellValue());
                carPO.setCity(carRow.getCell(1).getStringCellValue());
                carPO.setCarNumber(insurancePO.getCarNumber());
                carPOInsertFailList.add(carPO);
                carPOInsertFailList.set(0, 1);
                continue;
            }
            insuranceMapper.save(insurancePO);
            carMapper.insertVehiclePriceByCarId(carRow.getCell(0).getStringCellValue(), perInputVehiclePrice);
        }
        return carPOInsertFailList;
    }

    @Override
    @Cacheable(key = "'listCar'")
    public Map<String, Object> listCar(Map map) {
        List<CarPO> carPOS = carMapper.listPartCarByMap(map);
        List<CarVO> carList = carPOS.stream().map(ConvertUtils::convertToCarVO).collect(Collectors.toList());
        int count = carMapper.countPartCarByMap(map);
        HashMap<String, Object> returnMap = new HashMap<>();
        String message = "success";
        returnMap.put("code", 0);
        returnMap.put("message", message);
        returnMap.put("count", count);
        returnMap.put("data", carList);
        return returnMap;
    }

    @Override
    public Map<String, Object> listDriverLicense(Map map) {
        List<CarPO> carPOS = carMapper.listDriverLicense(map);
        List<CarVO> carList = carPOS.stream().map(ConvertUtils::convertToCarVO).collect(Collectors.toList());
        int count = carMapper.countDriverLicense(map);
        HashMap<String, Object> returnMap = new HashMap<>();
        String message = "success";
        returnMap.put("code", 0);
        returnMap.put("message", message);
        returnMap.put("count", count);
        returnMap.put("data", carList);
        return returnMap;
    }

    private Map getMap(ManagerCarSendDTO managerCarSendDTO) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("city", managerCarSendDTO.getCity());
        map.put("warehouse", managerCarSendDTO.getWarehouse());
        map.put("carNumber", managerCarSendDTO.getCarNumber());
        map.put("patenteNumber", managerCarSendDTO.getPatenteNumber());

        return map;
    }

    public InsuranceAndImagesVO convert(InsurancePO insurancePO) {
        if (null == insurancePO) {
            return null;
        }
        String operatorId = insurancePO.getOperatorId();
        String insurancePOId = insurancePO.getId();
        InsuranceAndImagesVO insuranceAndImagesVO = new InsuranceAndImagesVO();
        insuranceAndImagesVO.setCarNumber(insurancePO.getCarNumber());
        insuranceAndImagesVO.setCashBackAmount(insurancePO.getCashBackAmount());
        insuranceAndImagesVO.setId(insurancePOId);
        insuranceAndImagesVO.setInsuranceFee(insurancePO.getInsuranceFee());
        insuranceAndImagesVO.setOperatorId(operatorId);
        insuranceAndImagesVO.setStartDate(insurancePO.getStartDate().getTime());
        insuranceAndImagesVO.setStopDate(insurancePO.getStopDate().getTime());

        Administrator administrator = administratorService.getAdministratorById(operatorId);
        if (null != administrator) {
            insuranceAndImagesVO.setOperator(administrator.getName());
        }

        InsuranceResources insuranceResources = insuranceImageOperation.get(insurancePOId);
        if (null != insuranceResources) {
            insuranceAndImagesVO.setInsuranceImagePOS(insuranceResources.getImageResources());
        }

        return insuranceAndImagesVO;
    }

}
