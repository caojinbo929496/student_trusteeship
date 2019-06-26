package com.meng.student.trusteeship.entity.administrator;

import com.meng.student.trusteeship.entity.administrator.po.AdministratorPO;


import java.io.Serializable;

/**
 * @Author: 吴宸煊
 * @Description: 管理员实体（用于业务交互）
 * @Date: Created in 16:24 2018/3/14
 */
public class Administrator implements Serializable {
    /**
     * id编号
     */
    private String uuid;
    /**
     * 姓名
     */
    private String name;
    /**
     * 密码
     */
    private String password;

    public Administrator() {

    }

    public Administrator(AdministratorPO administratorPO) {
        this.setName(administratorPO.getName());
        this.setUuid(administratorPO.getUuid());
        this.setPassword(administratorPO.getPassword());
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
