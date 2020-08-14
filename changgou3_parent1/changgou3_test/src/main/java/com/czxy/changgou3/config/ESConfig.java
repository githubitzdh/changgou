package com.czxy.changgou3.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/13 0013
 **/
@Configuration
public class ESConfig {
    /**
     * 项目启动时,设置一个参数1
     */
    @PostConstruct
    void init(){
    System.setProperty("es.set.netty.runtime.available.processors","false");

}
}
