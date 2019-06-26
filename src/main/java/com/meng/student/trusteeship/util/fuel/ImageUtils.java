package com.meng.student.trusteeship.util.fuel;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;


/**
 * 图片工具类
 *
 * @author weiyangjun
 */
public class ImageUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageUtils.class);
    @Autowired
    RestTemplate restTemplate;

    /**
     * 调用易源计算验证码值,适合于算术验证码识别
     *
     * @param yzm 图片转成的字节数组
     * @return 验证码计算结果，若识别失败则返回空
     * @throws IOException
     */
    public static String identifyImageByYiyuan(byte[] yzm) {

        String result = null;
        String url = "https://route.showapi.com/184-6";
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        // 请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        // 也支持中文
        params.add("img_base64", new BASE64Encoder().encode(yzm));
        params.add("pointCode", "6");
        params.add("pointId", "59fc1f1a0cf29ea7bfa51e7a");
        params.add("showapi_appid", "59169");
        params.add("showapi_sign", "01b8b9243c604d008b38c426a29d9e53");
        params.add("showapi_test_draft", "false");
        params.add("showapi_test_draft", new Timestamp(System.currentTimeMillis()).toString());
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params,
                headers);
        // 执行HTTP请求
        ResponseEntity<String> response = client.exchange(url, HttpMethod.POST, requestEntity, String.class);
        result = response.getBody();
        return JSONObject.fromObject(JSONObject.fromObject(result).getString("showapi_res_body")).getString("Result");

    }

    /**
     * 调用易源计算验证码值,适合于英文数字验证码识别
     *
     * @param yzm 图片转成的字节数组
     * @return 验证码计算结果，若识别失败则返回空
     * @throws IOException
     */
    public static String identifyImageByYiyuan2(byte[] yzm) {

        String result = null;
        String url = "https://route.showapi.com/184-5";
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        // 请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        //LOGGER.info("获得的验证码字符串是:" + new BASE64Encoder().encode(yzm));
        // 也支持中文
        params.add("convert_to_jpg", "0");
        params.add("img_base64", new BASE64Encoder().encode(yzm));
        params.add("pointCode", "5");
        params.add("pointId", "5879c9966e36a015a03b8c8b");
        params.add("showapi_appid", "59169");
        params.add("showapi_sign", "01b8b9243c604d008b38c426a29d9e53");
        params.add("showapi_test_draft", "false");
        params.add("showapi_test_draft", new Timestamp(System.currentTimeMillis()).toString());
        params.add("typeId", "34");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params,
                headers);
        // 执行HTTP请求
        ResponseEntity<String> response = client.exchange(url, HttpMethod.POST, requestEntity, String.class);
        result = response.getBody();
        return JSONObject.fromObject(JSONObject.fromObject(result).getString("showapi_res_body")).getString("Result");

    }

    /**
     * 对字符串进行sha1加密运算
     *
     * @param pwd 字符串
     * @return 加密结果字符串
     * @throws NoSuchAlgorithmException
     */
    public static String sha1(String pwd) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("密码加密出现错误，" + e);
        }
        md.update(pwd.getBytes());
        StringBuffer stringBuffer = new StringBuffer();
        byte[] bits = md.digest();
        for (int i = 0; i < bits.length; i++) {
            int a = bits[i];
            if (a < 0) {
                a += 256;
            }
            if (a < 16) {
                stringBuffer.append("0");
            }
            stringBuffer.append(Integer.toHexString(a));
        }
        return stringBuffer.toString();
    }
}
