package com.meng.student.trusteeship.util;

import com.meng.student.trusteeship.entity.administrator.Administrator;
import com.meng.student.trusteeship.entity.administrator.po.AdministratorPO;
import com.meng.student.trusteeship.entity.fuel.RefuelingRecordForForms;
import com.meng.student.trusteeship.entity.fuel.dto.RefuelingRecordForFormsDTO;
import com.meng.student.trusteeship.entity.maintain.MaintainPO;
import com.meng.student.trusteeship.entity.maintain.MaintainVO;
import com.meng.student.trusteeship.entity.upkeep.UpkeepPO;
import com.meng.student.trusteeship.entity.upkeep.UpkeepVO;
import com.meng.student.trusteeship.entity.vehicle.*;
import com.meng.student.trusteeship.entity.vehicle.dto.CarDTO;
import com.meng.student.trusteeship.entity.vehicle.dto.ManegerCarAcceptDTO;
import com.meng.student.trusteeship.entity.vehicle.dto.ViolationDTO;
import com.meng.student.trusteeship.entity.vehicle.po.*;
import com.meng.student.trusteeship.entity.vehicle.vo.CarVO;
import com.meng.student.trusteeship.entity.vehicle.vo.InsuranceVO;
import com.meng.student.trusteeship.entity.vehicle.vo.ViolationVO;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description: 实体类之间的互相转换
 * @Author: 吴宸煊
 * @Date: Created in  2018/3/14 0014
 */
public class ConvertUtils {

    /**
     * VO转化PO 并对密码进行加密
     *
     * @param administrator
     * @return AdministratorPO
     */

    public static AdministratorPO convertToPO(Administrator administrator) {
        if (null == administrator) {
            return null;
        }
        AdministratorPO administratorPO = new AdministratorPO();
        administratorPO.setName(administrator.getName());
        administratorPO.setUuid(administrator.getUuid());
        administratorPO.setPassword(MD5Utils.getMD5(administrator.getPassword()));
        return administratorPO;
    }

    /**
     * PO转化VO 并对密码进行加密
     *
     * @param administratorPO
     * @return Administrator
     */
    public static Administrator convertToVO(AdministratorPO administratorPO) {
        Administrator administrator = new Administrator();
        administrator.setName(administratorPO.getName());
        administrator.setUuid(administratorPO.getUuid());
        administrator.setPassword(MD5Utils.getMD5(administratorPO.getPassword()));
        return administrator;
    }

    public static Violation convert(ViolationPO violationPO) {
        Violation violation = new Violation();
        violation.setId(violationPO.getId());
        violation.setViolationInformation(violationPO.getViolationInformation());
        violation.setViolationPlace(violationPO.getViolationPlace());
        violation.setViolationTime(violationPO.getViolationTime());
        violation.setStatus(violationPO.getStatus());
        violation.setProcessingUnit(violationPO.getProcessingUnit());
        violation.setPenalty(violationPO.getPenalty());
        violation.setDeductMark(violationPO.getDeductMark());
        violation.setCarNumber(violationPO.getCarNumber());
        violation.setCarId(violationPO.getCarId());
        return violation;
    }

    public static ViolationVO convertToViolationVO(ViolationPO violationPO) {
        if (null == violationPO) {
            return null;
        }
        ViolationVO violationVO = new ViolationVO();
        violationVO.setId(violationPO.getId());
        violationVO.setViolationInformation(violationPO.getViolationInformation());
        violationVO.setViolationPlace(violationPO.getViolationPlace());
        violationVO.setStatus(violationPO.getStatus());
        violationVO.setProcessingUnit(violationPO.getProcessingUnit());
        violationVO.setPenalty(violationPO.getPenalty());
        violationVO.setDeductMark(violationPO.getDeductMark());
        violationVO.setCarNumber(violationPO.getCarNumber());
        violationVO.setCarId(violationPO.getCarId());

        if (null != violationPO.getViolationTime()) {
            violationVO.setViolationTime(violationPO.getViolationTime().getTime());
        }
        return violationVO;
    }

    public static ViolationPO convert(Violation violation) {
        ViolationPO violationPO = new ViolationPO();
        violationPO.setId(violation.getId());
        violationPO.setCarId(violation.getCarId());
        violationPO.setCarNumber(violation.getCarNumber());
        violationPO.setDeductMark(violation.getDeductMark());
        violationPO.setPenalty(violation.getPenalty());
        violationPO.setProcessingUnit(violation.getProcessingUnit());
        violationPO.setStatus(violation.getStatus());
        violationPO.setViolationPlace(violation.getViolationPlace());
        violationPO.setViolationInformation(violation.getViolationInformation());
        violationPO.setViolationTime(new Timestamp(violation.getViolationTime().getTime()));
        if (null != violation.getOffender()) {
            violationPO.setOffender(violation.getOffender());
        }
        if (null != violation.getOffenderId()) {
            violationPO.setOffenderId(violation.getOffenderId());
        }
        return violationPO;
    }

    public static ViolationDTO convertToViolationDTO(Violation violation) {
        ViolationDTO violationDTO = new ViolationDTO();
        violationDTO.setId(violation.getId());
        violationDTO.setCarId(violation.getCarId());
        violationDTO.setCarNumber(violation.getCarNumber());
        violationDTO.setDeductMark(violation.getDeductMark());
        violationDTO.setPenalty(violation.getPenalty());
        violationDTO.setProcessingUnit(violation.getProcessingUnit());
        violationDTO.setStatus(violation.getStatus());
        violationDTO.setViolationPlace(violation.getViolationPlace());
        violationDTO.setViolationInformation(violation.getViolationInformation());
        violationDTO.setViolationTime(violation.getViolationTime().getTime());


        if (null != violation.getOffenderId()) {
            violationDTO.setOffenderId(violation.getOffenderId());
        }

        return violationDTO;
    }

    public static Insurance convert(InsuranceVO insuranceVO) {
        if (null == insuranceVO) {
            return null;
        }
        Insurance insurance = new Insurance();
        insurance.setId(UuidUtils.getUuid());
        insurance.setOperatorId(insuranceVO.getOperatorId());
        insurance.setStopDate(convert(insuranceVO.getStopDate()));
        insurance.setInsuranceFee(insuranceVO.getInsuranceFee());
        insurance.setCarNumber(insuranceVO.getCarNumber());
        insurance.setStartDate(convert(insuranceVO.getStartDate()));
        insurance.setCashBackAmount(insuranceVO.getCashBackAmount());

        return insurance;
    }

    public static Car convert(CarPO carPO) {
        if (null == carPO) {
            return null;
        }

        Car car = new Car();
        car.setId(carPO.getId());
        car.setBrandModelNumber(carPO.getBrandModelNumber());
        car.setCarNumber(carPO.getCarNumber());
        car.setCity(carPO.getCity());

        if (null != carPO.getEngineNumber()) {
            car.setEngineNumber(carPO.getEngineNumber());
        }
        if (null != carPO.getFrameNumber()) {
            car.setFrameNumber(carPO.getFrameNumber());
        }
        if (null != carPO.getOwners()) {
            car.setOwners(carPO.getOwners());
        }
        if (null != carPO.getStatus()) {
            car.setStatus(carPO.getStatus());
        }
        if (null != carPO.getVehiclePrice()) {
            car.setVehiclePrice(carPO.getVehiclePrice());
        }
        if (null != carPO.getVehicleType()) {
            car.setVehicleType(carPO.getVehicleType());
        }
        if (null != carPO.getWarehouse()) {
            car.setWarehouse(carPO.getWarehouse());
        }
        if (null != carPO.getRegistrationTime()) {
            Date date = convert(carPO.getRegistrationTime());
            car.setRegistrationTime(date);
        }
        if (null != carPO.getValidityDate()) {
            Date date1 = convert(carPO.getValidityDate());
            car.setValidityDate(date1);
        }
        if (null != carPO.getAnnualInspectionStatus()) {
            car.setAnnualInspectionStatus(carPO.getAnnualInspectionStatus());
        }

        return car;
    }

    public static CarVO convertToCarVO(CarPO carPO) {
        if (null == carPO) {
            return null;
        }

        CarVO carVO = new CarVO();
        carVO.setId(carPO.getId());
        carVO.setBrandModelNumber(carPO.getBrandModelNumber());
        carVO.setCarNumber(carPO.getCarNumber());
        carVO.setCity(carPO.getCity());
        carVO.setVehiclePrice(carPO.getVehiclePrice());

        if (null != carPO.getEngineNumber()) {
            carVO.setEngineNumber(carPO.getEngineNumber());
        }
        if (null != carPO.getFrameNumber()) {
            carVO.setFrameNumber(carPO.getFrameNumber());
        }
        if (null != carPO.getOwners()) {
            carVO.setOwners(carPO.getOwners());
        }
        if (null != carPO.getStatus()) {
            carVO.setStatus(carPO.getStatus());
        }
        if (null != carPO.getVehiclePrice()) {
            carVO.setVehiclePrice(carPO.getVehiclePrice());
        }
        if (null != carPO.getVehicleType()) {
            carVO.setVehicleType(carPO.getVehicleType());
        }
        if (null != carPO.getWarehouse()) {
            carVO.setWarehouse(carPO.getWarehouse());
        }
        if (null != carPO.getRegistrationTime()) {
            Date date = convert(carPO.getRegistrationTime());
            carVO.setRegistrationTime(date.getTime());
        }
        if (null != carPO.getValidityDate()) {
            Date date1 = convert(carPO.getValidityDate());
            carVO.setValidityDate(date1.getTime());
        }
        if (null != carPO.getUploadTime()) {
            Date uploadTime = convert(carPO.getUploadTime());
            carVO.setUploadTime(uploadTime.getTime());
        }
        if (null != carPO.getAnnualInspectionStatus()) {
            carVO.setAnnualInspectionStatus(carPO.getAnnualInspectionStatus());
        }

        return carVO;
    }

    public static CarPO convert(Car car) {
        CarPO carPO = new CarPO();
        carPO.setId(car.getId());
        carPO.setBrandModelNumber(car.getBrandModelNumber());
        carPO.setCarNumber(car.getCarNumber());
        carPO.setCity(car.getCity());
        carPO.setEngineNumber(car.getEngineNumber());
        carPO.setFrameNumber(car.getFrameNumber());
        carPO.setOwners(car.getOwners());
        carPO.setStatus(car.getStatus());
        carPO.setVehiclePrice(car.getVehiclePrice());
        carPO.setVehicleType(car.getVehicleType());
        carPO.setWarehouse(car.getWarehouse());
        if (null != car.getRegistrationTime()) {
            carPO.setRegistrationTime(new Timestamp(car.getRegistrationTime().getTime()));
        }
        if (null != car.getValidityDate()) {
            carPO.setValidityDate(new Timestamp(car.getValidityDate().getTime()));
        }
        if (null != car.getDareOfIssue()) {
            carPO.setDareOfIssue(new Timestamp(car.getDareOfIssue().getTime()));
        }
        Date date = new Date();
        carPO.setUploadTime(new Timestamp(date.getTime()));
        carPO.setAddress(car.getAddress());
        carPO.setNatureOfUse(car.getNatureOfUse());
        carPO.setAnnualInspectionStatus("当前年检");
        return carPO;
    }

    public static VehicleDrivingTimeInformationPO convert(VehicleDrivingTimeInformation vehicleDrivingTimeInformation) {
        VehicleDrivingTimeInformationPO vehicleDrivingTimeInformationPO = new VehicleDrivingTimeInformationPO();
        vehicleDrivingTimeInformationPO.setId(vehicleDrivingTimeInformation.getId());
        vehicleDrivingTimeInformationPO.setCarNumber(vehicleDrivingTimeInformation.getCarNumber());
        vehicleDrivingTimeInformationPO.setDriverId(vehicleDrivingTimeInformation.getPatenteId());
        vehicleDrivingTimeInformationPO.setCarId(vehicleDrivingTimeInformation.getCarId());
        vehicleDrivingTimeInformationPO.setStartTime(new Timestamp(vehicleDrivingTimeInformation.getStartTime().getTime()));
        if (null != vehicleDrivingTimeInformation.getStopTime()) {
            vehicleDrivingTimeInformationPO.setStopTime(new Timestamp(vehicleDrivingTimeInformation.getStopTime().getTime()));
        } else {
            vehicleDrivingTimeInformationPO.setStopTime(null);
        }
        return vehicleDrivingTimeInformationPO;
    }

    public static InsurancePO convert(Insurance insurance) {
        InsurancePO insurancePO = new InsurancePO();
        insurancePO.setId(insurance.getId());
        insurancePO.setCarNumber(insurance.getCarNumber());
        insurancePO.setOperatorId(insurance.getOperatorId());
        insurancePO.setStartDate(new Timestamp(insurance.getStartDate().getTime()));
        insurancePO.setStopDate(new Timestamp(insurance.getStopDate().getTime()));
        insurancePO.setInsuranceFee(insurance.getInsuranceFee());
        if (null != insurance.getCashBackAmount()) {
            insurancePO.setCashBackAmount(insurance.getCashBackAmount());
        }
        return insurancePO;
    }

    public static Insurance convert(InsurancePO insurancePO) {
        if (null == insurancePO) {
            return null;
        }
        Insurance insurance = new Insurance();
        insurance.setId(insurancePO.getId());
        insurance.setCarNumber(insurancePO.getCarNumber());
        insurance.setOperatorId(insurancePO.getOperatorId());
        insurance.setStartDate(insurancePO.getStartDate());
        insurance.setStopDate(insurancePO.getStopDate());
        insurance.setInsuranceFee(insurancePO.getInsuranceFee());
        if (null != insurance.getCashBackAmount()) {
            insurancePO.setCashBackAmount(insurancePO.getCashBackAmount());
        }
        return insurance;
    }

    public static InsuranceVO convertToInsuranceVO(InsurancePO insurancePO) {
        if (null == insurancePO) {
            return null;
        }
        Date startDate = insurancePO.getStartDate();
        Date stopDate = insurancePO.getStopDate();
        InsuranceVO insuranceVO = new InsuranceVO();
        insuranceVO.setId(insurancePO.getId());
        insuranceVO.setCarNumber(insurancePO.getCarNumber());
        insuranceVO.setOperatorId(insurancePO.getOperatorId());
        insuranceVO.setInsuranceFee(insurancePO.getInsuranceFee());
        if (null != startDate) {
            insuranceVO.setStartDate(startDate.getTime());
        }
        if (null != stopDate) {
            insuranceVO.setStopDate(stopDate.getTime());
        }
        if (null != insurancePO.getCashBackAmount()) {
            insuranceVO.setCashBackAmount(insurancePO.getCashBackAmount());
        }
        return insuranceVO;
    }

    public static ViolationUnity convert(ViolationUnityPo violationUnityPo) {
        ViolationUnity violationUnity = new ViolationUnity();
        violationUnity.setViolationInformation(violationUnityPo.getViolationInformation());
        violationUnity.setId(violationUnityPo.getId());
        violationUnity.setCarId(violationUnityPo.getCarId());
        violationUnity.setCarNumber(violationUnityPo.getCarNumber());
        violationUnity.setCity(violationUnityPo.getCity());
        violationUnity.setWarehouse(violationUnityPo.getWarehouse());
        violationUnity.setDeductMark(violationUnityPo.getDeductMark());
        violationUnity.setOffender(violationUnityPo.getOffender());
        violationUnity.setOffenderId(violationUnityPo.getOffenderId());
        violationUnity.setPenalty(violationUnityPo.getPenalty());
        violationUnity.setProcessingUnit(violationUnityPo.getProcessingUnit());
        violationUnity.setStatus(violationUnityPo.getStatus());
        violationUnity.setViolationTime(violationUnityPo.getViolationTime().getTime());
        violationUnity.setViolationPlace(violationUnityPo.getViolationPlace());
        violationUnity.setPatenteNumber(violationUnityPo.getPatenteNumber());

        return violationUnity;
    }

    public static Car convert(CarDTO carDTO) {
        Car car = new Car();
        car.setId(UuidUtils.getUuid());
        car.setCity(carDTO.getCity());
        car.setWarehouse(carDTO.getWarehouse());
        car.setUploadTime(new Date());
        car.setWarehouse(carDTO.getWarehouse());
        car.setEngineNumber(carDTO.getEngineNo());
        car.setFrameNumber(carDTO.getVin());
        car.setAnnualInspectionStatus("当前年检");
        car.setOwners(carDTO.getCarOwner());
        car.setBrandModelNumber(carDTO.getBrandModelNumber());
        car.setDareOfIssue(carDTO.getDareOfIssue());
        car.setAddress(carDTO.getAddress());
        car.setNatureOfUse(carDTO.getNatureOfUse());
        if (null != carDTO.getLicensePlate()) {
            car.setCarNumber(carDTO.getLicensePlate());
        }
        if (0 == carDTO.getCarClass()) {
            car.setVehicleType("自有包邮");
        }
        if (1 == carDTO.getCarClass()) {
            car.setVehicleType("自有不包邮");
        }
        if (2 == carDTO.getCarClass()) {
            car.setVehicleType("外包");
        }
        if (1 == carDTO.getState()) {
            car.setStatus(true);
        } else {
            car.setStatus(false);
        }

        if (null != carDTO.getValidityDate()) {
            car.setRegistrationTime(carDTO.getRegistrationTime());
            car.setValidityDate(getLastDay(carDTO.getValidityDate()));
        }
        return car;
    }

    public static CarAllInfo convert(CarAllInfoPO carAllInfoPO) {
        CarAllInfo carAllInfo = new CarAllInfo();
        Date stopDate = carAllInfoPO.getStopDate();
        Date validityDate = carAllInfoPO.getValidityDate();
        Date registrationTime = carAllInfoPO.getRegistrationTime();
        Date dareOfIssue = carAllInfoPO.getDareOfIssue();
        Date uploadTime = carAllInfoPO.getUploadTime();

        carAllInfo.setId(carAllInfoPO.getId());
        carAllInfo.setCity(carAllInfoPO.getCity());
        carAllInfo.setBrandModelNumber(carAllInfoPO.getBrandModelNumber());
        carAllInfo.setOwners(carAllInfoPO.getOwners());
        carAllInfo.setWarehouse(carAllInfoPO.getWarehouse());
        carAllInfo.setStatus(carAllInfoPO.getStatus());
        carAllInfo.setCarNumber(carAllInfoPO.getCarNumber());
        carAllInfo.setVehicleType(carAllInfoPO.getVehicleType());
        carAllInfo.setEngineNumber(carAllInfoPO.getEngineNumber());
        carAllInfo.setFrameNumber(carAllInfoPO.getFrameNumber());
        carAllInfo.setVehiclePrice(carAllInfoPO.getVehiclePrice());
        carAllInfo.setAddress(carAllInfoPO.getAddress());
        carAllInfo.setNatureOfUse(carAllInfoPO.getNatureOfUse());
        carAllInfo.setCurrentMileage(carAllInfoPO.getCurrentMileage());
        carAllInfo.setViceCardNumber(carAllInfoPO.getViceCardNumber());
        carAllInfo.setViolationNumber(carAllInfoPO.getViolationNumber());
        carAllInfo.setAnnualInspectionStatus(carAllInfoPO.getAnnualInspectionStatus());

        if (null != registrationTime) {
            carAllInfo.setRegistrationTime(registrationTime.getTime());
        }
        if (null != dareOfIssue) {
            carAllInfo.setDareOfIssue(dareOfIssue.getTime());
        }
        if (null != uploadTime) {
            carAllInfo.setUploadTime(uploadTime.getTime());
        }

        Date date = new Date();
        if (null != validityDate) {
            // 获取当前时间的年和月
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
            Calendar cal = Calendar.getInstance();
            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH) + 1;
            int result = yearNow * 12 + monthNow;
            // 获取数据库数据的年和月
            String validityDate1 = format.format(validityDate);
            String[] strs = validityDate1.split("-");
            Integer validityYear = Integer.valueOf(strs[0]);
            Integer validityMonth = Integer.valueOf(strs[1]);
            Integer validityResult = validityYear * 12 + validityMonth;
            //  按月份进行对比
            Integer ending = validityResult - result;

            carAllInfo.setValidityDate(validityDate.getTime());
            long vehicleValidityDay = (validityDate.getTime() - date.getTime()) / (1000 * 60 * 60 * 24);

            if (ending >= 2) {
                carAllInfo.setVehicleValidityStatus("正常");
            }
            if (ending < 2 && ending >= 0) {
                carAllInfo.setVehicleValidityStatus("即将到期");
            }
            if (ending < 0) {
                carAllInfo.setVehicleValidityStatus("已过期");
            }
        }
        Long insuranceValidityDay = null;

        if (null != stopDate) {
            carAllInfo.setStopDate(stopDate.getTime());
            insuranceValidityDay = (stopDate.getTime() - date.getTime()) / (1000 * 60 * 60 * 24);
        }
        //判断车辆年检状态

        //判断保险状态
        if (insuranceValidityDay != null && insuranceValidityDay >= 60) {
            carAllInfo.setInsuranceStatus("正常");
        }
        if (insuranceValidityDay != null && insuranceValidityDay < 60 && insuranceValidityDay >= 0) {
            carAllInfo.setInsuranceStatus("即将到期");
        }
        if (insuranceValidityDay != null && insuranceValidityDay < 0) {
            carAllInfo.setInsuranceStatus("已过期");
        }
        if (insuranceValidityDay == null) {
            carAllInfo.setInsuranceStatus("未上传");
        }

        return carAllInfo;
    }


    public static InsuranceImagePO convert(InsuranceImage insuranceImage) {
        InsuranceImagePO insuranceImagePO = new InsuranceImagePO();
        insuranceImagePO.setId(insuranceImage.getId());
        insuranceImagePO.setCarNumber(insuranceImage.getCarNumber());
        insuranceImagePO.setInsuranceId(insuranceImage.getInsuranceId());
        insuranceImagePO.setImagePath(insuranceImage.getImagePath());

        return insuranceImagePO;
    }

    public static InsuranceImage convert(InsuranceImagePO insuranceImagePO) {
        InsuranceImage insuranceImage = new InsuranceImage();
        insuranceImage.setId(insuranceImagePO.getId());
        insuranceImage.setImagePath(insuranceImagePO.getImagePath());
        insuranceImage.setCarNumber(insuranceImagePO.getCarNumber());
        insuranceImage.setInsuranceId(insuranceImagePO.getInsuranceId());

        return insuranceImage;
    }

    public static ManegerCarAcceptDTO convert(ManegerCarAcceptPO manegerCarAcceptPO) {
        if (null == manegerCarAcceptPO) {
            return null;
        }
        ManegerCarAcceptDTO manegerCarAcceptDTO = new ManegerCarAcceptDTO();
        if (null != manegerCarAcceptPO.getCarNumber()) {
            manegerCarAcceptDTO.setCarNumber(manegerCarAcceptPO.getCarNumber());
        }
        if (null != manegerCarAcceptPO.getCurrentMileage()) {
            manegerCarAcceptDTO.setLastMileage(manegerCarAcceptPO.getCurrentMileage());
        }
        if (null != manegerCarAcceptPO.getInsuranceStopDate()) {
            manegerCarAcceptDTO.setInsuranceStopDate(manegerCarAcceptPO.getInsuranceStopDate().getTime());
        }
        if (null != manegerCarAcceptPO.getTradingMoney()) {
            manegerCarAcceptDTO.setTradingMoney(manegerCarAcceptPO.getTradingMoney());
        }
        if (null != manegerCarAcceptPO.getTradingTime()) {
            manegerCarAcceptDTO.setTradingTime(manegerCarAcceptPO.getTradingTime().getTime());
        }
        if (null != manegerCarAcceptPO.getValidityDate()) {
            manegerCarAcceptDTO.setValidityDate(manegerCarAcceptPO.getValidityDate().getTime());
        }

        return manegerCarAcceptDTO;
    }

    public static RefuelingRecordForFormsDTO convert(RefuelingRecordForForms refuelingRecordForForms) {
        if (null == refuelingRecordForForms) {
            return null;
        }
        RefuelingRecordForFormsDTO refuelingRecordForFormsDTO = new RefuelingRecordForFormsDTO();
        refuelingRecordForFormsDTO.setCardMerchant(refuelingRecordForForms.getCardMerchant());
        refuelingRecordForFormsDTO.setCurrentMileage(refuelingRecordForForms.getCurrentMileage());
        refuelingRecordForFormsDTO.setDriverId(refuelingRecordForForms.getDriverId());
        refuelingRecordForFormsDTO.setDriverName(refuelingRecordForForms.getDriverName());
        refuelingRecordForFormsDTO.setMasterCardNumber(refuelingRecordForForms.getMasterCardNumber());
        refuelingRecordForFormsDTO.setMileageImgUrl(refuelingRecordForForms.getMileageImgUrl());
        refuelingRecordForFormsDTO.setPatenteNumber(refuelingRecordForForms.getPatenteNumber());
        refuelingRecordForFormsDTO.setTradingMoney(refuelingRecordForForms.getTradingMoney());
        refuelingRecordForFormsDTO.setTradingPosition(refuelingRecordForForms.getTradingPosition());
        refuelingRecordForFormsDTO.setViceCardNumber(refuelingRecordForForms.getViceCardNumber());
        if (null != refuelingRecordForForms.getTradingTime()) {
            refuelingRecordForFormsDTO.setTradingTime(refuelingRecordForForms.getTradingTime().getTime());
        }

        return refuelingRecordForFormsDTO;
    }

    public static MaintainVO convertToMaintainVO(MaintainPO maintainPO) {
        if (null == maintainPO) {
            return null;
        }
        return new MaintainVO(maintainPO);
    }

    public static UpkeepVO convertToUpkeepVO(UpkeepPO upkeepPOS) {
        if (null == upkeepPOS) {
            return null;
        }
        return new UpkeepVO(upkeepPOS);
    }

    private static Date convert(Long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = format.format(time);
        try {
            return format.parse(d);
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间转换失败", e);
        }
    }

    private static Date convert(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String s = sdf.format(date);
            return sdf.parse(s);
        } catch (ParseException e) {
            throw new IllegalArgumentException("日期转换异常");
        }
    }

    private static Date getLastDay(Date date) {
        if (null == date) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.roll(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }
}