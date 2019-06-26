package com.meng.student.trusteeship.entity.vehicle.po;


/**
 * @author caojinbo
 * @since 2018.3.13
 * 车辆保险对应的图片
 */
public class InsuranceImagePO {

    private String id;
    private String insuranceId;
    private String carNumber;
    private String imagePath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
