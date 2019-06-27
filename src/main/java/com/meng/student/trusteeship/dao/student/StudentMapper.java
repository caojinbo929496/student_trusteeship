package com.meng.student.trusteeship.dao.student;

import com.meng.student.trusteeship.entity.student.pojo.RegistrationIntention;
import com.meng.student.trusteeship.entity.student.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 学生dao
 */

@Mapper
public interface StudentMapper {


    Map<String, Object> listAllStudentByCondition(Map<String, Object> queryMap);

    void insertStudent(Student student);

    void insertRegistrationIntention(RegistrationIntention registrationIntention);
}
