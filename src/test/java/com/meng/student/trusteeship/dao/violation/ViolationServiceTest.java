package com.meng.student.trusteeship.dao.violation;

import com.meng.student.trusteeship.entity.vehicle.Violation;
import com.meng.student.trusteeship.entity.vehicle.ViolationUnity;
import com.meng.student.trusteeship.service.violation.ViolationService;
import com.meng.student.trusteeship.util.ConvertUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ViolationServiceTest {

    @Autowired
    private ViolationService violationService;

    @Autowired
    private ViolationMapper violationMapper;

    @Test
    public void getViolationPOByCarNumber() throws Exception {
        List<Violation> violationPOByCarNumber = violationService.getViolationByCarNumber("123");
        System.out.println(violationPOByCarNumber);
    }

    @Test
    public void getViolationVoInterface() throws Exception {
        HashMap<Object, Object> queryMap = new HashMap<>();
        queryMap.put("offender", "王老五");
        queryMap.put("queryStatus", "1");

        List<ViolationUnity> collect = violationMapper.listViolation(queryMap).stream().map(ConvertUtils::convert).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void getViolationPOById() throws Exception {
        Violation violation = violationService.getViolationById("123");
    }

    @Test
    public void save() throws Exception {
        Violation violation = new Violation();
        violation.setCarId("123456789");
        violation.setCarNumber("123");
        violation.setDeductMark(2);
        violation.setId("123456");
        violation.setPenalty(BigDecimal.valueOf(200.11));
        violation.setProcessingUnit("yijiupi");
        violation.setStatus(false);
        violation.setViolationTime(new java.util.Date());
        violation.setViolationInformation("11");
        violation.setViolationPlace("22");
        violationService.save(violation);
    }

}