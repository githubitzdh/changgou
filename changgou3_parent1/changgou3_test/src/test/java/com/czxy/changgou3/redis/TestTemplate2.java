package com.czxy.changgou3.redis;

import com.czxy.changgou3.TestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/25 0025
 **/
//springRunner就是之前的springJunit4ClassRunner类
@RunWith(SpringRunner.class)   //spring整合junit
@SpringBootTest(classes = TestApplication.class) //springboot 整合Junit
public class TestTemplate2 {
    @Resource   //注意:命名必须是stringRedisTemplate
   //@Resource先按照名称注入,如果没有匹配再按照类型(有多个实例则无法选择)
    private StringRedisTemplate stringRedisTemplate;
@Test
    public void run1(){
//添加字符串数据
 stringRedisTemplate.opsForValue().set("testd001","我是test");
}

    /**
     * TimeUnit时间单位
     * TimeUnit.SECONDS 秒
     * TimeUnit.MINUTES 分
     * TimeUnit.HOURS 小时
     * TimeUnit.DAYS 天
     * TimeUnit.MICROSECONDS 毫秒
     * TimeUnit.MICROSECONDS 微秒
     * TimeUnit.NANOSECONDS 纳秒
     */
    @Test
    public void run2(){
//添加字符串数据,有效时间5分钟
        stringRedisTemplate.opsForValue().set("test002","tewoshi2",5, TimeUnit.MINUTES);

    }

    @Test
    public void run3(){
//获得字符串数据
        String s = stringRedisTemplate.opsForValue().get("testd001");
        System.out.println(s);

    }

    @Test
    public void run4(){
//删除数据
       stringRedisTemplate.delete("testd001");


    }
}
