package com.meng.student.trusteeship.dao.administrator;

import com.meng.student.trusteeship.entity.administrator.po.AdministratorPO;
import com.meng.student.trusteeship.util.MD5Utils;
import com.meng.student.trusteeship.util.UuidUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class AdministratorMapperTest {

    @Autowired
    private AdministratorMapper administratorMapper;

    @Test
    public void getAdministratorById() throws Exception {
        AdministratorPO administratorPO = administratorMapper.getAdministratorById("4eb502ef234b11e899ec0242ac110002");
        System.out.println(administratorPO.toString());
    }

    @Test
    public void addAdministrator() {
        String name = "admin";
        String password = MD5Utils.getMD5("yijiupi");
        AdministratorPO administratorPO = new AdministratorPO();
        administratorPO.setUuid(UuidUtils.getUuid());
        administratorPO.setName(name);
        administratorPO.setPassword(password);

        AdministratorPO administrator = administratorMapper.getAdministrator(name, password);
        if (null == administrator) {
            administratorMapper.addAdministrator(administratorPO);
        } else {
            throw new IllegalArgumentException("数据库中存在该用户名和密码");
        }
    }
}