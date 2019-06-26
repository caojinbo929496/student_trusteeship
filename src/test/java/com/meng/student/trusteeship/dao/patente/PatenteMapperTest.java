package com.meng.student.trusteeship.dao.patente;

import com.meng.student.trusteeship.config.ViolationQuartz;
import com.meng.student.trusteeship.entity.patente.PatentePO;
import com.meng.student.trusteeship.entity.patente.PatenteVO;
import com.meng.student.trusteeship.entity.vehicle.Car;
import com.meng.student.trusteeship.service.patente.PatenteService;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fengqigui
 * @description
 * @date 2018/03/13 09:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PatenteMapperTest {

    @Autowired
    private PatenteMapper patenteMapper;


    @Autowired
    private PatenteService patenteService;

    @Test
    public void insert() throws Exception {

        for (int i = 10; i < 20; i++) {
            PatentePO patentePO = new PatentePO();
            patentePO.setAddress("武汉");
            patentePO.setBirth(new Date(new Date().getTime() - 111111111));
            patentePO.setEnddate(new Date(new Date().getTime() + 1111111));
            patentePO.setInitialdate(new Date(new Date().getTime() - 1111111));
            patentePO.setName("隔壁老王" + i);
            patentePO.setNumber("驾照编号：" + i);
            patentePO.setSex("男");
            patentePO.setStartdate(new Date(new Date().getTime() - 111111));
            patentePO.setState(1);
            patentePO.setType("C1");
            patentePO.setCity("黄冈");
            patentePO.setRepository("遗爱湖");
            patentePO.setUuid("aaaaa-" + i);
            int insert = patenteMapper.insert(patentePO);
            Assert.assertEquals(1, insert);
            System.out.println("影响的行数：----->>:" + insert);
        }

    }

    /**
     * @throws Exception
     */
    @Test
    public void insertSelective() throws Exception {
        for (int i = 20; i < 30; i++) {
            PatentePO patentePO = new PatentePO();
            patentePO.setAddress("武汉");
            patentePO.setBirth(new Date(new Date().getTime() - 111111111111L));
            patentePO.setEnddate(new Date(new Date().getTime() + 91111111111L));
            patentePO.setInitialdate(new Date(new Date().getTime() - 111111111111L));
            patentePO.setName("隔壁老王" + 32);
            patentePO.setNumber("42112319870606123" + i);
            patentePO.setSex("男");
            patentePO.setStartdate(new Date(new Date().getTime() - 61111111111L));
            patentePO.setType("C1");
            patentePO.setCity("黄冈");
            patentePO.setRepository("遗爱湖");
            patentePO.setUuid(UUID.randomUUID().toString().replace("-", ""));
            int insert = patenteMapper.insert(patentePO);
            Assert.assertEquals(1, insert);
            System.out.println("影响的行数：----->>:" + insert);
        }
    }

    @Test
    public void getByNumber() {

        PatentePO byNumber = patenteMapper.getByNumber("驾照编号：0");
        Assert.assertEquals("驾照编号：0", byNumber.getNumber());
        System.out.println("-----:" + byNumber.getNumber());
    }


    @Test
    public void getByPrimaryKey() throws Exception {

        PatentePO patentePO = patenteMapper.getByPrimaryKey("aaaaa-1");
        Assert.assertEquals("aaaaa-1", patentePO.getUuid());
        System.out.println(patentePO.getUuid());


    }

    @Test
    public void listByPrimaryKey() throws Exception {

        List<PatentePO> patentePOS = patenteMapper.listPatentes();
        Assert.assertNotNull("----不为空，长度为：" + patentePOS.size(), patentePOS);
        System.out.println("------------->" + patentePOS.size());


    }

    @Test
    public void deleteByExample() throws Exception {
        int asdasdsad = patenteMapper.deleteByPrimaryKey("aaaaa-1");
        Assert.assertEquals(1, asdasdsad);
        System.out.println("---->影响的行数：" + asdasdsad);

    }

    @Test
    public void deleteByPrimaryKey() throws Exception {

        int i = patenteMapper.deleteByPrimaryKey("aaaaa-1");
        Assert.assertEquals(1, i);
        System.out.println("删除的行数：---->" + i);

    }


    @Test
    public void updateByPrimaryKeySelective() throws Exception {
        PatentePO patentePO = new PatentePO();
        patentePO.setAddress("武汉");
        patentePO.setBirth(new Date(new Date().getTime() - 111111111));
        patentePO.setEnddate(new Date(new Date().getTime() + 1111111));
        patentePO.setInitialdate(new Date(new Date().getTime() - 1111111));
        patentePO.setName("隔壁老王" + 31);
        patentePO.setNumber("驾照编号" + 31);
        patentePO.setSex("男");
        patentePO.setStartdate(new Date(new Date().getTime() - 111111));
        patentePO.setType("C1");
        patentePO.setUuid("aaaaa-2");
        int i = patenteMapper.updateByPrimaryKeySelective(patentePO);
        Assert.assertEquals(1, i);
        System.out.println("更新---->" + i);


    }

    @Test
    public void updateByPrimaryKey() throws Exception {

        PatentePO patentePO = new PatentePO();
        patentePO.setAddress("武汉");
        patentePO.setBirth(new Date(new Date().getTime() - 111111111111L));
        patentePO.setEnddate(new Date(new Date().getTime() + 91111111111L));
        patentePO.setInitialdate(new Date(new Date().getTime() - 111111111111L));
        patentePO.setName("隔壁老王" + 32);
        patentePO.setNumber("驾照编号" + 32);
        patentePO.setSex("男");
        patentePO.setStartdate(new Date(new Date().getTime() - 111111));
        patentePO.setType("C1");
        patentePO.setUuid("aaaaa-2");
        patentePO.setCity("黄冈11");
        patentePO.setRepository("遗爱湖111");
        int i = patenteMapper.updateByPrimaryKeySelective(patentePO);
        Assert.assertEquals(1, i);
        System.out.println("更新---->" + i);


    }

    @Test
    public void likeName() {
        List<String> s = patenteMapper.listLikeByName("王");
        System.out.println("---:" + s.size());
        List<PatentePO> pos = patenteMapper.listLikeByCity("武");
        System.out.println("----" + pos);
    }

    @Test
    public void updatePatenteOfState() {
        List<PatentePO> patentePOS = patenteMapper.listPatentes();
        List<PatentePO> collect = patentePOS.stream().map(p -> {
            p.setState(2);
            return p;
        }).collect(Collectors.toList());
        patenteMapper.updateBatchPatenteById(collect);

    }

    @Test
    public void testListPagePatente() {
        Map<String, Object> map = new HashMap<>();
        map.put("currentPage", 0);
        map.put("pageSize", 10);
        map.put("name", "");
        map.put("city", "");
        map.put("repository", "");
        map.put("state", 2);
        List<PatentePO> patentePOS = patenteMapper.listPagePatente(map);
        Assert.assertEquals(10, patentePOS.size());
        System.out.println(patentePOS);

    }

    @Test
    public void testListByCityAndRepository() {

        Map<String, String> map = new HashMap<>(2);
        map.put("city", "黄冈");
        map.put("repository", "遗爱湖");
        List<PatentePO> patentePOS = patenteMapper.listByCityAndRepository(map);
        List<PatenteVO> patenteVOS = patenteService.listCityAndRepository("黄冈", "遗爱湖");

        Assert.assertEquals(patenteVOS.size(), patentePOS.size());
        System.out.println("------:" + patentePOS.size());


    }

    @Test
    public void testPatenteByRepository() {

        List<PatentePO> patentePOS = patenteMapper.listLikeByRepository(null);
        Assert.assertEquals(10, patentePOS.size());
        System.out.println("---:" + patentePOS.size());

    }

    @Autowired
    private ViolationQuartz violationQuartz;

    @Test
    public void testVoli() {

        Car car = new Car();
        violationQuartz.query(car);

    }


}