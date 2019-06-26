package com.meng.student.trusteeship.service.administrator;

import com.meng.student.trusteeship.entity.administrator.Administrator;


/**
 * @Author: 吴宸煊
 * @Description: 管理员service接口
 * @Date: Created in 14:18 2018/3/13
 */
public interface AdministratorService {

    /**
     * 管理员登陆
     *
     * @param name
     * @param password
     * @return boolean
     */
    Administrator getAdministrator(String name, String password);

    Administrator getAdministratorById(String id);

}
