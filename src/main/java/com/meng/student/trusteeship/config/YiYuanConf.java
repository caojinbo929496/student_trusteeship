package com.meng.student.trusteeship.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author fengqigui
 * @description 易源的数据查询
 * @date 2018/04/18 11:01
 */
@Configuration
public class YiYuanConf {


    @Value("${showapi.yiyuan.host}")
    private String host;

    @Value("${showapi.yiyuan.path}")
    private String path;

    @Value("${showapi.yiyuan.method}")
    private String method;

    @Value("${showapi.yiyuan.appcode}")
    private String appcode;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAppcode() {
        return appcode;
    }

    public void setAppcode(String appcode) {
        this.appcode = appcode;
    }
}
