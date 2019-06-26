package com.meng.student.trusteeship.config;

import com.meng.student.trusteeship.dao.patente.PatenteMapper;
import com.meng.student.trusteeship.entity.patente.PatentePO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengqigui
 * @description 定时检查时间驾照的是否过期
 * @date 2018/03/28 16:31
 */
@Configuration
public class PatenteQuartz {

    @Autowired
    private PatenteMapper patenteMapper;

    public static final Logger LOGGER = LoggerFactory.getLogger(PatenteQuartz.class);

    @Scheduled(cron = "${patente.pattern}")
    public void updatePatente() {
        LOGGER.info("驾照更新定时查询开始");
        List<PatentePO> patentePOS = patenteMapper.listPatentes();
        List<PatentePO> patentePOS1 = volidatePatentePast(patentePOS);
        patenteMapper.updateBatchPatenteById(patentePOS1);
        LOGGER.info("驾照更新定时查询结束");
    }

    /**
     * 和当前时间加一天的时间对比，判断是否过期
     *
     * @param patentePOS
     * @return
     */
    private List<PatentePO> volidatePatentePast(List<PatentePO> patentePOS) {

        List<PatentePO> patentes = new ArrayList<>();
        Long tempDate = System.currentTimeMillis() + 1 * 24 * 60 * 60 * 1000L;
        for (PatentePO patentePO : patentePOS) {
            if (patentePO.getState() != null && patentePO.getState() == 0) {
                continue;
            }
            if (patentePO.getEnddate().getTime() > tempDate) {
                // 驾驶证还未过期，设置为2 未过期
                patentePO.setState(2);
            } else {
                // 驾驶证今天过期，设置为过期
                patentePO.setState(1);
            }
            patentes.add(patentePO);

        }
        return patentes;

    }


}
