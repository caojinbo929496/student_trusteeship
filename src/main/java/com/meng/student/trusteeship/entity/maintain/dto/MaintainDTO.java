package com.meng.student.trusteeship.entity.maintain.dto;

import com.meng.student.trusteeship.entity.maintain.ImageMaintainFaultPO;
import com.meng.student.trusteeship.entity.maintain.ImageMaintainFreePO;
import com.meng.student.trusteeship.entity.maintain.ImageMaintainPO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MaintainDTO {
    /**
     * 维修主键ID
     */
    private String uuid;

    /**
     * 车辆的主键 ID
     */
    private String carId;

    /**
     * 维修的车牌号
     */
    private String carNumber;

    /**
     * 维修的时间
     */
    private Date date;


    /**
     * 维修的报修时间
     */
    private Date berichten;

    /**
     * 维修的 4S 店名
     */
    private String compannyName;

    /**
     * 4S店的具体位置
     */
    private String site;

    /**
     * 维修的备注
     */
    private String info;

    /**
     * 维修的费用
     */
    private BigDecimal free;

    /**
     * 驾驶证的 ID
     */
    private String patenteId;


    /**
     * 维修人员的驾照编号
     */
    private String patenteNumber;

    /**
     * 送维修的人的名字
     */
    private String patenteName;

    /**
     * 所属仓库
     */
    private String warehouse;

    /**
     * 所属城市
     */
    private String city;

    /**
     * 维修清单的图片
     */
    private List<ImageMaintainPO> imageMaintains;

    /**
     * 维修故障的图片
     */
    private List<ImageMaintainFaultPO> imageMaintainFaults;

    /**
     * 维修费用的图片
     */
    private List<ImageMaintainFreePO> imageMaintainFrees;

    List<String> troubleImgUrl;
    List<String> repairingImgUrl;
    List<String> repairingFeeImgUrl;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
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

    public Date getBerichten() {
        return berichten;
    }

    public void setBerichten(Date berichten) {
        this.berichten = berichten;
    }

    public String getCompannyName() {
        return compannyName;
    }

    public void setCompannyName(String compannyName) {
        this.compannyName = compannyName;
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

    public BigDecimal getFree() {
        return free;
    }

    public void setFree(BigDecimal free) {
        this.free = free;
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

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<ImageMaintainPO> getImageMaintains() {
        return imageMaintains;
    }

    public void setImageMaintains(List<ImageMaintainPO> imageMaintains) {
        this.imageMaintains = imageMaintains;
    }

    public List<ImageMaintainFaultPO> getImageMaintainFaults() {
        return imageMaintainFaults;
    }

    public void setImageMaintainFaults(List<ImageMaintainFaultPO> imageMaintainFaults) {
        this.imageMaintainFaults = imageMaintainFaults;
    }

    public List<ImageMaintainFreePO> getImageMaintainFrees() {
        return imageMaintainFrees;
    }

    public void setImageMaintainFrees(List<ImageMaintainFreePO> imageMaintainFrees) {
        this.imageMaintainFrees = imageMaintainFrees;
    }

    public List<String> getTroubleImgUrl() {
        return troubleImgUrl;
    }

    public void setTroubleImgUrl(List<String> troubleImgUrl) {
        this.troubleImgUrl = troubleImgUrl;
    }

    public List<String> getRepairingImgUrl() {
        return repairingImgUrl;
    }

    public void setRepairingImgUrl(List<String> repairingImgUrl) {
        this.repairingImgUrl = repairingImgUrl;
    }

    public List<String> getRepairingFeeImgUrl() {
        return repairingFeeImgUrl;
    }

    public void setRepairingFeeImgUrl(List<String> repairingFeeImgUrl) {
        this.repairingFeeImgUrl = repairingFeeImgUrl;
    }


    public String getPatenteNumber() {
        return patenteNumber;
    }

    public void setPatenteNumber(String patenteNumber) {
        this.patenteNumber = patenteNumber;
    }
}
