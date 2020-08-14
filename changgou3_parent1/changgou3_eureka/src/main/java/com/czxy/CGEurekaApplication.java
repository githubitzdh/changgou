package com.czxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/20 0020
 **/
@SpringBootApplication   //spring boot
@EnableEurekaServer   //注册中心服务
public class CGEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(CGEurekaApplication.class, args);
    }
}
