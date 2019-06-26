package com.meng.student.trusteeship.entity.patente;

import java.util.Date;
import java.util.UUID;

/**
 * @author fengqigui
 * 驾照实体类
 */
public class PatentePO {

    /**
     * uuid
     */
    private String uuid;

    /**
     * 驾照编号
     */
    private String number;

    /**
     * 驾使人名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 户籍住址
     */
    private String address;

    /**
     * 出生年月日
     */
    private Date birth;

    /**
     * 初次领驾照时间
     */
    private Date initialdate;

    /**
     * 准驾车型
     */
    private String type;

    /**
     * 有效其实日期
     */
    private Date startdate;

    /**
     * 有效截止日期
     */
    private Date enddate;


    /**
     * 驾照状态根据驾照期限判定：0:作废,1:过期,2:使用中
     */
    private Integer state;

    /**
     * 城市
     */
    private String city;

    /**
     * 仓库名
     */
    private String repository;
    /**
     * 国籍
     */
    private String nationality;

    public PatentePO() {
    }

    public PatentePO(PatenteVO patenteVO) {

        this.setUuid(UUID.randomUUID().toString().replace("-", ""));
        this.setStartdate(new Date(patenteVO.getStartdate()));
        this.setType(patenteVO.getType());
        this.setSex(patenteVO.getSex());
        this.setNumber(patenteVO.getNumber());
        this.setName(patenteVO.getName());
        this.setInitialdate(patenteVO.getInitialdate());
        this.setEnddate(new Date(patenteVO.getEnddate()));
        this.setBirth(patenteVO.getBirth());
        this.setAddress(patenteVO.getAddress());
        this.setState(patenteVO.getState());
        this.setCity(patenteVO.getCity());
        this.setRepository(patenteVO.getRepository());
        this.setNationality(patenteVO.getNationality());

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getInitialdate() {
        return initialdate;
    }

    public void setInitialdate(Date initialdate) {
        this.initialdate = initialdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository == null ? null : repository.trim();
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality == null ? null : nationality.trim();
    }

    @Override
    public String toString() {
        return "PatentePO{" +
                "uuid='" + uuid + '\'' +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", birth=" + birth +
                ", initialdate=" + initialdate +
                ", type='" + type + '\'' +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", state=" + state +
                ", city='" + city + '\'' +
                ", repository='" + repository + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}