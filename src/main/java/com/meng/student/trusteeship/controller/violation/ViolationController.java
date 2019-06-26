package com.meng.student.trusteeship.controller.violation;


import com.meng.student.trusteeship.dao.patente.PatenteMapper;
import com.meng.student.trusteeship.dao.violation.ViolationMapper;
import com.meng.student.trusteeship.entity.patente.PatentePO;
import com.meng.student.trusteeship.entity.result.BaseResult;
import com.meng.student.trusteeship.entity.vehicle.Violation;
import com.meng.student.trusteeship.entity.vehicle.ViolationUnity;
import com.meng.student.trusteeship.entity.vehicle.dto.ViolationDTO;
import com.meng.student.trusteeship.entity.vehicle.po.ViolationUnityPo;
import com.meng.student.trusteeship.entity.vehicle.vo.ViolationQueryVO;
import com.meng.student.trusteeship.service.violation.ViolationService;
import com.meng.student.trusteeship.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ViolationController {

    @Autowired
    private ViolationService violationService;

    @Autowired
    private PatenteMapper patenteMapper;

    @Autowired
    private ViolationMapper violationMapper;

    /**
     * 通过驾驶证号查询司机违章信息
     * 已过时没有使用
     *
     * @param patenteNumber 驾驶证号
     * @return 违章信息 List<Violation>
     */
    @RequestMapping(value = "/violation/query/{patenteNumber}", method = RequestMethod.GET)
    public List<ViolationDTO> getViolationByPatenteNumber(@PathVariable String patenteNumber) {
        Assert.hasLength(patenteNumber, "驾驶证号不能为空");
        PatentePO patentePO = patenteMapper.getByNumber(patenteNumber);
        if (null != patentePO) {
            List<Violation> violations = violationService.getViolationByOffenderId(patentePO.getUuid());
            return violations.stream().map(ConvertUtils::convertToViolationDTO).collect(Collectors.toList());
        }
        return null;
    }

    /**
     * 根据违章的查询条件筛选所需记录
     *
     * @param violationQueryVO 查询条件  包括城市仓库
     * @return Map map
     */
    @RequestMapping(value = "/violation/web/listViolation", method = RequestMethod.POST)
    public BaseResult getViolationVo(@RequestBody ViolationQueryVO violationQueryVO) {
        Assert.notNull(violationQueryVO, "查询条件不能为空");
        Assert.notNull(violationQueryVO.getPageSize(), "分页查询条件的页面大小不能为空");
        Assert.notNull(violationQueryVO.getCurrentPage(), "分页查询条件的当前页不能为空");
        Map queryMap = getQueryMap(violationQueryVO);
        Map map = violationService.listViolation(queryMap);
        return BaseResult.getResult(map);
    }

    /**
     * 根据违章的查询条件筛选所需记录 post
     *
     * @param violationQueryVO 查询条件  包括城市仓库 违章人  驾驶证号
     * @return List<ViolationUnity>
     */
    @RequestMapping(value = "/violation/listViolation", method = RequestMethod.POST)
    public List<ViolationUnity> getViolationVoInterface(@RequestBody ViolationQueryVO violationQueryVO) {
        Map queryMap = getQueryMap(violationQueryVO);
        queryMap.put("queryStatus", "1");

        return violationMapper.listViolation(queryMap).stream().map(ConvertUtils::convert).collect(Collectors.toList());
    }


    private Map getQueryMap(ViolationQueryVO violationQueryVO) {
        if (null == violationQueryVO) {
            return null;
        }
        HashMap<String, Object> queryMap = new HashMap<>();
        if (null != violationQueryVO.getCarNumber()) {
            queryMap.put("carNumber", violationQueryVO.getCarNumber());
        }
        if (null != violationQueryVO.getCity()) {
            queryMap.put("city", violationQueryVO.getCity());
        }
        if (null != violationQueryVO.getWarehouse()) {
            queryMap.put("warehouse", violationQueryVO.getWarehouse());
        }
        if (null != violationQueryVO.getOffender()) {
            queryMap.put("offender", violationQueryVO.getOffender());
        }
        if (null != violationQueryVO.getOffenderNumber()) {
            queryMap.put("offenderNumber", violationQueryVO.getOffenderNumber());
        }
        if (null != violationQueryVO.getStatus()) {
            queryMap.put("status", violationQueryVO.getStatus());
        }
        if (null != violationQueryVO.getCurrentPage()) {
            queryMap.put("currentPage", violationQueryVO.getCurrentPage());
        }
        if (null != violationQueryVO.getPageSize()) {
            queryMap.put("pageSize", violationQueryVO.getPageSize());
        }


        String offenderNumber = violationQueryVO.getOffenderNumber();
        if (null != offenderNumber) {
            PatentePO patentePO = patenteMapper.getByNumber(offenderNumber);
            if (null != patentePO) {
                queryMap.put("offenderId", patentePO.getUuid());
            } else {
                queryMap.put("offenderId", offenderNumber);
            }
        }


        if (null != violationQueryVO.getStartDateView()) {
            queryMap.put("startDate", new Timestamp(violationQueryVO.getStartDateView()));
        }
        if (null != violationQueryVO.getEndDateView()) {
            queryMap.put("endDate", new Timestamp(violationQueryVO.getEndDateView()));

        }

        return queryMap;
    }

    public ViolationUnity convert(ViolationUnityPo violationUnityPo) {
        ViolationUnity violationUnity = new ViolationUnity();
        violationUnity.setViolationInformation(violationUnityPo.getViolationInformation());
        violationUnity.setId(violationUnityPo.getId());
        violationUnity.setCarId(violationUnityPo.getCarId());
        violationUnity.setCarNumber(violationUnityPo.getCarNumber());
        violationUnity.setCity(violationUnityPo.getCity());
        violationUnity.setWarehouse(violationUnityPo.getWarehouse());
        violationUnity.setDeductMark(violationUnityPo.getDeductMark());
        violationUnity.setOffender(violationUnityPo.getOffender());
        violationUnity.setOffenderId(violationUnityPo.getOffenderId());
        violationUnity.setPenalty(violationUnityPo.getPenalty());
        violationUnity.setProcessingUnit(violationUnityPo.getProcessingUnit());
        violationUnity.setStatus(violationUnityPo.getStatus());
        violationUnity.setViolationTime(violationUnityPo.getViolationTime().getTime());
        violationUnity.setViolationPlace(violationUnityPo.getViolationPlace());

        PatentePO patentePO = patenteMapper.getByPrimaryKey(violationUnityPo.getOffenderId());
        String patentePONumber = patentePO.getNumber();
        if (null != patentePONumber) {
            violationUnity.setPatenteNumber(patentePONumber);
        }
        return violationUnity;
    }
}

