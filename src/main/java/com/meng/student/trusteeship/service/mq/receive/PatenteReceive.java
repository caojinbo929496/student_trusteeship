package com.meng.student.trusteeship.service.mq.receive;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meng.student.trusteeship.dao.patente.PatenteMapper;
import com.meng.student.trusteeship.entity.patente.PatentePO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

/**
 * @author fengqigui
 * @description RabbitMq 的监听 patente队列面的消息，
 * @date 2018/03/12 16:03
 */
@Component
public class PatenteReceive {

    public static final Logger LOGGER = LoggerFactory.getLogger(PatenteReceive.class);

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    PatenteMapper patenteMapper;


    /**
     * 监听 patente-1队列的消息
     *
     * @param content
     */
    @RabbitListener(queues = {"${mq.trading.carManagement.patente}"})
    public void receiveFanout(Message content) {
        LOGGER.info("驾驶证队列收到消息:{}", content);
        try {
            String message = new String(content.getBody(), "UTF-8");
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss aa", Locale.ENGLISH);
            objectMapper.setDateFormat(dateFormat);
            PatentePO patentePO = objectMapper.readValue(message, PatentePO.class);
            checkData(patentePO);
            PatentePO patente = patenteMapper.getByNumber(patentePO.getNumber());
            if (null != patente) {
                patentePO.setUuid(patente.getUuid());
                patentePO.setState(2);
                patenteMapper.updateByPrimaryKeySelective(patentePO);
            } else {
                patentePO.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
                patentePO.setRepository("");
                patenteMapper.insert(patentePO);
            }
            LOGGER.info("驾驶证的消费者消费消息成功，信息：{}", content);
        } catch (Exception e) {
            LOGGER.error("驾驶证的消费者消费消息失败，信息：{};异常：{}",content, e);
        }

    }


    /**
     * 检验前端传进来的数据
     * @param patentePO
     */
    private void checkData(PatentePO patentePO) {

        Assert.hasLength(patentePO.getName(), "驾驶员的姓名不为空");
        Assert.hasLength(patentePO.getNumber(), "驾驶证编号不为空");
        Assert.hasLength(patentePO.getType(), "驾照类型不为空");
        Assert.notNull(patentePO.getEnddate(), "有效过期时间不为空");

    }


}
