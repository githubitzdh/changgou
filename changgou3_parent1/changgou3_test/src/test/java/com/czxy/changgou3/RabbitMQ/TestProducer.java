package com.czxy.changgou3.RabbitMQ;

import com.czxy.changgou3.TestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/5/13 0013
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class TestProducer {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    public void run1(){
        //rabbitTemplate.convertAndSend("","changgou_test","666");
     //同时开启两个浏览器接收数据
     //   rabbitTemplate.convertAndSend("order_ex","changgou_test","9966");
        //更新状态
        rabbitTemplate.convertAndSend("","order_pay","1261954869242236928");
        //推送服务测试↓
    rabbitTemplate.convertAndSend("","order_pay_auto","1261954869242236928");
    }

}
