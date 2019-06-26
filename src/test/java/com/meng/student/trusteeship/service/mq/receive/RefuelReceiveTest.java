package com.meng.student.trusteeship.service.mq.receive;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meng.student.trusteeship.dao.fuel.RefuelingRecordMapper;
import com.meng.student.trusteeship.entity.fuel.po.AndroidRefuelingRecordPO;
import com.meng.student.trusteeship.util.UuidUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author weiyangjun
 * @date 2018/03/21 11:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RefuelReceiveTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RefuelReceive.class);

    @Autowired
    private RefuelingRecordMapper refuelingRecordMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void receiveRefuelingInformationFromAndroid() {
        String message = "{\"tradingTime\":\"Apr 9, 2018 1:42:45 PM\",\"imagePath\":\"http://yjp-dev-product.ufile.ucloud.cn/Product/2018/04/30fe21e6f83a47d6901ae2e4f269ebbd.png\",\"carNumber\":\"鄂A33VX9\",\"currentMileage\":1470.0,\"patenteNumber\":\"42050419940509401X\"} ";
        try {
            //String message = new String(refuelingInformation.getBody(), "UTF-8");
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss aa", Locale.ENGLISH);
            objectMapper.setDateFormat(dateFormat);
            AndroidRefuelingRecordPO androidRefuelingRecordPO = objectMapper.readValue(message, AndroidRefuelingRecordPO.class);


            androidRefuelingRecordPO.setUuid(UuidUtils.getUuid());
            refuelingRecordMapper.saveAndroidRefuelingRecord(androidRefuelingRecordPO);
            LOGGER.info("油卡消费成功：消息{}", message);
        } catch (Exception e) {
            LOGGER.error("油卡消费异常：消息{}, 异常：{}", message, e);
        }
    }
}