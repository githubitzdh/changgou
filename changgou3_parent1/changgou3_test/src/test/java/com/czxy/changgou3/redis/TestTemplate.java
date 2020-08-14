package com.czxy.changgou3.redis;

import com.czxy.changgou3.TestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/24 0024
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class TestTemplate {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Bean
    public  StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory){
        return new StringRedisTemplate(connectionFactory);
    }

    @Test
    public void test2(){
        System.out.println(stringRedisTemplate);
    }
}
