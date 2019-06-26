package com.meng.student.trusteeship.entity.upkeep;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author fengqigui
 * @description 保养实体
 * @date 2018/03/16 09:10
 */
public class UpkeepVO implements Serializable {


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
    private Long date;

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
    private Long berichten;

    /**
     * 汽车的ID
     */
    private String carId;

    /**
     * 保养的清单图片
     */
    private List<ImageUpkeepPO> imageUpkeeps;

    /**
     * 保养的费用图片
     */
    private List<ImageUpkeepFreePO> imageUpkeepFrees;
    /**
     * 所属仓库
     */
    private String warehouse;

    /**
     * 所属城市
     */
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public UpkeepVO(UpkeepPO upkeepPO) {

        this.setBerichten(upkeepPO.getBerichten().getTime());
        this.setCarId(upkeepPO.getCarId());
        this.setCarNumber(upkeepPO.getCarNumber());
        this.setCompanyName(upkeepPO.getCompanyName());
        this.setDate(upkeepPO.getDate().getTime());
        this.setFree(upkeepPO.getFree());
        this.setImageUpkeepFrees(upkeepPO.getImageUpkeepFrees());
        this.setImageUpkeeps(upkeepPO.getImageUpkeeps());
        this.setInfo(upkeepPO.getInfo());
        this.setMileage(upkeepPO.getMileage());
        this.setPatenteId(upkeepPO.getPatenteId());
        this.setPatenteName(upkeepPO.getPatenteName());
        this.setSite(upkeepPO.getSite());
        this.setUuid(upkeepPO.getUuid());
        this.setWarehouse(upkeepPO.getWarehouse());
        this.setCity(upkeepPO.getCity());
        this.setPatenteNumber(upkeepPO.getPatenteNumber());

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


    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

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

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
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
        return "UpkeepVO{" +
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
                ", imageUpkeeps=" + imageUpkeeps +
                ", imageUpkeepFrees=" + imageUpkeepFrees +
                ", warehouse='" + warehouse + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
