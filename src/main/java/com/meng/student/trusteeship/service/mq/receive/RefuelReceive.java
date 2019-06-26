package com.meng.student.trusteeship.service.mq.receive;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meng.student.trusteeship.dao.fuel.RefuelingRecordMapper;
import com.meng.student.trusteeship.entity.fuel.po.AndroidRefuelingRecordPO;
import com.meng.student.trusteeship.util.UuidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author weiyangjun
 * @date 2018/03/21 11:13
 */
@Component
public class RefuelReceive {
    private static final Logger LOGGER = LoggerFactory.getLogger(RefuelReceive.class);

    @Autowired
    private RefuelingRecordMapper refuelingRecordMapper;

    @Autowired
    private ObjectMapper objectMapper;


    /**
     * @param refuelingInformation
     * @throws ParseException
     * @throws IOException
     * @throws FileNotFoundException
     */
    @RabbitListener(queues = "${mq.trading.carManagement.refuelRecord}")
    public void receiveRefuelingInformationFromAndroid(Message refuelingInformation) {
        try {
            String message = new String(refuelingInformation.getBody(), "UTF-8");
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss aa", Locale.ENGLISH);
            objectMapper.setDateFormat(dateFormat);
            AndroidRefuelingRecordPO androidRefuelingRecordPO = objectMapper.readValue(message, AndroidRefuelingRecordPO.class);

            androidRefuelingRecordPO.setUuid(UuidUtils.getUuid());
            androidRefuelingRecordPO.setTradingTime(new Date());

            refuelingRecordMapper.saveAndroidRefuelingRecord(androidRefuelingRecordPO);
            LOGGER.info("油卡消费成功：消息{}", refuelingInformation);
        } catch (Exception e) {
            LOGGER.error("油卡消费异常：消息{}, 异常：{}", refuelingInformation, e);
        }
    }


}
