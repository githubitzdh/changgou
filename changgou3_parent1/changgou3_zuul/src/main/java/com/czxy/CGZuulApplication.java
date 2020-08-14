package com.czxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/20 0020
 **/
@SpringBootApplication
@EnableEurekaClient  //@EnableDiscoveryClient注册中心客户端(通用写法),等效@EnableEurekaClient
@EnableZuulProxy   //zuul配置
public class CGZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(CGZuulApplication.class,args);

    }
}
