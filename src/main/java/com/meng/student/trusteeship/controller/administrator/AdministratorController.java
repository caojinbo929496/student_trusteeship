package com.meng.student.trusteeship.controller.administrator;

import com.meng.student.trusteeship.entity.administrator.Administrator;
import com.meng.student.trusteeship.service.administrator.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


/**
 * @Author: 吴宸煊
 * @Description: 管理员controller层
 * @Date: Created in 14:38 2018/3/13
 */
@Controller
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;


    /**
     * 管理员登陆
     *
     * @param attributes
     * @param name
     * @param password
     * @param session
     * @return String
     */
    @RequestMapping("/loginIn")
    public String login(@RequestParam(required = false, value = "name") String name, @RequestParam(required = false, value = "password") String password, HttpSession session, RedirectAttributes attributes) {
        Assert.notNull(name, "用户名不能为空");
        Assert.notNull(password, "密码不能为空");
        Administrator administrator = administratorService.getAdministrator(name, password);
        if (null == administrator) {
            attributes.addFlashAttribute("msg", "用户名或者密码错误");
            return "redirect:login";
        }
        session.setAttribute("administrator", administrator);
        return "redirect:index";
    }


}
