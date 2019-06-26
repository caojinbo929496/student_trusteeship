package com.meng.student.trusteeship.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author fengqigui
 * @description
 * @date 2018/03/12 10:27
 */
@Controller
public class BaseController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexHtml(Model model) {
        model.addAttribute("title", "首页");
        return "redirect:index";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginIndex(Model model) {
        model.addAttribute("title", "后台登陆");
        return "login";
    }

    @RequestMapping(value = "/student/studentManager", method = RequestMethod.GET)
    public String studentInfo(Model model) {
        model.addAttribute("title", "新增车辆");
        return "student/studentManager";
    }


    @RequestMapping(value = "/patente", method = RequestMethod.GET)
    public String patenteHtml(Model model) {

        model.addAttribute("title", "驾照查询");
        return "patente/patente";

    }


    /**
     * 首页驾照跳转到驾照查询页面
     *
     * @param model
     * @param state
     * @return
     */
    @RequestMapping(value = "/pastPatenteQuery/{state}", method = RequestMethod.GET)
    public String patenteHtml(RedirectAttributes model, @PathVariable Integer state) {
        model.addFlashAttribute("state", state);
        return "redirect:/patente";
    }

    /**
     * 退出账号
     *
     * @param session
     * @return String
     */
    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    public String loginOut(HttpSession session, Model model) {
        model.addAttribute("title", "后台登陆");
        session.removeAttribute("administrator");
        return "login";
    }


    @RequestMapping(value = "/upkeep", method = RequestMethod.GET)
    public String upkeepHtml(Model model) {

        model.addAttribute("title", "保养记录");
        return "upkeep/upkeepList";

    }

    @RequestMapping(value = "/vehicleMonitoring", method = RequestMethod.GET)
    public String view(Model model) {
        model.addAttribute("title", "车辆监控");
        return "traffic/traffic";
    }

    @RequestMapping(value = "/vehicle/newVehicle", method = RequestMethod.GET)
    public String vehicle(Model model) {
        model.addAttribute("title", "新增车辆");
        return "vehicle/newVehicleList";
    }

    @RequestMapping(value = "/upkeep/{upkeepId}", method = RequestMethod.GET)
    public String upKeepDetailHtml(Model model, @PathVariable String upkeepId) {

        model.addAttribute("title", "保养记录");
        model.addAttribute("upkeepId", upkeepId);
        return "upkeep/upkeepDetail";

    }

    @RequestMapping(value = "/maintainList", method = RequestMethod.GET)
    public String maintainHtml(Model model) {

        model.addAttribute("title", "维修记录");
        return "maintain/maintainList";

    }


    @RequestMapping(value = "/maintain/{maintainId}", method = RequestMethod.GET)
    public String maintainDetailHtml(Model model, @PathVariable String maintainId) {
        model.addAttribute("maintainId", maintainId);
        model.addAttribute("title", "维修记录");
        return "maintain/maintainDetail";

    }

    @RequestMapping(value = "/vehicle/driverLicense", method = RequestMethod.GET)
    public String driverLicense(Model model) {
        model.addAttribute("title", "行驶证管理");

        return "vehicle/driverLicenseList";
    }

    @RequestMapping(value = "/violation/violationList/{type}", method = RequestMethod.GET)
    public String violation(Model model, @PathVariable Integer type) {

        model.addAttribute("type", type);
        model.addAttribute("title", "违章记录");
        return "violation/violationList";
    }


    @RequestMapping(value = "/violation/{name}", method = RequestMethod.GET)
    public String violation1(Model model, @PathVariable String name) {
        model.addAttribute("name", name);
        model.addAttribute("title", "违章记录");
        return "violation/violationList";
    }

    @RequestMapping(value = "/violation1/{number}", method = RequestMethod.GET)
    public String violation2(Model model, @PathVariable String number) {
        model.addAttribute("number", number);
        model.addAttribute("title", "违章记录");
        return "violation/violationList";
    }

    /**
     * 进入加油卡交易记录页面
     */
    @RequestMapping(value = "fuelCardManager/refuelRecord")
    public String refuelRecord(Model model) {
        model.addAttribute("title", "加油记录");
        return "fuelCardManager/refuelRecord";
    }

    /**
     * 进入加油卡管理页面
     *
     * @return
     */
    @RequestMapping(value = "fuelCardManager/fuelCardManager")
    public String fuelCardManager(Model model) {
        model.addAttribute("title", "加油卡管理");
        return "fuelCardManager/fuelCardManager";
    }


    /**
     * 油耗统计跳转到加油记录页面
     *
     * @param model
     * @param carNumber
     * @return
     */
    @RequestMapping(value = "getRefuelingRecordForWuChenXuan/{carNumber}", method = RequestMethod.GET)
    public String fuelCardManager(Model model, @PathVariable String carNumber) {

        model.addAttribute("wcxCarNumber", carNumber);
        model.addAttribute("title", "加油记录");
        return "fuelCardManager/refuelRecord";
    }

    @RequestMapping(value = "/vehicle/vehicleQuery/{type}", method = RequestMethod.GET)
    public String vehilceQuery(Model model, @PathVariable Integer type) {
        model.addAttribute("type", type);
        model.addAttribute("title", "车辆查询");
        return "vehicle/vehicleQueryList";
    }

    @RequestMapping(value = "/vehicle/vehicleDetails/{carNumber}", method = RequestMethod.GET)
    public String vehicleDetails(Model model, @PathVariable String carNumber) {
        model.addAttribute("title", "车辆基本信息");
        model.addAttribute("carNumber", carNumber);
        return "vehicle/vehicleDetails";
    }

    @RequestMapping(value = "fuelCardManager/fuelCardDetail/{masterCardNumber}/{viceCardNumber}/{cardMerchant}/{carNumber}", method = RequestMethod.GET)
    public String fuelCardDetail(Model model, @PathVariable("masterCardNumber") String masterCardNumber, @PathVariable(value = "viceCardNumber", required = false) String viceCardNumber, @PathVariable("cardMerchant") Integer cardMerchant, @PathVariable("carNumber") String carNumber) {
        model.addAttribute("title", "加油卡详情");
        model.addAttribute("masterCardNumber", masterCardNumber);
        model.addAttribute("viceCardNumber", viceCardNumber);
        model.addAttribute("cardMerchant", cardMerchant);
        model.addAttribute("carNumber", carNumber);
        return "fuelCardManager/fuelCardDetail";
    }
}
