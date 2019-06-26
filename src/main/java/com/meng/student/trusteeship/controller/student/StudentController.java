package com.meng.student.trusteeship.controller.student;


import com.google.gson.Gson;
import com.meng.student.trusteeship.entity.result.BaseResult;
import com.meng.student.trusteeship.entity.student.pojo.Student;
import com.meng.student.trusteeship.entity.student.pojo.StudentAllInfo;
import com.meng.student.trusteeship.entity.student.vo.StudentQueryVo;
import com.meng.student.trusteeship.service.student.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学生管理controller
 *
 * @author caojinbo
 * @since 2019.06.25
 */
@RestController
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

   @Autowired
   private StudentService studentService;

    @Autowired
    private Gson gson;

    @RequestMapping(value = "student/managerList", method = RequestMethod.POST)
    public Map<String, Object> studentManagerList(StudentQueryVo studentQueryVo) {
        Assert.notNull(studentQueryVo.getPage(), "当前页不能为空");
        Assert.notNull(studentQueryVo.getLimit(), "当前页面大小不能为空");
        Map<String, Object> queryMap = getStudentManagerQueryMap(studentQueryVo);
        return studentService.listAllStudent(queryMap);
    }
    

    @RequestMapping(value = "student/add", method = RequestMethod.POST)
    public BaseResult addVehicleInfo(@RequestBody Student student) {

        checkStudentInfo(student);
        
        studentService.addBaseStudentInfo(student);
        return BaseResult.getResult();
    }

    /**
     * 新增时检查学生信息的有效性
     * @param student
     */
    private void checkStudentInfo(Student student) {
        Assert.hasLength(student.getStudentName(), "车牌号不能为空");
        Assert.notNull(student.getSex(), "车牌号不能为空");
        Assert.notNull(student.getParentName1(), "保险费用不能为空");
        Assert.notNull(student.getParant1Phone(), "保险注册日期不能为空");
    }


    /**
     * 更新学生下面所有的信息
     *
     * @param StudentAllInfo 学生所有信息
     * @return 车辆信息 List<ManegerCarAcceptDTO>
     */
    @RequestMapping(value = "student/update", method = RequestMethod.POST)
    public BaseResult managerCarQuery(@RequestBody StudentAllInfo StudentAllInfo) {

        studentService.updateStudentAllInfo(StudentAllInfo);
        return BaseResult.getResult();

    }


    /**
     * 通过学生id查询学生下面所有的记录信息
     * @param studentId  学生id
     * @return 学生所有的数据
     */
    @RequestMapping(value = "student/studentAllInfo/{studentId}")
    public BaseResult getStudentAllInfo(@PathVariable String studentId) {
        Map map = studentService.getStudentAllInfo(studentId);
        return BaseResult.getResult(map);
    }

    /**
     * 返回一个所有学生的基本信息的excel文件
     *
     * @return 所有学生的基本信息
     */
    @RequestMapping(value = "student/responseStudentTemplate")
    public ResponseEntity<byte[]> responseStudentTemplate() {
        return studentService.responseVehicleTemplate();
    }

    /**
     * 以excel批量导入学生的基本信息
     *
     * @param studentExcel 上传的excel文件
     * @return
     */
    @RequestMapping(value = "student/uploadSeveralStudentInformation", method = RequestMethod.POST)
    public Map uploadSeveralStudentInformation(MultipartFile studentExcel, HttpSession session) {

        Map<String, Object> resultMap = new HashMap<>(2);
        List<Object> uploadSeveralResultList = null;
        try {
            uploadSeveralResultList = studentService.uploadSeveralStudentInformation(studentExcel.getInputStream(), session);
            Integer ifUploadSeveralSuccess = (Integer) uploadSeveralResultList.get(0);
            if (ifUploadSeveralSuccess == 0) {
                resultMap.put("result", "success");
            }
            if (ifUploadSeveralSuccess != 0) {
                resultMap.put("result", "false");
            }
            uploadSeveralResultList.remove(0);
        } catch (Exception e) {
            resultMap.put("result", "false");
        }

        resultMap.put("carPONotAddList", uploadSeveralResultList);
        return resultMap;
    }



    private Map<String, Object> getStudentManagerQueryMap(StudentQueryVo studentQueryVo) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("currentPage", (studentQueryVo.getPage() - 1) * studentQueryVo.getLimit());
        map.put("pageSize", studentQueryVo.getLimit());
        map.put("studentName", studentQueryVo.getStudentName());
        map.put("parentName", studentQueryVo.getParentName());
        map.put("intention", studentQueryVo.getIntention());

        map.put("startDate", studentQueryVo.getRegistrationStartTime());
        map.put("endDate", studentQueryVo.getRegistrationEndTime());

        return map;
    }

    private Date convert(Long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = format.format(time);
        try {
            return format.parse(d);
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间转换失败", e);
        }
    }

}
