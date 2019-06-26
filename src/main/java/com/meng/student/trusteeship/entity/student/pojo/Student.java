package com.meng.student.trusteeship.entity.student.pojo;

import java.util.Date;


/**
 * 学生基本信息
 */
public class Student {

    private String id;

    private String studentName;

    private String studentPhone;

    private String schoolClass;

    private Integer sex;

    private String homeAddress;

    private String parentName1;

    private Integer parant1Phone;

    private String parentName2;

    private Integer parant2Phone;

    private Date registrationTime;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(String schoolClass) {
        this.schoolClass = schoolClass;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getParentName1() {
        return parentName1;
    }

    public void setParentName1(String parentName1) {
        this.parentName1 = parentName1;
    }

    public Integer getParant1Phone() {
        return parant1Phone;
    }

    public void setParant1Phone(Integer parant1Phone) {
        this.parant1Phone = parant1Phone;
    }

    public String getParentName2() {
        return parentName2;
    }

    public void setParentName2(String parentName2) {
        this.parentName2 = parentName2;
    }

    public Integer getParant2Phone() {
        return parant2Phone;
    }

    public void setParant2Phone(Integer parant2Phone) {
        this.parant2Phone = parant2Phone;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
