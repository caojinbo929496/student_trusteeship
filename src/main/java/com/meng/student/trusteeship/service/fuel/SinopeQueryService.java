package com.meng.student.trusteeship.service.fuel;

import com.meng.student.trusteeship.entity.fuel.FuelCard;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * 中石化查询服务接口
 *
 * @author weiyangjun
 */
public interface SinopeQueryService {

    /**
     * 登陆中石化的方法,登陆只会按主卡登陆，不会按副卡登陆
     *
     * @param card 加油卡实体类，登陆需要用到卡号、密码
     * @return 长度为5的String数组，0号位置是JSESSIONID、1号位置是yunsuo_session_verify、2号位置是ticket、3号位置是主卡号、4号位置是副卡号
     * @throws NoSuchAlgorithmException 登陆时对密码进行加密时可能产生异常
     */
    String[] sinopeLogin(FuelCard card);

    /**
     * 中石化按日查询的方法.
     *
     * @param card      加油卡实体类.
     * @param startTime 按日查询开始日期
     * @param endTime   按日查询结束日期
     * @return 该卡交易记录json字符串，包括加油记录和圈存记录.
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    String sinopeQueryRecordByDay(FuelCard card, String startTime, String endTime);
}
