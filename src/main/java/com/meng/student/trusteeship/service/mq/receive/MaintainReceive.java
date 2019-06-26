package com.meng.student.trusteeship.service.mq.receive;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meng.student.trusteeship.dao.car.CarMapper;
import com.meng.student.trusteeship.dao.maintain.MaintainMapper;
import com.meng.student.trusteeship.dao.patente.PatenteMapper;
import com.meng.student.trusteeship.entity.maintain.ImageMaintainFaultPO;
import com.meng.student.trusteeship.entity.maintain.ImageMaintainFreePO;
import com.meng.student.trusteeship.entity.maintain.ImageMaintainPO;
import com.meng.student.trusteeship.entity.maintain.MaintainPO;
import com.meng.student.trusteeship.entity.maintain.dto.MaintainDTO;
import com.meng.student.trusteeship.entity.patente.PatentePO;
import com.meng.student.trusteeship.entity.vehicle.po.CarPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fengqigui
 * @description 维修信息的mq
 * @date 2018/03/20 18:16
 */
@Component
public class MaintainReceive {

    public static final Logger LOGGER = LoggerFactory.getLogger(MaintainReceive.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MaintainMapper maintainMapper;

    @Autowired
    private PatenteMapper patenteMapper;

    @Autowired
    private CarMapper carMapper;

    /**
     * @param content mq消息字段，额外的有维修清单，维修故障，维修费用
     */
    @RabbitListener(queues = "${mq.trading.carManagement.maintain}")
    public void maintainReceive(Message content) {

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss aa", Locale.ENGLISH);
            objectMapper.setDateFormat(dateFormat);
            String message = new String(content.getBody(), "UTF-8");
            Map<String, Object> map = objectMapper.readValue(message, new TypeReference<Map<String, Object>>() {
            });

            MaintainDTO maintainDTO = objectMapper.readValue(objectMapper.writeValueAsBytes(map), MaintainDTO.class);
            List<String> troubleImgUrl = maintainDTO.getTroubleImgUrl();
            List<String> repairingFeeImgUrl = maintainDTO.getRepairingFeeImgUrl();
            List<String> repairingImgUrl = maintainDTO.getRepairingImgUrl();

            // 检查报修时间是否为空，不为空则
            if (null == maintainDTO.getBerichten()) {
                maintainDTO.setBerichten(new Date());
            }
            checkData(maintainDTO);
            PatentePO patentePO = patenteMapper.getByNumber(maintainDTO.getPatenteNumber());
            CarPO carByCarNumber = carMapper.getCarByCarNumber(maintainDTO.getCarNumber());
            maintainDTO.setPatenteId(patentePO.getUuid());
            maintainDTO.setCarId(carByCarNumber.getId());
            maintainDTO.setPatenteName(patentePO.getName());
            checkData(maintainDTO);
            MaintainPO maintainPO = convert(maintainDTO);
            maintainMapper.insertSelective(maintainPO);

            if (null != troubleImgUrl) {
                saveImageFaultMaintains(troubleImgUrl, maintainPO);
            }

            // 清单图片
            if (null != repairingImgUrl) {
                saveRepairingImgUrl(repairingImgUrl, maintainPO);
            }

            // 费用图片
            if (null != repairingFeeImgUrl) {
                saveRepairingFeeImgUrl(repairingFeeImgUrl, maintainPO);
            }

            LOGGER.info("汽车维修的消费者消费消息成功，信息：{}", content);
        } catch (Exception e) {
            LOGGER.error("汽车维修的消费者消费消息失败，信息：{};异常{}", content, e);
        }


    }

    /**
     * 故障图片保存
     *
     * @param troubleImgUrl
     * @param maintainPO
     */
    private void saveImageFaultMaintains(List<String> troubleImgUrl, MaintainPO maintainPO) throws Exception {

        List<ImageMaintainFaultPO> imageFaultMaintains = troubleImgUrl.stream().map(p -> {
            ImageMaintainFaultPO image = new ImageMaintainFaultPO();
            image.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
            image.setMaintainId(maintainPO.getUuid());
            image.setPath(p);
            return image;
        }).collect(Collectors.toList());
        maintainMapper.insertMaintainFaultImageBatch(imageFaultMaintains);

    }

    /**
     * 清单图片
     *
     * @param repairingImgUrl
     * @param maintainPO
     */
    private void saveRepairingImgUrl(List<String> repairingImgUrl, MaintainPO maintainPO) throws Exception {

        List<ImageMaintainPO> imageMaintainPOS = repairingImgUrl.stream().map(p -> {
            ImageMaintainPO imageMaintainPO = new ImageMaintainPO();
            imageMaintainPO.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
            imageMaintainPO.setMaintainId(maintainPO.getUuid());
            imageMaintainPO.setPath(p);
            return imageMaintainPO;
        }).collect(Collectors.toList());
        maintainMapper.insertMaintainImageBatch(imageMaintainPOS);
    }


    /**
     * 费用图片
     *
     * @param repairingFeeImgUrl
     * @param maintainPO
     */
    private void saveRepairingFeeImgUrl(List<String> repairingFeeImgUrl, MaintainPO maintainPO) throws Exception {
        List<ImageMaintainFreePO> imageMaintainFreePOs = repairingFeeImgUrl.stream().map(p -> {
            ImageMaintainFreePO imageMaintainFreePO = new ImageMaintainFreePO();
            imageMaintainFreePO.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
            imageMaintainFreePO.setMaintainId(maintainPO.getUuid());
            imageMaintainFreePO.setPath(p);
            return imageMaintainFreePO;
        }).collect(Collectors.toList());
        maintainMapper.insertMaintainFreeImageBatch(imageMaintainFreePOs);
    }

    public void testReceive(MaintainDTO maintainDTO) {
        try {
            /*MaintainDTO maintainDTO = objectMapper.readValue(objectMapper.writeValueAsBytes(map), MaintainDTO.class);*/
            List<String> troubleImgUrl = maintainDTO.getTroubleImgUrl();
            List<String> repairingFeeImgUrl = maintainDTO.getRepairingFeeImgUrl();
            List<String> repairingImgUrl = maintainDTO.getRepairingImgUrl();
            // 检查报修时间是否为空，不为空则
            if (null == maintainDTO.getBerichten()) {
                maintainDTO.setBerichten(new Date());
            }

            PatentePO patentePO = patenteMapper.getByNumber(maintainDTO.getPatenteNumber());
            CarPO carByCarNumber = carMapper.getCarByCarNumber(maintainDTO.getCarNumber());
            maintainDTO.setPatenteId(patentePO.getUuid());
            maintainDTO.setCarId(carByCarNumber.getId());
            checkData(maintainDTO);
            MaintainPO maintainPO = convert(maintainDTO);
            maintainMapper.insertSelective(maintainPO);
            if (null != troubleImgUrl) {
                saveImageFaultMaintains(troubleImgUrl, maintainPO);
            }
            // 清单图片
            if (null != repairingImgUrl) {
                saveRepairingImgUrl(repairingImgUrl, maintainPO);
            }
            // 费用图片
            if (null != repairingFeeImgUrl) {
                saveRepairingFeeImgUrl(repairingFeeImgUrl, maintainPO);
            }

            LOGGER.info("汽车维修的消费者消费消息成功，信息：{}", 1);
        } catch (Exception e) {
            LOGGER.error("汽车维修的消费者消费消息失败，信息：{};异常{}", 1, e);
        }
    }

    private void checkData(MaintainDTO maintainDTO) {

        maintainDTO.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
        Assert.hasLength(maintainDTO.getCarNumber(), "maintain 的mq消费失败：car的Number不为空");
        Assert.hasLength(maintainDTO.getSite(), "maintain 的mq消费失败：维修地点不为空");
        Assert.notNull(maintainDTO.getFree(), "maintain 的mq消费失败：维修费用不为空");
        Assert.notNull(maintainDTO.getDate(), "maintain 的mq消费失败：维修时间不为空");
        Assert.hasLength(maintainDTO.getPatenteNumber(), "maintain 的mq消费失败：维修人的驾照编号不为空");
        Assert.hasLength(maintainDTO.getPatenteName(), "maintain 的mq消费失败：维修人的名字不为空");

    }

    private MaintainPO convert(MaintainDTO maintainDTO) {
        MaintainPO maintainPO = new MaintainPO();
        maintainPO.setUuid(maintainDTO.getUuid());
        maintainPO.setPatenteNumber(maintainDTO.getPatenteNumber());
        maintainPO.setCarId(maintainDTO.getCarId());
        maintainPO.setPatenteId(maintainDTO.getPatenteId());
        maintainPO.setBerichten(maintainDTO.getBerichten());
        maintainPO.setCarNumber(maintainDTO.getCarNumber());
        maintainPO.setCity(maintainDTO.getCity());
        maintainPO.setCompannyName(maintainDTO.getCompannyName());
        maintainPO.setDate(maintainDTO.getDate());
        maintainPO.setFree(maintainDTO.getFree());
        maintainPO.setImageMaintainFaults(maintainDTO.getImageMaintainFaults());
        maintainPO.setImageMaintainFrees(maintainDTO.getImageMaintainFrees());
        maintainPO.setImageMaintains(maintainDTO.getImageMaintains());
        maintainPO.setInfo(maintainDTO.getInfo());
        maintainPO.setWarehouse(maintainDTO.getWarehouse());
        maintainPO.setSite(maintainDTO.getSite());
        maintainPO.setPatenteName(maintainDTO.getPatenteName());

        return maintainPO;
    }


}
