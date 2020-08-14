package com.czxy.changgou3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/22 0022
 **/
@SpringBootApplication
//@EnableEurekaClient  //二选一
@EnableDiscoveryClient      //eureka客户端
public class CGWebServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CGWebServiceApplication.class,args);
    }
}
