package com.czxy.changgou3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/29 0029
 **/
@SpringBootApplication
@EnableDiscoveryClient   //注册中心
@EnableFeignClients    //远程调用
public class CGAuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CGAuthServiceApplication.class, args);
    }
}
