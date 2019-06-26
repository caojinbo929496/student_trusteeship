package com.meng.student.trusteeship.entity.upkeep;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author fengqigui
 * @date 2018/3/12 13:20:00
 * 保养实体
 */
public class UpkeepPO {

    /**
     * 主键ID
     */
    private String uuid;

    /**
     * 送保养人的 ID
     */
    private String patenteId;

    /**
     * 送保养人的姓名
     */
    private String patenteName;

    /**
     * 送保人的驾照编号
     */
    private String patenteNumber;


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
     * 所属仓库
     */
    private String warehouse;

    /**
     * 所属城市
     */
    private String city;

    /**
     * 保养的清单图片
     */
    private List<ImageUpkeepPO> imageUpkeeps;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 保养的费用图片
     */
    private List<ImageUpkeepFreePO> imageUpkeepFrees;

    public List<ImageUpkeepPO> getImageUpkeeps() {
        return imageUpkeeps;
    }

    public void setImageUpkeeps(List<ImageUpkeepPO> imageUpkeeps) {
        this.imageUpkeeps = imageUpkeeps;
    }

    public List<ImageUpkeepFreePO> getImageUpkeepFrees() {
        return imageUpkeepFrees;
    }

    public void setImageUpkeepFrees(List<ImageUpkeepFreePO> imageUpkeepFrees) {
        this.imageUpkeepFrees = imageUpkeepFrees;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
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
        this.companyName = companyName == null ? null : companyName.trim();
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
        this.carId = carId == null ? null : carId.trim();
    }

    public String getPatenteNumber() {
        return patenteNumber;
    }

    public void setPatenteNumber(String patenteNumber) {
        this.patenteNumber = patenteNumber;
    }

    @Override
    public String toString() {
        return "UpkeepPO{" +
                "uuid='" + uuid + '\'' +
                ", patenteId='" + patenteId + '\'' +
                ", patenteName='" + patenteName + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", date=" + date +
                ", mileage=" + mileage +
                ", free=" + free +
                ", companyName='" + companyName + '\'' +
                ", site='" + site + '\'' +
                ", info='" + info + '\'' +
                ", berichten=" + berichten +
                ", carId='" + carId + '\'' +
                ", warehouse='" + warehouse + '\'' +
                ", city='" + city + '\'' +
                ", imageUpkeeps=" + imageUpkeeps +
                ", imageUpkeepFrees=" + imageUpkeepFrees +
                '}';
    }
}