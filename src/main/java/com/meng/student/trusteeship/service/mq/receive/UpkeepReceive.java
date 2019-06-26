package com.meng.student.trusteeship.service.mq.receive;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meng.student.trusteeship.dao.car.CarMapper;
import com.meng.student.trusteeship.dao.patente.PatenteMapper;
import com.meng.student.trusteeship.dao.upkeep.UpkeepMapper;
import com.meng.student.trusteeship.entity.patente.PatentePO;
import com.meng.student.trusteeship.entity.upkeep.ImageUpkeepFreePO;
import com.meng.student.trusteeship.entity.upkeep.ImageUpkeepPO;
import com.meng.student.trusteeship.entity.upkeep.UpkeepPO;
import com.meng.student.trusteeship.entity.upkeep.dto.UpkeepReceiveDTO;
import com.meng.student.trusteeship.entity.vehicle.po.CarPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author fengqigui
 * @description 车辆保养的消费者，从供应链进行异步消息同步
 * @date 2018/03/19 16:47
 */

@Component
public class UpkeepReceive {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpkeepReceive.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UpkeepMapper upkeepMapper;

    @Autowired
    private PatenteMapper patenteMapper;

    @Autowired
    private CarMapper carMapper;

    @RabbitListener(queues = "${mq.trading.carManagement.upkeep}")
    public void receiveFanout(Message content) {

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss aa", Locale.ENGLISH);
            objectMapper.setDateFormat(dateFormat);
            String message = new String(content.getBody(), "UTF-8");
            UpkeepReceiveDTO upkeepReceiveDTO = objectMapper.readValue(message, UpkeepReceiveDTO.class);
            List<String> upkeepFreeImgUrls = upkeepReceiveDTO.getUpkeepFreeImgUrls();
            List<String> upkeepImgUrls = upkeepReceiveDTO.getUpkeepImgUrls();
            PatentePO patentePO = patenteMapper.getByNumber(upkeepReceiveDTO.getPatenteNumber());
            CarPO carPO = carMapper.getCarByCarNumber(upkeepReceiveDTO.getCarNumber());
            upkeepReceiveDTO.setCarId(carPO.getId());
            upkeepReceiveDTO.setPatenteId(patentePO.getUuid());
            upkeepReceiveDTO.setPatenteName(patentePO.getName());
            checkData(upkeepReceiveDTO);
            UpkeepPO upkeepPO = convert(upkeepReceiveDTO);
            upkeepMapper.insert(upkeepPO);

            // 车辆清单的图片
            if (null != upkeepImgUrls) {
                saveUpkeepImgUrls(upkeepImgUrls, upkeepPO);
            }
            // 车辆保养费用图片
            if (null != upkeepFreeImgUrls) {
                saveUpkeepFreeImgUrls(upkeepFreeImgUrls, upkeepPO);
            }
            LOGGER.info("汽车保养的消费者消费消息成功，信息：{}", content);
        } catch (Exception e) {
            LOGGER.error("汽车保养的消费者消费消息失败，信息：{};异常{}", content, e);
        }


    }

    /**
     * 保存车辆清单的图片
     *
     * @param upkeepImgUrls 车辆保养的清单图片路径
     * @param upkeepPO      保养的实体类
     */
    private void saveUpkeepImgUrls(List<String> upkeepImgUrls, UpkeepPO upkeepPO) throws Exception {

        List<ImageUpkeepPO> collect = upkeepImgUrls.stream().map(p -> {
            ImageUpkeepPO imageUpkeepPO = new ImageUpkeepPO();
            imageUpkeepPO.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
            imageUpkeepPO.setUpkeepId(upkeepPO.getUuid());
            imageUpkeepPO.setPath(p);
            return imageUpkeepPO;
        }).collect(Collectors.toList());
        upkeepMapper.insertUpkeepImageBatch(collect);

    }

    /**
     * 车辆保养费用图片
     *
     * @param upkeepFreeImgUrls
     * @param upkeepPO
     */
    public void saveUpkeepFreeImgUrls(List<String> upkeepFreeImgUrls, UpkeepPO upkeepPO) throws Exception {

        List<ImageUpkeepFreePO> imageUpkeepFreePOs = upkeepFreeImgUrls.stream().map(p -> {
            ImageUpkeepFreePO imageUpkeepFreePO = new ImageUpkeepFreePO();
            imageUpkeepFreePO.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
            imageUpkeepFreePO.setUpkeepId(upkeepPO.getUuid());
            imageUpkeepFreePO.setPath(p);
            return imageUpkeepFreePO;
        }).collect(Collectors.toList());
        upkeepMapper.insertUpkeepImageFreeBatch(imageUpkeepFreePOs);

    }


    private UpkeepPO convert(UpkeepReceiveDTO upkeepReceiveDTO) {
        Assert.notNull(upkeepReceiveDTO, "保养对象不能为空");
        UpkeepPO upkeepPO = new UpkeepPO();
        upkeepPO.setUuid(upkeepReceiveDTO.getUuid());
        upkeepPO.setBerichten(upkeepReceiveDTO.getBerichten());
        upkeepPO.setCarId(upkeepReceiveDTO.getCarId());
        upkeepPO.setCarNumber(upkeepReceiveDTO.getCarNumber());
        upkeepPO.setCompanyName(upkeepReceiveDTO.getCompanyName());
        upkeepPO.setDate(upkeepReceiveDTO.getDate());
        upkeepPO.setFree(upkeepReceiveDTO.getFree());
        upkeepPO.setInfo(upkeepReceiveDTO.getInfo());
        upkeepPO.setMileage(upkeepReceiveDTO.getMileage());
        upkeepPO.setPatenteId(upkeepReceiveDTO.getPatenteId());
        upkeepPO.setPatenteName(upkeepReceiveDTO.getPatenteName());
        upkeepPO.setPatenteNumber(upkeepReceiveDTO.getPatenteNumber());
        upkeepPO.setSite(upkeepReceiveDTO.getSite());
        upkeepPO.setCity(upkeepReceiveDTO.getCity());
        upkeepPO.setWarehouse(upkeepReceiveDTO.getCarId());
        return upkeepPO;
    }


    /**
     * 检查字段是否为空
     *
     * @param upkeepReceiveDTO
     */
    private void checkData(UpkeepReceiveDTO upkeepReceiveDTO) {

        if (upkeepReceiveDTO.getUuid() == null) {
            upkeepReceiveDTO.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
        }
        Assert.hasLength(upkeepReceiveDTO.getCarNumber(), "汽车的牌照不为空");
        Assert.hasLength(upkeepReceiveDTO.getPatenteName(), "驾驶员的姓名不为空");
        Assert.notNull(upkeepReceiveDTO.getDate(), "汽车保养的时间不为空");
        Assert.notNull(upkeepReceiveDTO.getMileage(), "汽车保养的里程数不为空");
        Assert.hasLength(upkeepReceiveDTO.getSite(), "驾驶保养的单位位置不为空");
        Assert.notNull(upkeepReceiveDTO.getCompanyName(), "汽车保养的单位不为空");
        Assert.notNull(upkeepReceiveDTO.getFree(), "汽车保养的费用不为空");

    }

    public void testReceive(UpkeepReceiveDTO upkeepReceiveDTO) {
        try {
            List<String> upkeepFreeImgUrls = upkeepReceiveDTO.getUpkeepFreeImgUrls();
            List<String> upkeepImgUrls = upkeepReceiveDTO.getUpkeepImgUrls();
            PatentePO patentePO = patenteMapper.getByNumber(upkeepReceiveDTO.getPatenteNumber());
            CarPO carPO = carMapper.getCarByCarNumber(upkeepReceiveDTO.getCarNumber());
            upkeepReceiveDTO.setCarId(carPO.getId());
            upkeepReceiveDTO.setPatenteId(patentePO.getUuid());
            upkeepReceiveDTO.setPatenteName(patentePO.getName());
            checkData(upkeepReceiveDTO);
            UpkeepPO upkeepPO = convert(upkeepReceiveDTO);
            upkeepMapper.insert(upkeepPO);

            // 车辆清单的图片
            if (null != upkeepImgUrls) {
                saveUpkeepImgUrls(upkeepImgUrls, upkeepPO);
            }

            // 车辆保养费用图片
            if (null != upkeepFreeImgUrls) {
                saveUpkeepFreeImgUrls(upkeepFreeImgUrls, upkeepPO);
            }

            LOGGER.info("汽车保养的消费者消费消息成功，信息：{}", 1);
        } catch (Exception e) {
            LOGGER.error("汽车保养的消费者消费消息失败，信息：{};异常{}", 1, e);
        }


    }


}
