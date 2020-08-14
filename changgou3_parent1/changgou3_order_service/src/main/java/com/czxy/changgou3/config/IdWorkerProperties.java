package com.czxy.changgou3.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhandehuang@itcast.cn
application.ym文件中的配置
 **/
@Data
@ConfigurationProperties(prefix = "sc.worker")
public class IdWorkerProperties {
    private long workerId;// 当前机器id

    private long datacenterId;// 序列号
}
