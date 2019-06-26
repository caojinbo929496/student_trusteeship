package com.meng.student.trusteeship.dao.index;

import com.meng.student.trusteeship.entity.index.po.NationalPatentePO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: 吴宸煊
 * @Description: 全国驾照 单元测试dao层
 * @Date: Created in 17:43 2018/3/27
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class NationalPatenteMapperTest {

    @Autowired
    private NationalPatenteMapper nationalPatenteMapper;

    @Test
    public void getNowExpiredPatente() {
        NationalPatentePO nationalPatentePO = nationalPatenteMapper.getNowExpiredPatente();
        Assert.assertEquals(new Integer("9"), nationalPatentePO.getNumberPatente());
    }

    @Test
    public void getThirtyDaysExpiredPatente() {
        NationalPatentePO nationalPatentePO = nationalPatenteMapper.getThirtyDaysExpiredPatente();
        Assert.assertEquals(new Integer("1"), nationalPatentePO.getNumberPatente());
    }

    @Test
    public void getSixtyDaysExpiredPatente() {
        NationalPatentePO nationalPatentePO = nationalPatenteMapper.getSixtyDaysExpiredPatente();
        Assert.assertEquals(new Integer("2"), nationalPatentePO.getNumberPatente());
    }

}