package com.meng.student.trusteeship.service.insurance;

import com.meng.student.trusteeship.entity.vehicle.Insurance;

public interface InsuranceService {

    void save(Insurance insurance);

    Insurance getByCarNumber(String carNumber);

    void updateInsuranceByCarNumberOfDate(Insurance insurance);

    void deleteInsuranceById(String insuranceId);
}
