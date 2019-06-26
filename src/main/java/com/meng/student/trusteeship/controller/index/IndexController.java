package com.meng.student.trusteeship.controller.index;

import com.meng.student.trusteeship.service.index.NationalInsuranceService;
import com.meng.student.trusteeship.service.index.NationalPatenteService;
import com.meng.student.trusteeship.service.index.NationalVehicleInspectionService;
import com.meng.student.trusteeship.service.index.NationalVehicleViolationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @Author: 吴宸煊
 * @Description: 首页controller层
 * @Date: Created in 11:36 2018/3/23
 */
@Controller
public class IndexController {
    @Autowired
    private NationalVehicleViolationService nationalVehicleViolationService;

    @Autowired
    private NationalPatenteService nationalPatenteService;

    @Autowired
    private NationalInsuranceService nationalInsuranceService;

    @Autowired
    private NationalVehicleInspectionService nationalVehicleInspectionService;

    @RequestMapping("/index")
    public String index(Model model) {

        model.addAttribute("title", "首页");

        /**
         * 当前未处理违章数
         */
        model.addAttribute("national", nationalVehicleViolationService.getNationViolation());

        /**
         *  本月违章数
         */
        model.addAttribute("thisMonthViolation", nationalVehicleViolationService.getThisMonthViolation());

        /**
         * 近30天违章数
         */
        model.addAttribute("thirtyDaysViolation", nationalVehicleViolationService.getThirtyDays());

        /**
         * 近60天违章数
         */
        model.addAttribute("sixtyViolation", nationalVehicleViolationService.getSixtyDays());

        /**
         *  当前已过期驾照
         */
        model.addAttribute("nowExpiredPatente", nationalPatenteService.getNowExpiredPatente());

        /**
         * 近30天内过期驾照
         */
        model.addAttribute("thirtyExpiredPatente", nationalPatenteService.getThirtyDaysExpiredPatente());

        /**
         * 近60天内过期驾照
         */
        model.addAttribute("sixtyDaysExpiredPatente", nationalPatenteService.getSixtyDaysExpiredPatente());

        /**
         * 当前已过期车辆保险
         */
        model.addAttribute("nowExpiredInsurance", nationalInsuranceService.getNowExpiredInsurance());

        /**
         * 近30天内到期车辆保险
         */
        model.addAttribute("thirtyDaysExpiredInsurance", nationalInsuranceService.getThirtyDaysExpiredInsurance());

        /**
         * 近60天内到期车辆保险
         */
        model.addAttribute("sixthDaysExpiredInsurance", nationalInsuranceService.getSixthDaysExpiredInsurance());

        /**
         *  当前已过期车辆年检
         */
        model.addAttribute("nowExpiredInspection", nationalVehicleInspectionService.getNowExpiredInspection());

        /**
         * 近30天内到期车辆年检
         */
        model.addAttribute("thirtyDaysExpiredInspection", nationalVehicleInspectionService.getThirtyDaysExpiredInspection());

        /**
         * 近60天内到期车辆年检
         */
        model.addAttribute("sixtyDaysExpiredInspection", nationalVehicleInspectionService.getSixtyDaysExpiredInspection());

        return "index";
    }
}
