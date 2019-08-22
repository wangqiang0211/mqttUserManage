package com.cdzic.mqttUserManage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAsync
@EnableScheduling
@SpringBootApplication
@EnableSwagger2
@MapperScan("com.cdzic.mqttUserManage.dao.mapper")
//public class MqttUserManageApplication extends SpringBootServletInitializer {
public class MqttUserManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqttUserManageApplication.class, args);
    }


//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(MqttUserManageApplication.class);
//    }


}
