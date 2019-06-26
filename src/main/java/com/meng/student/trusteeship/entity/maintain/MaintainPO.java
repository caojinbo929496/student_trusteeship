package com.meng.student.trusteeship.entity.maintain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author fengqigui
 * @date 2018/3/12 14:00:00
 * 维修实体类
 */
public class MaintainPO {

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
     * 送维修人员的驾照编号
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCompannyName() {
        return compannyName;
    }

    public void setCompannyName(String compannyName) {
        this.compannyName = compannyName == null ? null : compannyName.trim();
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site == null ? null : site.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
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
        this.patenteId = patenteId == null ? null : patenteId.trim();
    }

    public String getPatenteName() {
        return patenteName;
    }

    public void setPatenteName(String patenteName) {
        this.patenteName = patenteName == null ? null : patenteName.trim();
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId == null ? null : carId.trim();
    }

    public Date getBerichten() {
        return berichten;
    }

    public void setBerichten(Date berichten) {
        this.berichten = berichten;
    }

    public String getPatenteNumber() {
        return patenteNumber;
    }

    public void setPatenteNumber(String patenteNumber) {
        this.patenteNumber = patenteNumber;
    }

    @Override
    public String toString() {
        return "MaintainPO{" +
                "uuid='" + uuid + '\'' +
                ", carId='" + carId + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", date=" + date +
                ", berichten=" + berichten +
                ", compannyName='" + compannyName + '\'' +
                ", site='" + site + '\'' +
                ", info='" + info + '\'' +
                ", free=" + free +
                ", patenteId='" + patenteId + '\'' +
                ", patenteNumber='" + patenteNumber + '\'' +
                ", patenteName='" + patenteName + '\'' +
                ", warehouse='" + warehouse + '\'' +
                ", city='" + city + '\'' +
                ", imageMaintains=" + imageMaintains +
                ", imageMaintainFaults=" + imageMaintainFaults +
                ", imageMaintainFrees=" + imageMaintainFrees +
                '}';
    }
}