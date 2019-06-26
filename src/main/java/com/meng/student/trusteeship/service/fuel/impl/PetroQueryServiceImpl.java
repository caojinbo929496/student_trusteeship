package com.meng.student.trusteeship.service.fuel.impl;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.meng.student.trusteeship.entity.fuel.FuelCard;
import com.meng.student.trusteeship.service.fuel.PetroQueryService;
import com.meng.student.trusteeship.util.fuel.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @author weiyangjun
 * @date 2018/03/21 11:13
 */
@Service
public class PetroQueryServiceImpl implements PetroQueryService {

    Logger LOGGER = LoggerFactory.getLogger(PetroQueryServiceImpl.class);

    @Autowired
    RestTemplate restTemplate;

    /**
     * 获取中石油首页
     *
     * @return 0号位置是交易记录，1号位置是圈存记录
     */
    @Override
    public String[] petroQueryWithDay(FuelCard fuelCard, String startTime, String endTime) {
        String[] petroQueryResult = new String[2];

        WebClient webClient = new WebClient();
        //启用ajax支持
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.setJavaScriptTimeout(5000);
        webClient.getOptions().setCssEnabled(false);
        // 设置连接超时时间 ，这里是10S。如果为0，则无限期等待
        webClient.getOptions().setTimeout(15000);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        // js运行错误时，是否抛出异常
        webClient.getOptions().setThrowExceptionOnScriptError(false);

        //打开首页
        HtmlPage indexPage = null;
        try {
            indexPage = webClient.getPage("http://www.card.petrochina.com.cn/");
        } catch (Exception e) {
            LOGGER.info("中石油查询获取首页时出现了错误");
            petroQueryResult[0] = "-1";
            return petroQueryResult;
        }


        //获取用户名、密码、验证码框
        HtmlTextInput username = (HtmlTextInput) indexPage.getElementById("UserLogin1_txtuserid");
        HtmlPasswordInput password = (HtmlPasswordInput) indexPage.getElementById("UserLogin1_txtpwd");
        HtmlTextInput valiCode = (HtmlTextInput) indexPage.getElementById("UserLogin1_txtcode");

        //获取验证码图片并识别
        HtmlImage valiCodeImg = (HtmlImage) indexPage.getElementById("img_checkcode");
        byte[] validateImageBytes = null;
        //登录
        String validateCodeResult = null;
        try {
            ImageReader imageReader = valiCodeImg.getImageReader();
            BufferedImage validateImage = imageReader.read(0);
            ByteArrayOutputStream validateImageOutpuStream = new ByteArrayOutputStream();
            ImageIO.write(validateImage, "png", validateImageOutpuStream);
            validateImageBytes = validateImageOutpuStream.toByteArray();
            validateCodeResult = ImageUtils.identifyImageByYiyuan2(validateImageBytes);
        } catch (Exception e) {
            LOGGER.info("中时油获取验证码抛出了异常" + e);
            petroQueryResult[0] = "-1";
            return petroQueryResult;
        }

        //设值
        username.setValueAttribute(fuelCard.getMasterCardNumber());
        password.setValueAttribute(fuelCard.getPassword());
        valiCode.setValueAttribute(validateCodeResult);

        HtmlSubmitInput loginSubmit = (HtmlSubmitInput) indexPage.getElementById("UserLogin1_btnLogin");
        HtmlPage afterLoginPage = null;
        try {
            afterLoginPage = loginSubmit.click();
        } catch (Exception e) {
            LOGGER.info("登陆抛出了异常");
            petroQueryResult[0] = "-1";
            return petroQueryResult;
        }


        //查询加油信息
        TextPage refuelingQueryResult = null;
        String refuelingQueryResultJson = null;
        try {
            refuelingQueryResult = webClient.getPage("http://www.card.petrochina.com.cn/NewKljyk/Kljyk_Ykcx.ashx?Data_GeRen=" + startTime + "*" + endTime + "&Card_No=" + fuelCard.getMasterCardNumber() + "&currPage=1&date=0.20117920146590107&date1=0.8185542071766829");
            refuelingQueryResultJson = refuelingQueryResult.getContent();
            petroQueryResult[0] = refuelingQueryResultJson;
        } catch (Exception e) {
            LOGGER.info("中石油查询加油记录时出现了异常" + e);
            petroQueryResult[0] = "-1";
            return petroQueryResult;
        }

        //查询圈存信息
        TextPage rechargeQueryResult = null;
        String rechargeQueryResultJson = null;

        try {
            rechargeQueryResult = webClient.getPage("http://www.card.petrochina.com.cn/NewKljyk/NewAccountDealQuery.ashx?timeDanwei=null&timeGeren=" + startTime + "*" + endTime + "&cardAsn=&ownId=&Card_No=" + fuelCard.getMasterCardNumber() + "&typeDanwei=1&typeGeren=1&currPage=1&date=0.6758334401626309&date1=0.9452508508687438");
            rechargeQueryResultJson = rechargeQueryResult.getContent();
            petroQueryResult[1] = rechargeQueryResultJson;
        } catch (Exception e) {
            LOGGER.info("获取中石油圈存信息出现了异常" + e);
            petroQueryResult[0] = "-1";
            return petroQueryResult;
        }
        //关闭浏览器
        webClient.close();
        return petroQueryResult;
    }
}
