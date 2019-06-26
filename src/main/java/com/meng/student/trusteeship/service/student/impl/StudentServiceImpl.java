package com.meng.student.trusteeship.service.student.impl;

import com.meng.student.trusteeship.dao.student.StudentMapper;
import com.meng.student.trusteeship.entity.student.pojo.Student;
import com.meng.student.trusteeship.entity.student.pojo.StudentAllInfo;
import com.meng.student.trusteeship.service.student.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
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

    }

    @Override
    public Map<String, Object> listAllStudent(Map<String, Object> queryMap) {
        return null;
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
