package com.meng.student.trusteeship.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: 吴宸煊
 * @Description: MD5 工具类
 * @Date: Created in 16:38 2018/3/13
 */
public class MD5Utils {
    private static final Logger LOGGER = LoggerFactory.getLogger(MD5Utils.class);

    /**
     * 对传入的字符串加密
     *
     * @param str 传入需要加密的参数
     * @return 返回加密完的参数
     * @throws Exception
     */
    public static String getMD5(String str) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] byteDigest = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            // 32位加密
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.info("context", e);
            return null;
        }

    }
}
