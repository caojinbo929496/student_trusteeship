package com.meng.student.trusteeship.service.administrator.impl;

import com.meng.student.trusteeship.dao.administrator.AdministratorMapper;
import com.meng.student.trusteeship.entity.administrator.Administrator;
import com.meng.student.trusteeship.entity.administrator.po.AdministratorPO;
import com.meng.student.trusteeship.service.administrator.AdministratorService;
import com.meng.student.trusteeship.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author: 吴宸煊
 * @Description: 管理员service实现
 * @Date: Created in 14:24 2018/3/13
 */
@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    private AdministratorMapper administratorMapper;


    @Override
    public Administrator getAdministrator(String name, String password) {
        AdministratorPO administratorPO = administratorMapper.getAdministrator(name, MD5Utils.getMD5(password));
        Administrator administrator = convertToPatenteVO(administratorPO);
        return administrator;
    }

    @Override
    public Administrator getAdministratorById(String id) {
        AdministratorPO administratorPO = administratorMapper.getAdministratorById(id);
        return convertToPatenteVO(administratorPO);
    }


    /**
     * PO 转 VO
     *
     * @param
     * @return
     */
    private Administrator convertToPatenteVO(AdministratorPO administratorPO) {

        if (null == administratorPO) {
            return null;
        }
        Administrator administrator = new Administrator(administratorPO);
        return administrator;
    }
}
