package com.meng.student.trusteeship.service.student;

import com.meng.student.trusteeship.entity.student.pojo.Student;
import com.meng.student.trusteeship.entity.student.pojo.StudentAllInfo;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface StudentService {
    ResponseEntity<byte[]> responseVehicleTemplate();

    void addBaseStudentInfo(Student student);

    Map<String, Object> listAllStudent(Map<String, Object> queryMap);

    void updateStudentAllInfo(StudentAllInfo studentAllInfo);

    Map getStudentAllInfo(String studentId);

    List<Object> uploadSeveralStudentInformation(InputStream inputStream, HttpSession session);
}
