package com.czxy.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/5/11 0011
 **/
@SpringBootApplication
@EnableEurekaClient
public class CGPayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CGPayServiceApplication.class,args);
    }
}
