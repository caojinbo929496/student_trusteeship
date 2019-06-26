package com.meng.student.trusteeship;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author admin
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.meng.student.trusteeship.dao.**"})
@EnableScheduling
@EnableCaching
public class TrusteeshipApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TrusteeshipApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(this.getClass());
    }
}


