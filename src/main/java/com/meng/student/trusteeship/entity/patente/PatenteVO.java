package com.meng.student.trusteeship.entity.patente;

import java.util.Date;

/**
 * @author fengqigui
 * @description 驾照实体类
 * @date 2018/03/13 11:26
 */
public class PatenteVO {

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
     * 有效起始日期
     */
    private Long startdate;

    /**
     * 有效截止日期
     */
    private Long enddate;


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

    public PatenteVO() {
    }

    public PatenteVO(PatentePO patentePO) {
        this.setUuid(patentePO.getUuid());

        this.setType(patentePO.getType());
        this.setSex(patentePO.getSex());
        this.setNumber(patentePO.getNumber());
        this.setName(patentePO.getName());
        this.setInitialdate(patentePO.getInitialdate());
        this.setBirth(patentePO.getBirth());
        this.setAddress(patentePO.getAddress());
        this.setState(patentePO.getState());
        this.setCity(patentePO.getCity());
        this.setRepository(patentePO.getRepository());
        this.setRepository(patentePO.getRepository());
        if (patentePO.getStartdate() != null) {
            this.setStartdate(patentePO.getStartdate().getTime());
        }
        if (patentePO.getEnddate() != null) {
            this.setEnddate(patentePO.getEnddate().getTime());
        }

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        this.type = type;
    }

    public Long getStartdate() {
        return startdate;
    }

    public void setStartdate(Long startdate) {
        this.startdate = startdate;
    }

    public Long getEnddate() {
        return enddate;
    }

    public void setEnddate(Long enddate) {
        this.enddate = enddate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "PatenteVO{" +
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
