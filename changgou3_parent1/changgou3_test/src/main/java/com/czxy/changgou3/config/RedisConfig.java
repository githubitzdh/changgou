package com.czxy.changgou3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/24 0024
 **/
//@Configuration
public class RedisConfig {

    @Bean
    public  StringRedisTemplate redisTemplate(RedisConnectionFactory connectionFactory){
        return new StringRedisTemplate(connectionFactory);
    }
}
