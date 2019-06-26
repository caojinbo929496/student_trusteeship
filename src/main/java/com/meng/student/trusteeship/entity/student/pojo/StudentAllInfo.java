package com.meng.student.trusteeship.entity.student.pojo;

import java.util.List;


/**
 * 学生详情信息
 */
public class StudentAllInfo {

    private Student studentPO;

    private List<Payment> payments;

    private List<RegistrationIntention> registrationIntentionPOS;

    public Student getStudentPO() {
        return studentPO;
    }

    public void setStudentPO(Student studentPO) {
        this.studentPO = studentPO;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<RegistrationIntention> getRegistrationIntentionPOS() {
        return registrationIntentionPOS;
    }

    public void setRegistrationIntentionPOS(List<RegistrationIntention> registrationIntentionPOS) {
        this.registrationIntentionPOS = registrationIntentionPOS;
    }
}
