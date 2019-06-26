package com.meng.student.trusteeship.entity.upkeep.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class UpkeepReceiveDTO {
    List<String> upkeepImgUrls;
    List<String> upkeepFreeImgUrls;
    /**
     * 主键ID
     */
    private String uuid;

    /**
     * 送保养人的 ID
     */
    private String patenteId;

    /**
     * 送保人的驾照编号
     */
    private String patenteNumber;

    /**
     * 送保养人的姓名
     */
    private String patenteName;

    /**
     * 被保养的汽车牌照
     */
    private String carNumber;

    /**
     * 保养的时间
     */
    private Date date;

    /**
     * 保养时的里程数
     */
    private Integer mileage;

    /**
     * 保养的费用
     */
    private BigDecimal free;

    /**
     * 保养的 4s 店的名字
     */
    private String companyName;

    /**
     * 保养的 4s 店的位置
     */
    private String site;

    /**
     * 报养的备注
     */
    private String info;

    /**
     * 保养上报时间
     */
    private Date berichten;

    /**
     * 汽车的ID
     */
    private String carId;

    /**
     * 所在城市
     */
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getUpkeepImgUrls() {
        return upkeepImgUrls;
    }

    public void setUpkeepImgUrls(List<String> upkeepImgUrls) {
        this.upkeepImgUrls = upkeepImgUrls;
    }

    public List<String> getUpkeepFreeImgUrls() {
        return upkeepFreeImgUrls;
    }

    public void setUpkeepFreeImgUrls(List<String> upkeepFreeImgUrls) {
        this.upkeepFreeImgUrls = upkeepFreeImgUrls;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPatenteId() {
        return patenteId;
    }

    public void setPatenteId(String patenteId) {
        this.patenteId = patenteId;
    }

    public String getPatenteName() {
        return patenteName;
    }

    public void setPatenteName(String patenteName) {
        this.patenteName = patenteName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getFree() {
        return free;
    }

    public void setFree(BigDecimal free) {
        this.free = free;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getBerichten() {
        return berichten;
    }

    public void setBerichten(Date berichten) {
        this.berichten = berichten;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getPatenteNumber() {
        return patenteNumber;
    }

    public void setPatenteNumber(String patenteNumber) {
        this.patenteNumber = patenteNumber;
    }
}
