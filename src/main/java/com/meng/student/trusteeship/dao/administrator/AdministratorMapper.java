package com.meng.student.trusteeship.dao.administrator;

import com.meng.student.trusteeship.entity.administrator.po.AdministratorPO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;


/**
 * @Author: 吴宸煊
 * @Description: 管理员dao层
 * @Date: Created in 13:39 2018/3/13
 */
@Mapper
public interface AdministratorMapper {


    /**
     * 管理员登陆
     *
     * @param name
     * @param password
     * @return AdministratorPO
     */
    AdministratorPO getAdministrator(@Param(value = "name") String name, @Param(value = "password") String password);

    /**
     * 通过id查找管理员信息
     *
     * @param id uuid唯一主键
     * @return AdministratorPO
     */
    AdministratorPO getAdministratorById(String id);

    /**
     * 添加管理员信息
     *
     * @param administratorPO 管理员对象
     */
    void addAdministrator(AdministratorPO administratorPO);
}
