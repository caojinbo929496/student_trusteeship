package com.meng.student.trusteeship.dao.upkeep;

import com.meng.student.trusteeship.entity.QueryCondition;
import com.meng.student.trusteeship.entity.upkeep.ImageUpkeepFreePO;
import com.meng.student.trusteeship.entity.upkeep.ImageUpkeepPO;
import com.meng.student.trusteeship.entity.upkeep.UpkeepPO;
import com.meng.student.trusteeship.entity.upkeep.UpkeepVO;
import com.meng.student.trusteeship.entity.upkeep.query.UpkeepQueryCondition;
import com.meng.student.trusteeship.service.upkeep.UpkeepService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;

/**
 * @author fengqigui
 * @description
 * @date 2018/03/16 10:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UpkeepMapperTest {

    @Autowired
    private UpkeepMapper upkeepMapper;

    @Autowired
    private UpkeepService upkeepService;

    @Test
    public void deleteByPrimaryKey() throws Exception {
    }

    @Test
    public void insert() throws Exception {

        UpkeepPO upkeepPO = new UpkeepPO();
        upkeepPO.setBerichten(new Date());
        upkeepPO.setCarId("1");
        upkeepPO.setCarNumber("鄂A88889");
        upkeepPO.setCompanyName("车轮滚滚");
        upkeepPO.setDate(new Date());
        BigDecimal bigDecimal = new BigDecimal("996.6");
        upkeepPO.setFree(bigDecimal);
        upkeepPO.setInfo("换发动机，换驾驶室");
        upkeepPO.setMileage(1562);
        upkeepPO.setPatenteId("aaaaa-0");
        upkeepPO.setPatenteName("钻石王老五");
        upkeepPO.setSite("武汉关山大道119号");
        upkeepPO.setUuid("9");
        int insert = upkeepMapper.insert(upkeepPO);
        assertEquals("---->OK", 1, 1);
        System.out.println("插入的结果----》：" + insert);

    }

    @Test
    public void getByPrimaryKey() throws Exception {
        UpkeepPO byPrimaryKey = upkeepMapper.getByPrimaryKey("2");
        Assert.notNull(byPrimaryKey, "应该不为空");
        System.out.println("------->" + byPrimaryKey.getUuid());
    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {
    }

    @Test
    public void updateByPrimaryKey() throws Exception {
    }

    @Test
    public void listPage() throws Exception {

        Map<String, Integer> map = new HashMap<>();
        map.put("startRows", 0);
        map.put("rowsNum", 10);
        List<UpkeepPO> upkeepPOS = upkeepMapper.listPage(map);
        Assert.notNull(upkeepPOS, "错误");
        System.out.println("---数量:" + upkeepPOS.size() + "-----:" + upkeepPOS);

    }

    @Test
    public void getCount() {
        int count = upkeepMapper.getCount();
        assertEquals("---数据库有两条记录", 2, count);
        System.out.println("总的记录数----->" + count);


    }

    @Test
    public void getCounts() {

        QueryCondition queryCondition = new QueryCondition(10, 0);
        int count = upkeepMapper.getCounts(queryCondition);
        assertEquals("---数据库有两条记录", 13, count);
        System.out.println("总的记录数----->" + count);


    }

    @Test
    public void queryKeyWords() {


        QueryCondition queryCondition = new QueryCondition(10, 0);
        queryCondition.setCityName("武");
        queryCondition.setCarNumber("鄂");
        queryCondition.setRepository("武");
        queryCondition.setPatenteName("老");
        int count = upkeepMapper.getCounts(queryCondition);
        List<UpkeepPO> upkeepPOS = upkeepMapper.queryKeyWords(queryCondition);
        assertEquals("---数据库有两条记录", 2, count);
        System.out.println("总的记录数----->" + count);


    }


    @Test
    public void testInsertBatchImage() {

        List<ImageUpkeepPO> list = new ArrayList<>();
        ImageUpkeepPO image;
        for (int i = 1; i < 4; i++) {
            image = new ImageUpkeepPO();
            image.setUuid("a-" + i);
            image.setUpkeepId(i + "");
            image.setPath("path-" + i);
            list.add(image);
        }

        upkeepMapper.insertUpkeepImageBatch(list);
        System.out.println("--------");
    }

    @Test
    public void testInsertFreeImageBatch() {

        List<ImageUpkeepFreePO> list = new ArrayList<>();
        ImageUpkeepFreePO image;
        for (int i = 1; i < 4; i++) {
            image = new ImageUpkeepFreePO();
            image.setUuid("a-" + i);
            image.setUpkeepId(i + "");
            image.setPath("path-" + i);
            list.add(image);
        }

        upkeepMapper.insertUpkeepImageFreeBatch(list);
        System.out.println("--------");
    }

    @Test
    public void testUpkeepByCarNumber() {

        List<UpkeepPO> upkeep = upkeepMapper.getByCarNumber("鄂A0021");
        assertEquals("相等", "鄂A0021", upkeep.get(0).getCarNumber());
        System.out.println(upkeep);

    }

    @Test
    public void testlistUpkeepByCarNumberAndPatenteName() {

        UpkeepQueryCondition queryCondition = new UpkeepQueryCondition();
        queryCondition.setCarNumber("苏12345");
        queryCondition.setPatenteNumber("驾照编号:0");
        List<UpkeepPO> upkeepPOS = upkeepMapper.listUpkeepByCarNumberAndPatenteNumber(queryCondition);
        List<UpkeepVO> list = upkeepService.listUpkeepByCarNumberAndPatenteNumber(queryCondition);
        assertEquals("---->", upkeepPOS.size(), list.size());
        System.out.println(upkeepPOS.size() + "--");


    }


}