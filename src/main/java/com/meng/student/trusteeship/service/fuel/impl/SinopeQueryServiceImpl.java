package com.meng.student.trusteeship.service.fuel.impl;

import com.meng.student.trusteeship.entity.fuel.FuelCard;
import com.meng.student.trusteeship.service.fuel.SinopeQueryService;
import com.meng.student.trusteeship.util.fuel.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 中石化查询服务实现类
 *
 * @author weiYangJun
 */
@Service
public class SinopeQueryServiceImpl implements SinopeQueryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SinopeQueryServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 在正式按日查询之前，需要执行beforeQuery1、2、3三个方法改变中石化服务器内部状态以获得正确查询结果，这三步的执行结果存放在该数组中.
     */
    String[] beforeQueryResult = null;

    /**
     * 登录
     *
     * @param card 加油卡实体类，登陆需要用到卡号、密码
     * @return
     * @throws NoSuchAlgorithmException
     */
    @Override
    public String[] sinopeLogin(FuelCard card) {
        // 获得验证码
        String[] yzmResult = sinopeYZM();

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        String toCookie = "HttpOnly=true; memberAccount=" + card.getMasterCardNumber() + ";" + yzmResult[2]
                + "; yunsuo_session_verify=" + yzmResult[1]
                + "; Hm_lvt_3a7bd54a4d8be76079e496de5147b070=1521079643,1521079657,1521087907,1521088173; HttpOnly=true; province=42; Hm_lpvt_3a7bd54a4d8be76079e496de5147b070=1521088173";
        headers.add("Cookie", toCookie);
        headers.add("Connection", "keep-alive");
        headers.add("Accept", "application/json, text/javascript, */*; q=0.01");
        headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.add("Host", "www.sinopecsales.com");
        headers.add("Referer", "http://www.sinopecsales.com/websso/loginAction_form.action");
        headers.add("X-Requested-With", "XMLHttpRequest");

        // 封装参数
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("check", yzmResult[0]);
        params.add("memberAccount", card.getMasterCardNumber());
        params.add("memberUmm", ImageUtils.sha1(card.getPassword()));

        params.add("rememberMe", "on");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params,
                headers);
        // 执行HTTP请求
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://www.sinopecsales.com/websso/loginAction_login.json", HttpMethod.POST, requestEntity,
                String.class);
        HttpHeaders responseHeaders = responseEntity.getHeaders();
        String loginResultBody = responseEntity.getBody();
        List<String> responseCookies = responseHeaders.get("Set-Cookie");
        String cookie = responseCookies.toString();
        String[] loginResult = new String[3];
        String loginResult_0 = loginResult[0];
        try {
            loginResult[0] = cookie.substring(cookie.indexOf("JSESSIONID"), cookie.indexOf("; Path=/websso; HttpOnly"));
        } catch (Exception e) {
        }
        // yunsuo_session_verify

        Integer verifyStartIndex = cookie.indexOf("[");
        Integer verifyEndIndex = cookie.indexOf("; Expires=");
        if (verifyStartIndex == -1 || verifyEndIndex == -1) {
            verifyEndIndex = cookie.indexOf("; expires=");
            if (verifyEndIndex == -1 || verifyStartIndex == -1) {
                return null;
            }
        }
        loginResult[1] = cookie.substring(verifyStartIndex, verifyEndIndex);
        String loginResult_2 = loginResult[2];
        try {
            loginResult[2] = cookie.substring(cookie.indexOf("ticket"), cookie.indexOf("; Version=1; Domain"));
        } catch (Exception e) {
            loginResult[2] = loginResult_2;
        }
        return loginResult;

    }

    /**
     * 按日查询,
     *
     * @param card
     * @param startTime 按日查询开始日期  格式参照2018-04-18
     * @param endTime   按日查询结束日期  格式参照2018-04-23
     * @return 返回值为-1表示查询失败，需重新查询
     */
    @Override
    public String sinopeQueryRecordByDay(FuelCard card, String startTime, String endTime) {
        try {
            beforeQueryResult = sinopeLogin(card);
        } catch (Exception e) {
            LOGGER.info("验证码识别错误、或登陆过程中的其他错误,错误{}", e);
            return "-1";
        }
        if (beforeQueryResult == null) {
            return "-1";
        }
        try {
            beforeQuery(beforeQueryResult);
        } catch (Exception e) {
            return "-1";
        }
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        String toCookie = "HttpOnly=true; HttpOnly=true; HttpOnly=true; " + beforeQueryResult[0] + "; "
                + beforeQueryResult[1]
                + "; Hm_lvt_3a7bd54a4d8be76079e496de5147b070=1521088173,1521097804,1521107061,1521162663; province=42; Hm_lpvt_3a7bd54a4d8be76079e496de5147b070=1521163373; HttpOnly=true; "
                + beforeQueryResult[2];
        headers.add("Accept", "application/json, text/javascript, */*; q=0.01");
        headers.add("Accept-Encoding", "gzip, deflate");
        headers.add("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        headers.add("Cache-Control", "no-cache");
        headers.add("Connection", "keep-alive");
        headers.add("Content-Length ", "105");
        headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.add("Cookie", toCookie);
        headers.add("Host", "www.sinopecsales.com");
        headers.add("Pragma", "no-cache");
        headers.add("Referer", "http://www.sinopecsales.com/gas/webjsp/query/billDetail.jsp");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; …) Gecko/20100101 Firefox/59.0");
        headers.add("X-Requested-With", "XMLHttpRequest");

        // 封装参数
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        // 添加参数
        params.add("cardMember.cardNo", card.getMasterCardNumber());
        params.add("dateFlag", "true");
        headers.add("Connection", "keep-alive");
        params.add("endTime", endTime);
        params.add("startTime", startTime);
        params.add("traType", "false");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params,
                headers);
        Long timeParam = System.currentTimeMillis();
        // 执行HTTP请求
        ResponseEntity<String> responseEntity = restTemplate
                .exchange("http://www.sinopecsales.com/gas/webjsp/billQueryAction_transactionLog.json?sjs="
                        + timeParam, HttpMethod.POST, requestEntity, String.class);
        return responseEntity.getBody();
    }

    /**
     * 这是按日查询第一步，
     * 往"http://www.sinopecsales.com/gas/webjsp/billQueryAction_getCardInfoObject.json"发请求
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public void beforeQuery(String[] beforeQueryResult) {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        String toCookie = "HttpOnly=true; HttpOnly=true; " + beforeQueryResult[0] + ";" + beforeQueryResult[1]
                + "; Hm_lvt_3a7bd54a4d8be76079e496de5147b070=1521088173,1521097804,1521107061,1521162663; province=42; Hm_lpvt_3a7bd54a4d8be76079e496de5147b070=1521163373; HttpOnly=true;"
                + beforeQueryResult[2];
        headers.add("Accept", "application/json, text/javascript, */*; q=0.01");
        headers.add("Accept-Encoding", "gzip, deflate");
        headers.add("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        headers.add("Cache-Control", "no-cache");
        headers.add("Connection", "keep-alive");
        headers.add("Content-Length ", "37");
        headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.add("Cookie", toCookie);
        headers.add("Host", "www.sinopecsales.com");
        headers.add("Pragma", "no-cache");
        headers.add("Referer", "http://www.sinopecsales.com/gas/webjsp/query/billDetail.jsp");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; …) Gecko/20100101 Firefox/59.0");
        headers.add("X-Requested-With", "XMLHttpRequest");
        // 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        // 添加参数
        params.add("cardMember.cardNo", "1000111100017801234");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params,
                headers);
        // 执行HTTP请求
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://www.sinopecsales.com/gas/webjsp/billQueryAction_getCardInfoObject.json", HttpMethod.POST,
                requestEntity, String.class);

        List<String> responseCookies = responseEntity.getHeaders().get("Set-Cookie");

        String cookie = responseCookies.toString();
        try {
            // jsessionid
            beforeQueryResult[0] = cookie.substring(cookie.indexOf("JSESSIONID"), cookie.indexOf("; Path=/gas"));
        } catch (Exception e) {

        }
        beforeQuery2();
    }

    /**
     * 按日查询第二步，
     * 往"http://www.sinopecsales.com/gas/webjsp/billQueryAction_queryViceCardList.json"发请求
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public void beforeQuery2() {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        String toCookie = "HttpOnly=true; HttpOnly=true; " + beforeQueryResult[0] + ";" + beforeQueryResult[1]
                + "; Hm_lvt_3a7bd54a4d8be76079e496de5147b070=1521088173,1521097804,1521107061,1521162663; province=42; Hm_lpvt_3a7bd54a4d8be76079e496de5147b070=1521163373; HttpOnly=true;"
                + beforeQueryResult[2];
        headers.add("Accept", "application/json, text/javascript, */*; q=0.01");
        headers.add("Accept-Encoding", "gzip, deflate");
        headers.add("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        headers.add("Cache-Control", "no-cache");
        headers.add("Connection", "keep-alive");
        headers.add("Content-Length ", "62");
        headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.add("Cookie", toCookie);
        headers.add("Host", "www.sinopecsales.com");
        headers.add("Pragma", "no-cache");
        headers.add("Referer", "http://www.sinopecsales.com/gas/webjsp/query/billDetail.jsp");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; …) Gecko/20100101 Firefox/59.0");
        headers.add("X-Requested-With", "XMLHttpRequest");
        // 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        // 添加参数
        params.add("cardMember.cardNo", "1000111100017801234");
        params.add("cardsType", "-1");
        params.add("lastCardNo", "");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params,
                headers);
        // 执行HTTP请求
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://www.sinopecsales.com/gas/webjsp/billQueryAction_queryViceCardList.json", HttpMethod.POST,
                requestEntity, String.class);

        List<String> responseCookies = responseEntity.getHeaders().get("Set-Cookie");

        String cookie = responseCookies.toString();

        try {
            // 云锁session
            beforeQueryResult[1] = cookie.substring(cookie.indexOf("yunsuo_session_verify"),
                    cookie.indexOf("; expires="));
        } catch (Exception e) {

        }
        beforeQuery3();

    }

    /**
     * 按日查询第三步，
     * 往"http://www.sinopecsales.com/gas/webjsp/billQueryAction_queryViceCardList.json"发请求.
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public void beforeQuery3() {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        String toCookie = "HttpOnly=true; HttpOnly=true; " + beforeQueryResult[0] + ";" + beforeQueryResult[1]
                + "; Hm_lvt_3a7bd54a4d8be76079e496de5147b070=1521088173,1521097804,1521107061,1521162663; province=42; Hm_lpvt_3a7bd54a4d8be76079e496de5147b070=1521163373; HttpOnly=true;"
                + beforeQueryResult[2];
        headers.add("Accept", "application/json, text/javascript, */*; q=0.01");
        headers.add("Accept-Encoding", "gzip, deflate");
        headers.add("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        headers.add("Cache-Control", "no-cache");
        headers.add("Connection", "keep-alive");
        headers.add("Content-Length ", "81");
        headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.add("Cookie", toCookie);
        headers.add("Host", "www.sinopecsales.com");
        headers.add("Pragma", "no-cache");
        headers.add("Referer", "http://www.sinopecsales.com/gas/webjsp/query/billDetail.jsp");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; …) Gecko/20100101 Firefox/59.0");
        headers.add("X-Requested-With", "XMLHttpRequest");
        // 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        // 添加参数
        params.add("cardMember.cardNo", "1000111100017801234");
        params.add("cardsType", "-1");
        params.add("lastCardNo", "1000111100017801234");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params,
                headers);
        // 执行HTTP请求
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://www.sinopecsales.com/gas/webjsp/billQueryAction_queryViceCardList.json", HttpMethod.POST,
                requestEntity, String.class);

        List<String> responseCookies = responseEntity.getHeaders().get("Set-Cookie");

        String cookie = responseCookies.toString();
    }

    /**
     * 获取验证码的方法
     *
     * @return 字符串数组，包含验证码计算结果、验证码请求获得的yunsuo_session_verify、JSESSIONID
     * @throws IOException
     */
    public String[] sinopeYZM() {
        ResponseEntity<byte[]> responseEntity = restTemplate
                .getForEntity("http://www.sinopecsales.com/websso/YanZhengMaServlet?" + Math.random(), byte[].class);
        byte[] yzm = responseEntity.getBody();
        // 获得验证码之后，调用易源接口进行识别
        String yzmResult = ImageUtils.identifyImageByYiyuan(yzm);
        // 将获取验证码获得的jsession等多个验证身份的信息作为返回值方法字符串数组中
        HttpHeaders headers = responseEntity.getHeaders();
        String responseHeaders = headers.toString();
        List<String> cookies = headers.get("Set-Cookie");
        String jSessionIdCookie = cookies.toString();
        String cookie = cookies.get(0);
        String yunsuoSessionVerify = cookie.substring(cookie.indexOf("=") + 1, cookie.indexOf(";"));
        String jSessionId = jSessionIdCookie.substring(jSessionIdCookie.indexOf("JSESSIONID"),
                jSessionIdCookie.indexOf("; Path=/websso"));
        return new String[]{yzmResult, yunsuoSessionVerify, jSessionId};
    }
}
