package com.meng.student.trusteeship.entity.maintain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author fengqigui
 * @description maintain 的 VO
 * @date 2018/03/19 14:07
 */
public class MaintainVO implements Serializable {


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
    private Long date;
    /**
     * 维修的报修时间
     */
    private Long berichten;

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
     * 送维修的人的名字
     */
    private String patenteName;

    /**
     * 送维修人员的驾照编号
     */
    private String patenteNumber;

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

    public MaintainVO() {
    }

    public MaintainVO(MaintainPO maintainPO) {

        this.setCarId(maintainPO.getCarId());
        this.setCarNumber(maintainPO.getCarNumber());
        this.setCompannyName(maintainPO.getCompannyName());
        this.setDate(maintainPO.getDate().getTime());
        this.setFree(maintainPO.getFree());
        this.setImageMaintainFaults(maintainPO.getImageMaintainFaults());
        this.setImageMaintainFrees(maintainPO.getImageMaintainFrees());
        this.setImageMaintains(maintainPO.getImageMaintains());
        this.setInfo(maintainPO.getInfo());
        this.setPatenteId(maintainPO.getPatenteId());
        this.setPatenteName(maintainPO.getPatenteName());
        this.setSite(maintainPO.getSite());
        this.setUuid(maintainPO.getUuid());
        this.setCity(maintainPO.getCity());
        this.setWarehouse(maintainPO.getWarehouse());
        if (maintainPO.getBerichten() != null) {
            this.setBerichten(maintainPO.getBerichten().getTime());
        }
        this.setPatenteNumber(maintainPO.getPatenteNumber());

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

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
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


    public Long getBerichten() {
        return berichten;
    }

    public void setBerichten(Long berichten) {
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
        return "MaintainVO{" +
                "uuid='" + uuid + '\'' +
                ", carId='" + carId + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", date=" + date +
                ", compannyName='" + compannyName + '\'' +
                ", site='" + site + '\'' +
                ", info='" + info + '\'' +
                ", free=" + free +
                ", patenteId='" + patenteId + '\'' +
                ", patenteName='" + patenteName + '\'' +
                ", warehouse='" + warehouse + '\'' +
                ", city='" + city + '\'' +
                ", imageMaintains=" + imageMaintains +
                ", imageMaintainFaults=" + imageMaintainFaults +
                ", imageMaintainFrees=" + imageMaintainFrees +
                '}';
    }
}
