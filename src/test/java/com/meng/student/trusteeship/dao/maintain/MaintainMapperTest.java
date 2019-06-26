package com.meng.student.trusteeship.dao.maintain;

import com.meng.student.trusteeship.entity.QueryCondition;
import com.meng.student.trusteeship.entity.maintain.*;
import com.meng.student.trusteeship.entity.maintain.query.MaintainQueryCondition;
import com.meng.student.trusteeship.service.maintain.MaintainService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author fengqigui
 * @description
 * @date 2018/03/19 14:33
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MaintainMapperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MaintainMapperTest.class);

    @Autowired
    private MaintainMapper maintainMapper;

    @Autowired
    private MaintainService maintainService;

    @Test
    public void deleteByPrimaryKey() throws Exception {
    }

    @Test
    public void insert() throws Exception {
    }

    @Test
    public void insertSelective() throws Exception {
    }

    @Test
    public void selectByPrimaryKey() throws Exception {
        MaintainPO maintainPO = maintainMapper.selectByPrimaryKey("2");
        Assert.assertEquals("2", maintainPO.getUuid());
        System.out.println("---->" + maintainPO.getUuid() + "---date---:" + maintainPO.getBerichten());

    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {
    }

    @Test
    public void updateByPrimaryKey() throws Exception {
        MaintainPO maintainPO = maintainMapper.selectByPrimaryKey("1");
        Assert.assertEquals("1", maintainPO.getUuid());
        System.out.println(maintainPO.getCarNumber());
    }

    @Test
    public void listMaintain() throws Exception {
        List<MaintainPO> maintainPOS = maintainMapper.listMaintain(new QueryCondition(10, 0));
        Assert.assertEquals("----------OK", 2, maintainPOS.size());
        System.out.println(maintainPOS.size() + "---UUID:1--" + maintainPOS.get(0).getUuid());
    }

    @Test
    public void insertMaintainImage() {

        List<ImageMaintainPO> list = new ArrayList<>();
        ImageMaintainPO imageMaintainPO;
        for (int i = 1; i < 3; i++) {
            imageMaintainPO = new ImageMaintainPO();
            imageMaintainPO.setUuid("a-" + i);
            imageMaintainPO.setMaintainId(i + "");
            imageMaintainPO.setPath("a-" + i);
            list.add(imageMaintainPO);
        }
        maintainMapper.insertMaintainImageBatch(list);
        System.out.println("______-----______");

    }

    @Test
    public void insertMaintainFaultImage() {

        List<ImageMaintainFaultPO> list = new ArrayList<>();
        ImageMaintainFaultPO imageMaintainPO;
        for (int i = 1; i < 3; i++) {
            imageMaintainPO = new ImageMaintainFaultPO();
            imageMaintainPO.setUuid("a-" + i);
            imageMaintainPO.setMaintainId(i + "");
            imageMaintainPO.setPath("a-" + i);
            list.add(imageMaintainPO);
        }
        maintainMapper.insertMaintainFaultImageBatch(list);
        System.out.println("______-----______");

    }

    @Test
    public void insertMaintainFreeImage() {

        List<ImageMaintainFreePO> list = new ArrayList<>();
        ImageMaintainFreePO imageMaintainPO;
        for (int i = 1; i < 3; i++) {
            imageMaintainPO = new ImageMaintainFreePO();
            imageMaintainPO.setUuid("a-" + i);
            imageMaintainPO.setMaintainId(i + "");
            imageMaintainPO.setPath("a-" + i);
            list.add(imageMaintainPO);
        }
        maintainMapper.insertMaintainFreeImageBatch(list);
        System.out.println("______-----______");

    }


    @Test
    public void testGetBuCarNumber() {
        List<MaintainPO> maintainPO = maintainMapper.getByCarNumber("鄂A0029");
        assertEquals("----OK", "鄂A0029", maintainPO.get(0).getCarNumber());
        System.out.println("------:" + maintainPO.get(0).getCarNumber());
    }


    @Test
    public void testListMaintainByCarNumber() {

        MaintainQueryCondition maintainQueryCondition = new MaintainQueryCondition();
        maintainQueryCondition.setCarNumber("鄂A0029");
        maintainQueryCondition.setPatenteNumber("驾照编号:0");
        List<MaintainPO> list = maintainMapper.listMaintainByCarNumberAndPatenteNumber(maintainQueryCondition);
        List<MaintainVO> maintainVOS = maintainService.listMaintainByCarNumberAndPatenteNumber(maintainQueryCondition);
        assertEquals("----OK", maintainVOS.size(), list.size());
        System.out.println("------:" + list.size() + "-----");

    }


}