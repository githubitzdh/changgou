package com.czxy.changgou3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/27 0027
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class CGCartServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CGCartServiceApplication.class,args);
    }
}
