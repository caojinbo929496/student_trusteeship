package com.meng.student.trusteeship.service.mq.receive;

import com.google.gson.Gson;
import com.meng.student.trusteeship.entity.administrator.Administrator;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class TestReceiver {

    @Autowired
    private Gson gson;

    @RabbitListener(queues = "hello.queue1")
    public String processMessage1(String message) {
        System.out.println(Thread.currentThread().getName() + "接受到的消息来自hello.queue1,消息为：" + message);
        return message.toUpperCase();
    }

    @RabbitListener(queues = "hello.queue2")
    public String processMessage2(String message) {
        System.out.println(Thread.currentThread().getName() + "接受到的消息来自hello.queue2,消息为：" + message);
        return message.toUpperCase();
    }

    @RabbitListener(queues = "administrator.001")
    public void processMessage3(Message message) {
        try {
            String s = new String(message.getBody(), "UTF-8");
            Administrator administrator = gson.fromJson(s, Administrator.class);
            System.out.println(administrator.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "接受到的消息来自hello.queue2,消息为：" + message);
    }

}
