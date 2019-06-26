package com.meng.student.trusteeship.entity.administrator.po;

import com.meng.student.trusteeship.entity.administrator.Administrator;

import java.io.Serializable;

/**
 * @Author: 吴宸煊
 * @Description: 管理员实体（用于数据库）
 * @Date: Created in 13:51 2018/3/13
 */
public class AdministratorPO implements Serializable {
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

    public AdministratorPO() {
    }

    public AdministratorPO(Administrator administrator) {
        this.setName(administrator.getName());
        this.setUuid(administrator.getUuid());
        this.setPassword(administrator.getPassword());
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
