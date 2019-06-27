package com.meng.student.trusteeship.service.student.impl;

import com.meng.student.trusteeship.dao.student.StudentMapper;
import com.meng.student.trusteeship.entity.student.pojo.RegistrationIntention;
import com.meng.student.trusteeship.entity.student.pojo.Student;
import com.meng.student.trusteeship.entity.student.pojo.StudentAllInfo;
import com.meng.student.trusteeship.enums.StudentIntention;
import com.meng.student.trusteeship.service.student.StudentService;
import com.meng.student.trusteeship.util.UuidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class StudentServiceImpl implements StudentService {


    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public ResponseEntity<byte[]> responseVehicleTemplate() {
        return null;
    }

    @Override
    public void addBaseStudentInfo(Student student) {
        Assert.notNull(student, "新增学生信息为空！");

        LOGGER.info("新增学生基本信息：{}", student.toString());
        Date nowDate = new Date();
        String uuid = UuidUtils.getUuid();
        student.setId(uuid);
        student.setRegistrationTime(new Timestamp(nowDate.getTime()));
        studentMapper.insertStudent(student);
        LOGGER.info("成功新增学生基本信息");


        LOGGER.info("更新{}的意向为想来", student.getStudentName());
        RegistrationIntention registrationIntention = new RegistrationIntention();
        registrationIntention.setStudentId(uuid);
        registrationIntention.setIntention(StudentIntention.WANT.getIndex());
        studentMapper.insertRegistrationIntention(registrationIntention);
        LOGGER.info("更新意向表成功");
    }

    @Override
    public Map<String, Object> listAllStudent(Map<String, Object> queryMap) {

        LOGGER.info("学生信息管理界面查询条件：{}", queryMap.toString());
        Map<String, Object> allStudentBaseInfo = studentMapper.listAllStudentByCondition(queryMap);
        LOGGER.info("学生信息管理界面查询结果有：{}条", allStudentBaseInfo.size());

        return allStudentBaseInfo;
    }

    @Override
    public void updateStudentAllInfo(StudentAllInfo studentAllInfo) {

    }

    @Override
    public Map getStudentAllInfo(String studentId) {
        return null;
    }

    @Override
    public List<Object> uploadSeveralStudentInformation(InputStream inputStream, HttpSession session) {
        return null;
    }
}
