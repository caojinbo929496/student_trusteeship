
package com.meng.student.trusteeship.service.insurance.impl;

import com.meng.student.trusteeship.dao.insurance.InsuranceMapper;
import com.meng.student.trusteeship.service.insurance.InsuranceService;
import com.meng.student.trusteeship.entity.vehicle.Insurance;
import com.meng.student.trusteeship.entity.vehicle.po.InsurancePO;
import com.meng.student.trusteeship.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
public class InsuranceServiceImpl implements InsuranceService {

    @Autowired
    private InsuranceMapper insuranceMapper;

    @Override
    public void save(Insurance insurance) {
        InsurancePO insurancePO = ConvertUtils.convert(insurance);
        insuranceMapper.save(insurancePO);
    }

    @Override
    public Insurance getByCarNumber(String carNumber) {
        Assert.hasLength(carNumber, "车牌号不能为空");
        List<InsurancePO> insurancePOS = insuranceMapper.getByCarNumber(carNumber);
        if (insurancePOS.size() == 0) {
            return null;
        }
        InsurancePO insurancePO = null;
        Optional<InsurancePO> first = insurancePOS.stream().filter(p -> p.getStopDate() != null).sorted(Comparator.comparing(InsurancePO::getStopDate).reversed()).findFirst();
        if (first.isPresent()) {
            insurancePO = first.get();
        }
        return ConvertUtils.convert(insurancePO);
    }

    @Override
    public void updateInsuranceByCarNumberOfDate(Insurance insurance) {

        InsurancePO insurancePO = insuranceMapper.getInsuranceByCarNumberOfDate(insurance);
        insurance.setId(insurancePO.getId());
        insuranceMapper.updateInsuranceByCarNumberOfDate(insurance);

    }

    @Override
    public void deleteInsuranceById(String insuranceId) {
        insuranceMapper.deleteInsuranceById(insuranceId);
    }


}
