package com.meng.student.trusteeship.controller.vehicle;


import com.google.gson.Gson;
import com.meng.student.trusteeship.dao.car.CarMapper;
import com.meng.student.trusteeship.dao.mongo.InsuranceImageOperation;
import com.meng.student.trusteeship.entity.administrator.Administrator;
import com.meng.student.trusteeship.entity.mongo.InsuranceResources;
import com.meng.student.trusteeship.entity.result.BaseResult;
import com.meng.student.trusteeship.entity.vehicle.Car;
import com.meng.student.trusteeship.entity.vehicle.CarAllInfo;
import com.meng.student.trusteeship.entity.vehicle.Insurance;
import com.meng.student.trusteeship.entity.vehicle.VehicleAdditionalInformation;
import com.meng.student.trusteeship.entity.vehicle.dto.ManagerCarSendDTO;
import com.meng.student.trusteeship.entity.vehicle.dto.ManegerCarAcceptDTO;
import com.meng.student.trusteeship.entity.vehicle.vo.CarQueryVO;
import com.meng.student.trusteeship.entity.vehicle.vo.InsuranceVO;
import com.meng.student.trusteeship.entity.vehicle.vo.VehicleConditionQueryVO;
import com.meng.student.trusteeship.service.insurance.InsuranceService;
import com.meng.student.trusteeship.service.vehicle.CarService;
import com.meng.student.trusteeship.util.ConvertUtils;
import com.meng.student.trusteeship.util.UuidUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 车辆基本信息接口
 *
 * @author caojinbo
 * @since 2018.3.20
 */
@RestController
public class VehicleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleController.class);

    @Autowired
    private CarService carService;

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private Gson gson;

    @Autowired
    private InsuranceImageOperation insuranceImageOperation;

    @RequestMapping(value = "vehicle/listCar", method = RequestMethod.POST)
    public Map<String, Object> vehiclepagequery(CarQueryVO carQueryVO) {
        Assert.notNull(carQueryVO.getPage(), "当前页不能为空");
        Assert.notNull(carQueryVO.getLimit(), "当前页面大小不能为空");
        Map<String, Object> queryMap = getMap(carQueryVO);
        return carService.listCar(queryMap);
    }

    @RequestMapping(value = "/vehicle/listDriverLicense", method = RequestMethod.POST)
    public Map<String, Object> listDriverLicense(CarQueryVO carQueryVO) {
        Assert.notNull(carQueryVO.getPage(), "当前页不能为空");
        Assert.notNull(carQueryVO.getLimit(), "当前页面大小不能为空");
        Map<String, Object> queryMap = getMap(carQueryVO);
        return carService.listDriverLicense(queryMap);
    }


    @RequestMapping(value = "vehicle/addExtraInfo", method = RequestMethod.POST)
    public BaseResult addVehicleInfo(@RequestBody VehicleAdditionalInformation vehicleAdditionalInformation, HttpSession session) {

        check(vehicleAdditionalInformation);

        String carNumber = vehicleAdditionalInformation.getCarNumber();
        BigDecimal vehiclePrice = vehicleAdditionalInformation.getVehiclePrice();
        insertVehiclePrice(carNumber, vehiclePrice);

        saveInsurance(vehicleAdditionalInformation, session);

        return BaseResult.getResult();
    }

    /**
     * 保存保险的照片
     *
     * @param files     照片
     * @param carNumber 车牌号
     * @return 空
     */
    @RequestMapping(value = "vehicle/addInsuranceImage/{carNumber}", method = RequestMethod.POST)
    public BaseResult addVehicleInsuranceImage(MultipartFile[] files, @PathVariable String carNumber) {

        ArrayList<String> imageResources = new ArrayList<>();
        Insurance insurance = insuranceService.getByCarNumber(carNumber);
        String insuranceId = insurance.getId();
        for (MultipartFile file : files) {
            String encode = null;
            try {
                encode = new BASE64Encoder().encode(file.getBytes());
            } catch (Exception e) {
                LOGGER.error("车牌号:{}存放保险图片时，转换流异常{}", carNumber, e);
                insuranceService.deleteInsuranceById(insuranceId);
                throw new IllegalArgumentException("车牌号:" + carNumber + "存放保险图片时，转换流异常" + e);
            }
            imageResources.add(encode);
        }

        InsuranceResources insuranceResources = new InsuranceResources();
        insuranceResources.setId(insuranceId);
        insuranceResources.setImageResources(imageResources);
        insuranceImageOperation.save(insuranceResources);
        return BaseResult.getResult();
    }

    /**
     * 通过 城市，仓库 准备查询，车牌号和驾驶证号进行模糊查询车辆信息
     *
     * @param managerCarDto 查询条件 ManagerCarSendDTO
     * @return 车辆信息 List<ManegerCarAcceptDTO>
     */
    @RequestMapping(value = "vehicle/manager/query", method = RequestMethod.POST)
    public List<ManegerCarAcceptDTO> managerCarQuery(@RequestBody ManagerCarSendDTO managerCarDto) {

        return carService.listWarehouseInfo(managerCarDto);

    }

    /**
     * 通过车牌号返回年检有效期和保险到期时间
     *
     * @param carNumber 车牌号
     * @return 车牌号，保险到期时间， 年检有效期 的 map
     */
    @RequestMapping(value = "vehicle/info/{carNumber}", method = RequestMethod.GET)
    public String get(@PathVariable String carNumber) {

        Date validityDate = getValidityDate(carNumber);
        Date insuranceStopDate = getInsuranceStopDate(carNumber);
        Map map = returnMap(carNumber, validityDate, insuranceStopDate);
        return gson.toJson(map);

    }

    /**
     * 根据条件查询城市仓库下的所有车辆信息
     *
     * @param vehicleConditionQueryVO 查询条件
     * @return 所需的车辆信息
     */
    @RequestMapping(value = "vehicle/web/listCarAllInfo", method = RequestMethod.POST)
    public BaseResult listCarAllInfo(@RequestBody VehicleConditionQueryVO vehicleConditionQueryVO) {
        Map map = getMap(vehicleConditionQueryVO);
        Map returnmap = carService.listCarAllInfo(map);
        return BaseResult.getResult(returnmap);
    }

    /**
     * 根据条件查询城市仓库下的所有车辆信息 post 前端APP调用接口
     *
     * @param vehicleConditionQueryVO 查询条件
     * @return List<CarAllInfo>
     */
    @RequestMapping(value = "vehicle/listCarAllInfo", method = RequestMethod.POST)
    public List<CarAllInfo> listCarAllInfoInterface(@RequestBody VehicleConditionQueryVO vehicleConditionQueryVO) {
        Map map = getMap(vehicleConditionQueryVO);
        map.put("queryStatus", "1");
        List<CarAllInfo> carAllInfos = carMapper.listCarAllInfo(map).stream().map(ConvertUtils::convert).collect(Collectors.toList());
        return carAllInfos;
    }

    /**
     * 保存车辆保险的  controller
     *
     * @param insuranceVO 车辆保险的实体类
     * @return
     */
    @RequestMapping(value = "vehicle/insurance", method = RequestMethod.POST)
    public BaseResult saveInsurance(@RequestBody InsuranceVO insuranceVO) {

        insuranceVO.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        checkInsurance(insuranceVO);
        Insurance insurance = ConvertUtils.convert(insuranceVO);
        insuranceService.save(insurance);
        return BaseResult.getResult();

    }

    /**
     * 根据车牌号码和该车的最新一次的时间，进行更新操作
     *
     * @param insuranceVO 车辆保险的实体类
     * @return
     */
    @RequestMapping(value = "vehicle/modifyInsurance/{insuranceId}", method = RequestMethod.POST)
    public BaseResult updateInsurance(@RequestBody InsuranceVO insuranceVO, @PathVariable String insuranceId) {
        checkInsurance(insuranceVO);
        Insurance insurance = ConvertUtils.convert(insuranceVO);

        insuranceService.deleteInsuranceById(insuranceId);
        insuranceImageOperation.delete(insuranceId);

        insuranceService.save(insurance);
        return BaseResult.getResult();
    }


    @RequestMapping(value = "vehicle/carAllInfo/{carNumber}")
    public BaseResult getCarAllInfo(@PathVariable String carNumber) {
        Map map = carService.getCarAllInfo(carNumber);
        return BaseResult.getResult(map);
    }

    /**
     * 返回一个车辆补全信息的excel文件
     *
     * @return
     */
    @RequestMapping(value = "vehicle/responseVehicleTemplate")
    public ResponseEntity<byte[]> responseVehicleTemplate() {
        return carService.responseVehicleTemplate();
    }

    /**
     * 以excel批量导入车辆补全信息
     *
     * @param vehicleExcel 上传的excel文件
     * @return
     */
    @RequestMapping(value = "vehicle/uploadSeveralVehicleInformation", method = RequestMethod.POST)
    public Map uploadSeveralVehicleInformation(MultipartFile vehicleExcel, HttpSession session) {

        Map<String, Object> resultMap = new HashMap<>(2);
        List<Object> uploadSeveralResultList = null;
        try {
            uploadSeveralResultList = carService.uploadSeveralVehicleInformation(vehicleExcel.getInputStream(), session);
            Integer ifUploadSeveralSuccess = (Integer) uploadSeveralResultList.get(0);
            if (ifUploadSeveralSuccess == 0) {
                resultMap.put("result", "success");
            }
            if (ifUploadSeveralSuccess != 0) {
                resultMap.put("result", "false");
            }
            uploadSeveralResultList.remove(0);
        } catch (IOException | InvalidFormatException e) {
            resultMap.put("result", "false");
        }

        resultMap.put("carPONotAddList", uploadSeveralResultList);
        return resultMap;
    }


    private Map returnMap(String carNumber, Date validityDate, Date insuranceStopDate) {
        Map<String, Object> map = new HashMap<>();
        map.put("carNumber", carNumber);
        if (null == validityDate && null == insuranceStopDate) {
            return null;
        }

        if (null != validityDate) {
            map.put("validityDate", validityDate.getTime());
        }
        if (null != insuranceStopDate) {
            map.put("insuranceStopDate", insuranceStopDate.getTime());
        }

        return map;
    }


    private Date getValidityDate(String carNumber) {
        Car car = carService.getCarByCarNumber(carNumber);
        if (null == car) {
            return null;
        }
        return car.getValidityDate();
    }

    private Date getInsuranceStopDate(String carNumber) {
        Insurance insurance = insuranceService.getByCarNumber(carNumber);
        if (null == insurance) {
            return null;
        }
        return insurance.getStopDate();
    }

    private void insertVehiclePrice(String carNumber, BigDecimal vehiclePrice) {
        Car car = carService.getCarByCarNumber(carNumber);
        car.setVehiclePrice(vehiclePrice);
        carService.updateVehiclePrice(car);
    }


    private void saveInsurance(VehicleAdditionalInformation vehicleAdditionalInformation, HttpSession session) {
        Administrator administrator = (Administrator) session.getAttribute("administrator");
        Assert.notNull(administrator, "没有该管理员的信息");
        String operateId = administrator.getUuid();

        Insurance insurance = new Insurance();
        insurance.setId(UuidUtils.getUuid());
        insurance.setCarNumber(vehicleAdditionalInformation.getCarNumber());
        insurance.setStopDate(convert(vehicleAdditionalInformation.getValidityDate()));
        insurance.setStartDate(convert(vehicleAdditionalInformation.getRegistrationTime()));
        insurance.setInsuranceFee(vehicleAdditionalInformation.getInsuranceFee());
        insurance.setOperatorId(operateId);
        if (null != vehicleAdditionalInformation.getCashBackAmount()) {
            insurance.setCashBackAmount(vehicleAdditionalInformation.getCashBackAmount());
        }

        insuranceService.save(insurance);
    }

    private void check(VehicleAdditionalInformation vehicleAdditionalInformation) {
        Assert.hasLength(vehicleAdditionalInformation.getCarNumber(), "车牌号不能为空");
        Assert.notNull(vehicleAdditionalInformation.getInsuranceFee(), "保险费用不能为空");
        Assert.notNull(vehicleAdditionalInformation.getRegistrationTime(), "保险注册日期不能为空");
        Assert.notNull(vehicleAdditionalInformation.getValidityDate(), "保险失效日期不能为空");
        Assert.notNull(vehicleAdditionalInformation.getVehiclePrice(), "车辆价格不能为空");
    }

    private Map<String, Object> getMap(CarQueryVO carQueryVO) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("currentPage", (carQueryVO.getPage() - 1) * carQueryVO.getLimit());
        map.put("pageSize", carQueryVO.getLimit());
        map.put("city", carQueryVO.getCity());
        map.put("carNumber", carQueryVO.getCarNumber());
        map.put("warehouse", carQueryVO.getWarehouse());

        map.put("registrationTime", carQueryVO.getRegistrationTime());
        map.put("startDate", carQueryVO.getStartDate());
        map.put("endDate", carQueryVO.getEndDate());
        if (null != carQueryVO.getStatus() && !carQueryVO.getStatus()) {
            map.put("status", 0);
        }
        if (null != carQueryVO.getStatus() && carQueryVO.getStatus()) {
            map.put("status", 1);
        }
        return map;
    }

    private Map getMap(VehicleConditionQueryVO vehicleConditionQueryVO) {
        HashMap<String, Object> map = new HashMap<>();
        if (null != vehicleConditionQueryVO.getCity()) {
            map.put("city", vehicleConditionQueryVO.getCity());
        }
        if (null != vehicleConditionQueryVO.getCarNumber()) {
            map.put("carNumber", vehicleConditionQueryVO.getCarNumber());
        }
        if (null != vehicleConditionQueryVO.getWarehouse()) {
            map.put("warehouse", vehicleConditionQueryVO.getWarehouse());
        }
        if (null != vehicleConditionQueryVO.getCurrentPage()) {
            map.put("currentPage", (vehicleConditionQueryVO.getCurrentPage() - 1) * vehicleConditionQueryVO.getPageSize());
        }
        if (null != vehicleConditionQueryVO.getPageSize()) {
            map.put("pageSize", vehicleConditionQueryVO.getPageSize());
        }
        if (null != vehicleConditionQueryVO.getInsuranceIsNull()) {
            map.put("insuranceIsNull", vehicleConditionQueryVO.getInsuranceIsNull());
        }
        if (null != vehicleConditionQueryVO.getCarCheckIsNull()) {
            map.put("carCheckIsNull", vehicleConditionQueryVO.getCarCheckIsNull());
        }

        Date insuranceStartDate = null;
        if (vehicleConditionQueryVO.getInsuranceStartDateView() != null) {
            insuranceStartDate = new Date(vehicleConditionQueryVO.getInsuranceStartDateView());
        }
        Date insuranceStopDate = null;
        if (vehicleConditionQueryVO.getInsuranceStopDateView() != null) {
            insuranceStopDate = new Date(vehicleConditionQueryVO.getInsuranceStopDateView());
        }

        Date carCheckStartDate = null;
        if (vehicleConditionQueryVO.getCarCheckStartDateView() != null) {
            carCheckStartDate = new Date(vehicleConditionQueryVO.getCarCheckStartDateView());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
            String str = format.format(carCheckStartDate);
            Date date = null;
            try {
                date = format.parse(str);
            } catch (ParseException e) {
                LOGGER.info("时间转换异常：{}", e);
            }

        }
        Date carCheckStopDate = null;
        if (vehicleConditionQueryVO.getCarCheckStopDateView() != null) {

            carCheckStopDate = new Date(vehicleConditionQueryVO.getCarCheckStopDateView());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(carCheckStopDate);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date time = calendar.getTime();
            carCheckStopDate = time;

        }

        Boolean violationStatus = vehicleConditionQueryVO.getViolationStatus();
        Boolean vehicleStatus = vehicleConditionQueryVO.getVehicleStatus();

        if (null != vehicleStatus) {
            map.put("vehicleStatus", vehicleConditionQueryVO.getVehicleStatus());
        }
        if (null != insuranceStartDate) {
            map.put("insuranceStartDate", insuranceStartDate);
        }
        if (null != insuranceStopDate) {
            map.put("insuranceStopDate", insuranceStopDate);
        }
        if (null != carCheckStartDate) {
            map.put("carCheckStartDate", carCheckStartDate);
        }
        if (null != carCheckStopDate) {
            map.put("carCheckStopDate", carCheckStopDate);
        }
        if (null != violationStatus) {
            map.put("violationStatus", violationStatus);
        }

        return map;
    }


    private void checkInsurance(InsuranceVO insuranceVO) {

        Assert.notNull(insuranceVO.getCashBackAmount(), "保险的返现金额不为空");
        Assert.notNull(insuranceVO.getStopDate(), "保险的截止时间不为空");
        Assert.notNull(insuranceVO.getCarNumber(), "保险的车牌号码不为空");
        Assert.notNull(insuranceVO.getInsuranceFee(), "保险的费用不为空");
        Assert.notNull(insuranceVO.getOperatorId(), "保险的操作人ID不为空");
        Assert.notNull(insuranceVO.getStartDate(), "保险的开始时间不为空");

    }

    private Date convert(Long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = format.format(time);
        try {
            return format.parse(d);
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间转换失败", e);
        }
    }

}
