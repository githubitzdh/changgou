package com.czxy.changgou3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/16 0016
 **/
@SpringBootApplication
@EnableEurekaClient  //注册中心
@EnableFeignClients //远程调用
public class SearchServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchServiceApplication.class,args);
    }
}
